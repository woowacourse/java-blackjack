package blackjack.domain.fixture;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.CardType;
import java.util.Arrays;
import java.util.List;

public class CardFixture {
    public static List<Card> createCards(final CardType... types) {
        return Arrays.stream(types)
                .map(CardFixture::createCard)
                .toList();
    }

    private static Card createCard(final CardType type) {
        return new Card(CardShape.DIAMOND, type);
    }
}
