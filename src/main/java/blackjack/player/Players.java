package blackjack.player;

import blackjack.GameReport;
import blackjack.player.card.CardFactory;

import java.util.List;

public class Players {
    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public void initCards(CardFactory cardFactory) {
        for (int i = 0; i < 2; i++) {
            players.forEach(player -> player.addCard(cardFactory.drawCard()));
        }
    }

    public List<GameReport> getReports() {

        return null;
    }
}
