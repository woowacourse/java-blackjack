package blackjack.player;

import java.util.Objects;

import blackjack.player.card.CardBundle;

public class Gambler extends Player {
	private final String name;

	public Gambler(CardBundle cardBundle, String name) {
		super(cardBundle);
		this.name = name;
	}

	@Override
	public boolean isDealer() {
		return false;
	}

	@Override
	public boolean isGambler() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Gambler gambler = (Gambler)o;
		return Objects.equals(name, gambler.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}
