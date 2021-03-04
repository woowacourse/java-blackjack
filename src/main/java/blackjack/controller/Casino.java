package blackjack.controller;

import blackjack.domain.Game;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class Casino {
    public Casino() {
    }

    public void start() {
        Game game = Game.of(InputView.inputPlayerNames());
        Dealer dealer = game.getDealer();
        List<Player> players = game.getPlayers();

        game.setUpTwoCards();
        OutputView.printSetup(dealer, players);

        playerTurn(game);
        doDealerTurn(game);

        OutputView.printFinalCardInfo(dealer, players);
        game.fightPlayers();
        OutputView.printWinOrLoseResult(dealer, players);
    }

    private void playerTurn(Game game) {
        for (Player player : game.getPlayers()) {
            doPlayerTurn(player, game);
        }
    }

    //TODO:: 블랙잭이나 버스트일때 질문하지 않는 로직 추가.(메세지도 출력)
    private void doPlayerTurn(Player player, Game game) {
        while (InputView.inputYesOrNo(player)) {
            game.giveCard(player);
            OutputView.printCardInfo(player);
            OutputView.printMessage("");
        }
    }

    //TODO:: 0개를 뽑을 때, 메세지 추가가
    private void doDealerTurn(Game game) {
        int dealerDrawCount = game.playDealerTurn();
        OutputView.printDealerDrawMessage(dealerDrawCount);
    }
}
