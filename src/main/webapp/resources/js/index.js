jQuery(document).ready(function($) {
	$(".userImgCenter").click(function(){
		$(".centerList").toggleClass("hidden");
	});
	$("button").click(function(){
		$("button").toggleClass("orange");
	});
});