package demo.licensemanager.partner.service;

import demo.licensemanager.common.ObjectFactory;
import demo.licensemanager.mapping.JpaPartnerToPartnerConverter;
import demo.licensemanager.mapping.PartnerToJpaPartnerConverter;
import demo.licensemanager.partner.PartnerRepo;
import demo.licensemanager.partner.model.JpaPartner;
import demo.licensemanager.partner.model.Partner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.converter.Converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PartnerServiceImplTest {

  @Mock
  PartnerRepo partnerRepo;

  @Captor
  ArgumentCaptor<JpaPartner> jpaPartnerCaptor;

  @InjectMocks
  PartnerServiceImpl sut;

  PartnerToJpaPartnerConverter toJpaPartnerConverter = new PartnerToJpaPartnerConverter();

  Converter<JpaPartner, Partner> toPartnerConverter = new JpaPartnerToPartnerConverter();


  @BeforeEach
  void setUp() {
    sut = new PartnerServiceImpl(toJpaPartnerConverter, toPartnerConverter, partnerRepo);
  }


  @Test
  void addPartner() {
    // Given
    var givenPartner = ObjectFactory.createPartner(2);

    // When
    when(partnerRepo.save(any())).thenReturn(null);
    sut.addPartner(givenPartner);

    // Then
    verify(partnerRepo).save(jpaPartnerCaptor.capture());
    var actual = jpaPartnerCaptor.getValue();
    assertThat(actual).isNotNull();
    assertThat(actual.getClients()).isNotNull()
      .hasSize(givenPartner.getClients().size());
  }


}