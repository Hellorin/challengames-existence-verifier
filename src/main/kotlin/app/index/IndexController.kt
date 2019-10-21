package app.index

import spark.Request
import spark.Response

class IndexController {

    fun homepage(req: Request, resp: Response): String {
        return """
            <html>
                <body>
                    Upload a file in csv format with game names
                    <form action='/games' method='post' enctype='multipart/form-data'>
                        <input type='file' name='uploaded_file' accept='.csv'>
                        <button>Upload csv</button>
                    </form>
                    or determine it just for one game:
                    <form action='/game' method='post' enctype='multipart/form-data'>
                        <input type='text' name='game'>
                        <button>Exists?</button>
                    </form>                    
                </body>
            </html>
        """
    }
}