package demo.licensemanager.partner;

import demo.licensemanager.partner.model.Partner;
import demo.licensemanager.partner.model.PatchPartner;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/partners")
@RequiredArgsConstructor
public class PartnerRest {

  private final PartnerService partnerService;


  /**
   * Add / Create / Register a new Partner ...
   */
  @Profile(value = "default")
  @PostMapping
  public ResponseEntity<HttpStatus> addPartner(@Valid @RequestBody Partner partner) {
    partnerService.addPartner(partner);
    return ResponseEntity.ok().build();
  }

  @Profile(value = "default")
  @GetMapping
  public ResponseEntity<List<Partner>> getPartnerList() {
    final var partnerList = partnerService.getPartnerList();
    return ResponseEntity.ok(partnerList);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Partner> getPartner(@PathVariable("id") int partnerId) {
    final var partner = partnerService.getPartner(partnerId);
    return ResponseEntity.ok(partner);
  }

  @PatchMapping
  public ResponseEntity<HttpStatus> addClients(@Valid @RequestBody PatchPartner patchPartner) {
    partnerService.addClients(patchPartner);
    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }


}
