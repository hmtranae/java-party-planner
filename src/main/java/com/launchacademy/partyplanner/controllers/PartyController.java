package com.launchacademy.partyplanner.controllers;

import com.launchacademy.partyplanner.models.Friend;
import com.launchacademy.partyplanner.models.Party;
import com.launchacademy.partyplanner.repositories.FriendRepository;
import com.launchacademy.partyplanner.repositories.PartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PartyController {
  private PartyRepository partyRepository;
  private FriendRepository friendRepository;

  @Autowired
  public PartyController(PartyRepository partyRepository, FriendRepository friendRepository) {
    this.partyRepository = partyRepository;
    this.friendRepository = friendRepository;
  }

  @GetMapping("/parties")
  public String getIndex(Model model) {
    model.addAttribute("parties", partyRepository.findAll());
    return "parties/index";
  }

  @GetMapping
  public String redirectFromRoot(Model model) {
    return "redirect:/parties";
  }

  @GetMapping("/parties/{partyName}")
  public String getShow(@PathVariable String partyName, Model model) {
    partyName = partyRepository.formatUrlString(partyName);
    Integer partyId = partyRepository.findByName(partyName).getId();

    Friend friend = new Friend();

    model.addAttribute("party", partyRepository.findByName(partyName));
    model.addAttribute("friendsInvited", friendRepository.findAllByPartyId(partyId));
    model.addAttribute("allFriends", friendRepository.findAll());
    model.addAttribute("friend", friend);
    return "parties/show";
  }

  @GetMapping("/parties/new")
  public String getNewParty(Model model) {
    Party party = new Party();
    model.addAttribute("party", party);
    return "parties/new";
  }

  @PostMapping("/parties")
  public String createParty(@ModelAttribute Party party, Model model) {
    String partyName = party.getName();
    partyRepository.save(party);
    return "redirect:/parties/" + partyName;
  }

  @PostMapping("/party/friends")
  public String inviteFriendToParty(@ModelAttribute Friend friend, Model model) {
    System.out.println(friend);
    return null;
  }
}
