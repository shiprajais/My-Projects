package com.spark

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import net.liftweb.json._


object  DataAnalysis  {
  
   case class Page(pv: String, e:String){                    //To parse the json
     def this(pv:String) = this(pv, "")
    def this() = this("","")
  }
  
  case class TableAdEvent(pv: String, e:String)              // Table ad-event having page view id and event for view and click
  
  case class TableAssest(pv: String)                         // Table assets having page view id
  
  implicit val formats = net.liftweb.json.DefaultFormats
  
  
  def main(args: Array[String]) {
 
    val conf = new SparkConf()
                 .setAppName("Data Analysis")
                 .setMaster("local")
    val context = new SparkContext(conf)
  
    val sqlContext = new org.apache.spark.sql.SQLContext(context)
    import sqlContext._
  
    val adEvents = context.textFile(args(0)).map(f => f.split(", \\{").lastOption)                  // extracting json
    val assets = context.textFile(args(1)).map(f => f.split(", \\{").lastOption) 
    
    val adEventsTable = adEvents.map(t => TableAdEvent( parse(("{"+t.getOrElse(""))).extract[Page].pv , parse(("{"+t.getOrElse(""))).extract[Page].e ) )    // mapping json to sql table
    val assetsTable = assets.map(t => TableAssest( parse("{"+t.getOrElse("")).extract[Page].pv  ) )
    
    
    adEventsTable.registerAsTable("adEvents")
    assetsTable.registerAsTable("assets")
   
    val query =sql("select at.pv , at.pvCoun , sum(case when ae.e = 'view' then 1 else 0 end) view, sum(case when ae.e = 'click' then 1 else 0 end) click"+
                    "from (SELECT pv , count(*) as pvCoun FROM assets group by pv) at LEFT OUTER JOIN adEvents ae ON at.pv = ae.pv group by at.pv , at.pvCoun")
    val results = query.map(t => t).collect()
    
    context.parallelize(results.toSeq).saveAsTextFile(args(2))
   
  }

}