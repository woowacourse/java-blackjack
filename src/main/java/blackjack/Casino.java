package blackjack;

import blackjack.domain.card.Card;
import blackjack.domain.common.Names;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.GamePlayer;
import blackjack.domain.player.Players;
import blackjack.domain.result.Result;
import blackjack.view.BlackjackCommand;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Casino {
    public void run() {
        Blackjack blackjack = new Blackjack();

        Names names = Names.from(InputView.inputPlayerNames());
        Players players = blackjack.acceptPlayers(names);

        OutputView.printPlayers(players);

        players.getGamePlayers()
               .forEach(gamePlayer -> processGamePlayer(blackjack, gamePlayer));

        processDealer(blackjack, players.getDealer());
        OutputView.printPlayersWithScore(players);

        Result result = players.getDealer()
                               .checkResult(players.getGamePlayers());
        OutputView.printResult(result);
    }

    private void processDealer(Blackjack blackjack, Dealer dealer) {
        if (dealer.isReceivable()) {
            //TODO: OutputView로 분리해야 함
            System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
            Card card = blackjack.draw();
            dealer.drawCard(card);
            return;
        }
        System.out.println("딜러는 17이상라 카드를 받지 않았습니다.");
    }

    private void processGamePlayer(Blackjack blackjack, GamePlayer gamePlayer) {
        BlackjackCommand command = InputView.inputBlackjackCommand(gamePlayer.getName());
        while (command.isHit() && gamePlayer.isReceivable()) {
            Card card = blackjack.draw();
            gamePlayer.drawCard(card);
            OutputView.printGamePlayer(gamePlayer);
            //TODO : 플레이어가 버스트여도 끝내야함

            command = InputView.inputBlackjackCommand(gamePlayer.getName());
        }
    }

}
