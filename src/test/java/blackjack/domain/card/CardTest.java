package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static blackjack.domain.card.Kind.*;
import static blackjack.domain.card.Value.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class CardTest {

    private static Stream<Arguments> makeDummyCards() {
        return Stream.of(
                arguments(new Card(SPADE, ACE), SPADE, ACE),
                arguments(new Card(DIAMOND, TEN), DIAMOND, TEN),
                arguments(new Card(HEART, JACK), HEART, JACK)
        );
    }

    @DisplayName("카드의 문양과 값을 가진 카드가 생성된다")
    @ParameterizedTest
    @MethodSource("makeDummyCards")
    void should_CreateCard_When_GiveCardKindAndValue(Card testCard,
                                                     Kind expectedKind, Value expectedValue) {
        assertAll(
                () -> assertThat(testCard.getKind()).isEqualTo(expectedKind),
                () -> assertThat(testCard.getValue()).isEqualTo(expectedValue)
        );
    }

    @DisplayName("카드가 가진 점수를 확인할 수 있다")
    @Test
    void should_getCardScore() {
        Card testCard1 = new Card(SPADE, TEN);
        Card testCard2 = new Card(DIAMOND, FOUR);

        assertAll(
                () -> assertThat(testCard1.getScore()).isEqualTo(10),
                () -> assertThat(testCard2.getScore()).isEqualTo(4)
        );
    }
}
