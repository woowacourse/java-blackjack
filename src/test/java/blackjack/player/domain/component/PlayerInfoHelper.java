package blackjack.player.domain.component;

public class PlayerInfoHelper {
	public static PlayerInfo aPlayerInfo(String namme) {
		return new PlayerInfo(namme, Money.createMoney(1000));
	}
}
