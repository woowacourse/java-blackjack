package blackjack.domain.participant;

import java.util.Objects;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.state.Hit;
import blackjack.domain.state.PlayerState;

public abstract class Gamer {
	public static final String COMMA_DELIMITER = ",";

	protected final PlayerName name;
	protected PlayerState playerState;

	protected Gamer(String name) {
		this.name = new PlayerName(name);
		this.playerState = new Hit(new Cards());
	}

	public void receiveCard(Card card) {
		playerState = playerState.drawNewCard(card);
	}

	public abstract boolean canReceiveCard(boolean drawFlag);

	public int calculatePoint() {
		return playerState.calculatePoint();
	}

	public PlayerState getPlayerState() {
		return playerState;
	}

	public String getName() {
		return name.getName();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Gamer gamer = (Gamer)o;
		return Objects.equals(name, gamer.name) && Objects.equals(playerState, gamer.playerState);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, playerState);
	}
}
