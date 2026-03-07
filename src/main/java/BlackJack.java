import domain.deck.CardDeck;
import domain.deck.Deck;
import domain.player.Dealer;
import domain.player.Gambler;
import domain.player.Gamblers;
import java.util.List;
import parser.AnswerParser;
import parser.PlayNameParser;
import view.InputView;
import view.OutputView;

public class BlackJack {
    private static final int INITIAL_CARD_COUNT = 2;
    private final CardDeck cardDeck;

    public BlackJack() {
        this.cardDeck = new Deck();
    }

    public void start() {
        Dealer dealer = new Dealer();
        Gamblers gamblers = new Gamblers(getNames());
        initialDeal(dealer, gamblers);
        printInitialDealInfo(dealer, gamblers);

        gamblersTurn(gamblers);
        dealerTurn(dealer);

        printFinalPlayerInfo(dealer, gamblers);
        printFinalResult(dealer, gamblers);
    }

    private List<String> getNames() {
        OutputView.printStartMessage();
        return PlayNameParser.splitNames(InputView.readLine());
    }

    private void initialDeal(Dealer dealer, Gamblers gamblers) {
        for (int i = 0; i < INITIAL_CARD_COUNT; i++) {
            dealer.deal(cardDeck);
            gamblers.dealAll(cardDeck);
        }
    }

    private void printInitialDealInfo(Dealer dealer, Gamblers gamblers) {
        OutputView.printInitMessage(gamblers.getNames());
        OutputView.printDealerFirstCard(dealer.showFirstCard());

        gamblers.gamblerCardInfo()
                .forEach(OutputView::printPlayerCards);
    }

    private void gamblersTurn(Gamblers gamblers) {
        gamblers.forEach(this::gamblerTurn);
    }

    private void gamblerTurn(Gambler gambler) {
        while (true) {
            if (!askHit(gambler.getName())) {
                break;
            }
            gambler.deal(cardDeck);

            if (gambler.isBust()) {
                OutputView.printPlayerBust(gambler.getName());
                break;
            }
            OutputView.printPlayerCards(gambler.getCardInfo());
        }
    }

    private boolean askHit(String name) {
        OutputView.askHit(name);
        return AnswerParser.parse(InputView.readLine());
    }

    private void dealerTurn(Dealer dealer) {
        while (true) {
            boolean canStand = dealer.canStand();
            if (canStand) {
                break;
            }

            OutputView.printDealerHit();
            dealer.deal(cardDeck);
        }
    }

    private void printFinalPlayerInfo(Dealer dealer, Gamblers gamblers) {
        OutputView.printFinalPlayer(dealer.getCardInfo());
        gamblers.gamblerCardInfo()
                .forEach(OutputView::printFinalPlayer);
    }

    private void printFinalResult(Dealer dealer, Gamblers gamblers) {
        OutputView.printFinalResultHeader();
        OutputView.printResult(gamblers.getResult(dealer.score()));
    }
}
