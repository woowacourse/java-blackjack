package blackjack.factory;

import blackjack.common.ErrorMessage;
import blackjack.domain.Card;
import blackjack.domain.CardRank;
import blackjack.domain.CardSuit;
import blackjack.domain.Deck;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class SingleDeckFactory implements DeckGenerator {

    private static final int DECK_SIZE = 52;

    @Override
    public Deck generate() {
        List<Card> cards = Arrays.stream(CardSuit.values())
                .flatMap(suit -> Arrays.stream(CardRank.values())
                        .map(rank -> new Card(suit, rank)))
                .toList();
        validate(cards);

        return Deck.shuffled(cards);
    }

    private void validate(List<Card> cards) {
        if (cards.size() != DECK_SIZE) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DECK_SIZE.getMessage());
        }

        if (new HashSet<>(cards).size() != DECK_SIZE) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATED_CARD_EXISTED.getMessage());
        }
    }
}
