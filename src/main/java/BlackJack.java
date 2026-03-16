import domain.BettingMoney;
import domain.deck.Deck;
import domain.deck.StandardDeck;
import domain.player.Dealer;
import domain.player.Gambler;
import domain.player.Gamblers;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
        Gamblers gamblers = new Gamblers(createGambler());

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

    private List<Gambler> createGambler() {
        List<Gambler> gamblers = new ArrayList<>();
        List<String> names = getPlayerNames();
        for (String name : names) {
            OutputView.requestBettingMoney(name);
            int money = Integer.parseInt(InputView.readLine());
            gamblers.add(new Gambler(name, new BettingMoney(money)));
        }
        return gamblers;
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
        OutputView.addNewLine();
    }

    private void gamblersTurn(Gamblers gamblers) {
        for (Gambler gambler : gamblers.getGamblers()) {
            gamblerTurn(gambler);
        }
        OutputView.addNewLine();
    }

    private void gamblerTurn(Gambler gambler) {
        while (true) {
            if (gambler.isBlackJack()) {
                OutputView.printIsBlackJack(gambler.getName());
                break;
            }
            OutputView.askHit(gambler.getName());
            boolean answer = AnswerParser.parse(InputView.readLine());
            if (!answer) {
                OutputView.printPlayerCards(gambler);
                break;
            }

            gambler.deal(deck);
            OutputView.printPlayerCards(gambler);
            if (gambler.isBust()) {
                OutputView.printPlayerBust(gambler.getName());
                break;
            }
        }
    }

    private void dealerTurn(Dealer dealer) {
        while (true) {
            if (dealer.isBlackJack()) {
                OutputView.printDealerBlackJack();
                break;
            }
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
        Map<String, Integer> gamblersResult = getResult(dealer, gamblers);
        int dealerIncome = 0;
        for (int income : gamblersResult.values()) {
            dealerIncome -= income;
        }
        OutputView.printResult(gamblersResult, dealerIncome);
    }

    private Map<String, Integer> getResult(Dealer dealer, Gamblers gamblers) {
        Map<String, Integer> gamblersResult = new LinkedHashMap<>();
        for (Gambler gambler : gamblers.getGamblers()) {
            gamblersResult.put(gambler.getName(), gambler.calculateFinalIncome(dealer));
        }
        return gamblersResult;
    }
}
