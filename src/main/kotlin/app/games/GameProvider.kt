package app.games

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlin.random.Random

class GameProvider {
    private fun getGame(gameName: String) : SingleGameResult {
        val foundGames = khttp.post("https://api-v3.igdb.com/games/",
                headers = mapOf(Pair("user-key", IGDBApiKeyProvider.getApiKey())),
                data = "search \"$gameName\"; fields name;").jsonArray.map { Gson().fromJson(it.toString(), HashMap::class.java) }.map { it["name"] as String }
        return SingleGameResult(gameName, foundGames)
    }

    fun getGameResult(gameName: String): SetGameResult = SetGameResult(listOf(getGame(gameName)));

    fun getGamesResult(gameNames: List<String>): SetGameResult = SetGameResult(gameNames.map { getGame(it) })
}