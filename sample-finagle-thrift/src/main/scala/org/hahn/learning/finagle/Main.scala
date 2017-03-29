package org.hahn.learning.finagle

import java.nio.ByteBuffer
import java.util

import com.twitter.finagle.Thrift
import com.twitter.util.Future
import com.xiaomi.cubedb.thrift._

/**
  * Created by jianghan on 2017/3/28.
  */
object Main {

  def main(args: Array[String]): Unit = {
    val server = Thrift.server.serveIface(
      "localhost:12345",
      new CubeDBService.ServiceIface[Future] {
        override def put(key: String, value: ByteBuffer): Future[Response] = null

        override def get(key: String): Future[GetResult] = null

        override def multipleGet(keys: util.List[String]): Future[MultipleGetResult] = null

        override def convertPut(key: String, value: ByteBuffer, convertPut: CubeDBFunc): Future[Response] = null

        override def convertGet(key: String, inputData: ByteBuffer, classNameOfConvert: CubeDBFunc): Future[GetResult] = null

        override def convertMultipleGet(keys: util.List[String], inputData: ByteBuffer, classNameOfConvert: CubeDBFunc, isLocal: Boolean): Future[MultipleGetResult] = null

        override def combineMultipleGet(keys: util.List[String], inputData: ByteBuffer, classNameOfConvert: CubeDBFunc, classNameOfCombine: CubeDBFunc, isLocal: Boolean): Future[CombineGetResult] = null

        override def combineMultipleFilter(includeKeys: util.List[String], excludeKeys: util.List[String], inputData: ByteBuffer, classNameOfFilter: CubeDBFunc, classNameOfConvert: CubeDBFunc, classNameOfMerge: CubeDBFunc, isLocal: Boolean): Future[CombineGetResult] = null
      }
    )
  }

}
