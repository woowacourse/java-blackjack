package blackjack.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;
import java.util.stream.Collectors;

public class CardDeck {

    private final Stack<PlayingCard> playingCards;

    public CardDeck() {
        Stack<PlayingCard> playingCards = createPlayingCards();
        Collections.shuffle(playingCards);
        this.playingCards = playingCards;
    }

    private Stack<PlayingCard> createPlayingCards() {
        return Arrays.stream(Suit.values())
            .filter(suit -> !suit.equals(Suit.BURST))
            .flatMap(suit -> Arrays.stream(Denomination.values())
                .filter(denomination -> !denomination.equals(Denomination.BURST))
                .map(denomination -> PlayingCard.of(suit, denomination)))
            .collect(Collectors.toCollection(Stack::new));
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
