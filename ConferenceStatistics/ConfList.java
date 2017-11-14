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
public class ConfList {
public static class TokenizerMapper
extends Mapper<Object, Text, Text, Text>{
private final static Text one = new Text();
private Text word = new Text();
public void map(Object key, Text value, Context context
) throws IOException, InterruptedException {
StringTokenizer itr = new StringTokenizer(value.toString(),"\t");
while (itr.hasMoreTokens()) {
	//itr.nextToken();
	//itr.nextToken();
word.set(itr.nextToken());
itr.nextToken();
Text token = new Text(itr.nextToken());
//word.set(value.toString());
context.write(token, word);
}
}
}
public static class TextReducer
extends Reducer<Text,Text,Text,Text> {
private Text  result = new Text ();
public void reduce(Text key, Iterable<Text> values, Context context
) throws IOException, InterruptedException {

String listConf="";
for (Text  val : values) {
	//String[] temp = val.toString().split("/t");
	//Text rate = new Text(temp[1]);
	
	listConf = listConf+val.toString()+",";
	//result.set(val);

}
Text abc = new Text(listConf);
result.set(abc);
context.write(key, result);
}
}
public static void main(String[] args) throws Exception {
Configuration conf = new Configuration();
Job job = Job.getInstance(conf, "word count");
job.setJarByClass(ConfList.class);
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
