package demo.licensemanager.client;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "devices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JpaDevice {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @NotNull
  private String name;
  @Enumerated(EnumType.STRING)
  private OSType os;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "client_id")
  private JpaClient jpaClient;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "clients_products"
    , joinColumns = @JoinColumn(name = "client_id", referencedColumnName = "id")
    , inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id")
  )
  private Set<JpaProduct> products;


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    JpaDevice jpaDevice = (JpaDevice) o;
    return id == jpaDevice.id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "JpaDevice{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", os=" + os +
      '}';
  }

}
