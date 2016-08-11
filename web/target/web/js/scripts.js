function checkPass()
{
    var pass1 = document.getElementById('password');
    var pass2 = document.getElementById('password-confirm');
    var message = document.getElementById('confirmMessage');

    var goodColor = "#66cc66";
    var badColor = "#ff6666";

    if(pass1.value == pass2.value){
        pass2.style.borderColor = goodColor;
        message.style.color = goodColor;
        message.innerHTML = "Passwords Match!";
        document.getElementById('signUp').disabled = false;
    }else{
        pass2.style.borderColor = badColor;
        message.style.color = badColor;
        message.innerHTML = "Passwords Do Not Match!";
        document.getElementById('signUp').disabled = true;
    }
}

function goBack(){
    window.history.back();
}


function validateForm(){
    var message = document.getElementById('confirmMessage');

    var goodColor = "#66cc66";
    var badColor = "#ff6666";

    var username = document.forms["loginForm"]["username"].value;
    var usernameField = document.getElementById('username');
    alert("username:" + username);
    if(username == null || username == ""){
        message.innerHTML = "Please, fill empty fields";
        usernameField.borderColor = badColor;
        alert("111");
        return false;
    }
}
