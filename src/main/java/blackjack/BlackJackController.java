package blackjack;

import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.game.BlackJackGame;
import blackjack.domain.game.BlackJackReferee;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackController {

	public void run() {
		BlackJackGame blackJackGame = new BlackJackGame(InputView.askNames(), InputView::askBet,
			new Deck(Card.getCards()));

		Dealer dealer = blackJackGame.getDealer();
		List<Player> players = blackJackGame.getPlayers();

		OutputView.printGamers(dealer, players);

		BlackJackReferee result = blackJackGame.play(InputView::askHitOrStay, OutputView::checkHoldingCards);

		OutputView.printAdditionalDrawDealer(dealer.findHitCount());
		OutputView.printFinalCards(dealer, players);
		OutputView.printFinalResult(result);
	}
}
