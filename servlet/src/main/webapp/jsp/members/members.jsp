<%@ page import="java.util.List" %>
<%@ page import="com.example.servlet.domain.member.MemberRepository" %>
<%@ page import="com.example.servlet.domain.member.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    MemberRepository memberRepository = MemberRepository.getInstance();
    List<Member> members = memberRepository.findAll();
%>

<html>
<head>
    <meta charset="UTF-8">
    <title>회원 목록</title>
</head>
<body>
<a href="/index.html">메인</a>

<table border="1">
    <thead>
    <tr>
        <th>id</th>
        <th>username</th>
        <th>age</th>
    </tr>
    </thead>
    <tbody>
    <%
        for (Member member : members) {
    %>
    <tr>
        <td><%= member.getId() %></td>
        <td><%= member.getUsername() %></td>
        <td><%= member.getAge() %></td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
</body>
</html>