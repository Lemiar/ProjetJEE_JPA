<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Saisie Commande</title>
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
</head>
<body>
<%@include file="header.jsp" %>
<p></p>
<div class="container">
<div class="card">
  <div class="card-header">
    Saisie des Caommandes
  </div>
  <div class="card-body">
      <form action="saveCommande" method="post">   
      <div class="form-group">
       <label class="control-label">Date Commande :  </label>   
           <input  type="date" name="dateDebut" class="form-control" ></input> 
      </div>
      <div class="form-group">   
       <select name="client" class="form-control">
          <c:forEach items="${cltModel.clients}" var="clt">        
            <option value="${clt.id}">${clt.id}</option>
         </c:forEach>
       </select>
     </div>
     <div class="form-group">   
       <select name="produit" class="form-control">
          <c:forEach items="${prodModel.produits}" var="prod">        
            <option value="${prod.id}">${prod.nom}</option>
         </c:forEach>
       </select>
     </div>
      <div>
        <button type="submit" class="btn btn-primary">Ajouter</button>
      </div>
      </form>     
  </div>
</div>
</div>
</body>
</html>