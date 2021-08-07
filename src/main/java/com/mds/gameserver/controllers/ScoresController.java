package com.mds.gameserver.controllers;
import com.mds.gameserver.service.UserService;
import com.mds.gameserver.views.PlayerInfo;
import com.mds.gameserver.views.Scores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/")
public class ScoresController {
    @Autowired
    private UserService userService;
    @PostMapping("newscore")
    public ResponseEntity registrationUser(@RequestBody PlayerInfo playerInfo) {
        userService.newScore(playerInfo);
        return ResponseEntity.ok("GoodCreate");
    }
    @GetMapping("scores/top10")
    public ResponseEntity<Scores> getTop10(){
        return ResponseEntity.ok(userService.getTop10());
    }
    @GetMapping("scores/del")
    public ResponseEntity del(){
        userService.deleteScore();
        return ResponseEntity.ok("scores");
    }
}
