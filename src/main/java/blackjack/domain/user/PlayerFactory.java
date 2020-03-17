package blackjack.domain.user;

import static java.util.stream.Collectors.*;

import java.util.List;

public class PlayerFactory {
	// TODO: 2020-03-17 player 인원 수 체크 및 중복 검사
	public static List<Player> create(List<String> playerNames) {
		return playerNames.stream()
			.map(Player::new)
			.collect(toList());
	}
}
