import controller.BlackjackController;
import domain.card.CardRepository;
import domain.card.Deck;
import domain.user.Dealer;
import domain.user.Players;
import view.InputView;

public class Application {
	public static void main(String[] args) {
		Deck deck = new Deck(CardRepository.toList());
		Dealer dealer = new Dealer();
		BlackjackController.run(deck, dealer, Players.of(InputView.inputPlayerNames()));
	}
}
