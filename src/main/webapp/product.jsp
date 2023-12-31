<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<body>

      <h1 class="giua"><i class="fa-brands fa-shopify"></i> Sản Phẩm</h1>
      <br>
      
      
      <div class="row">
        <div class="col-md-1">
            <div class="col-md-6 category" data-mdb-filter="color">
               <h3 class="fa-lg"> Category</h3>
                <div class="form-check mt-3">
                  <input
                    class="form-check-input"
                    type="radio"
                    name="inlineRadioOptions"
                    id="inlineRadio1"
                    value="Tất cả"
                    checked
                    onchange="window.location.replace('${pageContext.request.contextPath}/product')"

                  />
                  <label class="category-text" for="inlineRadio1">Tất Cả</label>

                   <c:forEach items="${listCategory }" var="c">
                <c:if test="${c.trangThai == 0 }">
            
                  <input
                    class="form-check-input"
                    type="radio"
                    name="inlineRadioOptions"
                    id="inlineRadio1"
                    value="${c.id}"
                    ${id == c.id ? 'checked="checked"' : ''}
                    onchange="window.location.replace('${pageContext.request.contextPath}/product/${c.id}')"
                    ${id == c.cate ? 'checked="checked"' : ''}
                  />
                  <label class="category-text" for="inlineRadio1">${c.ten }</label>
                
                </c:if>
                
                </c:forEach>
                </div>
               
               </div>
              
            
          </div>
          <div class="col-md-11">
            <div class="container">
                <div class="row">
                  <c:forEach items="${listProduct }" var="p">
          <div class="col-md-3 col-6">
            <div class="thumbnail xoagach">
              <a href="detail?sanpham=${p.id }">
                <div class="anhsp"><c:if test="${p.giamGia > 0 }"><span class="giamgia1" >- ${p.giamGia}%</span></c:if><img src="${pageContext.request.contextPath}/files/${p.anh}" alt="Lights" style="width:100%"></div>
                <div class="tensp">
                  ${fn:toUpperCase(p.tieuDe)}
                </div>
              </a>
             <div class="line"><p class="money1"> <c:if test="${p.giamGia < 1 }"><fmt:formatNumber>${p.gia}</fmt:formatNumber> VNĐ</c:if></p><p class="money1"> <c:if test="${p.giamGia > 0 }"><fmt:formatNumber>${p.gia - (p.gia * (p.giamGia /100))}</fmt:formatNumber> VNĐ</c:if></p>
             <p class="gach"><c:if test="${p.giamGia >0 }"><fmt:formatNumber>${p.gia}</fmt:formatNumber> VNĐ</c:if></p>
              
            </div>
              <br>
              <div>
                <button class="nutthemgio"><a href="detail?sanpham=${p.id }"><i class="fa-solid fa-cart-shopping"></i><p class="gian">Mua Ngay</p></a></button>
              </div>
            </div>
            <div class="ribbon-wrap">
            <!--   <div class="ribbon2"><i class="fa-solid fa-fire"></i> New</div> -->
            </div>
        </div>
          </c:forEach>
                  </div>
              </div>
			<div class="aa">
			
			<nav aria-label="Page navigation example">
				<ul class="pagination">
					<li class="page-item"><a class="page-link" href="product">1</a></li>
					<c:forEach begin="2" end="${count }" var="i">
						<li class="page-item"><a class="page-link" href="?page=${i}">${i}</a></li>
					</c:forEach>
					</nav>
			</div>
		</div>
		
	</div>
   


</body>
