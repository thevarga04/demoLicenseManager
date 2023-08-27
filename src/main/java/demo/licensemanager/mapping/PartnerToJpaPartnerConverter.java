package demo.licensemanager.mapping;

import demo.licensemanager.client.Client;
import demo.licensemanager.client.JpaClient;
import demo.licensemanager.partner.model.JpaPartner;
import demo.licensemanager.partner.model.Partner;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

import static demo.licensemanager.mapping.Collections.ofNullable;

@Component
public class PartnerToJpaPartnerConverter implements Converter<Partner, JpaPartner> {

  @Override
  public JpaPartner convert(Partner partner) {
    return JpaPartner.builder()
      .id(partner.getId())
      .username(partner.getUsername())
      .password(partner.getPassword())
      .clients(toJpaClientList(partner.getClients()))
      .build();
  }

  public List<JpaClient> toJpaClientList(List<Client> clientList) {
    return ofNullable(clientList)
      .map(this::toJpaClient)
      .toList();
  }

  public JpaClient toJpaClient(Client client) {
    return JpaClient.builder()
      .id(client.getId())
      .email(client.getEmail())
      .phoneNumber(client.getPhoneNumber())
      .build();
  }


}
