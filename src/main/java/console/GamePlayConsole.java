package console;

import model.card.Deck;
import model.participant.Player;
import model.participant.Players;
import view.GamePlayView;

public final class GamePlayConsole extends Console {
    private final GamePlayView gamePlayView = new GamePlayView();

    public void dealAllPlayerCards(final Players players, final Deck deck) {
        for (Player player : players.getPlayers()) {
            dealPlayerCards(player, deck);
        }
    }

    private void dealPlayerCards(final Player player, final Deck deck) {
        while (player.isRunning()) {
            display(gamePlayView.getPlayerDealGuide(player));
            String answer = readLine();
            if (answer.equalsIgnoreCase("y")) {
                player.receiveCard(deck.draw());
            } else if (answer.equalsIgnoreCase("n")) {
                player.stay();
            } else {
                throw new IllegalArgumentException("잘못된 입력입니다.");
            }
            display(gamePlayView.getParticipantCards(player.getName(), player.getCards()));
            display(gamePlayView.getEmptyLine());
        }
    }
}
