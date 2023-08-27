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
public class JpaPartnerToPartnerConverter implements Converter<JpaPartner, Partner> {

  @Override
  public Partner convert(JpaPartner jpa) {
    return Partner.builder()
      .id(jpa.getId())
      .username(jpa.getUsername())
      .clients(toClientList(jpa.getClients()))
      .build();
  }


  public List<Client> toClientList(List<JpaClient> jpaClientList) {
    return ofNullable(jpaClientList)
      .map(this::toClient)
      .toList();
  }

  public Client toClient(JpaClient jpa) {
    return Client.builder()
      .id(jpa.getId())
      .email(jpa.getEmail())
      .phoneNumber(jpa.getPhoneNumber())
      .build();
  }

}
