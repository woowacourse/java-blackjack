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
        if (cards.isEmpty()) {
            throw new IllegalStateException("cards가 null입니다.");
        }
        this.cards = cards;
        this.pickStrategy = pickStrategy;
    }

    public static CardDeck of(PickStrategy pickStrategy) {
        List<Card> cards = new ArrayList<>();

        Arrays.stream(Suit.values())
                .forEach(suit -> Arrays.stream(Rank.values())
                        .forEach(rank -> cards.add(Card.openedCard(rank, suit))));

        if (cards.size() != CARD_DECK_SIZE) {
            throw new IllegalStateException("덱이 잘 못 생성됐습니다.");
        }

        return new CardDeck(cards, pickStrategy);
    }

    // 카드 덱에서 1장 가져온다.
    public Card pick() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("카드 덱에 카드의 개수가 부족합니다.");
        }

        return pickStrategy.pick(cards);
    }
}
