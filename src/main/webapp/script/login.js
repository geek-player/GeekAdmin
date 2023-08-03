$("#login").click(function () {
    let inputs = $(".geek-input");
    for (let i = 0 ; i < inputs.length ; i ++ ){
        if ( inputs.eq(i).val() === "" ) {
            createToast("error","请输入用户名和密码！");
        }
    }
})
