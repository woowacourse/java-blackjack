package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.common.ErrorMessage;
import blackjack.factory.SingDeckGenerator;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class DeckTest {

    @DisplayName("생성되는 시점에 카드의 개수는 52장이어야 한다.")
    @Test
    void test6() {
        // given
        SingDeckGenerator singDeckGenerator = new SingDeckGenerator();
        List<Card> cards = singDeckGenerator.generate();

        // when & then
        assertAll(() -> assertThat(cards).hasSize(52),
                () -> assertThatCode(() -> new Deck(cards)).doesNotThrowAnyException());
    }

    @DisplayName("카드는 섞일 수 있다.")
    @Test
    void test3() {
        // given
        SingDeckGenerator singDeckGenerator = new SingDeckGenerator();
        List<Card> cards = singDeckGenerator.generate();
        Deck deck = new Deck(cards);

        // then
        assertThat(deck.getCards()).containsExactlyInAnyOrderElementsOf(cards);
    }

    @DisplayName("카드뽑을 때")
    @Nested
    class DrawCard {
        @DisplayName("시작 카드는 2개를 뽑는다")
        @Test
        void test4() {
            // given
            SingDeckGenerator singDeckGenerator = new SingDeckGenerator();
            List<Card> cards = singDeckGenerator.generate();
            Deck deck = new Deck(cards);
            int expect = 2;

            // when
            List<Card> takeCards = deck.takeStartCards();

            // then
            assertThat(takeCards).hasSize(expect);
        }

        @DisplayName("카드가 소진되면 예외를 던진다.")
        @Test
        void test5() {
            Deck deck = new Deck(List.of());

            assertThatThrownBy(deck::takeStartCards).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ErrorMessage.EMPTY_DECK_SIZE.getMessage());
        }
    }
}
