package blackjack.model.blackjack;

import blackjack.model.card.Card;
import blackjack.model.card.Rank;
import blackjack.model.card.Suit;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

final class ShuffledCardDispenser implements CardDispenser {

    private final Queue<Card> deck;

    ShuffledCardDispenser() {
        this.deck = new LinkedList<>(shuffledCards());
    }

    private List<Card> shuffledCards() {
        List<Card> cardPool = createCardPool();
        Collections.shuffle(cardPool);
        return cardPool;
    }

    private List<Card> createCardPool() {
        return Stream.of(Suit.values())
            .flatMap(
                suit -> Stream.of(Rank.values())
                    .map(rank -> new Card(rank, suit)))
            .collect(Collectors.toList());
    }

    @Override
    public Card issue() {
        if (deck.isEmpty()) {
            throw new IllegalStateException("남아있는 카드가 없습니다.");
        }
        return deck.remove();
    }
}
