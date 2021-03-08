package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class CardTest {

    private static Stream<Arguments> testCardSample() {
        return Stream.of(
            Arguments.of(new Card("5", "클로버"), 5),
            Arguments.of(new Card("K", "스페이드"), 10),
            Arguments.of(new Card("A", "하트"), 11)
        );
    }

    @ParameterizedTest
    @DisplayName("카드 생성 및 점수 확인 테스트")
    @MethodSource("testCardSample")
    void general_card(Card sampleCard, int answerPoint) {
        assertThat(sampleCard.getCardValue()).isEqualTo(answerPoint);
    }
}