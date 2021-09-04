package com.mds.gameserver.chat;

import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class ChatListenUser{
    private String name;
    private ChatListenService chatListenService;
    private long numChat;
    private boolean stop=false;
    private long timeLife = 15000;
    private long time1,time2;
    private ResponseEntity responseEntity = null;
    public ChatListenUser(String name,ChatListenService chatListenService,long numChat){
        this.numChat = numChat;
        this.name = name;
        this.chatListenService = chatListenService;
        System.out.println(name+" add");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public ResponseEntity waitMes(){
        time2=System.currentTimeMillis();
        while (!stop){
            updateLife();
            long size =chatListenService.getListMessage().size()-1;
            if(numChat<=size){
                return getResponseEntity();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(name+" endLife");
        chatListenService.deleteListenUser(name);
        return null;
    }
    private void updateLife(){
        time1=System.currentTimeMillis();
        timeLife = timeLife - (time1-time2);
        time2=System.currentTimeMillis();
        if(timeLife<=0){
            stop = true;
        }
    }
    ResponseEntity getResponseEntity(){
        List<String> list = chatListenService.getListMessage();
        List<ChatMessage> chatMessageList = new ArrayList<ChatMessage>();
        long size =list.size()-1;
        long i;
        if(numChat==0&&size>9) {
            System.out.println("FirstRequest");
            i=size-9;
        } else i=numChat;
        for(;i<=size;i++){
            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setMessage(list.get((int)i));
            chatMessage.setId(i);
            chatMessageList.add(chatMessage);
        }
        RequestChatMessage requestChatMessage = new RequestChatMessage();
        requestChatMessage.setChatMessageList(chatMessageList);
        responseEntity = ResponseEntity.ok(requestChatMessage);
        return responseEntity;
    }
}
