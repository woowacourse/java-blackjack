package domain.member;

import domain.card.Deck;
import domain.card.Card;
import java.util.Collections;
import java.util.List;

public class Players {

    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public void initDraw(Deck deck) {
        players.forEach(member -> member.initDraw(deck));
    }

    public void draw(Player player, Card card) {
        player.draw(card);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
