package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static domain.card.CardValue.values;
import static java.util.stream.Collectors.groupingBy;
import static org.assertj.core.api.Assertions.assertThat;
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

            assertEquals(cards.size(), values().length);
        }

        assertEquals(result.size(), CardShape.values().length);
    }

    @Test
    void 카드를_뽑을_수_있다() {
        // given
        final CardDeck cardDeck = CardDeck.shuffledFullCardDeck();
        final int before = cardDeck.cards().size();

        // when
        final Card draw = cardDeck.draw();

        // then
        assertThat(draw).isNotNull();
        assertThat(cardDeck.cards().size()).isEqualTo(before - 1);
    }
}
