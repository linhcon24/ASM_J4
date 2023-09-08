<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<body>


<c:if test="${account.role == 0 }">
<h1 style="text-align: center;">Quản lý Category</h1>
<br>
<div class="btnThem"><button type="button" class="btn btn-secondary"><a href="category/add"><i class="fa-solid fa-circle-plus"></i> Thêm category</a></button></div>
<br>
<table class="table">
<tr>
<td>Id</td>
<td>Tên Category</td>
<td>Chức năng</td>
</tr>
<c:forEach items="${listCategory }" var="p">
<c:if test="${p.trangThai == 0 }">
<tr>
<td>${p.id }</td>
<td>${p.ten }</td>
 <td>
            <button type="button" class="btn btn-danger btnSua"> <a href="category/update?idCategory=${p.id }"><i class="fa-solid fa-wrench"></i> Sửa</a></button>
            <button type="button" class="btn btn-warning btnSua" ><a href="category/delete?idCategory=${p.id }"><i class="fa-solid fa-trash"></i> Xóa</a></button>
 </td>
</tr>
</c:if>
</c:forEach>
</table>
    <div class="aa">
           <nav aria-label="Page navigation example">
			<ul class="pagination">
				<li class="page-item"><a class="page-link" href="category">1</a></li>
				<c:forEach begin="2" end="${count }" var="i">
					<li class="page-item"><a class="page-link" href="?page=${i}">${i}</a></li>
				</c:forEach>
    
    </div>
</c:if>
<c:if test="${account == null ||account.role == 1 }">
<h1>Bạn không có quyền truy cập chức năng này !</h1>
</c:if>

</body>
