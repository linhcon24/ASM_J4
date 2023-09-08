<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<body>


<c:if test="${account.role == 0 }">
<div class="container">
    <h1>Thêm Category mới</h1>
    <p style="color: red">${message }</p>
    <form action="${pageContext.request.contextPath}/category/add" method="post">
 
   <label for="exampleInputEmail1">Tên Category * :</label>
    <input type="text" class="form-control"  id="exampleInputEmail1" placeholder="Nhập tên category " name="ten" required>
       
      <br>
      <button type="submit" class="btn btn-danger"><i class="fa-solid fa-plus"></i> Thêm Category</button>
  </div>
  </div>
              </form>
        </div>
<br>
</c:if>
<c:if test="${account == null ||account.role == 1 }">
<h1>Bạn không có quyền truy cập chức năng này !</h1>
</c:if>

</body>