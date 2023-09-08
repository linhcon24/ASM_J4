<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<body>

<c:if test="${account.role == 0 }">
<div class="container">
    <h1>Thêm user mới</h1>
    <p style="color: red">${message }</p>
    <form action="${pageContext.request.contextPath}/users/add" method="post">
 
                <label for="exampleInputEmail1">Username * :</label>
    <input type="text" class="form-control"  id="exampleInputEmail1" placeholder="Nhập username " name="username" required>
                  <label for="exampleInputEmail1">Password * :</label>
    <input type="text" class="form-control"  id="exampleInputEmail1" placeholder="Nhập password " name="password" required>
       <label for="exampleInputEmail1">Email * :</label>
    <input type="text" class="form-control" id="exampleInputEmail1" placeholder="Nhập email " name="email" required>
       <label for="exampleInputEmail1">Vai trò * :</label>
    <select class="form-control" id="exampleFormControlSelect1" name="role" required>
        <option>Admin</option>
        <option>Thành viên</option>
      </select>
      <br>
      <button type="submit" class="btn btn-danger"><i class="fa-solid fa-plus"></i> Thêm User</button>
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
