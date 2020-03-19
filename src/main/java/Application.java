import static view.InputView.*;
import static view.OutputView.*;

import domain.card.CardRepository;
import domain.card.Deck;
import domain.PlayerMoneys;
import domain.controller.BlackjackController;
import domain.user.Dealer;
import domain.user.User;
import domain.user.Users;

public class Application {
	public static void main(String[] args) {
		User dealer = new Dealer();
		Deck deck = Deck.of(CardRepository.toList());
		Users users = Users.of(inputPlayerNames(), dealer);
		PlayerMoneys playerMoneys = BlackjackController.getBettingMoney(users.getPlayers());

		BlackjackController.proceedInitialPhase(users, deck);

		if (dealer.isNotBlackJack()) {
			BlackjackController.proceedGame(users.getPlayers(), dealer, deck);
		}

		printResultStatus(users);
		printResultProfit(playerMoneys, dealer);
	}
}
