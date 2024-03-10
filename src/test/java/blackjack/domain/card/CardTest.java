package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

public class CardTest {

    // TODO HandFixture 로 변경 (클래스 분리)
    public static final List<Card> CARDS_SCORE_4 = List.of(
            new Card(Value.TWO, Shape.HEART),
            new Card(Value.TWO, Shape.SPADE)
    );
    public static final List<Card> TWO_ACE = List.of(
            new Card(Value.ACE, Shape.HEART),
            new Card(Value.ACE, Shape.SPADE)
    );
    public static final List<Card> SCORE_13_WITH_ACE = List.of(
            new Card(Value.ACE, Shape.HEART),
            new Card(Value.KING, Shape.HEART),
            new Card(Value.TWO, Shape.HEART)
    );
    public static final List<Card> CARDS_SCORE_16 = List.of(
            new Card(Value.JACK, Shape.HEART),
            new Card(Value.SIX, Shape.HEART)
    );
    public static final List<Card> CARDS_SCORE_17 = List.of(
            new Card(Value.JACK, Shape.HEART),
            new Card(Value.SEVEN, Shape.HEART)
    );
    public static final List<Card> CARDS_SCORE_21 = List.of(
            new Card(Value.JACK, Shape.HEART),
            new Card(Value.EIGHT, Shape.HEART),
            new Card(Value.THREE, Shape.HEART)
    );
    public static final List<Card> BLACKJACK = List.of(
            new Card(Value.ACE, Shape.HEART),
            new Card(Value.KING, Shape.HEART)
    );
    public static final List<Card> CARDS_SCORE_22 = List.of(
            new Card(Value.JACK, Shape.HEART),
            new Card(Value.SEVEN, Shape.HEART),
            new Card(Value.FIVE, Shape.HEART)
    );
    public static final List<Card> BUSTED = List.of(
            new Card(Value.KING, Shape.DIAMOND),
            new Card(Value.QUEEN, Shape.DIAMOND),
            new Card(Value.JACK, Shape.DIAMOND)
    );

    @DisplayName("카드 값에 따라 점수를 확인할 수 있다")
    @Nested
    class CardScoreTest {

        @DisplayName("숫자 카드는 카드의 숫자 값을 점수로 가진다")
        @ParameterizedTest
        @CsvSource({"TWO, 2", "TEN, 10"})
        void scoreTest_whenNumberCard(Value value, int expected) {
            Card card = new Card(value, Shape.CLOVER);

            assertAll(
                    () -> assertThat(card.getMinScore()).isEqualTo(expected),
                    () -> assertThat(card.getMaxScore()).isEqualTo(expected)
            );
        }

        @DisplayName("Jack, Queen, King 카드는 모두 10점을 가진다.")
        @ParameterizedTest
        @EnumSource(names = {"JACK", "QUEEN", "KING"})
        void scoreTest_whenJQK_Card(Value value) {
            Card card = new Card(value, Shape.HEART);

            assertAll(
                    () -> assertThat(card.getMinScore()).isEqualTo(10),
                    () -> assertThat(card.getMaxScore()).isEqualTo(10)
            );
        }

        @DisplayName("Ace 카드는 1, 또는 11점을 가진다.")
        @Test
        void scoreTest_whenAceCard() {
            Card card = new Card(Value.ACE, Shape.HEART);

            assertAll(
                    () -> assertThat(card.getMinScore()).isEqualTo(1),
                    () -> assertThat(card.getMaxScore()).isEqualTo(11)
            );
        }
    }
}
