package blackjack.player.domain;

import java.util.Objects;

import blackjack.card.domain.CardBundle;
import blackjack.player.domain.component.PlayerInfo;

public class Gambler extends Player {
	private static final int BLACKJACK_MAXIMUM_VALUE = 21;
	private final PlayerInfo playerInfo;

	public Gambler(CardBundle cardBundle, PlayerInfo playerInfo) {
		super(cardBundle);
		this.playerInfo = playerInfo;
	}

	@Override
	public boolean isHit() {
		return cardBundle.calculateScore() < BLACKJACK_MAXIMUM_VALUE;
	}

	@Override
	public String getName() {
		return playerInfo.getName();
	}

	public PlayerInfo getPlayerInfo() {
		return playerInfo;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Gambler gambler = (Gambler)o;
		return Objects.equals(playerInfo, gambler.playerInfo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(playerInfo);
	}
}
