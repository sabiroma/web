package lab.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet({"/DriversLists"})
public class DriversList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String lang = request.getParameter("lang");
        Locale locale = "en".equalsIgnoreCase(lang) ? Locale.ENGLISH : new Locale("ru", "RU");

        ResourceBundle res = ResourceBundle.getBundle("Drivers", locale, new UTF8Control());

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!doctype html><html lang='" + locale.getLanguage() + "'>");
        out.println("<head><meta charset='UTF-8'><title>" + res.getString("title") + "</title>");
        out.println("<style>body{font-family:Arial, sans-serif;margin:24px} table{border-collapse:collapse} "
                + "td,th{border:1px solid #333;padding:6px 10px}</style></head><body>");

        out.println("<h1>" + res.getString("title") + "</h1>");
        out.println("<table>");
        out.println("<tr><th>" + res.getString("fio") + "</th>"
                + "<th>" + res.getString("cats") + "</th>"
                + "<th>" + res.getString("exp") + "</th>"
                + "<th>" + res.getString("active") + "</th></tr>");

        out.println("<tr><td>Иванов И.И.</td><td>B, C</td><td>7</td><td>Да</td></tr>");
        out.println("<tr><td>Петров П.П.</td><td>B</td><td>2</td><td>Да</td></tr>");
        out.println("<tr><td>Сидоров С.С.</td><td>B, C, D</td><td>12</td><td>Нет</td></tr>");
        out.println("<tr><td>Кузнецов К.К.</td><td>B</td><td>4</td><td>Да</td></tr>");

        out.println("</table></body></html>");
    }

    public static class UTF8Control extends ResourceBundle.Control {
        @Override
        public ResourceBundle newBundle(String baseName, Locale locale, String format,
                                        ClassLoader loader, boolean reload)
                throws IllegalAccessException, InstantiationException, IOException {
            String bundleName = toBundleName(baseName, locale);
            String resourceName = toResourceName(bundleName, "properties");

            try (InputStream is = loader.getResourceAsStream(resourceName)) {
                if (is == null) return null;
                try (InputStreamReader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
                    return new PropertyResourceBundle(reader);
                }
            }
        }
    }
}
