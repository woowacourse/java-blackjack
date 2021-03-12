package blackjack.controller;

import blackjack.domain.YesOrNo;
import blackjack.domain.game.BlackJackGame;
import blackjack.domain.game.Result;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Gamblers;
import blackjack.domain.player.Money;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.*;

import static blackjack.domain.YesOrNo.YES;
import static blackjack.domain.YesOrNo.of;

public class BlackJackController {


    public static void main(String[] args) {
        run();
    }

    public static void run() {
        Gamblers gamblers = createPlayers();
        BlackJackGame blackJackGame = new BlackJackGame(gamblers);

        playFirstTurn(blackJackGame);

        giveEachGamblerCard(blackJackGame, gamblers);
        giveDealerCards(blackJackGame);

        Result result = blackJackGame.calculateResult();
        result.calculateProfit();
        OutputView.printResult(result);
    }

    private static Gamblers createPlayers() {
        Map<String, Money> playerInfo = createNameAndBattingMoney();
        List<Gambler> gamblers = new ArrayList<>();
        try {
            playerInfo.forEach((key, value) -> gamblers.add(new Gambler(key, value)));
        } catch (Exception e) {
            OutputView.printError(e);
            return createPlayers();
        }
        return new Gamblers(gamblers);
    }

    private static Map<String, Money> createNameAndBattingMoney() {
        String allName = InputView.askPlayerNames();
        List<String> names = Arrays.asList(allName.split(","));
        Map<String, Money> playerInfo = new HashMap<>();
        for (String name : names) {
            Money money = createMoney(name);
            playerInfo.put(name, money);
        }
        return playerInfo;
    }

    private static Money createMoney(String name) {
        try {
            return new Money(InputView.askPlayerBattingMoney(name));
        } catch (Exception e) {
            OutputView.printError(e);
            return createMoney(name);
        }
    }

    private static void playFirstTurn(BlackJackGame blackJackGame) {
        blackJackGame.initDealerCards();
        blackJackGame.initGamblersCards();
        OutputView.printDealerCardInfo(blackJackGame.getDealer());
        OutputView.printPlayersCardInfo(blackJackGame.getGamblers());
    }

    private static void giveEachGamblerCard(BlackJackGame blackJackGame, Gamblers gamblers) {
        for (Gambler gambler : gamblers.gamblers()) {
            giveGamblerCard(blackJackGame, gambler);
        }
    }

    private static void giveGamblerCard(BlackJackGame blackJackGame, Gambler gambler) {
        if (gambler.isBlackJack()) {
            OutputView.printBlackJack(gambler);
            return;
        }
        while (isAbleToDraw(gambler)) {
            drawCard(blackJackGame, gambler);
        }
        if (gambler.isTwentyOne()) {
            OutputView.printTwentyOne();
        }
    }

    private static boolean isAbleToDraw(Gambler gambler) {
        return !gambler.isBust()
                && !gambler.isTwentyOne()
                && decideDecision(gambler.name()) == YES;
    }

    private static YesOrNo decideDecision(final String playerName) {
        try {
            return of(InputView.askDrawOrNot(playerName));
        } catch (Exception e) {
            OutputView.printError(e);
            return decideDecision(playerName);
        }
    }

    private static void drawCard(BlackJackGame blackJackGame, Gambler gambler) {
        blackJackGame.giveGamblerCard(gambler);
        if (gambler.isBust()) {
            OutputView.printBust();
            return;
        }
        OutputView.printPlayerCardInfo(gambler);
    }

    private static void giveDealerCards(BlackJackGame blackJackGame) {
        while (blackJackGame.ableToDraw()) {
            OutputView.printGiveDealer();
            blackJackGame.giveDealerCard();
        }
    }
}
