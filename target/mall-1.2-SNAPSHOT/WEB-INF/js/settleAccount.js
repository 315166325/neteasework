(function (w, d, u) {
    var settleAccount = util.get('settleAccount');
    if (!settleAccount) {
        return;
    }
    var name = 'products';
    var products = util.getCookie(name);
    // 通过cookie中的products查数据库
    if (products == null)
        products = "";
    var datas = products.map(function (arr) {
        return {'gid': arr.id, 'number': arr.num, 'buyPrice': arr.price};
    });
    var datas = JSON.stringify(datas);
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4) {
            var status = xhr.status;
            if (status >= 200 && status < 300 || status == 304) {
                var json = JSON.parse(xhr.responseText);
                products = json.cart;
                products = products.map(function (arr) {
                    return {
                        'id': arr.gid,
                        'num': arr.number,
                        'price': arr.buyPrice,
                        'title': arr.title,
                        'image': arr.image
                    };
                });
            }
        }
    };
    xhr.open('post', '/cart2', false);
    xhr.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
    xhr.send(datas);

    var $ = function (id) {
        return document.getElementById(id);
    }

    var str = "<tr>" +
        "<th>" + '商品图片' + "</th>" +
        "<th>" + '商品名称' + "</th>" +
        "<th>" + '数量' + "</th>" +
        "<th>" + '价格' + "</th>" +
        "</tr>";

    for (var i = 0; i < products.length; i++) {
        str = str +
            "<tr>" +
            "<td><a href=\"/show?id=" + products[i].id + "\">" + "<img src=\"" + products[i].image + "\" alt=\"\">" + "</a></td>" +
            "<td><a href=\"/show?id=" + products[i].id + "\">" + products[i].title + "</a></td>" +
            "<td>" +
            "<span class=\"lessNum\" onclick=\"lessNum(" + products[i].id + ");\"><a>-</a></span>" +
            "<span class=\"totalNum\" id=\"totalNum" + products[i].id + "\">" + products[i].num + "</span>" +
            "<span  class=\"moreNum\" onclick=\"moreNum(" + products[i].id + ");\"><a>+</a></span>" +
            "</td>" +
            "<td>" + products[i].price + "</td>" +
            "</tr>";
    }
    $("newTable").innerHTML = str;

    var loading = new Loading();
    var layer = new Layer();
    $('Account').onclick = function (e) {
        var newProducts = products.map(function (arr) {
            return {'gid': arr.id, 'number': arr.num, 'buyPrice': arr.price};
        });
        console.log(newProducts);
        var ele = e.target;
        layer.reset({
            content: '确认购买吗？',
            onconfirm: function () {
                layer.hide();
                loading.show();

                var xhr = new XMLHttpRequest();
                var data = JSON.stringify(newProducts);
                xhr.onreadystatechange = function () {
                    if (xhr.readyState == 4) {
                        var status = xhr.status;
                        if (status >= 200 && status < 300 || status == 304) {
                            var json = JSON.parse(xhr.responseText);
                            if (json && json.code == 200) {
                                loading.result('购买成功', function () {
                                    location.href = '/account';
                                });
                                util.deleteCookie(name);
                            } else {
                                alert(json.message);
                            }
                        } else {
                            loading.result(message || '购买失败');
                        }
                    }
                };
                xhr.open('post', '/buy');
                xhr.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
                xhr.send(data);
            }.bind(this)
        }).show();
        return;
    }

})(window, document);

function GoBack() {
    window.history.back();
}

function lessNum(i) {
    var num = $('totalNum' + i).textContent;
    var name = 'products';
    var productList = util.getCookie(name);
    if (num <= 1) {
        var msg = "您真的确定要从购物车中删除该商品吗？";
        if (confirm(msg) == true) {
            util.deleteOne(productList, i);
            util.setCookie(name, productList);
            //刷新购物车
            location.href = '/cart';
        }
    } else {
        num--;
        $('totalNum' + i).innerHTML = num;
        //将变化同步到cookie中去
        util.modifyOne(productList, i, num);

    }
}

function moreNum(i) {
    var num = $('totalNum' + i).textContent;
    num++;
    $('totalNum' + i).innerHTML = num;
    //将变化同步到cookie中去
    var name = 'products';
    var productList = util.getCookie(name);
    util.modifyOne(productList, i, num);
    util.setCookie(name, productList);
}

var $ = function (id) {
    return document.getElementById(id);
}

