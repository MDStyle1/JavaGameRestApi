let container =document.getElementById("container");
let butSend = document.getElementById("butSend");
let textMessage = document.getElementById("textMessage");
let id =0;
waitMessage();
function goToMenu(){
    document.location.href = "http://188.243.224.61:8080/menu";
}
function sendMessage() {
    butSend.disabled=true;
    let xhr = new XMLHttpRequest();
    let json = JSON.stringify({
        message: textMessage.value,
    });
    xhr.open('POST', 'http://188.243.224.61:8080/chat/send',true);
    xhr.setRequestHeader('Accept', 'application/json');
    xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    xhr.send(json);
    xhr.onload = function() {
        if (xhr.status != 200) {
            alert(`Ошибка ${xhr.status}: ${xhr.statusText}`);
            butSend.disabled=false;
        } else {
            butSend.disabled=false;
            textMessage.value="";
        }
    };
}
function waitMessage() {
    let xhr = new XMLHttpRequest();
    let json = JSON.stringify({
        id: id
    });
    xhr.open('POST', 'http://188.243.224.61:8080/chat/listen',true);
    xhr.setRequestHeader('Accept', 'application/json');
    xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    xhr.responseType = 'json';
    xhr.send(json);
    xhr.onload = function() {
        if (xhr.status != 200) {
            alert(`Ошибка ${xhr.status}: ${xhr.statusText}`);
            setTimeout(() => { waitMessage() }, 2000);
        } else {
            let response = xhr.response;
            if(response!=null){
                response = response.chatMessageList;
                response.forEach(function(mes) {
                    id=mes.id+1;
                    addMessage(mes.message);
                });
            }
            waitMessage();
        }
    };
}
function addMessage(message) {
    let startId = message.indexOf(":");
    let mes = message.slice(0, startId);
    let div = document.createElement("div");
    div.id ="mes"+container.childElementCount;
    let cName="otherMesInChat";
    if(mes==name){
        cName="myMesInChat";
    }
    div.className=cName;
    div.innerHTML ="<p>"+message+" </p>";
    container.appendChild(div);
}