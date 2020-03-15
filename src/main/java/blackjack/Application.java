package blackjack;

import static blackjack.util.StringUtil.*;

import java.util.List;

import blackjack.controller.BlackjackController;
import blackjack.domain.BlackjackTable;
import blackjack.domain.card.CardFactory;
import blackjack.domain.card.Deck;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.PlayerFactory;
import blackjack.view.InputView;

public class Application {
	public static void main(String[] args) {
		Deck deck = new Deck(CardFactory.create());
		Dealer dealer = new Dealer(Dealer.NAME);
		List<Player> players = PlayerFactory.create(parsingPlayerNames(InputView.inputPlayerNames()));
		BlackjackTable blackjackTable = new BlackjackTable(deck);

		BlackjackController blackjackController = new BlackjackController(blackjackTable);
		blackjackController.playGame(dealer, players);
	}
}
