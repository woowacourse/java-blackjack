import domain.card.CardDeck;
import domain.game.WhetherAddCard;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;
import domain.user.User;
import factory.CardFactory;
import factory.PlayerFactory;
import util.CardDistributor;
import util.ResultCalculator;
import view.InputView;
import view.OutputView;

public class Application {
    private static final int DEFAULT_CARD_SIZE = 2;

    public static void main(String[] args) {
        final CardDeck cardDeck = new CardDeck(CardFactory.create());
        final Dealer dealer = new Dealer();
        final Players players = new Players(PlayerFactory.create(InputView.inputNames()));

        distributeInitCard(cardDeck, dealer, players);
        initBrief(dealer, players);
        addMoreCards(cardDeck, dealer, players);
        printResults(dealer, players);
    }

    private static void distributeInitCard(CardDeck cardDeck, Dealer dealer, Players players) {
        distributeToUser(cardDeck, dealer);
        for (Player player : players) {
            distributeToUser(cardDeck, player);
        }
    }

    private static void distributeToUser(CardDeck cardDeck, User user) {
        int distributeCount = DEFAULT_CARD_SIZE;
        while (distributeCount-- > 0) {
            CardDistributor.giveOneCard(cardDeck, user);
        }
    }

    private static void initBrief(Dealer dealer, Players players) {
        OutputView.printDistributeMessage(players);
        OutputView.printInitStatus(dealer, players);
    }

    private static void addMoreCards(CardDeck cardDeck, Dealer dealer, Players players) {
        userMoreCard(cardDeck, players);
        dealerMoreCard(cardDeck, dealer);
    }

    private static void userMoreCard(CardDeck cardDeck, Players players) {
        for (Player player : players) {
            giveCardsIfPlayerWant(cardDeck, player);
        }
    }

    private static void giveCardsIfPlayerWant(CardDeck cardDeck, Player player) {
        while (player.isNotBust() && WhetherAddCard.of(InputView.inputMoreCard(player)).isYes()) {
            CardDistributor.giveOneCard(cardDeck, player);
            OutputView.printStatus(player);
        }
    }

    private static void dealerMoreCard(CardDeck cardDeck, Dealer dealer) {
        if (dealer.shouldAddCard()) {
            CardDistributor.giveOneCard(cardDeck, dealer);
            OutputView.printDealerAddCard();
        }
    }

    private static void printResults(Dealer dealer, Players players) {
        OutputView.printUsersResult(dealer, players);
        OutputView.printLastResult(ResultCalculator.getResults(dealer, players));
    }
}
