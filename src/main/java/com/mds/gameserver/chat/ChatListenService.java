package com.mds.gameserver.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatListenService {
    @Autowired
    private List<ChatListenUser> listenUserList;
    @Autowired
    private List<String> listMessage;
    public ChatListenService(){
    }
    public ResponseEntity addListenUser(RequestChat requestChat){
        System.out.println("in addListenUser");
        ChatListenUser chatListenUser = updateListenUser(requestChat);
        if(chatListenUser!=null){
            ResponseEntity responseEntity = chatListenUser.waitMes();
            deleteListenUser(chatListenUser.getName());
            return responseEntity;
        }
        System.out.println("out addListenUser");
        return null;
    }

    public List<String> getListMessage() {
        return listMessage;
    }

    public void deleteListenUser(String name){
        listenUserList.removeIf(item->item.getName().equals(name));
    }
    synchronized private ChatListenUser updateListenUser(RequestChat requestChat){
        System.out.println("in updateListenUser");
        ChatListenUser chatListenUser = null;
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(name);
        if(listenUserList.stream().filter(item->item.getName().equals(name)).findFirst().isEmpty()){
            chatListenUser = new ChatListenUser(name,this,requestChat.getId());
            listenUserList.add(chatListenUser);
        }
        System.out.println("out updateListenUser");
        return chatListenUser;
    }
    synchronized public void addMessage(ChatSendMessage chatMessage){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        String message =name+":"+chatMessage.getMessage();
        listMessage.add(message);
        ChatMessage chatMessage1 = new ChatMessage();
        chatMessage1.setMessage(message);
        chatMessage1.setId(listMessage.size()-1);
    }
}
