package demo.licensemanager.partner.model;

import demo.licensemanager.client.JpaClient;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "partners")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JpaPartner {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column(unique = true)
  private String username;
  private String password;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "partner_id")
  private List<JpaClient> clients;


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    JpaPartner that = (JpaPartner) o;
    return id == that.id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "JpaPartner{" +
      "id=" + id +
      ", username='" + username + '\'' +
      '}';
  }
}
