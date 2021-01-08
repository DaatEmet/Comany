package company.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import company.model.Partner;

public interface PartnerRepository extends JpaRepository<Partner, String>{

}
