import app.games.GamesController
import app.index.IndexController
import app.util.Paths
import spark.Spark.*

fun getHerokuAssignedPort() : Int {
    val processBuilder = ProcessBuilder()
    if (processBuilder.environment()["PORT"] != null) {
        return Integer.parseInt(processBuilder.environment()["PORT"])
    }
    return 4567
}

fun main(args: Array<String>) {
    port(getHerokuAssignedPort())

    staticFiles.location("/public")

    val indexController = IndexController()
    val gamesController = GamesController()

    get(Paths.Web.PING) { _, res ->
        res.status(200)
        "OK"
    }

    get(
            Paths.Web.Index.HOME,
            indexController::homepage
    )

    post(
            Paths.Web.Games.POST_GAME,
            gamesController::digestGameNameToDetermineExistence
    )
    post(
            Paths.Web.Games.POST_GAMES,
            gamesController::digestGameNamesToDetermineExistence
    )
}