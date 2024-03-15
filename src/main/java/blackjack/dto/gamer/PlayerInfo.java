package blackjack.dto.gamer;

import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.gamer.Player;

public record PlayerInfo(String name, List<Card> cards) {

	public static PlayerInfo from(final Player player) {
		return new PlayerInfo(player.getName(), player.getCards());
	}
}
