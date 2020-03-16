package blackjack;

import blackjack.card.domain.CardBundle;
import blackjack.card.domain.Drawable;
import blackjack.player.domain.Dealer;
import blackjack.player.domain.Player;
import blackjack.player.domain.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackjackController {
    private final Drawable drawable;
    private final InputView inputView;

    public BlackjackController(Drawable drawable, InputView inputView) {
        this.drawable = drawable;
        this.inputView = inputView;
    }

    public void run() {
        Player dealer = new Dealer(CardBundle.emptyBundle());
        Players players = new Players(makePlayers(dealer));

        drawStartingCards(players);
        drawGambler(players);
        drawDealer(dealer);

        OutputView.showReports(players);
    }

    private List<Player> makePlayers(Player dealer) {
        List<Player> players = inputView.inputPlayNames()
                .toPlayers();
        players.add(dealer);
        return players;
    }

    private void drawStartingCards(Players players) {
        players.drawStartingCard(drawable);
        OutputView.showCards(players);
    }

    private void drawGambler(Players players) {
        for (Player player : players.findGamblers()) {
            drawEachGambler(player);
        }
    }

    private void drawEachGambler(Player gambler) {
        while (gambler.isDrawable() && inputView.inputDrawRequest(gambler).isDraw()) {
            gambler.drawCard(drawable);
            OutputView.showCardInfo(gambler);
        }
    }

    private void drawDealer(Player dealer) {
        while (dealer.isDrawable()) {
            dealer.drawCard(drawable);
            OutputView.showDealerDrawMessage();
        }
    }
}
