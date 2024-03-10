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
        final Players players = joinPlayer();

        processGame(players);

        checkGameResult(players);
    }

    private Players joinPlayer() {
        final Names names = Names.from(InputView.inputPlayerNames());
        final Players players = blackjack.acceptPlayers(names);
        PlayerView.printPlayers(players.dealer(), players.gamePlayers());
        return players;
    }

    private void processGame(final Players players) {
        players.gamePlayers()
               .forEach(gamePlayer -> processGamePlayer(blackjack, gamePlayer));
        processDealer(blackjack, players.dealer());
        PlayerView.printPlayersWithScore(players);
    }

    private void checkGameResult(final Players players) {
        final Result result = blackjack.checkPlayersResult(players.dealer(), players.gamePlayers());
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
