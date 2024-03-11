package cardGame;

import card.Card;
import card.CardDeck;
import java.util.function.BiConsumer;
import player.Player;
import player.dto.SinglePlayerStatusDto;

public class SingleMatch {

    private final Player player;

    public SingleMatch(Player player) {
        this.player = player;
    }

    public boolean isBustPlayer() {
        return player.isBust();
    }

    public Player getPlayer() {
        return player;
    }

    public void getMoreCard(Card card) {
        player.receiveCard(card);
    }

    public SinglePlayerStatusDto play(BiConsumer<SingleMatch, CardDeck> playMatch, CardDeck cardDeck) {
        playMatch.accept(this, cardDeck);
        return new SinglePlayerStatusDto(player.getName(), player.getCards());
    }
}
