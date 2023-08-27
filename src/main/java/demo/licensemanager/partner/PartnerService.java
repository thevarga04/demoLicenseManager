package demo.licensemanager.partner;

import demo.licensemanager.partner.model.Partner;
import demo.licensemanager.partner.model.PatchPartner;

import java.util.List;

public interface PartnerService {

  List<Partner> getPartnerList();

  void addPartner(Partner partner);

  Partner getPartner(int partnerId);

  void addClients(PatchPartner patchPartner);

}
