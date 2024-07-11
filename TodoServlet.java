package com.pinoybudgettracker.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.pinoybudgettracker.uti.DBConnection;

@WebServlet("/todo")
public class TodoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> todos = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT task, status FROM todo");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                todos.add(rs.getString("task") + (rs.getBoolean("status") ? " (Completed)" : " (Pending)"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("todos", todos);
        request.getRequestDispatcher("/WEB-INF/todo.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String task = request.getParameter("task");

        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO todo (task) VALUES (?)");
            ps.setString(1, task);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("todo");
    }
}
