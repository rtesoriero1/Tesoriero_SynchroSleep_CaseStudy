/**
 * This is used to display a message that confirms your dream journal has been updated. 
 The function removes a hidden tag from the div to make the message visible as part of a onSubmit action.
 */
 console.log("Display Dream Success Function")
 
  var button = document.getElementById("submit");
 
 function displayDreamSuccess() {
	
	 var element = document.getElementById("alert-info");
	 element.classList.remove("hidden");
	 return false;	 
 }
 
 
 document.getElementById("dreamTitle").style.color = "lightblue";
 
 
  button.addEventListener("click", displayDreamSuccess);
