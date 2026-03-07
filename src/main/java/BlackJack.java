import domain.deck.CardDeck;
import domain.deck.Deck;
import domain.player.Dealer;
import domain.player.Gambler;
import domain.player.Gamblers;
import domain.player.Player;
import dto.PlayerCardInfo;
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
        return PlayNameParser.splitNames(InputView.readLine());
    }

    private void initialDeal(Dealer dealer, Gamblers gamblers) {
        for (int i = 0; i < INITIAL_CARD_COUNT; i++) {
            dealer.deal(cardDeck);
            gamblers.dealAll(cardDeck);
        }
    }

    private void printInitialDealInfo(Dealer dealer, Gamblers gamblers) {
        List<String> playerNames = gamblers.getGamblers().stream()
                .map(Gambler::getName)
                .toList();
        OutputView.printInitMessage(playerNames);
        OutputView.printDealerFirstCard(dealer.firstCard());

        gamblers.forEach(this::printPlayerCardInfo);
    }
    private void printPlayerCardInfo(Player player){
        OutputView.printPlayerCards(PlayerCardInfo.from(player));
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
            OutputView.printPlayerCards(PlayerCardInfo.from(gambler));
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
        printFinalPlayerCardInfo(dealer);
        gamblers.forEach(this::printFinalPlayerCardInfo);
    }

    private void printFinalPlayerCardInfo(Player player){
        OutputView.printFinalPlayer(PlayerCardInfo.from(player));
    }

    private void printFinalResult(Dealer dealer, Gamblers gamblers) {
        OutputView.printFinalResultHeader();
        OutputView.printResult(gamblers.getResult(dealer.score()));
    }


}
