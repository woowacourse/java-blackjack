package console;

import card.Deck;
import java.util.List;
import participant.Dealer;
import participant.Player;
import view.GamePlayView;

public final class GamePlayConsole extends Console {
    private final GamePlayView gamePlayView = new GamePlayView();

    public void drawAllPlayerCards(final List<Player> players, final Deck deck) {
        for (Player player : players) {
            display(gamePlayView.getEmptyLine());
            drawPlayerCards(player, deck);
        }
    }

    public void drawDealerCards(final Dealer dealer, final Deck deck) {
        display(gamePlayView.getEmptyLine());
        while (dealer.isRunning()) {
            display(gamePlayView.getDealerHitGuide());
            dealer.receiveCard(deck.draw());
        }
    }

    private void drawPlayerCards(final Player player, final Deck deck) {
        while (player.isRunning()) {
            display(gamePlayView.getPlayerDealGuide(player));
            drawCardByAnswer(player, Answer.of(readLine()), deck);
            display(gamePlayView.getParticipantCards(player.getName(), player.getCards()));
            display(gamePlayView.getEmptyLine());
        }
    }

    private void drawCardByAnswer(final Player player, final Answer answer, final Deck deck) {
        if (answer.isYes()) {
            player.receiveCard(deck.draw());
            return;
        }
        player.stay();
    }
}
