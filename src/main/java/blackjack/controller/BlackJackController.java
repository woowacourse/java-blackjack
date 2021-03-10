package blackjack.controller;

import blackjack.domain.YesOrNo;
import blackjack.domain.game.BlackJackGame;
import blackjack.domain.game.Result;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static blackjack.domain.YesOrNo.YES;
import static blackjack.domain.YesOrNo.of;

public class BlackJackController {


    public static void main(String[] args) {
        run();
    }

    public static void run() {
        Players gamblers = createPlayers(InputView.askPlayerNames());
        BlackJackGame blackJackGame = new BlackJackGame(gamblers);

        playFirstTurn(blackJackGame);

        giveEachGamblerCard(blackJackGame, gamblers);
        giveDealerCards(blackJackGame);

        Result result = blackJackGame.calculateResult();
        OutputView.printResult(result);
    }

    private static Players createPlayers(final String allName) {
        List<String> names = Arrays.asList(allName.split(","));
        List<Player> players;
        try {
            players = names.stream()
                    .map(Gambler::new)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            OutputView.printError(e);
            return createPlayers(InputView.askPlayerNames());
        }
        return new Players(players);
    }

    private static void playFirstTurn(BlackJackGame blackJackGame) {
        blackJackGame.initDealerCards();
        blackJackGame.initPlayerCards();
        OutputView.printDealerCardInfo(blackJackGame.getDealer());
        OutputView.printPlayersCardInfo(blackJackGame.getGamblers());
    }

    private static void giveEachGamblerCard(BlackJackGame blackJackGame, Players gamblers) {
        for (Player player : gamblers.players()) {
            giveGamblerCard(blackJackGame, player);
        }
    }

    private static void giveGamblerCard(BlackJackGame blackJackGame, Player gambler) {
        if (gambler.isBlackJack()) {
            OutputView.printMessage(gambler.name() + "님 BLACK JACK!");
            return;
        }
        while (isAbleToDraw(gambler)) {
            drawCard(blackJackGame, gambler);
        }
        if (gambler.isTwentyOne()) {
            OutputView.printMessage("21이 되었습니다.");
        }
    }

    private static boolean isAbleToDraw(Player gambler) {
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

    private static void drawCard(BlackJackGame blackJackGame, Player gambler) {
        blackJackGame.giveGamblerCard(gambler);
        if (gambler.isBust()) {
            OutputView.printMessage("카드의 합이 21이 넘어 bust 되었습니다.");
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
