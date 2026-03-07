package domain;

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

    public BlackJack(CardDeck cardDeck) {
        this.cardDeck = cardDeck;
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
        OutputView.printInitMessage(gamblers.getNames());
        OutputView.printDealerFirstCard(dealer.firstCard());

        gamblers.forEach(this::printPlayerCardInfo);
    }

    private void printPlayerCardInfo(Player player) {
        OutputView.printPlayerCards(PlayerCardInfo.from(player));
    }

    private void gamblersTurn(Gamblers gamblers) {
        gamblers.forEach(this::gamblerTurn);
    }

    private void gamblerTurn(Gambler gambler) {
        while (askHit(gambler.getName())) {

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
        while (!dealer.canStand()) {
            OutputView.printDealerHit();
            dealer.deal(cardDeck);
        }
    }

    private void printFinalPlayerInfo(Dealer dealer, Gamblers gamblers) {
        printFinalPlayerCardInfo(dealer);
        gamblers.forEach(this::printFinalPlayerCardInfo);
    }

    private void printFinalPlayerCardInfo(Player player) {
        OutputView.printFinalPlayer(PlayerCardInfo.from(player));
    }

    private void printFinalResult(Dealer dealer, Gamblers gamblers) {
        OutputView.printFinalResultHeader();
        OutputView.printResult(gamblers.getResult(dealer.score()));
    }

}
