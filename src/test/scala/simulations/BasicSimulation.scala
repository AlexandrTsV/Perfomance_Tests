package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class BasicSimulation extends Simulation {

  val httpProtocol = http
    .baseUrl("https://rpg.fandom.com/wiki/")
    .acceptHeader(
      "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"
    )
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .userAgentHeader(
      "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0"
    )

  val scn =
    scenario("Send requests")
      .exec(
        http("main page")
          .get("RPG")
          .check(status is 200)
      )
      .exec(
        http("random page")
          .get("Myra")
          .check(status is 200)
      )
      .exec(
        http("another random page")
          .get("Ars_Magica")
          .check(status is 200)
      )

  setUp(scn.inject(atOnceUsers(100)).protocols(httpProtocol))
}