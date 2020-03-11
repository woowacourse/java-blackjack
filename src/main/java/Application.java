import domain.*;
import factory.CardFactory;
import factory.PlayerFactory;
import util.ResultCalculator;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        CardDeck cardDeck = new CardDeck();
        Dealer dealer = new Dealer();
        Players players = new Players(PlayerFactory.create(InputView.inputNames()));

        cardDeck.shuffle();
        dealer.addCard(cardDeck.drawOne());
        dealer.addCard(cardDeck.drawOne());

        for (Player player : players) {
            dealer.giveOneCard(cardDeck, player);
            dealer.giveOneCard(cardDeck, player);
        }

        OutputView.printDistributeMessage(players);
        OutputView.printInitStatus(dealer, players);

        if (dealer.isNotBlackJack()) {
            askMoreCard(cardDeck, dealer, players);
            checkDealerCardAndGiveMoreCard(cardDeck, dealer);
        }

        OutputView.printUsersResult(dealer, players);
        OutputView.printLastResult(ResultCalculator.getResults(dealer, players));
    }

    private static void askMoreCard(CardDeck cardDeck, Dealer dealer, Players players) {
        for (Player player : players) {
            giveIfWant(cardDeck, dealer, player);
        }
    }

    private static void giveIfWant(CardDeck cardDeck, Dealer dealer, Player player) {
        while (player.isNotBust() && WhetherAddCard.of(InputView.inputMoreCard(player)).isYes()) {
            dealer.giveOneCard(cardDeck, player);
            OutputView.printStatus(player.getName(), player.getCards());
        }
    }

    private static void checkDealerCardAndGiveMoreCard(CardDeck cardDeck, Dealer dealer) {
        if (dealer.shouldAddCard()) {
            dealer.addCard(cardDeck.drawOne());
            OutputView.printDealerAddCard();
        }
    }
}
