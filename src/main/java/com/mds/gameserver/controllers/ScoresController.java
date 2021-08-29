package com.mds.gameserver.controllers;
import com.mds.gameserver.service.ScoresService;
import com.mds.gameserver.views.ScoresInfo;
import com.mds.gameserver.views.Scores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/scores/")
public class ScoresController {
    @Autowired
    private ScoresService scoresService;
    @PostMapping("newscore")
    @PreAuthorize("hasAuthority('creatorscores')")
    public ResponseEntity registrationUser(@RequestBody ScoresInfo scoresInfo) {
        scoresService.newScore(scoresInfo);
        return ResponseEntity.ok("GoodCreate");
    }
    @GetMapping("top10")
//    @PreAuthorize("hasAuthority('read')")
    public ResponseEntity<Scores> getTop10(){
        return ResponseEntity.ok(scoresService.getTop10());
    }
    @GetMapping("del")
    @PreAuthorize("hasAuthority('write')")
    public ResponseEntity del(){
        scoresService.deleteScore();
        return ResponseEntity.ok("scores");
    }
}
