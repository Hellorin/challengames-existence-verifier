package app.games

object IGDBApiKeyProvider {
    private var cachedKey : String?= null

    fun getApiKey(): String {
        if (cachedKey == null) {
            cachedKey = this::class.java.getResource("/igdb-api.properties").readText(Charsets.UTF_8)
        }
        return cachedKey!!
    }
}