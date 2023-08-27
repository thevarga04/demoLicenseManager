package demo.licensemanager.partner.service;

import demo.licensemanager.mapping.PartnerToJpaPartnerConverter;
import demo.licensemanager.partner.PartnerRepo;
import demo.licensemanager.partner.PartnerService;
import demo.licensemanager.partner.model.JpaPartner;
import demo.licensemanager.partner.model.Partner;
import demo.licensemanager.partner.model.PatchPartner;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PartnerServiceImpl implements PartnerService {

  private final PartnerToJpaPartnerConverter toJpaPartnerConverter;
  private final Converter<JpaPartner, Partner> toPartnerConverter;
  private final PartnerRepo partnerRepo;


  @Override
  public List<Partner> getPartnerList() {
    final var jpaPartnerList = partnerRepo.findAll();
    return jpaPartnerList.stream()
      .map(toPartnerConverter::convert)
      .toList();
  }

  @Override
  public void addPartner(Partner partner) {
    final var jpaPartner = toJpaPartnerConverter.convert(partner);
    if (jpaPartner == null) {
      throw new ValidationException();
    }
    partnerRepo.save(jpaPartner);
  }

  @Override
  public Partner getPartner(int partnerId) {
    final var jpaPartner = partnerRepo.findById(partnerId)
      .orElseThrow(EntityNotFoundException::new);
    return toPartnerConverter.convert(jpaPartner);
  }

  @Override
  public void addClients(PatchPartner patchPartner) {
    final var jpaPartner = partnerRepo.findById(patchPartner.getId())
      .orElseThrow(EntityNotFoundException::new);

    final var jpaClientList = toJpaPartnerConverter.toJpaClientList(patchPartner.getClients());
    if (jpaPartner.getClients() == null) {
      jpaPartner.setClients(jpaClientList);
    } else {
      jpaPartner.getClients().addAll(jpaClientList);
    }

    partnerRepo.save(jpaPartner);
  }


}
