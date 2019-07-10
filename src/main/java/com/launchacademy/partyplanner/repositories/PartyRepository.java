package com.launchacademy.partyplanner.repositories;

import com.launchacademy.partyplanner.models.Friend;
import com.launchacademy.partyplanner.models.Party;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartyRepository extends CrudRepository<Party, Integer> {
  default String formatUrlString(String url) {
    url = url.trim();
    url = url.replaceAll("%20", "\\s");

    return url;
  }

  Party findByName(String name);
}
