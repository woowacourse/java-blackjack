package domain.utils;

import static domain.MockCard.CLUB_ACE_CARD;
import static domain.MockCard.HEART_TEN_CARD;
import static domain.MockCard.SPADE_NINE_CARD;
import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import utils.CardConvertor;

public class CardConvertorTest {
    @ParameterizedTest
    @DisplayName("카드를 문자열로 변환")
    @MethodSource("provideCardsAndString")
    void convert(List<Card> cards, List<String> expected) {
        assertThat(CardConvertor.convertToString(cards)).isEqualTo(expected);
    }

    public static Stream<Arguments> provideCardsAndString() {
        return Stream.of(
                Arguments.of(List.of(CLUB_ACE_CARD), List.of("A클로버")),
                Arguments.of(List.of(HEART_TEN_CARD), List.of("10하트")),
                Arguments.of(List.of(SPADE_NINE_CARD), List.of("9스페이드")),
                Arguments.of(List.of(CLUB_ACE_CARD, HEART_TEN_CARD), List.of("A클로버", "10하트")),
                Arguments.of(List.of(CLUB_ACE_CARD, HEART_TEN_CARD, SPADE_NINE_CARD), List.of("A클로버", "10하트", "9스페이드"))
        );
    }
}
