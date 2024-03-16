package model.card;

import static model.card.CardNumber.ACE;
import static model.card.CardNumber.FIVE;
import static model.card.CardNumber.JACK;
import static model.card.CardNumber.QUEEN;
import static model.card.CardShape.CLOVER;
import static model.card.CardShape.DIAMOND;
import static model.card.CardShape.HEART;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CardsTest {

    @DisplayName("카드 숫자 합 계산한다")
    @ParameterizedTest
    @MethodSource("provideCardsAndExpectedTotal")
    void testCalculateTotalCardNumbers(List<Card> cardValues, int expectedTotal) {
        Cards cards = new Cards(cardValues);
        assertThat(cards.score().getValue()).isEqualTo(expectedTotal);
    }

    public static Stream<Arguments> provideCardsAndExpectedTotal() {
        return Stream.of(
            Arguments.of(
                List.of(new Card(JACK, DIAMOND), new Card(FIVE, CLOVER), new Card(ACE, HEART)), 16
            ),
            Arguments.of(
                List.of(new Card(ACE, DIAMOND), new Card(QUEEN, HEART)), 21
            ),
            Arguments.of(
                List.of(new Card(ACE, DIAMOND), new Card(ACE, HEART), new Card(ACE, CLOVER)), 13
            )
        );
    }
}
