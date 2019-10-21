package app.util

class Paths {
    class Web {
        companion object {
            const val PING: String = "/ping"
        }

        class Index {
            companion object {
                const val HOME: String = "/"
            }
        }

        class Games {
            companion object {
                const val POST_GAME: String = "/game"
                const val POST_GAMES: String = "/games"
            }
        }
    }
}
