package blackjack.player.domain;

import java.util.Objects;

import blackjack.card.domain.CardBundle;
import blackjack.player.domain.report.GameReport;

public class Gambler extends Player {
	public Gambler(CardBundle cardBundle, String name) {
		super(cardBundle, name);
	}

	@Override
	public boolean isHit() {
		return isNotBurst() && isNotBlackjack();
	}

	@Override
	public GameReport createReport(Player player) {
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
