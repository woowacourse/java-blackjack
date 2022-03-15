package blackjack.domain.participant;

import blackjack.domain.card.Card;
import java.util.List;

public class Participants {

    private final List<Player> players;
    private final Dealer dealer;

    public Participants(List<Player> players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public void drawCard(Participant participant, Card card) {
        if (participant.getClass() == Player.class) {
            findPlayer((Player) participant).drawCard(card);
            return;
        }
        dealer.drawCard(card);
    }

    public Player findPlayer(Player player) {
        int index = players.indexOf(player);
        return players.get(index);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }
}
