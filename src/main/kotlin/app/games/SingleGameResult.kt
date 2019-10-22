package app.games

data class SingleGameResult(val name: String, val matches: List<String>) {
    fun getMatchesString(): String = matches.joinToString(", ")

    fun isMatching(): Boolean = matches.count() == 1

    fun isMultpleMatching(): Boolean = matches.count() > 1

    fun isNotMatching(): Boolean = matches.count() == 0
}

data class SetGameResult(val gameResults: List<SingleGameResult>) {
    fun getNumberOfMatch(): Int = gameResults.filter { it.isMatching() }.count()

    fun getNumberOfMultipleMatch(): Int = gameResults.filter { it.isMultpleMatching() }.count()

    fun getNumberOfNotMatch(): Int = gameResults.filter { it.isNotMatching() }.count()
}