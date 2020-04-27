function validateForm(){
    const username = $("input[name=username]").val()
    const password = $("input[name=password]").val()
    const passwordConfirm = $("input[name=passwordConfirm]").val()

    if(username.length == 0){
        $("#errors").html("Username must not be empty!");
        $("#errors").css("display", "block");
        return false;
    }

    if(password.length == 0){
        $("#errors").html("Password must not be empty!");
        $("#errors").css("display", "block");
        return false;
    }

    if(password != passwordConfirm){
        $("#errors").html("Passwords do not match!");
        $("#errors").css("display", "block");
        return false;
    }

    $("form[name=registerForm]").submit();
}

function validateUsername(){
    const username = $("input[name=username]").val()

}