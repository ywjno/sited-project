$(function(){app.user().then(function(e){if(e.authenticated){var a=$($("#login-menu").html());a.find(".page-header__operation-avatar img").attr("src",e.imageURL),a.find(".page-header__operation-username").text(e.nickname),$(".header-account").html(a)}});var e=$("body"),a=$(".page-header"),n=a.find(".page-header__search"),i=a.find(".page-header__search-input"),s=a.find(".page-header__search-mask");n.click(function(){a.addClass("searching"),n.css("width",a.width()+"px")}),s.click(function(){i.val(""),a.removeClass("searching"),n.css("width","")}),$(".page-header__operation-toggle").click(function(){a.toggleClass("shown-links"),e.toggleClass("fixed")}),$(".page-header__nav-list-mask").click(function(){a.removeClass("shown-links"),e.removeClass("fixed")}),$(".page-nav__link").click(function(e){$(e.currentTarget).parent().toggleClass("shown")})});