import domain.BlackjackResult;
import domain.deck.CardDeck;
import domain.player.Dealer;
import domain.player.Gambler;
import domain.player.Gamblers;
import domain.player.Player;
import dto.PlayerCardDto;
import dto.PlayerInitCardDto;
import java.util.List;
import parser.AmountParser;
import parser.AnswerParser;
import parser.PlayNameParser;
import view.InputView;
import view.OutputView;

public class BlackJack {

    private static final int INITIAL_CARD_COUNT = 2;

    private final CardDeck cardDeck;

    public BlackJack(CardDeck cardDeck) {
        this.cardDeck = cardDeck;
    }

    public void start() {
        Dealer dealer = new Dealer();
        Gamblers gamblers = new Gamblers(getGamblers());
        initialDeal(dealer, gamblers);
        printInitialDealInfo(dealer, gamblers);

        gamblersTurn(gamblers);
        dealerTurn(dealer);

        printFinalPlayerInfo(dealer, gamblers);
        printFinalResult(dealer, gamblers);
    }

    private List<Gambler> getGamblers() {
        return getPlayerNames().stream()
                .map(this::getGambler)
                .toList();
    }

    private List<String> getPlayerNames() {
        OutputView.printStartMessage();
        return PlayNameParser.splitNames(InputView.readLine());
    }

    private Gambler getGambler(String name) {
        OutputView.printBettingMessage(name);
        int amount = AmountParser.parse(InputView.readLine());
        return new Gambler(name, amount);
    }

    private void initialDeal(Dealer dealer, Gamblers gamblers) {
        for (int i = 0; i < INITIAL_CARD_COUNT; i++) {
            dealer.deal(cardDeck);
            gamblers.dealAll(cardDeck);
        }
    }

    private void printInitialDealInfo(Dealer dealer, Gamblers gamblers) {
        OutputView.printInitMessage(dealer.getName(), gamblers.getNames());
        printPlayerCardInfo(dealer);

        gamblers.forEach(this::printPlayerCardInfo);
    }

    private void printPlayerCardInfo(Player player) {
        OutputView.printPlayerInitCards(PlayerInitCardDto.from(player));
    }

    private void gamblersTurn(Gamblers gamblers) {
        gamblers.forEach(this::gamblerTurn);
    }

    private void gamblerTurn(Gambler gambler) {
        if (checkBlackJack(gambler)) {
            return;
        }

        while (!gambler.isBust() && askHit(gambler.getName())) {
            gambler.deal(cardDeck);
            OutputView.printPlayerCards(PlayerCardDto.from(gambler));
        }
        if (gambler.isBust()) {
            OutputView.printPlayerBust(gambler.getName());
        }
    }

    private boolean checkBlackJack(Gambler gambler) {
        if (gambler.isBlackJack()) {
            OutputView.printPlayerBlackJack(gambler.getName());
            return true;
        }

        return false;
    }

    private boolean askHit(String name) {
        OutputView.askHit(name);
        return AnswerParser.parse(InputView.readLine());
    }

    private void dealerTurn(Dealer dealer) {
        while (!dealer.canStand()) {
            OutputView.printDealerHit(dealer.getName());
            dealer.deal(cardDeck);
        }
    }

    private void printFinalPlayerInfo(Dealer dealer, Gamblers gamblers) {
        printFinalPlayerCardInfo(dealer);
        gamblers.forEach(this::printFinalPlayerCardInfo);
    }


    private void printFinalPlayerCardInfo(Player player) {
        OutputView.printFinalPlayer(PlayerCardDto.from(player));
    }

    private void printFinalResult(Dealer dealer, Gamblers gamblers) {
        OutputView.printFinalResultHeader();

        BlackjackResult blackjackResult = BlackjackResult.from(dealer, gamblers);

        OutputView.printResult(dealer.getName(), blackjackResult);
    }
}
