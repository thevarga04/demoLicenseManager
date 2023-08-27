package demo.licensemanager.client;

import demo.licensemanager.partner.model.JpaPartner;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JpaClient {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String email;
  @Min(100)
  private long phoneNumber;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "partner_id")
  private JpaPartner jpaPartner;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "client_id")
  private List<JpaDevice> devices;


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    JpaClient jpaClient = (JpaClient) o;
    return id == jpaClient.id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "JpaClient{" +
      "id=" + id +
      ", email='" + email + '\'' +
      ", phoneNumber=" + phoneNumber +
      '}';
  }

}
