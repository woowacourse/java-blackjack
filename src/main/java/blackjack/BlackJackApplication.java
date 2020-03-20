package blackjack;

import blackjack.domain.BettingMoney;
import blackjack.domain.CardDraw;
import blackjack.domain.Name;
import blackjack.domain.Names;
import blackjack.domain.card.CardDeck;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Gamblers;
import blackjack.domain.result.GameResult;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.LinkedHashMap;
import java.util.Map;

public class BlackJackApplication {

    private static final int FIRST_TIME_DRAW_COUNT = 2;

    public static void main(String[] args) {
        try {
            startGame();
        } catch (Exception e) {
            OutputView.printExceptionMessage(e.getMessage());
        }
    }

    private static void startGame() {
        Names names = Names.of(InputView.inputNames());
        Gamblers gamblers = new Gamblers(getPlayerInfo(names));
        Dealer dealer = new Dealer();
        CardDeck cardDeck = new CardDeck();
        distributeFirstCards(dealer, gamblers, cardDeck);
        drawMoreCards(dealer, gamblers, cardDeck);
        printCalculatedResult(dealer, gamblers);
    }

    private static Map<Name, BettingMoney> getPlayerInfo(Names names) {
        Map<Name, BettingMoney> playerInfo = new LinkedHashMap<>();
        for (Name name : names.getNames()) {
            playerInfo.put(name, BettingMoney.of(InputView.inputMoney(name)));
        }
        return playerInfo;
    }

    private static void distributeFirstCards(Dealer dealer, Gamblers gamblers, CardDeck cardDeck) {
        dealer.drawCard(cardDeck, FIRST_TIME_DRAW_COUNT);
        for (Gambler gambler : gamblers.getGamblers()) {
            gambler.drawCard(cardDeck, FIRST_TIME_DRAW_COUNT);
        }
        OutputView.printCardDistribution(dealer, gamblers);
        OutputView.printUsersCards(dealer, gamblers);
    }

    private static void drawMoreCards(Dealer dealer, Gamblers gamblers, CardDeck cardDeck) {
        for (Gambler gambler : gamblers.getGamblers()) {
            drawMorePlayerCardManual(cardDeck, gambler);
        }
        drawMoreDealerCardAuto(cardDeck, dealer);
    }

    private static void drawMorePlayerCardManual(CardDeck cardDeck, Gambler gambler) {
        while (gambler.canDrawCard() && CardDraw.of(InputView.inputMoreCard(gambler)).isYes()) {
            gambler.drawCard(cardDeck);
            OutputView.printPlayerCards(gambler);
        }
    }

    private static void drawMoreDealerCardAuto(CardDeck cardDeck, Dealer dealer) {
        while (dealer.canDrawCard()) {
            OutputView.printDealerOneMoreCard(dealer);
            dealer.drawCard(cardDeck);
        }
    }

    private static void printCalculatedResult(Dealer dealer, Gamblers gamblers) {
        GameResult gameResult = new GameResult(dealer, gamblers);
        OutputView.printUsersCardsAndScore(dealer, gamblers);
        OutputView.printFinalResult(dealer, gameResult);
    }
}
