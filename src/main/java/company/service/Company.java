package company.service;

import company.dto.CampaignDto;
import company.dto.PartnerDto;

public interface Company {
	boolean createCampaign(String id, CampaignDto campaignDto);
	CampaignDto getCampaign(String id);
	CampaignDto updateCampaign(String id, CampaignDto campaignDto);
	boolean removeCampaign(String id);
	boolean createPartner(String campaignId, PartnerDto dto);
}
