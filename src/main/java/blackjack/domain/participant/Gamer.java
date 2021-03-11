package blackjack.domain.participant;

import java.util.Objects;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.state.PlayerState;

public abstract class Gamer {
	public static final String COMMA_DELIMITER = ",";

	protected final PlayerName name;
	private final int money;
	protected PlayerState playerState;

	protected Gamer(String name, int money) {
		this.name = new PlayerName(name);
		this.money = money;
	}

	public void receiveCard(Card card) {
		playerState = playerState.drawNewCard(card);
	}

	public abstract boolean canReceiveCard();

	public abstract boolean continueDraw(String draw, Deck deck);

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
