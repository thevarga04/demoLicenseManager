package demo.licensemanager.partner;

import demo.licensemanager.partner.model.JpaPartner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerRepo extends JpaRepository<JpaPartner, Integer> {

}
