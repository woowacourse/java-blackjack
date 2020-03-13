package blackjack.player.domain;

import blackjack.card.domain.CardBundle;
import blackjack.player.domain.report.GameReport;

import java.util.Objects;

public class Gambler extends Player {
	public Gambler(CardBundle cardBundle, String name) {
		super(cardBundle, name);
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
	public boolean isDrawable() {
		return isNotBurst() && isNotBlackjack();
	}

	@Override
	public GameReport getReport(Player player) {
		throw new UnsupportedOperationException();
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
