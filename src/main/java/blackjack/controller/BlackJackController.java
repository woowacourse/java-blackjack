package blackjack.controller;

import blackjack.domain.YesOrNo;
import blackjack.domain.game.BlackJackGame;
import blackjack.domain.game.Result;
import blackjack.domain.player.BettingMoney;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Gamblers;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackController {

    private final BlackJackGame blackJackGame = new BlackJackGame();

    public void run() {
        Gamblers gamblers = createGamblers();
        blackJackGame.addGamblers(gamblers);

        playFirstTurn();

        giveEachGamblerCard(gamblers);
        giveDealerCards();

        Result result = blackJackGame.calculateResult();
        result.calculateProfit();
        OutputView.printResult(result);
    }

    private Gamblers createGamblers() {
        try {
            return new Gamblers(collectGamblers());
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            return createGamblers();
        }
    }

    private List<Gambler> collectGamblers() {
        String gamblersName = InputView.askGamblersName();
        List<String> names = Arrays.asList(gamblersName.split(","));
        return names.stream()
                .map(name -> new Gambler(name, createBettingMoney(name)))
                .collect(Collectors.toList());
    }

    private BettingMoney createBettingMoney(final String name) {
        try {
            return new BettingMoney(InputView.askPlayerBattingMoney(name));
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            return createBettingMoney(name);
        }
    }

    private void playFirstTurn() {
        blackJackGame.initDealerCards();
        blackJackGame.initGamblersCards();
        OutputView.printDealerCardInfo(blackJackGame.getDealer());
        OutputView.printPlayersCardInfo(blackJackGame.Gamblers());
    }

    private void giveEachGamblerCard(final Gamblers gamblers) {
        for (Gambler gambler : gamblers.getGamblers()) {
            giveGamblerCard(gambler);
        }
    }

    private void giveGamblerCard(final Gambler gambler) {
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

    private boolean isAbleToDraw(final Gambler gambler) {
        return gambler.isHit() && isYesDecision(gambler.name());
    }

    private boolean isYesDecision(final String playerName) {
        try {
            YesOrNo decision = YesOrNo.of(InputView.askDrawOrNot(playerName));
            return decision.isYes();
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            return isYesDecision(playerName);
        }
    }

    private void drawGamblerCard(final Gambler gambler) {
        blackJackGame.giveGamblerCard(gambler);
        if (gambler.isBust()) {
            OutputView.printBust();
            return;
        }
        OutputView.printPlayerCardInfo(gambler);
    }

    private void giveDealerCards() {
        while (blackJackGame.ableToDraw()) {
            OutputView.printGiveDealer();
            blackJackGame.giveDealerCard();
        }
    }
}
