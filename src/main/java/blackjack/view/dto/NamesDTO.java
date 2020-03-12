package blackjack.view.dto;

import blackjack.card.domain.CardBundle;
import blackjack.player.domain.Gambler;
import blackjack.player.domain.Player;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class NamesDTO {
	private static final String DELIMITER = ",";

	private final String inputNames;

	public NamesDTO(String inputNames) {
		validate(inputNames);
		this.inputNames = inputNames;
	}

	private void validate(String inputNames) {
		if (inputNames == null || inputNames.trim().isEmpty()) {
			throw new IllegalArgumentException(String.format("%s 비어있는 값을 입력했습니다.", inputNames));
		}
	}

	public List<Player> toPlayers() {
		return Arrays.stream(inputNames.split(DELIMITER))
			.map(name -> new Gambler(new CardBundle(), name))
			.collect(Collectors.toList());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		NamesDTO namesDTO = (NamesDTO)o;
		return Objects.equals(inputNames, namesDTO.inputNames);
	}

	@Override
	public int hashCode() {
		return Objects.hash(inputNames);
	}
}
