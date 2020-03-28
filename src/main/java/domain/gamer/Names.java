package domain.gamer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *   class description
 *
 *   @author ParkDooWon, AnHyungJu  
 */
public class Names {
	private static final int MAX_PLAYER = 5;
	private static final int EMPTY = 0;

	private List<Name> names;

	public Names(String[] inputPlayersName) {
		validate(inputPlayersName);
		names = Arrays.stream(inputPlayersName)
			.map(Name::new)
			.collect(Collectors.toList());
	}

	private void validate(String[] playersName) {
		validateNullAndEmpty(playersName);
		validateDuplicatedName(playersName);
		validateHeadcount(playersName);
	}

	private void validateNullAndEmpty(String[] playerNames) {
		if ((playerNames == null) || (playerNames.length == EMPTY)) {
			throw new IllegalArgumentException("null이나 빈 값이 들어올 수 없습니다.");
		}
	}

	private void validateDuplicatedName(String[] playersName) {
		int distinctSize = (int)Arrays.stream(playersName)
			.distinct().count();

		if (distinctSize != playersName.length) {
			throw new IllegalArgumentException("중복된 이름이 있습니다.");
		}
	}

	private void validateHeadcount(String[] playersName) {
		if (playersName.length > MAX_PLAYER) {
			throw new IllegalArgumentException("인원수 초과입니다.");
		}
	}

	public List<Name> getNames() {
		return names;
	}
}
