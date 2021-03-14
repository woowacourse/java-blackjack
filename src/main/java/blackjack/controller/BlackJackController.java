package blackjack.controller;

import blackjack.domain.YesOrNo;
import blackjack.domain.game.BlackJackGame;
import blackjack.domain.game.Result;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Gamblers;
import blackjack.domain.player.Money;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BlackJackController {

    private static final BlackJackGame blackJackGame = new BlackJackGame();

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        Gamblers gamblers = createGamblers();
        blackJackGame.addGamblers(gamblers);

        playFirstTurn();

        giveEachGamblerCard(gamblers);
        giveDealerCards();

        Result result = blackJackGame.calculateResult();
        result.calculateProfit();
        OutputView.printResult(result);
    }

    private static Gamblers createGamblers() {
        try {
            return new Gamblers(collectGamblers());
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            return createGamblers();
        }
    }

    private static List<Gambler> collectGamblers() {
        String gamblersName = InputView.askGamblersName();
        List<String> names = Arrays.asList(gamblersName.split(","));
        List<Gambler> gamblers = new ArrayList<>();
        names.stream()
                .map(String::trim)
                .forEach(name -> {
                    Money money = createMoney(name);
                    gamblers.add(new Gambler(name, money));
                });
        return gamblers;
    }

    private static Money createMoney(final String name) {
        try {
            return new Money(InputView.askPlayerBattingMoney(name));
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            return createMoney(name);
        }
    }

    private static void playFirstTurn() {
        blackJackGame.initDealerCards();
        blackJackGame.initGamblersCards();
        OutputView.printDealerCardInfo(blackJackGame.getDealer());
        OutputView.printPlayersCardInfo(blackJackGame.Gamblers());
    }

    private static void giveEachGamblerCard(final Gamblers gamblers) {
        for (Gambler gambler : gamblers.getGamblers()) {
            giveGamblerCard(gambler);
        }
    }

    private static void giveGamblerCard(final Gambler gambler) {
        if (gambler.isBlackJack()) {
            OutputView.printBlackJack(gambler);
            return;
        }
        while (isAbleToDraw(gambler)) {
            drawGamblerCard(gambler);
        }
        if (gambler.isTwentyOne()) {
            OutputView.printTwentyOne();
        }
    }

    private static boolean isAbleToDraw(final Gambler gambler) {
        return gambler.isHit() && isYesDecision(gambler.name());
    }

    private static boolean isYesDecision(final String playerName) {
        try {
            YesOrNo decision = YesOrNo.of(InputView.askDrawOrNot(playerName));
            return decision.isYes();
        } catch (Exception e) {
            OutputView.printError(e);
            return isYesDecision(playerName);
        }
    }

    private static void drawGamblerCard(final Gambler gambler) {
        blackJackGame.giveGamblerCard(gambler);
        if (gambler.isBust()) {
            OutputView.printBust();
            return;
        }
        OutputView.printPlayerCardInfo(gambler);
    }

    private static void giveDealerCards() {
        while (blackJackGame.ableToDraw()) {
            OutputView.printGiveDealer();
            blackJackGame.giveDealerCard();
        }
    }
}
