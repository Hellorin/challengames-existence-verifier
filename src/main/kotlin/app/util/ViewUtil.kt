package app.util

import org.apache.velocity.app.VelocityEngine
import spark.ModelAndView
import spark.Request
import spark.template.velocity.VelocityTemplateEngine

object ViewUtil {

    fun render(request: Request, model: Map<String, Any>, templatePath: String): String {
        return strictVelocityEngine().render(ModelAndView(model, templatePath))
    }

    private fun strictVelocityEngine(): VelocityTemplateEngine {
        val velocityEngine = VelocityEngine()

        velocityEngine.setProperty("runtime.references.strict", true)
        velocityEngine.setProperty("resource.loader", "class")
        velocityEngine.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader")

        return VelocityTemplateEngine(velocityEngine)
    }
}