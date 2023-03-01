package deck;

import card.Card;
import card.CardShape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.groupingBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("CardDeck 은")
class CardDeckTest {

    @Test
    void 카드를_종류별로_값_별로_한장씩_모두_가진_채로_생성한다() {
        // given
        CardDeck cardDeck = CardDeck.shuffledFullCardDeck();

        // when
        final Map<CardShape, List<Card>> result = cardDeck.cards()
                .stream()
                .collect(groupingBy(Card::cardShape));

        // then
        for (final CardShape cardShape : result.keySet()) {
            final Set<Card> cards = new HashSet<>(result.get(cardShape));

            assertEquals(cards.size(), 13);
        }

        assertEquals(result.size(), 4);
    }
}