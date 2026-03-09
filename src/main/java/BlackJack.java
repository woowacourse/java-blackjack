import domain.deck.Deck;
import domain.deck.StandardDeck;
import domain.player.Dealer;
import domain.player.Gambler;
import domain.player.Gamblers;
import dto.GamblerCardInfo;
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
        initialDeal(dealer, gamblers); //초기 2장 지급
        printInitialDealInfo(dealer, gamblers); //초기 상태 출력

        gamblersTurn(gamblers); // 플레이어턴 순회
        dealerTurn(dealer); // 딜러 턴

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
        OutputView.printDealerFirstCard(dealer.getFirstCardInfo());

        for (GamblerCardInfo gamblerCardInfo : gamblers.gamblerCardInfos()) {
            OutputView.printPlayerCards(gamblerCardInfo);
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
            OutputView.printPlayerCards(gambler.getCardInfo());
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
        OutputView.printFinalDealer(dealer.getCardInfo(), dealer.score());
        for (GamblerCardInfo gamblerCardInfo : gamblers.gamblerCardInfos()) {
            OutputView.printFinalPlayer(gamblerCardInfo);
        }
    }

    private void printFinalResult(Dealer dealer, Gamblers gamblers) {
        OutputView.printFinalResultHeader();
        OutputView.printResult(gamblers.getResult(dealer));
    }
}
