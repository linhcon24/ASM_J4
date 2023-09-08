<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<body>
<c:if test="${account.role == 0 }">
<div class="container">
    <h1>Sửa user </h1>
    <p style="color: red">${message }</p>
    <form action="${pageContext.request.contextPath}/users/update" method="post">
 
                <label for="exampleInputEmail1">Username * :</label>
    <input type="text" class="form-control"  id="exampleInputEmail1" placeholder="Nhập username " name="username" value="${account1.username }" readonly="readonly" required>
                  <label for="exampleInputEmail1">Password * :</label>
    <input type="text" class="form-control"  id="exampleInputEmail1" placeholder="Nhập password " name="password" value="${account1.password }" required>
       <label for="exampleInputEmail1">Email * :</label>
    <input type="text" class="form-control" id="exampleInputEmail1" placeholder="Nhập email " name="email" value="${account1.email }" required>
       <label for="exampleInputEmail1">Vai trò * :</label>
    <select class="form-control" id="exampleFormControlSelect1" name="role" required>
        <option ${account1.role == 0 ? "selected" : ""}>Admin</option>
        <option ${account1.role == 1 ? "selected" : ""}>Thành viên</option>
      </select>
      <br>
      <button type="submit" class="btn btn-success"><i class="fa-solid fa-plus"></i> Sửa User</button>
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
