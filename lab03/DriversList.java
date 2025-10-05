package lab.web;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DriversLists")
public class DriversList extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");

        out.println("<!doctype html>");
        out.println("<html lang='ru'><head><meta charset='UTF-8'>");
        out.println("<title>Список водителей</title>");
        out.println("<style>body{font-family:Arial, sans-serif;margin:24px} table{border-collapse:collapse} td,th{border:1px solid #333;padding:6px 10px}</style>");
        out.println("</head><body>");

        out.println("<h1>Список водителей автопарка " + (name != null ? escape(name) : "") + "</h1>");
        out.println("<table>");
        out.println("<tr><th>ФИО</th><th>Категории</th><th>Стаж (лет)</th><th>Активен</th></tr>");
        out.println("<tr><td>Иванов И.И.</td><td>B, C</td><td>7</td><td>Да</td></tr>");
        out.println("<tr><td>Петров П.П.</td><td>B</td><td>2</td><td>Да</td></tr>");
        out.println("<tr><td>Сидоров С.С.</td><td>B, C, D</td><td>12</td><td>Нет</td></tr>");
        out.println("<tr><td>Кузнецов К.К.</td><td>B</td><td>4</td><td>Да</td></tr>");
        out.println("</table>");

        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }

    private String escape(String s) {
        return s.replace("&","&amp;").replace("<","&lt;").replace(">","&gt;");
    }
}
