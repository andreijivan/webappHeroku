function validateForm(form){
    const username = $("input[name=username]").val()
    const password = $("input[name=password]").val()
    const passwordConfirm = $("input[name=passwordConfirm]").val()

    if(username.length == 0){
            $("div[id=errors]").innerHTML = "Username must not be empty!";
            $("div[id=errors]").style.display = "block";
        return false;
    }

    if(password != passwordConfirm){
         $("div[id=errors]").innerHTML = "Passwords do not match!";
         $("div[id=errors]").style.display = "block";
        return false;
    }

    $("form[name=registerForm]").submit();

}