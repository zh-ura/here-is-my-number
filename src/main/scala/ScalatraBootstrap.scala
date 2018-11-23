import org.scalatra._
import javax.servlet.ServletContext
import org.slf4j.{Logger, LoggerFactory}
import com.github.zhura.mynumber.{MagicNumbers, PhonegetRoutes}
import com.github.zhura.mynumber.util.spark.WordBucketingUtils
import org.apache.spark.{SparkConf, SparkContext}

class ScalatraBootstrap extends LifeCycle {
  val logger: Logger = LoggerFactory.getLogger(getClass)

  protected implicit def executor = scala.concurrent.ExecutionContext.Implicits.global

  /**
    * @inheritdoc
    */
  override def init(context: ServletContext) {
    //TODO move spark part out; it's a pre-condition job
    val conf = new SparkConf().setAppName("words-app").setMaster("local")
    val sc = new SparkContext(conf)
    val buckets = WordBucketingUtils.bucketWords("words", sc)
    val lengthBuckets = MagicNumbers.wordLengthRange.map(length => (length, WordBucketingUtils.bucketsOfLength(length, buckets))).toMap
    sc.stop()

    context.mount(new PhonegetRoutes(lengthBuckets), "/*")
  }

  /**
    * @inheritdoc
    */
  override def destroy(context: ServletContext): Unit = {
    super.destroy(context)
  }
}
