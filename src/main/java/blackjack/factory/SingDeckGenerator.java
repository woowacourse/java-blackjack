package blackjack.factory;

import blackjack.common.ErrorMessage;
import blackjack.domain.Card;
import blackjack.domain.CardRank;
import blackjack.domain.CardSuit;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class SingDeckGenerator implements DeckGenerator {

    private static final int DECK_SIZE = 52;

    @Override
    public List<Card> generate() {
        List<Card> deck = Arrays.stream(CardSuit.values())
                .flatMap(suit -> Arrays.stream(CardRank.values())
                        .map(rank -> new Card(suit, rank)))
                .toList();
        validate(deck);

        return deck;
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
