package blackjack.model.carddeck;

import blackjack.common.error.ErrorCode;
import blackjack.model.card.Card;
import blackjack.model.card.Rank;
import blackjack.model.card.Suit;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CardDeck {

    private final List<Card> cards;
    private final PickStrategy pickStrategy;

    public CardDeck(List<Card> cards, PickStrategy pickStrategy) {
        validateCards(cards);
        validatePickStrategy(pickStrategy);

        this.cards = cards;
        this.pickStrategy = pickStrategy;
    }

    private void validateCards(List<Card> cards) {
        if (cards == null || cards.isEmpty()) {
            throw new IllegalStateException(ErrorCode.NULL_OR_EMPTY_CARDS.getMessage());
        }
    }

    private void validatePickStrategy(PickStrategy pickStrategy) {
        if (pickStrategy == null) {
            throw new IllegalArgumentException(ErrorCode.NULL_PICK_STRATEGY.getMessage());
        }
    }

    public static CardDeck of(PickStrategy pickStrategy) {
        List<Card> cards = Arrays.stream(Suit.values())
                .flatMap(suit -> Arrays.stream(Rank.values())
                        .map(rank -> Card.of(rank, suit)))
                .collect(Collectors.toList());

        return new CardDeck(cards, pickStrategy);
    }

    public Card pick() {
        validateCanPick();
        return pickStrategy.pick(cards);
    }

    private void validateCanPick() {
        if (cards.isEmpty()) {
            throw new IllegalStateException(ErrorCode.EMPTY_CARD_DECK.getMessage());
        }
    }
}
