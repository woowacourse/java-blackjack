package blackjack.model.cardDeck;

import blackjack.model.card.Card;
import blackjack.model.card.Rank;
import blackjack.model.card.Suit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardDeck {

    private static final int CARD_DECK_SIZE = 52;

    private final List<Card> cards;
    private final PickStrategy pickStrategy;

    private CardDeck(List<Card> cards, PickStrategy pickStrategy) {
        validate(cards, pickStrategy);

        this.cards = cards;
        this.pickStrategy = pickStrategy;
    }

    private void validate(List<Card> cards, PickStrategy pickStrategy) {
        if (cards == null || cards.size() != CARD_DECK_SIZE) {
            throw new IllegalArgumentException("카드덱이 null이거나 카드 개수가"  + CARD_DECK_SIZE + "가 아닙니다.");
        }

        if (pickStrategy == null) {
            throw new IllegalArgumentException("pickStrategy가 null입니다.");
        }
    }

    public static CardDeck of(PickStrategy pickStrategy) {
        List<Card> cards = new ArrayList<>();

        Arrays.stream(Suit.values())
                .forEach(suit -> Arrays.stream(Rank.values())
                        .forEach(rank -> cards.add(Card.createOpenedCard(rank, suit))));

        return new CardDeck(cards, pickStrategy);
    }

    public Card pick() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("카드 덱에 카드의 개수가 부족합니다.");
        }

        return pickStrategy.pick(cards);
    }
}
