package net.eldiosantos.cloudstorage.config

import com.typesafe.config.{Config, ConfigFactory}
import org.slf4j.LoggerFactory

class StorageConfiguration(_config: Config) {
  val _dropbox = new DropboxConfiguration(_config.getConfig("dropbox"))

  def dropbox(): DropboxConfiguration = _dropbox
}

class DropboxConfiguration(val _config: Config) {
  val appKey = _config.getString("app-key")
  val appSecret = _config.getString("app-secret")
  val accessToken = _config.getString("app-token")
  val url = _config.getString("url")
}

object StorageConfiguration {
  val logger = LoggerFactory.getLogger(getClass)
  val conf = ConfigFactory.load()
  val _conf = new StorageConfiguration(conf.getConfig("storage"))

  logger.info(_conf.toString)

  println(conf.entrySet().toString)
  logger.info(conf.entrySet().toString)

  def apply(): StorageConfiguration = _conf
}
