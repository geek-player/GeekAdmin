$('#geek-navigation-fold').click(function () {
    if ( $('#geek-navigation').is('.open') ) {
        $('#geek-navigation').removeClass("open")
        $('#geek-navigation').addClass("close")
    } else {
        $('#geek-navigation').removeClass("close")
        $('#geek-navigation').addClass("open")
    }
})

let menus = $('.geek-item');
for (let i = 0 ; i < menus.length ; i ++ ){
    menus[i].addEventListener("click",function (){
        for (let j = 0 ; j < menus.length ; j ++ ) {
            menus.eq(j).removeClass("current")
            menus.eq(j).find('img').attr('src',menus.eq(j).find('img').attr('src').replace('_current.','.'))
        }
        menus.eq(i).addClass("current")
        menus.eq(i).find('img').attr('src',menus.eq(i).find('img').attr('src').replace('.','_current.'))
        $('#geek-page').attr('src', 'page/'+menus.eq(i).find('img').attr('alt')+'.jsp');
        $('title').html(menus.eq(i).find('span').html()+' - Geek Admin')
    })
}
