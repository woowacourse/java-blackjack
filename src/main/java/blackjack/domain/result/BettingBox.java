package blackjack.domain.result;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Money;
import blackjack.domain.user.Player;

public class BettingBox {

	private final Map<Player, Money> playersProfit;

	public BettingBox(List<Player> players, Dealer dealer) {
		this.playersProfit = new HashMap<>();
		players.stream()
			.forEach(player -> putProfit(dealer, player));
	}

	private void putProfit(Dealer dealer, Player player) {
		playersProfit.put(player, calculateProfit(dealer, player));
	}

	private Money calculateProfit(Dealer dealer, Player player) {
		return player.state().calculateProfit(player.getMoney(), dealer);
	}

	public Map<Player, Money> playerProfit() {
		return Collections.unmodifiableMap(playersProfit);
	}

	public double dealerProfit() {
		double dealerProfit = 0;
		for (Player player : playersProfit.keySet()) {
			dealerProfit -= playersProfit.get(player).getMoney();
		}
		return dealerProfit;
	}
}
