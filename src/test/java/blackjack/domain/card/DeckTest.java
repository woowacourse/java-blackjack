package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    @Test
    @DisplayName("셔플된 덱을 생성한다")
    void createShuffledDeck() {
        // Given

        // When
        Assertions.assertThatCode(Deck::shuffled)
                .doesNotThrowAnyException();
    }

    @DisplayName("카드를 순서대로 뽑는다.")
    @Test
    void drawInitialCards() {
        // given
        final Deck deck = new Deck(() -> new ArrayDeque<>(Arrays.asList(
                new Card(Shape.CLOB, CardScore.A),
                new Card(Shape.DIAMOND, CardScore.SIX))));

        // when
        final Hand hand = deck.drawInitialCards(2);

        // then
        assertAll(
                () -> assertThat(hand.getFirstCard()).isEqualTo(new Card(Shape.CLOB, CardScore.A)),
                () -> assertThat(hand.subHand(1, hand.getSize())).isEqualTo(
                        new Hand(List.of(new Card(Shape.DIAMOND, CardScore.SIX))))
        );
    }
}
