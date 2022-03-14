package blackjack.model;

import static blackjack.model.Rank.*;
import static blackjack.model.Suit.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class CardsTest {

    @ParameterizedTest(name = "[{index}] Best Score 계산")
    @MethodSource("provideCards")
    @DisplayName("최고의 카드 점수 계산")
    void bestScore(Cards cards, int expect) {
        assertThat(cards.bestScore()).isEqualTo(new Score(expect));
    }

    protected static Stream<Arguments> provideCards() {
        return Stream.of(
                Arguments.of(new Cards(
                        List.of(new Card(ACE, SPADE), new Card(JACK, HEART))), 21),
                Arguments.of(new Cards(
                        List.of(new Card(ACE, DIAMOND), new Card(JACK, DIAMOND), new Card(KING, CLOVER))), 21),
                Arguments.of(new Cards(
                        List.of(new Card(ACE, DIAMOND), new Card(ACE, SPADE),
                                new Card(ACE, HEART), new Card(ACE, CLOVER))), 14),
                Arguments.of(new Cards(
                        List.of(new Card(QUEEN, CLOVER), new Card(JACK, HEART), new Card(KING, DIAMOND))), 30),
                Arguments.of(new Cards(
                        List.of(new Card(THREE, DIAMOND), new Card(TWO, DIAMOND))), 5),
                Arguments.of(new Cards(
                        List.of(new Card(ACE, DIAMOND), new Card(ACE, SPADE))), 12)
        );
    }

    @Test
    @DisplayName("카드 발급")
    void takeCards() {
        Cards cards = new Cards(new ArrayList<>(List.of(new Card(JACK, DIAMOND), new Card(THREE, CLOVER))));
        cards.takeCard(new Card(ACE, HEART));
        assertThat(cards.bestScore()).isEqualTo(new Score(14));
    }
}
