package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.EnumSource.Mode;
import org.junit.jupiter.params.provider.MethodSource;

class CardTest {


    private static Stream<Arguments> generateAllCards() {
        return Arrays.stream(Emblem.values())
                .flatMap(emblem -> Arrays.stream(Number.values())
                        .map(number -> Card.from(number, emblem)))
                .map(Arguments::of);
    }

    @Test
    @DisplayName("Card 생성시 에이스 일 경우 하위 타입인 AceCard로 객체를 생성한다.")
    void from_ShouldGenerateAceCard_WhenNumberIsAce() {
        Card expected = Card.from(Number.ACE, Emblem.SPADE);

        assertThat(expected).isExactlyInstanceOf(AceCard.class);
    }

    @ParameterizedTest
    @EnumSource(mode= Mode.EXCLUDE, names = {"ACE"})
    @DisplayName("Card 생성시 에이스가 아닐 경우 그대로 Card로 객체를 생성한다.")
    void from_ShouldGenerateCard_WhenNumberIsNotAce(Number number) {
        Card expected = Card.from(number, Emblem.SPADE);
        assertThat(expected).isExactlyInstanceOf(Card.class);
    }

    @ParameterizedTest
    @MethodSource("generateAllCards")
    @DisplayName("카드의 번호와 엠블럼 정보를 정해진 포맷의 String으로 반환한다.")
    void toString_CardNumberValueAndEmblemValueToString_IsEqualsToFormattedString(Card card) {

        List<String> expectedCards = List.of(
                "A스페이드", "2스페이드", "3스페이드", "4스페이드", "5스페이드", "6스페이드",
                "7스페이드", "8스페이드", "9스페이드", "10스페이드", "J스페이드", "Q스페이드", "K스페이드",
                "A다이아몬드", "2다이아몬드", "3다이아몬드", "4다이아몬드", "5다이아몬드", "6다이아몬드",
                "7다이아몬드", "8다이아몬드", "9다이아몬드", "10다이아몬드", "J다이아몬드", "Q다이아몬드",
                "K다이아몬드", "A클로버", "2클로버", "3클로버", "4클로버", "5클로버", "6클로버", "7클로버",
                "8클로버", "9클로버", "10클로버", "J클로버", "Q클로버", "K클로버", "A하트", "2하트",
                "3하트", "4하트", "5하트", "6하트", "7하트", "8하트", "9하트", "10하트", "J하트", "Q하트",
                "K하트"
        );

        // When & Then
        assertThat(expectedCards).contains(card.toString());

    }

    @Test
    @DisplayName("카드는 자신의 숫자값을 반환한다.")
    void getNumberValue_ReturnCardNumberIntValue_IsEqualToExpectNumber() {
        // Given
        Card card = Card.from(Number.FOUR, Emblem.CLUB);

        // When
        int value = card.getCardActualValue();

        // Then
        assertThat(value).isEqualTo(4);
    }

}
