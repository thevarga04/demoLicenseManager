package demo.licensemanager.common;

import demo.licensemanager.client.Client;
import demo.licensemanager.partner.model.Partner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ObjectFactory {

  private static final AtomicInteger ID = new AtomicInteger(1);


  public static Partner createPartner(int amount) {
    return Partner.builder()
      .id(ID.get())
      .username("ACME")
      .clients(createClientList(amount))
      .build();
  }

  public static List<Client> createClientList(int amount) {
    final var clientList = new ArrayList<Client>(amount);
    while (amount-- > 0) {
      clientList.add(createClient());
    }
    return clientList;
  }

  public static Client createClient() {
    return Client.builder()
      .id(ID.get())
      .email("john.doe@acme.com")
      .phoneNumber(1_000L * ID.getAndIncrement())
      .build();
  }


}
