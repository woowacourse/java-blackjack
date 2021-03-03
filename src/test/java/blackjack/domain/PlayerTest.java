package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PlayerTest {

    static Stream<Arguments> generateData() {
        return Stream.of(
            Arguments.of(Arrays.asList(
                new Card(Symbol.ACE, Shape.HEART),
                new Card(Symbol.KING, Shape.HEART),
                new Card(Symbol.NINE, Shape.HEART)
            ), true), // 합 : 20
            Arguments.of(Arrays.asList(
                new Card(Symbol.ACE, Shape.HEART),
                new Card(Symbol.KING, Shape.HEART),
                new Card(Symbol.TEN, Shape.HEART)
            ), false) // 합 : 21
        );
    }

    @ParameterizedTest
    @DisplayName("ACE를 1로 했을 때 카드 합이 21 미만일 경우 true를 반환")
    @MethodSource("generateData")
    public void isAbleToReceiveCard(List<Card> inputCards, boolean result) {
        Cards cards = new Cards(inputCards);

        Player player = new Player("jason");
        player.receiveDefaultCards(cards);

        assertThat(player.isAbleToReceiveCard()).isEqualTo(result);
    }
}
