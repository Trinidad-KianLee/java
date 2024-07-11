<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Notes</title>
</head>
<body>
    <h1>Notes</h1>
    <form action="notes" method="post">
        <input type="text" name="note" required>
        <button type="submit">Add Note</button>
    </form>
    <h2>All Notes</h2>
    <ul>
        <% List<String> notes = (List<String>) request.getAttribute("notes"); 
           if (notes == null) {
               notes = new ArrayList<>();
           }
           for (String note : notes) { %>
            <li><%= note %></li>
        <% } %>
    </ul>
</body>
</html>
