<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>webscoket</title>
    <script type="text/javascript" src="/js/jquery-1.8.2.min.js"></script>
</head>
<body>
    <input type="text" id="user" />
    <button id="connect" onclick="connect();">连接</button><br/>
    联系人：<select id="users">
            <option value="all">全部</option>
    </select>
    <input type="text" id="txt" />
    <button id="send" onclick="send();">发送</button><br/>
    <p id="msg"></p>
<script>
    var userInfo;
    var websocket = null;

    function send(){
        var user = new Object();
        user.username = $("#users option:selected").val();
        user.msg = $("#txt").val();
        var json = JSON.stringify(user);
        websocket.send(json);
        showMsg(userInfo+'：'+user.msg);
    }

    function connect(){
        var user = $("#user").val();
        newSockt(user);
        $("#user").attr("readonly","readonly");
    }

    function newSockt(user){
        userInfo = user;
        if('WebSocket' in window){
        websocket = new WebSocket("ws://localhost:9085/webSocket/"+user)
        }else{
            alert("该浏览器不支持websocket")
        }
        websocket.onopen = function(event){
            console.log('建立连接')
        }

        websocket.onclose = function(event){
            console.log('连接关闭')
        }
        websocket.onmessage = function(event){
            console.log('收到消息:'+event.data);
            var data = JSON.parse(event.data);
            if(data){
                if(data.type == 2 ){
                    showMsg(data.user.username+"："+data.user.msg)
                }else if(data.type == 1){
                    showMsg(data.user.username+data.user.msg);
                    addOnlineUser();
                }
            }
        }

        websocket.onerror = function(){
            alert("websocket发生通信错误")
        }

        websocket.onbeforeunload = function(event){
            websocket.close()
        }
    }

    function showMsg(msg){
        var text = document.getElementById("msg");
        text.innerText = text.innerText + "\n"+ msg;
    }

    function addOnlineUser(){
        var users = document.getElementById("users");
        var index = 1;
        $.get("/getOnLineUser", function(data){
            for(var i = 0; i < data.length; i++){
                if(!(userInfo == data[i])){
                    var no = new Option();
                    no.value = data[i];
                    no.text = data[i];
                    users.options[index] = no;
                    index++;
                }
            }
        }, "json");
    }
</script>
</body>
</html>