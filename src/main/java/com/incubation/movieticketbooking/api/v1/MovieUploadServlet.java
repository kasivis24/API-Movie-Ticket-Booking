package com.incubation.movieticketbooking.api.v1;

import com.incubation.movieticketbooking.data.db.AppDb;
import com.incubation.movieticketbooking.data.dto.Movie;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.sql.SQLException;

@WebServlet("/api/v1/upload-movie")
@MultipartConfig
public class MovieUploadServlet extends HttpServlet {

    private static final String UPLOAD_DIR = "uploads";

    private final AppDb appDb = AppDb.getInstance();

    public MovieUploadServlet() throws SQLException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        request.setCharacterEncoding("UTF-8");

        try {
            String appPath = request.getServletContext().getRealPath("");
            String uploadPath = appPath + File.separator + UPLOAD_DIR;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdir();

            Part filePart = request.getPart("thumbnail");
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String savedFilePath = uploadPath + File.separator + fileName;
            filePart.write(savedFilePath);

            System.out.println(fileName);


            String name = request.getParameter("name");
            String genre = request.getParameter("genre");
            String language = request.getParameter("language");
            String desc = request.getParameter("desc");
            int duration = Integer.parseInt(request.getParameter("duration"));
            String imageUrl = "/uploads/" + fileName;

            PrintWriter out = response.getWriter();

            System.out.println("Uploaded: " + name + ", " + genre + ", " + language);
            System.out.println("Saved image path: " + savedFilePath);

            response.setContentType("application/json");

            if (appDb.uploadMovie(new Movie(0,name,genre,duration,imageUrl,language,desc))){
                out.print("{\"status\":\"success\",\"message\":\"Movie uploaded\"}");
            }else{
                out.print("{\"status\":\"error\", \"message\":\"Database error occurred.\"}");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Upload failed");
        }
    }
}

