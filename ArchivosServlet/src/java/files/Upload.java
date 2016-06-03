
package files;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author dordonez
 */
@WebServlet("/upload")
@MultipartConfig
public class Upload extends HttpServlet {
    public static final String FILES_PATH = "C:\\Users\\diego\\Desktop\\_tmp\\";
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String description = request.getParameter("description"); // Recupera <input type="text" name="description">
        Part filePart = request.getPart("file"); // Recupera <input type="file" name="file">
        String fileName = filePart.getSubmittedFileName();
        InputStream fileContent = filePart.getInputStream();
        Path newFile = Paths.get(FILES_PATH + fileName);
        Files.copy(fileContent, newFile, StandardCopyOption.REPLACE_EXISTING);
        
        PrintWriter out = response.getWriter();
        out.println("Grabado: " + fileName);
        out.println(description);
    }
    
}
