import blackjack.domain.Blackjack;
import blackjack.domain.card.Card;
import blackjack.domain.common.Names;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.GamePlayer;
import blackjack.domain.player.Players;
import blackjack.domain.result.Result;
import blackjack.view.BlackjackCommand;
import blackjack.view.InputView;
import blackjack.view.PlayerView;
import blackjack.view.ResultView;

public class Casino {
    private final Blackjack blackjack;

    public Casino(final Blackjack blackjack) {
        this.blackjack = blackjack;
    }

    public void playBlackJack() {
        // 게임 참여 하는 Player 참여
        final Players players = joinPlayer();

        // 게임 진행 로직 , Players 진행
        processGame(players);

        // 결과
        checkGameResult(players);
    }

    private Players joinPlayer() {
        final Names names = Names.from(InputView.inputPlayerNames());
        final Players players = blackjack.acceptPlayers(names);
        PlayerView.printPlayers(players.getDealer(), players.getGamePlayers());
        return players;
    }

    private void processGame(final Players players) {
        players.getGamePlayers()
               .forEach(gamePlayer -> processGamePlayer(blackjack, gamePlayer));
        processDealer(blackjack, players.getDealer());
        PlayerView.printPlayersWithScore(players);
    }

    private void checkGameResult(final Players players) {
        final Result result = blackjack.checkPlayersResult(players.getDealer(), players.getGamePlayers());
        ResultView.printResult(result);
    }

    private void processDealer(final Blackjack blackjack, final Dealer dealer) {
        if (dealer.isReceivable()) {
            PlayerView.printDealerDrawMessage();
            final Card card = blackjack.draw();
            dealer.drawCard(card);
            return;
        }
        PlayerView.printDealerNotDrawMessage();
    }

    private void processGamePlayer(final Blackjack blackjack, final GamePlayer gamePlayer) {
        while (gamePlayer.isReceivable() && isHit(gamePlayer)) {
            final Card card = blackjack.draw();
            gamePlayer.drawCard(card);
            PlayerView.printGamePlayer(gamePlayer);
        }
    }

    private boolean isHit(final GamePlayer gamePlayer) {
        final BlackjackCommand command = InputView.inputBlackjackCommand(gamePlayer.getNameAsString());
        return command.isHit();
    }
}
