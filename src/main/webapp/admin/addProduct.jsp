<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<body>
<c:if test="${account.role == 0 }">
<div class="container">
    <h1>Thêm sản phẩm mới</h1>
    <form action="${pageContext.request.contextPath}/admin/add" method="post" enctype="multipart/form-data">
 
                <div class="form-group">
                  <label for="exampleFormControlFile1"> Ảnh Sản Phẩm <span style="color: red">*</span>:</label>
                  <input type="file" class="form-control-file" id="fileUpload" name="anh" required>
                </div>
                <label for="exampleInputEmail1">Tên Sản Phẩm <span style="color: red">*</span>:</label>
    <input type="text" class="form-control" name="tieude" id="exampleInputEmail1" placeholder="Nhập tên sản phẩm" required>
    
    <label for="exampleInputEmail1" required>Số Lượng Sản Phẩm <span style="color: red">*</span>:</label>
    <input type="number" class="form-control" id="exampleInputEmail1" name="soluong" placeholder="Nhập số lượng sản phẩm" required>
    <label for="exampleInputEmail1" required>Giá Sản Phẩm <span style="color: red">*</span>:</label>
    <input type="number" class="form-control" id="exampleInputEmail1" placeholder="Nhập giá bán sản phẩm" name="gia" required>
    <label for="exampleInputEmail1">Giảm Giá (%)</label>
    <input type="number" class="form-control" id="exampleInputEmail1" placeholder="Nhập % giảm giá cho sản phẩm" value="0" name="giamgia" required>
    <label for="exampleInputEmail1">Phân Loại  <span style="color: red">*</span>:</label>
    <select class="form-control" id="exampleFormControlSelect1" name="phanloai" required>
    <c:forEach items="${listCategory }" var="p">
    <option>${p.ten }</option>
    </c:forEach>
        
      </select>
      <label for="exampleInputEmail1">Kiểu Size  <span style="color: red">*</span>:</label>
      <br>
            
                  <input  type="checkbox" name="kichthuoc" id="inlineCheckbox1" value="S">S
                  <input type="checkbox" name="kichthuoc" id="inlineCheckbox1" value="M">M
                  <input type="checkbox" name="kichthuoc" id="inlineCheckbox1" value="L">L
                  <input type="checkbox" name="kichthuoc" id="inlineCheckbox1" value="XL">XL
                  <input type="checkbox" name="kichthuoc" id="inlineCheckbox1" value="XXL">XXL
    
         

      <br>
      <label for="exampleInputEmail1">Mô Tả Sản Phẩm <span style="color: red">*</span>:</label>
      <input type="text" class="form-control" id="exampleInputEmail1" placeholder="Nhập mô tả sản phẩm" name="mota" required>
      <label for="exampleInputEmail1">Chất Liệu Sản Phẩm <span style="color: red">*</span>:</label>
      <input type="text" class="form-control" id="exampleInputEmail1" placeholder="Nhập chất liệu sản phẩm" name="chatlieu" required>
      <label for="exampleInputEmail1">Thiết kế Sản Phẩm <span style="color: red">*</span>:</label>
      <input type="text" class="form-control" id="exampleInputEmail1" placeholder="Nhập thiết kế sản phẩm" name="thietke" required>
      <br>
      <button type="submit" class="btn btn-primary"><i class="fa-solid fa-plus"></i> Thêm Sản Phẩm</button>
  </div>
  </div>
              </form>
        </div>
<br>
</c:if>
<c:if test="${account == null ||account.role == 1 }">
<h1>Bạn không có quyền truy cập chức năng này !</h1>
</c:if>

<!-- <script>

    const image = document.querySelector("img"),
    input = document.querySelector("input");

    input.addEventListener("change" , () => {
        image.src = URL.createObjectURL(input.files[0]);
    });
</script> -->
</body>
</html>