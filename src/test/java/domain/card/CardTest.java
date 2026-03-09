package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class CardTest {
    @ParameterizedTest
    @MethodSource("provideCardData")
    void 카드_정보_한국어로_변환_테스트(Card card, String korName) {
        assertThat(card.cardToKorName()).isEqualTo(korName);
    }

    static Stream<Arguments> provideCardData() {
        return Stream.of(
                Arguments.of(new Card(TrumpSuit.HEART, TrumpNumber.ACE), "A하트"),
                Arguments.of(new Card(TrumpSuit.DIAMOND, TrumpNumber.SEVEN), "7다이아몬드"),
                Arguments.of(new Card(TrumpSuit.SPADE, TrumpNumber.JACK), "J스페이드")
        );
    }
}