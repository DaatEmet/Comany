package company.service;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import company.dao.CampaignRepository;
import company.dao.PartnerRepository;
import company.dto.CampaignDto;
import company.dto.PartnerDto;
import company.exception.CampaignNotFoundException;
import company.exception.FormatFieldException;
import company.exception.PartnerExistException;
import company.model.Campaign;
import company.model.Partner;

@Service
public class CompanyImpl implements Company {

	@Autowired
	PartnerRepository partnerRepository;

	@Autowired
	CampaignRepository campaignRepository;

	@Autowired
	ModelMapper mapper;

	@Override
	public boolean createCampaign(String id, CampaignDto campaignDto) {
		if (campaignRepository.existsById(id))
			return false;
		Campaign campaign = new Campaign(id, campaignDto.getCampaignName(), campaignDto.getMandatoryFields());
		campaignRepository.save(campaign);
		return true;
	}

	@Override
	public CampaignDto getCampaign(String id) {
		Campaign campaign = campaignRepository.findById(id).orElseThrow(() -> new CampaignNotFoundException());
		return mapper.map(campaign, CampaignDto.class);
	}

	@Override
	@Transactional
	public CampaignDto updateCampaign(String id, CampaignDto campaignDto) {
		Campaign campaign = campaignRepository.findById(id).orElseThrow(() -> new CampaignNotFoundException());
		campaign.setCampaignName(campaignDto.getCampaignName());
		campaign.setMandatoryFields(campaignDto.getMandatoryFields());
		campaignRepository.save(campaign);
		return campaignDto;
	}

	@Override
	@Transactional
	public boolean removeCampaign(String id) {
		Campaign campaign = campaignRepository.findById(id).orElseThrow(() -> new CampaignNotFoundException());
		campaignRepository.delete(campaign);
		return true;
	}

	@Override
	public boolean createPartner(String campaignId, PartnerDto dto) {
		Campaign campaign = campaignRepository.findById(campaignId).orElseThrow(() -> new CampaignNotFoundException());
		if (partnerRepository.existsById(dto.getName()))
			 throw new PartnerExistException();
		// Field[] fields = dto.getClass().getDeclaredFields();
		// List<String> declaredFields =
		// Arrays.stream(fields).map(s->s.getName()).collect(Collectors.toList());
		for (String f : campaign.getMandatoryFields()) {
			// if(!declaredFields.contains(f)) return false;
			try {
				Field field = dto.getClass().getDeclaredField(f);
				field.setAccessible(true);
				if (field.get(dto) == null)
					throw new PartnerExistException();
			} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
				e.printStackTrace();
			}
		}
		if (campaign.getMandatoryFields().contains("phone")) {
			check(dto.getPhone(), ".*"); //^[\\w-.]+@([\\w-]+.)+[\\w-]{2,4}$
		}
		if (campaign.getMandatoryFields().contains("email")) {
			check(dto.getEmail(), ".*"); //^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$
		}
		Partner partner = new Partner(dto.getName(), dto.getFirstName(), dto.getEmail(), dto.getPhone(), campaign);
		partnerRepository.save(partner);
		return true;
	}

	private void check(String str, String regexp) {
		CharSequence inputStr = str;
		Pattern pattern = Pattern.compile(regexp, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(inputStr);
		if (!matcher.matches()) {
			System.out.println("Proverka not passed");
			throw new FormatFieldException();
		}
		System.out.println("passed");
	}

}
