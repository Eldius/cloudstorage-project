package net.eldiosantos.cloudstorage.config

import com.typesafe.config.{Config, ConfigFactory}

class StorageConfiguration(_config: Config) {
  val dropbox = new DropboxConfiguration(_config.getConfig("dropbox"))
}

class DropboxConfiguration(val _appKey: String, val _appSecret: String, val _accessToken: String) {
  val appKey = _appKey;
  val appSecret = _appSecret;
  val accessToken = _accessToken
}

object StorageConfiguration {
  val conf = new StorageConfiguration(ConfigFactory.load().getConfig("storage"))

  def apply(): StorageConfiguration = conf
}
