<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<body>

<c:if test="${account.role == 0 }">
<div class="container">
    <h1>Sửa sản phẩm</h1>
    <form action="${pageContext.request.contextPath}/admin/update" method="post" enctype="multipart/form-data">
 
                <div class="form-group">
                  <label for="exampleFormControlFile1"> Ảnh Sản Phẩm <span style="color: red">*</span>:</label>
                  <input type="file" class="form-control-file" id="fileUpload" name="anh" value="c:/${product.anh }" >
                  
                </div>
       <!--           <label for="exampleInputEmail1">Id Sản Phẩm <span style="color: red">*</span>:	</label>  -->
    <input type="hidden" class="form-control" name="id" id="exampleInputEmail1" readonly="readonly" value="${product.id }" required>
                <label for="exampleInputEmail1">Tên Sản Phẩm <span style="color: red">*</span>:</label>
    <input type="text" class="form-control" name="tieude" id="exampleInputEmail1" placeholder="Nhập tên sản phẩm" value="${product.tieuDe }" required>
    
    <label for="exampleInputEmail1" required>Số Lượng Sản Phẩm <span style="color: red">*</span>:</label>
    <input type="number" class="form-control" id="exampleInputEmail1" name="soluong" placeholder="Nhập số lượng sản phẩm" value="${product.soLuong }" required>
    <label for="exampleInputEmail1" required>Giá Sản Phẩm <span style="color: red">*</span>:</label>
    <input type="number" class="form-control" id="exampleInputEmail1" placeholder="Nhập giá bán sản phẩm" name="gia" value="${product.gia }" required>
    <label for="exampleInputEmail1">Giảm Giá (%)</label>
    <input type="number" class="form-control" id="exampleInputEmail1" placeholder="Nhập % giảm giá cho sản phẩm" value="${product.giamGia }" name="giamgia" required>
    <label for="exampleInputEmail1">Phân Loại  <span style="color: red">*</span>:</label>
    <select class="form-control" id="exampleFormControlSelect1" name="phanloai" value="${product.phanLoai }" required>
        <option>Áo Polo</option>
        <option>Áo Khoác</option>
        <option>Áo Sơ mi</option>
      </select>
      <label for="exampleInputEmail1">Kiểu Size  <span style="color: red">*</span>:</label>
      <br>
            
                  <input type="checkbox" name="kichthuoc" id="inlineCheckbox1" value="S" ${S }>S
                  <input type="checkbox" name="kichthuoc" id="inlineCheckbox1" value="M" ${M }>M
                  <input type="checkbox" name="kichthuoc" id="inlineCheckbox1" value="L" ${L }>L
                  <input type="checkbox" name="kichthuoc" id="inlineCheckbox1" value="XL" ${XL }>XL
                  <input type="checkbox" name="kichthuoc" id="inlineCheckbox1" value="XXL" ${XXL }>XXL
    
         

      <br>
      <label for="exampleInputEmail1">Mô Tả Sản Phẩm <span style="color: red">*</span>:</label>
      <input type="text" class="form-control" id="exampleInputEmail1" placeholder="Nhập mô tả sản phẩm" value="${product.moTa }" name="mota" required>
      <label for="exampleInputEmail1">Chất Liệu Sản Phẩm <span style="color: red">*</span>:</label>
      <input type="text" class="form-control" id="exampleInputEmail1" placeholder="Nhập chất liệu sản phẩm" value="${product.chatLieu }" name="chatlieu" required>
      <label for="exampleInputEmail1">Thiết kế Sản Phẩm <span style="color: red">*</span>:</label>
      <input type="text" class="form-control" id="exampleInputEmail1" placeholder="Nhập thiết kế sản phẩm" name="thietke" value="${product.thietKe }" required>
      <br>
      <button type="submit" class="btn btn-warning"><i class="fa-solid fa-plus"></i> Sửa Sản Phẩm</button>
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
