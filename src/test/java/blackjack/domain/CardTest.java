package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

public class CardTest {

    @DisplayName("카드 값에 따라 점수를 확인할 수 있다")
    @Nested
    class CardScoreTest {

        @DisplayName("숫자 카드는 카드의 숫자 값을 점수로 가진다")
        @ParameterizedTest
        @CsvSource({"TWO, 2", "TEN, 10"})
        void scoreTest_whenNumberCard(Card.Value value, int expected) {
            Card card = new Card(value, Card.Shape.CLOVER);

            assertAll(
                    () -> assertThat(card.getMinScore()).isEqualTo(expected),
                    () -> assertThat(card.getMaxScore()).isEqualTo(expected)
            );
        }

        @DisplayName("Jack, Queen, King 카드는 모두 10점을 가진다.")
        @ParameterizedTest
        @EnumSource(names = {"JACK", "QUEEN", "KING"})
        void scoreTest_whenJQK_Card(Card.Value value) {
            Card card = new Card(value, Card.Shape.HEART);

            assertAll(
                    () -> assertThat(card.getMinScore()).isEqualTo(10),
                    () -> assertThat(card.getMaxScore()).isEqualTo(10)
            );
        }

        @DisplayName("Ace 카드는 1, 또는 11점을 가진다.")
        @Test
        void scoreTest_whenAceCard() {
            Card card = new Card(Card.Value.ACE, Card.Shape.HEART);

            assertAll(
                    () -> assertThat(card.getMinScore()).isEqualTo(1),
                    () -> assertThat(card.getMaxScore()).isEqualTo(11)
            );
        }
    }

}
