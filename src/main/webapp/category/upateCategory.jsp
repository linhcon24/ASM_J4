<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<body>


<c:if test="${account.role == 0 }">
<div class="container">
    <h1>Sửa Category </h1>
    <form action="${pageContext.request.contextPath}/category/update" method="post">
 
            <label for="exampleInputEmail1">Id * :</label>
    		<input type="text" class="form-control"  id="exampleInputEmail1"  name="id" value="${cate.id }" readonly="readonly" required>
            <label for="exampleInputEmail1">Password * :</label>
    		<input type="text" class="form-control"  id="exampleInputEmail1" placeholder="Nhập id " name="ten" value="${cate.ten }" required>
    
      <br>
      <button type="submit" class="btn btn-danger"><i class="fa-solid fa-plus"></i> Sửa Category</button>
    </form>
      </div>

<br>
</c:if>
<c:if test="${account == null ||account.role == 1 }">
<h1>Bạn không có quyền truy cập chức năng này !</h1>
</c:if>

</body>
