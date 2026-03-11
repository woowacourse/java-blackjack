import domain.deck.Deck;
import domain.deck.StandardDeck;
import domain.player.Dealer;
import domain.player.Gambler;
import domain.player.Gamblers;
import java.util.List;
import parser.AnswerParser;
import parser.PlayerNameParser;
import view.InputView;
import view.OutputView;

public class BlackJack {
    private static final int INITIAL_CARD_COUNT = 2;

    private final Deck deck;

    public BlackJack() {
        this.deck = new StandardDeck();
    }

    public void start() {
        Dealer dealer = new Dealer();
        Gamblers gamblers = new Gamblers(getPlayerNames());

        initialDeal(dealer, gamblers);
        printInitialDealInfo(dealer, gamblers);
        gamblersTurn(gamblers);
        dealerTurn(dealer);

        printFinalPlayerInfo(dealer, gamblers);
        printFinalResult(dealer, gamblers);
    }

    private List<String> getPlayerNames() {
        OutputView.printStartMessage();
        return PlayerNameParser.splitNames(InputView.readLine());
    }

    private void initialDeal(Dealer dealer, Gamblers gamblers) {
        for (int i = 0; i < INITIAL_CARD_COUNT; i++) {
            dealer.deal(deck);
            gamblers.dealAll(deck);
        }
    }

    private void printInitialDealInfo(Dealer dealer, Gamblers gamblers) {
        OutputView.printInitMessage(gamblers.getNames());
        OutputView.printDealerFirstCard(dealer.getFirstCard());

        for (Gambler gambler : gamblers.getGamblers()) {
            OutputView.printPlayerCards(gambler);
        }
    }

    private void gamblersTurn(Gamblers gamblers) {
        for (Gambler gambler : gamblers.getGamblers()) {
            gamblerTurn(gambler);
        }
    }

    private void gamblerTurn(Gambler gambler) {
        while (true) {
            OutputView.askHit(gambler.getName());
            boolean answer = AnswerParser.parse(InputView.readLine());
            if (!answer) {
                break;
            }

            gambler.deal(deck);

            if (gambler.isBust()) {
                OutputView.printPlayerBust(gambler.getName());
                break;
            }
            OutputView.printPlayerCards(gambler);
        }
    }

    private void dealerTurn(Dealer dealer) {
        while (true) {
            boolean canStand = dealer.canStand();
            if (canStand) {
                break;
            }

            OutputView.printDealerHit();
            dealer.deal(deck);
        }
    }

    private void printFinalPlayerInfo(Dealer dealer, Gamblers gamblers) {
        OutputView.printFinalDealer(dealer, dealer.score());
        for (Gambler gambler : gamblers.getGamblers()) {
            OutputView.printFinalPlayer(gambler);
        }
    }

    private void printFinalResult(Dealer dealer, Gamblers gamblers) {
        OutputView.printFinalResultHeader();
        OutputView.printResult(gamblers.getResult(dealer));
    }
}
