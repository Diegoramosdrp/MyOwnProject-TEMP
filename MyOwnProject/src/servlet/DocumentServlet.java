package servlet;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/file/*")
public class DocumentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private String filePath;

	public DocumentServlet() {
		super();
	}

	public void init() throws ServletException {
		this.filePath = "C:/Users/Usuario/Desktop/upload";
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestedFile = request.getPathInfo();

		if (requestedFile == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
			return;
		}

		File file = new File(filePath, URLDecoder.decode(requestedFile, "UTF-8"));

		if (!file.exists()) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
			return;
		}

		String contentType = getServletContext().getMimeType(file.getName());

		if (contentType == null || !contentType.startsWith("file")) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
			return;
		}

		response.reset();
		response.setContentType(contentType);
		response.setHeader("Content-Length", String.valueOf(file.length()));

		Files.copy(file.toPath(), response.getOutputStream());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
