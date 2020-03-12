package blackjack.card.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CardDeckTest {

    @DisplayName("CardDeck 에 더 이상 없을때 뽑으려하면 Exception 발생")
    @Test
    void drawCard() {
        CardDeck cardDeck = CardDeck.getInstance(new CardCreateStrategy() {
            @Override
            public List<Card> getCards() {
                return Collections.emptyList();
            }
        });

        assertThatThrownBy(cardDeck::drawCard)
                .isInstanceOf(NoSuchElementException.class);
    }


}