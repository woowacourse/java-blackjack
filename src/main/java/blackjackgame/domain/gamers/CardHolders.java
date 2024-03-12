package blackjackgame.domain.gamers;

import blackjackgame.domain.blackjack.PlayerRandomCardDrawStrategy;
import blackjackgame.domain.card.Deck;
import java.util.List;

public class CardHolders {
    private final List<CardHolder> players;

    public CardHolders(List<CardHolder> players) {
        this.players = players;
    }

    public void drawNTimes(Deck deck, int execution_count) {
        players.forEach(player -> player.draw(deck, new PlayerRandomCardDrawStrategy(player), execution_count));
    }

    public List<String> getRawPlayerNames() {
        return players.stream()
                .map(CardHolder::getRawName)
                .toList();
    }

    public List<CardHolder> getCardHolders() {
        return players;
    }
}
