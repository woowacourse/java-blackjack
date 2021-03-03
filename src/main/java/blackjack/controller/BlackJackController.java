package blackjack.controller;

import blackjack.domain.YesOrNo;
import blackjack.domain.game.BlackJackGame;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import jdk.internal.util.xml.impl.Input;

public class BlackJackController {
    static final BlackJackGame blackJackGame = new BlackJackGame(); // 요건 대화 필요

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        Players players = blackJackGame.createPlayers(InputView.askPlayerNames());
        blackJackGame.initPlayerCards(players);
        OutputView.printPlayers(players);

        giveGamblerCard(players);
        giveDealerCard(players);
    }

    private static void giveGamblerCard(Players players) {
        players.players().stream()
                .filter(player -> player instanceof Gambler)
                .peek(player -> blackJackGame.giveCard(player, InputView.askDrawOrNot(player)));
    }

    private static void giveDealerCard(Players players) {
        players.players().stream()
                .filter(player -> player instanceof Dealer)
                .peek(player -> blackJackGame.giveCard(player))
                .findFirst();
    }
}

/*
딜러와 pobi, jason에게 2장의 나누었습니다.
딜러: 3다이아몬드
pobi카드: 2하트, 8스페이드
jason카드: 7클로버, K스페이드

pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
y
pobi카드: 2하트, 8스페이드, A클로버
pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
n
jason은 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
n
jason카드: 7클로버, K스페이드

딜러는 16이하라 한장의 카드를 더 받았습니다.
 */
