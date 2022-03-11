package blackjack.domain.dto;

import java.util.List;

public class DealerResultDto {
	private String name;
	private List<String> outcome;

	public DealerResultDto(String name, List<String> outcome) {
		this.name = name;
		this.outcome = outcome;
	}

	public String getName() {
		return name;
	}

	public List<String> getOutcome() {
		return outcome;
	}
}
