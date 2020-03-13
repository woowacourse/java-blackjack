package blackjack.view.dto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import blackjack.card.domain.CardBundle;
import blackjack.player.domain.Gambler;
import blackjack.player.domain.Player;

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
}
