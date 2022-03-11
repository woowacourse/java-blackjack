package blackjack.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;
import java.util.stream.Collectors;

public class CardDeck {
    private final Stack<PlayingCard> playingCards;

    public CardDeck() {
        Stack<PlayingCard> playingCards = Arrays.stream(Suit.values())
            .flatMap(suit -> Arrays.stream(Denomination.values())
                .map(denomination -> PlayingCard.of(suit, denomination)))
            .collect(Collectors.toCollection(Stack::new));

        Collections.shuffle(playingCards);
        this.playingCards = playingCards;
    }

    public PlayingCard getCard() {
        return playingCards.pop();
    }

    public void drawTo(Player player) {
        player.getCard(playingCards.pop());
    }

    public PlayingCard pop() {
        return playingCards.pop();
    }

    public PlayingCard justPeek() {
        return playingCards.peek();
    }
}
