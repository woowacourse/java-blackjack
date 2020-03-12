import domain.deck.Deck;
import domain.deck.DeckFactory;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;
import util.YesOrNo;
import view.InputView;
import view.OutputView;

public class Application {

    private static final int FIRST_CARD_COUNT = 2;

    public static void main(String[] args) {
        String names = InputView.receiveNameInput();
        Players players = Players.of(names);

        Dealer dealer = Dealer.appoint();
        Deck deck = DeckFactory.getDeck();

        for (int i = 0; i < FIRST_CARD_COUNT; i++) {
            dealer.draw(deck.dealOut());
            players.draw(deck);
        }

        OutputView.printFirstDealOutResult(dealer, players);

        players.getPlayers()
                .forEach(player -> playersAdditionalDraw(deck, player));

        while (dealer.isAvailableToDraw()) {
            OutputView.printDealerDealOut();
            dealer.draw(deck.dealOut());
        }
    }

    private static void playersAdditionalDraw(Deck deck, Player player) {
        while(player.isAvailableToDraw() && isYes(player)) {
            player.draw(deck.dealOut());
            OutputView.printDealOutResult(player);
        }
    }

    private static boolean isYes(Player player) {
        String input = InputView.receiveYesOrNoInput(player.getName());
        return YesOrNo.of(input).isYes();
    }
}
