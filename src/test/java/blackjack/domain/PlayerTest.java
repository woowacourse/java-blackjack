package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Card.Shape;
import blackjack.domain.Card.Value;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PlayerTest {

    @DisplayName("점수를 계산할 수 있다.")
    @ParameterizedTest
    @MethodSource("provideCardsAndScore")
    void calculateScoreTest(List<Card> cards, int expected) {
        Player player = new Player(cards);

        assertThat(player.calculateScore()).isEqualTo(expected);
    }

    // TODO Fixture로 추출하기
    static Stream<Arguments> provideCardsAndScore() {
        return Stream.of(Arguments.of(List.of(new Card(Value.ACE, Shape.HEART), new Card(Value.KING, Shape.HEART)), 21),
                Arguments.of(List.of(new Card(Value.ACE, Shape.HEART), new Card(Value.ACE, Shape.SPADE)), 12),
                Arguments.of(List.of(new Card(Value.ACE, Shape.HEART), new Card(Value.KING, Shape.HEART),
                        new Card(Value.TWO, Shape.HEART)), 13),
                Arguments.of(List.of(new Card(Value.KING, Shape.HEART), new Card(Value.TWO, Shape.HEART)), 12));
    }
}
