package blackjackgame.domain.blackjack;

import blackjackgame.domain.card.Deck;
import java.util.List;

public class CardHolderGamers {
    private final List<CardHolderGamer> players;

    public CardHolderGamers(List<CardHolderGamer> players) {
        this.players = players;
    }

    public void drawNTimes(Deck deck, int execution_count) {
        players.forEach(player -> player.draw(deck, new PlayerRandomCardDrawStrategy(player), execution_count));
    }

    public List<String> getRawPlayerNames() {
        return players.stream()
                .map(CardHolderGamer::getRawName)
                .toList();
    }

    public List<CardHolderGamer> getPlayers() {
        return players;
    }
}
