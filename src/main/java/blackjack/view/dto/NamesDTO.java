package blackjack.view.dto;

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

	// public List<Player> toPlayers() {
	// 	return Arrays.stream(inputNames.split(DELIMITER))
	// 		.map(name -> new Gambler(new CardBundle(), name, playerInfo))
	// 		.collect(Collectors.toList());
	// }
}
