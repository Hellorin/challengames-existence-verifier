package app.games

import kotlin.random.Random

class GameProvider {
    fun getGame(gameName: String): SetGameResult = SetGameResult(listOf(SingleGameResult(gameName, listOf(gameName))))

    fun getGames(gameNames: List<String>): SetGameResult {

        return SetGameResult(gameNames.map {
            val listOfMatches = when (Random.nextInt(3)) {
                0 -> listOf(it)
                1 -> listOf()
                2 -> listOf(it, it + "2")
                else -> listOf()
            }
            SingleGameResult(it, listOfMatches)
        })
    }
}