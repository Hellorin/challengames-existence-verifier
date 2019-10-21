package app.games

class GameProvider {
    fun getGame(gameName: String): GameResult = GameResult(gameName, listOf(gameName))

    fun getGames(gameNames: List<String>): List<GameResult> = gameNames.map { GameResult(it, listOf(it)) }
}