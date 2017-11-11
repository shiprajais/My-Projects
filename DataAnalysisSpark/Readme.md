Command to run the spark job is :


spark-submit --class com.spark.DataAnalysis --master local arg0 arg1 arg2 arg3

In the above command
arg0 is the path to the sparkJob.jar file
arg1 is the path to the file ad-events
arg2 is the path to the file assets
arg3 is path to output folder

The above command will generate output folder with the file having results.

The output file has rows which has comma seperated fields
sample: [531ec21c-b9f7-456e-abcb-c150cc0a9c2b,5,0,0]
first field is page view id
second field is asset impressions
third field is views and
fourth field is clicks



The folder has following items:
generated output file part-00000 in output folder,
generated logs in file log,
source code and
jar sparkJob.jar