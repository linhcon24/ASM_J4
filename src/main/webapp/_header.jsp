<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<header >
    <nav class="navbar navbar-expand-lg navbar-light nav">
      <a class="navbar-brand" href="index"><img class="logo" src="${pageContext.request.contextPath}/img/5.png"></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
      
        <div class="collapse navbar-collapse" id="navbarSupportedContent" >
          <ul class="navbar-nav mr-auto">
            <li class="nav-item menu">
              <a class="chu-m" href="${pageContext.request.contextPath}/index"><i class="fa-solid fa-house"></i><fmt:message key="homepage.menu.home"></fmt:message> <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item menu">
              <a class="chu-m" href="${pageContext.request.contextPath}/product"><i class="fa-brands fa-shopify"></i> <fmt:message key="homepage.menu.product"></fmt:message> </a>
            </li>
            <li class="nav-item menu">
              <a class="chu-m" href="${pageContext.request.contextPath}/info"><i class="fa-solid fa-circle-info"></i><fmt:message key="homepage.menu.about"></fmt:message></a>
            </li> 
    		<c:if test="${account.role ==0 }"><li class="nav-item menu">
              <a class="chu-m" href="${pageContext.request.contextPath}/admin"><i class="fa-brands fa-product-hunt"></i><fmt:message key="homepage.menu.quanLyPro"></fmt:message></a>
            </li>
            <li class="nav-item menu">
              <a class="chu-m" href="${pageContext.request.contextPath}/users"><i class="fa-solid fa-users-gear"></i><fmt:message key="homepage.menu.quanLyUser"></fmt:message></a>
            </li>
             </c:if>
            
            
          </ul>
          <div class="button-cangiua">
           <!--  <button class="nut"><a class="nav-link menu" href="#"><i class="fa-solid fa-magnifying-glass"></i></a></button> -->
           <div style="margin-left: 70px"><a  style="color: white" href="?language=vi">Vi</a> | <a style="color: white" href="?language=en">En</a></div>
          <button class="nut"><a class="nav-link menu" href="cart"><i class="fa-solid fa-cart-plus"></i><p class="soluong"></p></a>
          
          </button>
          <button class="nuthan dropdown show">
            <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    <i class="fa-solid fa-user"></i> <fmt:message key="homepage.menu.hi"></fmt:message> , <c:if test="${account != null }">${account.username}</c:if>
    <c:if test="${account == null }"><fmt:message key="homepage.menu.khach"></fmt:message></c:if>
  </a>
  <c:choose>
  <c:when test="${account != null }">
  <div class="dropdown-menu drop" aria-labelledby="dropdownMenuLink">
    <div class="nutdrop">
    <a class="dropdown-item" href="${pageContext.request.contextPath}/change"><fmt:message key="homepage.menu.change"></fmt:message></a>
    <a class="dropdown-item" href="#" onclick="dangXuat()"><fmt:message key="homepage.menu.logout"></fmt:message></a>
    </div>
  </div>
  </c:when>
  <c:otherwise>
  <div class="dropdown-menu drop" aria-labelledby="dropdownMenuLink">
    <div class="nutdrop">
    <a class="dropdown-item" href="${pageContext.request.contextPath}/login"><fmt:message key="homepage.menu.login"></fmt:message></a>
    <a class="dropdown-item" href="${pageContext.request.contextPath}/register"><fmt:message key="homepage.menu.dky"></fmt:message></a>
    <a class="dropdown-item" href="${pageContext.request.contextPath}/forget"><fmt:message key="homepage.menu.forget"></fmt:message></a>
    </div>
  </div>
  </c:otherwise>
  </c:choose>
  
          </button>

          </div>
        </div>
      </nav>
  </header>
    <br>
    <script>
    	function dangXuat() {
    		Swal.fire({
    			  title: 'Bạn chắc chắn muốn đăng xuất?',
    			  showCancelButton: true,
    			  confirmButtonText: 'Đăng xuất',
    			}).then((result) => {
    			  /* Read more about isConfirmed, isDenied below */
    			  if (result.isConfirmed) {
    				  Swal.fire('Đăng xuất thành công !', '', 'success');
    				  setTimeout(() => {
    					  location.href = "./logout";
					}, 2000);
    				
    			    
    			  } else if (result.isDenied) {
    			   
    			  }
    			})
		}
    	
    </script>