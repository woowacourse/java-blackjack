package blackjack.controller;

import blackjack.domain.YesOrNo;
import blackjack.domain.game.BlackJackGame;
import blackjack.domain.game.Result;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackController {
    static final BlackJackGame blackJackGame = new BlackJackGame(); // 요건 대화 필요

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        Players players = blackJackGame.createPlayers(InputView.askPlayerNames());
        blackJackGame.initPlayerCards(players);
        OutputView.printPlayers(players);

        for (Player player : players) {
            giveGamblerCard(player);
        }

        giveDealerCards();
        OutputView.printMessage("");
        Result result = blackJackGame.getResult(players);
        OutputView.printResult(result);
    }

    private static void giveGamblerCard(final Player player) {
        if (!(player instanceof Gambler)) {
            return;
        }

        while (YesOrNo.of(InputView.askDrawOrNot(player)) == YesOrNo.YES ) {
            blackJackGame.giveCard(player);
            OutputView.printPlayer(player);
        }
    }

    private static void giveDealerCards(){
        while(blackJackGame.ableToDraw()){
            OutputView.printGiveDealer();
            blackJackGame.giveDealerCard();
        }
    }
}
