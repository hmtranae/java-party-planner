package com.launchacademy.partyplanner.controllers;

import com.launchacademy.partyplanner.models.Friend;
import com.launchacademy.partyplanner.repositories.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class FriendController {
  private FriendRepository friendRepository;

  @Autowired
  public FriendController(FriendRepository friendRepository) {
    this.friendRepository = friendRepository;
  }

  @GetMapping("/friends")
  public String getIndex(Model model) {
    model.addAttribute("friends", friendRepository.findByOrderByFirstNameAsc());
    return "friends/index";
  }

  @GetMapping("/friends/new")
  public String getNewFriend(Model model) {
    Friend friend = new Friend();
    model.addAttribute("friend", friend);
    return "friends/new";
  }

  @PostMapping("/friends")
  public String createFriend(@ModelAttribute Friend friend, Model model) {
    friendRepository.save(friend);
    return "redirect:/friends";
  }
}
