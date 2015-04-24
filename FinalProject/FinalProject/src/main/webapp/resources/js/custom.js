/*=============================================================
    Authour URL: www.designbootstrap.com

    http://www.designbootstrap.com/

    License: MIT

    http://opensource.org/licenses/MIT

    100% Free To use For Personal And Commercial Use.

    IN EXCHANGE JUST TELL PEOPLE ABOUT THIS WEBSITE

========================================================  */           

$(document).ready(function () {

	/*====================================
           METIS MENU 
     ======================================*/

	//$('#main-menu').metisMenu();

	/*======================================
    LOAD APPROPRIATE MENU BAR ON SIZE SCREEN
    ========================================*/

	$(window).bind("load resize", function () {
		if ($(this).width() < 768) {
			$('div.sidebar-collapse').addClass('collapse')
		} else {
			$('div.sidebar-collapse').removeClass('collapse')
		}
	});
	/*======================================
   WRITE YOUR SCRIPTS BELOW
   ========================================*/

	$("#edit-profile-info").hide();




	$('#edit-btn').click(function(){
		var coupleName = $(".couple-name").html();
		var p1Age = $(".p1-age").html();
		var p1Hometown = $(".p1-hometown").html();
		var p1Occupation = $(".p1-occupation").html();

		var p2Age = $(".p2-age").html();
		var p2Hometown = $(".p2-hometown").html();
		var p2Occupation = $(".p2-occupation").html();

		var currLoc = $(".curr-location").html();
		var story = $(".story").html();
		var activites =$(".activities").html();

		var restaurant= $(".restaurant").html();
		var artist =$(".artist").html();

		var look = $(".looking-for").html();


		$('#profile-info').hide();
		$("#edit-profile-info").show();
		$("#couple-name").attr("placeholder", coupleName);
		$("#p1-age").attr("placeholder", p1Age);
		$("#p1-loc").attr("placeholder", p1Hometown);
		$("#p1-occ").attr("placeholder", p1Occupation);

		$("#p2-age").attr("placeholder", p2Age);
		$("#p2-loc").attr("placeholder", p2Hometown);
		$("#p2-occ").attr("placeholder", p2Occupation);

		$("#curr-loc").attr("placeholder", currLoc);
	});

	$('#save-btn').click(function(){

		$('#edit-profile-info').hide();
		$("#profile-info").show();

	});
});
