package demo.licensemanager.client;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {

  private int id;
  @NotNull
  private String email;
  @Min(100)
  private long phoneNumber;


}
