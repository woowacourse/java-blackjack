package blackjack.controller;

import blackjack.domain.YesOrNo;
import blackjack.domain.game.BlackJackGame;
import blackjack.domain.game.Result;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import static blackjack.domain.YesOrNo.*;

public class BlackJackController {
    static final BlackJackGame blackJackGame = new BlackJackGame(); // 요건 대화 필요

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        Players players = createPlayers();
        blackJackGame.initPlayerCards(players);
        OutputView.printPlayersCardInfo(players);

        giveEachGamblerCard(players);
        giveDealerCards();
        OutputView.printNewLine();

        Result result = blackJackGame.getResult(players);
        OutputView.printResult(result);
    }

    private static Players createPlayers() {
        try {
            return blackJackGame.createPlayers(InputView.askPlayerNames());
        } catch (Exception e) {
            OutputView.printError(e);
            return createPlayers();
        }
    }

    private static void giveEachGamblerCard(final Players players) {
        for (Player player : players) {
            giveGamblerCard(player);
        }
    }

    private static void giveGamblerCard(final Player player) {
        if (player instanceof Dealer) {
            return;
        }
        while (decideDecision(player) == YES) {
            blackJackGame.giveCard(player);
            OutputView.printPlayerCardInfo(player);
        }
    }

    private static YesOrNo decideDecision(final Player player) {
        try {
            return of(InputView.askDrawOrNot(player));
        } catch (Exception e) {
            OutputView.printError(e);
            return decideDecision(player);
        }
    }

    private static void giveDealerCards() {
        while (blackJackGame.ableToDraw()) {
            OutputView.printGiveDealer();
            blackJackGame.giveDealerCard();
        }
    }
}
