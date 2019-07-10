package com.launchacademy.partyplanner.models;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "friends")
public class Friend {

  @Id
  @SequenceGenerator(name = "friend_generator", sequenceName = "friends_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "friend_generator")
  @Column(name = "id", nullable = false, unique = true)
  private Integer id;

  @NotEmpty
  @Column(name = "first_name", nullable = false)
  private String firstName;

  @NotEmpty
  @Column(name = "last_name", nullable = false)
  private String lastName;

  @ManyToMany(
      fetch = FetchType.LAZY,
      cascade = {
          CascadeType.PERSIST,
          CascadeType.MERGE
      })
  @JoinTable(name = "parties_friends",
      joinColumns = {@JoinColumn(name = "party_id")},
      inverseJoinColumns = {@JoinColumn(name = "friend_id")})
  private Set<Party> parties = new HashSet<>();

  @Override
  public String toString() {
    return "Friend{" +
        "id=" + id +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", parties=" + parties +
        '}';
  }
}
