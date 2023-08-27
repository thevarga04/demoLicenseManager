package demo.licensemanager.partner.model;

import demo.licensemanager.client.Client;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatchPartner {

  private int id;
  @NotNull
  @Size(min = 1)
  private List<Client> clients;

}
