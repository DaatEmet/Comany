package company.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(of = {"id"})
public class Campaign implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 570892918122571058L;
	
	@Id
	String id;
	String campaignName;
	@ElementCollection
	List<String> mandatoryFields;
	@OneToMany(mappedBy = "campaign", fetch = FetchType.EAGER)
	List<Partner> partners;
	public Campaign(String id, String campaignName, List<String> mandatoryFields) {
		this.id = id;
		this.campaignName = campaignName;
		this.mandatoryFields = mandatoryFields;
	}
	
}
