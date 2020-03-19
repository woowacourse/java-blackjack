package blackjack;

import static blackjack.util.StringUtil.*;
import static java.util.stream.Collectors.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import blackjack.controller.BlackjackController;
import blackjack.domain.blackjack.BlackjackTable;
import blackjack.domain.card.CardFactory;
import blackjack.domain.card.Deck;
import blackjack.domain.result.BettingMoney;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.PlayerFactory;
import blackjack.view.InputView;

public class Application {
	public static void main(String[] args) {
		Deck deck = new Deck(CardFactory.create());
		Dealer dealer = new Dealer(Dealer.NAME);
		List<Player> players = PlayerFactory.create(parsingPlayerNames(InputView.inputPlayerNames()));
		Map<Player, BettingMoney> playersBettingMoney = generatePlayersBettingMoney(players);
		BlackjackTable blackjackTable = new BlackjackTable(deck, dealer, players, playersBettingMoney);

		BlackjackController blackjackController = new BlackjackController(blackjackTable);
		blackjackController.run();
	}

	private static Map<Player, BettingMoney> generatePlayersBettingMoney(List<Player> players) {
		return players.stream()
			.collect(toMap(Function.identity(),
				player -> BettingMoney.valueOf(InputView.inputBettingMoneyFrom(player)),
				(x, y) -> x,
				LinkedHashMap::new));
	}
}
