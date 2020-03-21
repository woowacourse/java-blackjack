package blackjack.player.domain.component;

public class PlayerInfoHelper {
	public static PlayerInfo aPlayerInfo(String namme) {
		return new PlayerInfo(namme, Money.create(1000));
	}
}
