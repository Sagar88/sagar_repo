import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession
// $example on$
import org.apache.spark.mllib.feature.{Word2Vec, Word2VecModel}
// $example off$

object Word2Vec {

  def main(args: Array[String]): Unit = {

    val sc = SparkSession.builder.master("local").appName("Simple Application").getOrCreate().sparkContext

    // $example on$
    val input = sc.textFile("/Users/sagarkulkarni/tmp/note1/1.csv").map(line => line.split(" ").toSeq)

    val word2vec = new Word2Vec()

    val model = word2vec.fit(input)

    val synonyms = model.findSynonyms("disease", 15)

    for((synonym, cosineSimilarity) <- synonyms) {
      println(s"$synonym $cosineSimilarity")
    }

    // Save and load model
    model.save(sc, "myModelPath")
    val sameModel = Word2VecModel.load(sc, "myModelPath")
    // $example off$

    sc.stop()
  }
}