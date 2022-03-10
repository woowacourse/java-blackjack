package blackjack.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;
import java.util.stream.Collectors;

public class CardDeck {
    private final Stack<PlayingCard> playingCards;

    private CardDeck() {
        Stack<PlayingCard> playingCards = Arrays.stream(Suit.values())
            .flatMap(suit -> Arrays.stream(Denomination.values())
                .map(denomination -> PlayingCard.of(suit, denomination)))
            .collect(Collectors.toCollection(Stack::new));

        Collections.shuffle(playingCards);
        this.playingCards = playingCards;
    }

    public static CardDeck getInstance() {
        return new CardDeck();
    }

    public PlayingCard getCard() {
        return playingCards.pop();
    }

    public void drawTo(final Player player) {
        player.addCard(playingCards.pop());
    }
}
