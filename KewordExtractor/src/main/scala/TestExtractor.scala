import org.apache.spark.mllib.feature.{HashingTF, IDF}
import org.apache.spark.mllib.linalg.Vector
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession


class TestExtractor {



  def extractKeyword() : Unit =
  {

    print(" **** H I***** ")

    val sc = SparkSession.builder.master("local").appName("Simple Application").getOrCreate().sparkContext

    val documents: RDD[Seq[String]] = sc.textFile("/Users/sagarkulkarni/tmp/aa.txt")
      .map(_.split(" ").toSeq)

    val hashingTF = new HashingTF()
    val tf: RDD[Vector] = hashingTF.transform(documents)

    // While applying HashingTF only needs a single pass to the data, applying IDF needs two passes:
    // First to compute the IDF vector and second to scale the term frequencies by IDF.
    tf.cache()
    val idf = new IDF().fit(tf)
    val tfidf: RDD[Vector] = idf.transform(tf)

    println("*** " +tfidf.first())
    // spark.mllib IDF implementation provides an option for ignoring terms which occur in less than
    // a minimum number of documents. In such cases, the IDF for these terms is set to 0.
    // This feature can be used by passing the minDocFreq value to the IDF constructor.
    val idfIgnore = new IDF(minDocFreq = 2).fit(tf)
    val tfidfIgnore: RDD[Vector] = idfIgnore.transform(tf)


    println("tfidf: ")
    tfidf.foreach(x => println(x))

    println("tfidfIgnore: ")
    tfidfIgnore.foreach(x => println(x))

    sc.stop()

  }
}
