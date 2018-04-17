$(document).ready(function(){
    $("#usr-btn").click(function(){
        $(".main-div").load("user_registration.html");
    });
    var loginDetails={
    		username:'Praveen Shet G',
    		userid:1,
    		groupid:1,
    		permissions:{}
    }
});
closeSidebar();
function openSidebar() {
    document.getElementById("mySidebar").style.display = "block";
}
function closeSidebar() {
    document.getElementById("mySidebar").style.display = "none";
}


var myMap = new Map();

var keyString = 'a string',
    keyObj = {},
    keyFunc = function() {};

// setting the values
myMap.set("username", "Praveen Shet G");
myMap.set("userid", 1);
myMap.set("groupid", 1);