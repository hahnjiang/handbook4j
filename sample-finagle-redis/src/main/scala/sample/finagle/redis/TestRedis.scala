package sample.finagle.redis

import com.twitter.finagle.redis.Client
import com.twitter.finagle.redis.util.RedisCluster
import com.twitter.io.Buf
import com.twitter.util.Await

/**
  * Created by jianghan on 16-6-30.
  */
object TestRedis extends App {

  println("Starting Redis instance...")
  RedisCluster.start(1)

  val client = Client(RedisCluster.hostAddresses())
  println("Setting foo -> bar...")
  client.set(Buf.Utf8("foo"), Buf.Utf8("bar"))
  println("Getting value for key 'foo'")

  val getResult = Await.result(client.get(Buf.Utf8("foo")))
  getResult match {
    case Some(Buf.Utf8(s)) => println("Got result: " + s)
    case None => println("Didn't get the value!")
  }

  println("Closing client...")
  client.close()
  println("Stopping Redis instance...")
  RedisCluster.stop()
  println("Done!")

}
