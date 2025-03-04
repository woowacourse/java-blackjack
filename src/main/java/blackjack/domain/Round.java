package blackjack.domain;

import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.List;

public class Round {
    private final CardDeck cardDeck;
    private final List<Player> players; //

    public Round(CardDeck cardDeck) {
        this.cardDeck = cardDeck;
        this.players = new ArrayList<>();
    }

    public void register(Player player) {
        players.add(player);
    }

    public void distributeCards(String playerName, int cardCount) {
        Player foundPlayer = players.stream()
                .filter(player -> player.isNameEquals(playerName))
                .findAny()
                .orElseThrow();
        for (int i = 0; i < cardCount; i++) {
            Card card = cardDeck.getCard();
            foundPlayer.addCard(card);
        }
    }
}