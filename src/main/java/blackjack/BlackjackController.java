package blackjack;

import blackjack.card.domain.CardBundle;
import blackjack.card.domain.CardDeck;
import blackjack.player.domain.Dealer;
import blackjack.player.domain.Player;
import blackjack.player.domain.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackjackController {
    private final CardDeck cardDeck;
    private final InputView inputView;

    public BlackjackController(CardDeck cardDeck, InputView inputView) {
        this.cardDeck = cardDeck;
        this.inputView = inputView;
    }

    public void run() {
        Player dealer = new Dealer(new CardBundle());
        Players players = new Players(makePlayers(dealer));

        drawStartingCards(players);
        if (dealer.isNotBlackjack()) {
            drawGambler(players);
            drawDealer(dealer);
        }

        OutputView.showReports(players);
    }

    private List<Player> makePlayers(Player dealer) {
        List<Player> players = inputView.inputPlayNames()
                .toPlayers();
        players.add(dealer);
        return players;
    }

    private void drawStartingCards(Players players) {
        players.drawStartingCard(cardDeck);
        OutputView.showCards(players);
    }

    private void drawGambler(Players players) {
        for (Player player : players.findGamblers()) {
            drawEachGambler(player);
        }
    }

    private void drawEachGambler(Player gambler) {
        while (gambler.isDrawable() && inputView.inputDrawRequest(gambler).isDraw()) {
            gambler.addCard(cardDeck.draw());
            OutputView.showCardInfo(gambler);
        }
    }

    private void drawDealer(Player dealer) {
        while (dealer.isDrawable()) {
            dealer.addCard(cardDeck.draw());
            OutputView.showDealerDrawMessage();
        }
    }
}
