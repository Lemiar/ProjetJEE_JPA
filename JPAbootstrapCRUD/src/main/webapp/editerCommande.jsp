<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title> Modification des commandes</title>
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
</head>
<body>
<%@include file="header.jsp" %>
<p></p>
<div class="container">
<div class="card">
  <div class="card-header">
    Modification des commandes
  </div>
  <div class="card-body">
      <form action="updateCommande" method="post" >
      <div hidden class="form-group"  >
       <label class="control-label">ID :</label>
       <input type="text" name="id" class="form-control" value="${commande.id}"/>
      </div>   
      <div class="form-group">
       <label class="control-label">Date Commande :  </label>   
          <fmt:formatDate pattern="yyyy-MM-dd" value="${commande.dateDebut}" var="formatDate" />
           <input  type="date" name="dateDebut" class="form-control"  value="${formatDate}"></input> 
      </div>
      <div class="form-group">   
       <select name="client" class="form-control">
       <option value="${commande.client.id }" selected>${commande.client.id }</option>
          <c:forEach items="${cltModel.clients}" var="clt">   
          <c:if test="${clt.id != commande.client.id }">     
            <option value="${clt.id}">${clt.id}</option>
          </c:if>
         </c:forEach>
       </select>
     </div>
     <div class="form-group">   
       <select name="produit" class="form-control">
       <option value="${commande.produit.id }" selected>${commande.produit.nom }</option>
          <c:forEach items="${prodModel.produits}" var="prod">   
          <c:if test="${prod.id != commande.produit.id }">     
            <option value="${prod.id}">${prod.nom}</option>
          </c:if>
         </c:forEach>
       </select>
     </div>
      <div>     
       <button type="submit" class="btn btn-primary">Modifier</button>
      </div>
      </form>     
  </div>
</div>
</div>
</body>
</html>