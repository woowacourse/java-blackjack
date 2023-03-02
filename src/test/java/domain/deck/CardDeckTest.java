package domain.deck;

import domain.card.Card;
import domain.card.CardShape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static domain.card.CardShape.CLOVER;
import static domain.card.CardShape.DIAMOND;
import static domain.card.CardShape.HEART;
import static domain.card.CardShape.SPADE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("CardDeck 은")
class CardDeckTest {

    @Test
    void 카드를_종류별로_값_별로_한장씩_모두_가진_채로_생성한다() {
        // given

        final CardDeck cardDeck = CardDeck.shuffledFullCardDeck();
        final Map<CardShape, List<Card>> numberOfEachCardShape = new HashMap<>();

        // when

        for (int count = 0; count < 52; count++) {
            final Card card = cardDeck.draw();
            final CardShape cardShape = card.cardShape();

            numberOfEachCardShape.computeIfAbsent(cardShape, key -> new ArrayList<>());

            numberOfEachCardShape.get(cardShape).add(card);
        }

        // then
        assertAll(
                () -> assertEquals(4, numberOfEachCardShape.size()),
                () -> assertEquals(13, numberOfEachCardShape.get(HEART).size()),
                () -> assertEquals(13, numberOfEachCardShape.get(CLOVER).size()),
                () -> assertEquals(13, numberOfEachCardShape.get(DIAMOND).size()),
                () -> assertEquals(13, numberOfEachCardShape.get(SPADE).size())
        );
    }

    @Test
    void 카드를_한_장씩_꺼낼_수_있다() {
        // given
        final CardDeck cardDeck = CardDeck.shuffledFullCardDeck();
        int maxDrawCount = 52;

        // when
        while (maxDrawCount-- > 0) {
            cardDeck.draw();
        }

        // then
        assertThatThrownBy(cardDeck::draw).isInstanceOf(EmptyStackException.class);
    }
}
