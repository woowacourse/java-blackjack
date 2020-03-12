import domain.card.CardDeck;
import domain.game.WhetherAddCard;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;
import domain.user.Users;
import factory.CardFactory;
import factory.UserFactory;
import util.CardDistributor;
import util.ResultGenerator;
import view.InputView;
import view.OutputView;

public class Application {
    private static final int DEFAULT_CARD_SIZE = 2;

    public static void main(String[] args) {
        final CardDeck cardDeck = new CardDeck(CardFactory.create());
        final Dealer dealer = new Dealer();
        final Users users = new Users(UserFactory.create(InputView.inputNames()));

        distributeInitCard(cardDeck, dealer, users);
        initBrief(dealer, users);
        addMoreCards(cardDeck, dealer, users);
        printResults(dealer, users);
    }

    private static void distributeInitCard(final CardDeck cardDeck, final Dealer dealer, final Users users) {
        distributeToPlayer(cardDeck, dealer);
        for (User user : users) {
            distributeToPlayer(cardDeck, user);
        }
    }

    private static void distributeToPlayer(final CardDeck cardDeck, final Player player) {
        int distributeCount = DEFAULT_CARD_SIZE;

        while (distributeCount-- > 0) {
            CardDistributor.giveOneCard(cardDeck, player);
        }
    }

    private static void initBrief(final Dealer dealer, final Users users) {
        OutputView.printDistributeMessage(users);
        OutputView.printInitStatus(dealer, users);
    }

    private static void addMoreCards(final CardDeck cardDeck, final Dealer dealer, final Users users) {
        userMoreCard(cardDeck, users);
        dealerMoreCard(cardDeck, dealer);
    }

    private static void userMoreCard(final CardDeck cardDeck, final Users users) {
        for (User user : users) {
            giveCardsIfUserWant(cardDeck, user);
        }
    }

    private static void giveCardsIfUserWant(final CardDeck cardDeck, final User user) {
        while (user.isNotBust() && WhetherAddCard.of(InputView.inputMoreCard(user)).isYes()) {
            CardDistributor.giveOneCard(cardDeck, user);
            OutputView.printStatus(user);
        }
    }

    private static void dealerMoreCard(final CardDeck cardDeck, final Dealer dealer) {
        while (dealer.shouldAddCard()) {
            CardDistributor.giveOneCard(cardDeck, dealer);
            OutputView.printDealerAddCard();
        }
    }

    private static void printResults(final Dealer dealer, final Users users) {
        OutputView.printPlayersResult(dealer, users);
        OutputView.printLastResult(ResultGenerator.create(dealer, users));
    }
}
