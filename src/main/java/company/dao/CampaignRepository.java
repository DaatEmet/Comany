package company.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import company.model.Campaign;

public interface CampaignRepository extends JpaRepository<Campaign, String>{

}
