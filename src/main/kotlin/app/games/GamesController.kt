package app.games

import app.util.ViewUtil
import spark.Request
import spark.Response
import java.util.stream.Collectors
import javax.servlet.MultipartConfigElement


class GamesController {
    private val templatePath: String = "/velocity/GAMES/games.vtl"

    private val templateGameRequestData = "results"

    private val gameProvider: GameProvider = GameProvider()

    fun digestGameNamesToDetermineExistence(request: Request, response: Response): String {
        // Extract game name from request
        val gameNames = extractMultipleGameDataFromRequest(request)

        // Get information from external API
        val gameResults = gameProvider.getGamesResult(gameNames)

        // Build model with extracted data
        return buildModelAndRender(request, gameResults, response)
    }

    fun digestGameNameToDetermineExistence(request: Request, response: Response): String {
        // Extract game name from request
        val gameName = extractSingleGameDataFromRequest(request)

        // Get information from external API
        val gameResults = gameProvider.getGameResult(gameName)

        // Build model and render
        return buildModelAndRender(request, gameResults, response)
    }

    private fun buildModelAndRender(request: Request, gameResult: SetGameResult, response: Response): String {
        // Build model with extracted data
        val model = mutableMapOf<String, Any>(
                Pair(
                        templateGameRequestData,
                        gameResult
                )
        )

        // Render result
        response.type("text/html")
        return ViewUtil.render(request, model, templatePath)
    }

    private fun extractSingleGameDataFromRequest(request: Request): String {
        request.attribute("org.eclipse.jetty.multipartConfig", MultipartConfigElement("/temp"))
        return String(request.raw().getPart("game").inputStream.readBytes())
    }

    private fun extractMultipleGameDataFromRequest(request: Request): List<String> {
        request.attribute("org.eclipse.jetty.multipartConfig", MultipartConfigElement("/temp"))

        return request.raw().getPart("uploaded_file").inputStream.bufferedReader().lines()
                .skip(1)
                .map { it.split(";")[0] }
                .collect(Collectors.toList())
    }
}