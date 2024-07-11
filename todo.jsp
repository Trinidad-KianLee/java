<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>To-Do List</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
    <h1>To-Do List</h1>
    <form action="todo" method="post">
        <input type="text" name="task" placeholder="Task" required>
        <button type="submit">Add Task</button>
    </form>
    <h2>All Tasks</h2>
    <ul>
        <% 
            List<String> todos = (List<String>) request.getAttribute("todos");
            if (todos != null) {
                for (String todo : todos) {
        %>
            <li><%= todo %></li>
        <% 
                }
            } else {
        %>
            <li>No tasks available.</li>
        <% } %>
    </ul>
</body>
</html>
