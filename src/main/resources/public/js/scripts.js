$(document).ready(function(){

  $(".fa-bars").click(function(){
    $(".main-menu").slideToggle();
  });

  $(".fa-chevron-down").click(function(){
    $(".main-form").slideToggle();
    $(".fa-chevron-down").hide();
    $(".fa-chevron-up").show();
  });
  $(".fa-chevron-up").click(function(){
    $(".main-form").slideToggle();
    $(".fa-chevron-down").show();
    $(".fa-chevron-up").hide();
  });
});
