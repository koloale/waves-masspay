package com.wavesplatform.http.swagger

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.github.swagger.akka.model.{Info, License}
import com.github.swagger.akka.{HasActorSystem, SwaggerHttpService}
import com.wavesplatform.settings.{Constants, RestAPISettings}
import io.swagger.models.Swagger

import scala.reflect.runtime.universe.Type

class SwaggerDocService(val actorSystem: ActorSystem, val materializer: ActorMaterializer, val apiTypes: Seq[Type], settings: RestAPISettings)
  extends SwaggerHttpService with HasActorSystem {

  override val host: String = settings.bindAddress + ":" + settings.port
  override val info: Info = Info("The Web Interface to the Waves Mass Payment Service API",
    Constants.VersionString,
    "Waves Mass Payment Service",
    "License: Apache License, Version 2.0",
    None,
    Some(License("Apache License, Version 2.0", "https://github.com/wavesplatform/Waves/blob/master/LICENSE"))
  )

  //Let swagger-ui determine the host and port
  override val swaggerConfig: Swagger = new Swagger().basePath(prependSlashIfNecessary(basePath)).info(info).scheme(scheme)
}
