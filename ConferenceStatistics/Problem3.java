import java.io.IOException;
import java.util.StringTokenizer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
public class Problem3 {
public static class TokenizerMapper
extends Mapper<Object, Text, Text, Text>{
private final static Text one = new Text();
private Text token = new Text();
public void map(Object key, Text value, Context context
) throws IOException, InterruptedException {
StringTokenizer itr = new StringTokenizer(value.toString(),"\t");
String year="";

while (itr.hasMoreTokens()) {
	itr.nextToken();
	String confYear = itr.nextToken();
StringTokenizer itr1 = new StringTokenizer(confYear);
 while (itr1.hasMoreTokens()) {
	year = (String)itr1.nextToken();
}
String conf = confYear.replace(year,"");
token.set(conf);

Text word = new Text(itr.nextToken());

context.write(token, word);
}
}
}
public static class TextReducer
extends Reducer<Text,Text,Text,Text> {
private Text  result = new Text ();
public void reduce(Text key, Iterable<Text> values, Context context
) throws IOException, InterruptedException {

String cities="";
for (Text  val : values) {
	
	
	cities = cities+val.toString()+",";
	

}
Text abc = new Text(cities);
result.set(abc);
context.write(key, result);
}
}
public static void main(String[] args) throws Exception {
Configuration conf = new Configuration();
Job job = Job.getInstance(conf, "conf cities");
job.setJarByClass(Problem3.class);
job.setMapperClass(TokenizerMapper.class);
job.setCombinerClass(TextReducer.class);
job.setReducerClass(TextReducer.class);
job.setOutputKeyClass(Text.class);
job.setOutputValueClass(Text.class);
FileInputFormat.addInputPath(job, new Path(args[0]));
FileOutputFormat.setOutputPath(job, new Path(args[1]));
System.exit(job.waitForCompletion(true) ? 0 : 1);
}
}

