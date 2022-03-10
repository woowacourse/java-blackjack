package blackjack.dto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import blackjack.domain.Role;

public class FinalResultDto {

	private final Map<String, PersonalResultDto> results;

	private FinalResultDto(final List<Role> roles) {
		this.results = roles.stream()
			.collect(Collectors.toMap(Role::getName, PersonalResultDto::from));
	}

	public static FinalResultDto from(final List<Role> roles) {
		return new FinalResultDto(roles);
	}
}
