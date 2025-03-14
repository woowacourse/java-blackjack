package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.ArrayDeque;
import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    @DisplayName("카드를 순서대로 뽑는다.")
    @Test
    void spreadCards() {
        // given
        final Deck deck = new Deck(() -> new ArrayDeque<>(Arrays.asList(
                new Card(Shape.CLOB, CardScore.A),
                new Card(Shape.DIAMOND, CardScore.SIX))));

        // when
        final Card firstCard = deck.spreadCards(1).getFirstCard();
        final Card secondCard = deck.spreadCards(1).getFirstCard();

        // then
        assertAll(
                () -> assertThat(firstCard).isEqualTo(new Card(Shape.CLOB, CardScore.A)),
                () -> assertThat(secondCard).isEqualTo(new Card(Shape.DIAMOND, CardScore.SIX))
        );
    }
}
