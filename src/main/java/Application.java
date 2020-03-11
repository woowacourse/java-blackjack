import domain.deck.Deck;
import domain.deck.DeckFactory;
import domain.user.Dealer;
import domain.user.Players;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        String names = InputView.receiveNameInput();
        Players players = Players.of(names);

        Dealer dealer = Dealer.appoint();
        Deck deck = DeckFactory.getDeck();

        for (int i = 0; i < 2; i++) {
            dealer.draw(deck.dealOut());
            players.draw(deck);
        }

        OutputView.printFirstDealOutResult(dealer, players);
    }
}
