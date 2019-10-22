package app.index

import app.util.ViewUtil
import spark.Request
import spark.Response

class IndexController {
    private val templatePath: String = "/velocity/INDEX/index.vtl"

    fun homepage(req: Request, resp: Response): String = ViewUtil.render(req, mapOf(), templatePath)
}