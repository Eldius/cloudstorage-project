package net.eldiosantos.cloudstorage.config

import com.google.gson.Gson
import com.typesafe.config.Config

/**
  * Created by Eldius on 30/01/2017.
  */
trait ParserService {
  def parse[T](json: String, classOf: Class[T]): T
  //def parserClass(clazzName: String): Boolean
}

class GsonParserService extends ParserService {
  val gson = new Gson()

  override def parse[T](json: String, classOf: Class[T]): T = gson.fromJson(json, classOf)
}

object ParserService{
  val parserService: ParserService = null;
  def apply(): ParserService = new GsonParserService
}
