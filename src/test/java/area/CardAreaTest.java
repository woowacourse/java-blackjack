package area;

import card.Card;
import card.CardValue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static card.CardValue.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("CardArea 은")
class CardAreaTest {

    @Test
    void 카드를_두장만_받아서_생성된다() {
        // when & then
        assertDoesNotThrow(() -> new CardArea(new Card(ACE), new Card(TWO)) {
        });
    }

    @Test
    void 카드를_추가할_수_있다() {
        // given
        final CardArea cardArea = new CardArea(new Card(THREE), new Card(TWO)) {
        };

        // when
        final int beforeSize = cardArea.cards().size();
        cardArea.addCard(new Card(FOUR));

        // then
        assertThat(cardArea.cards().size()).isEqualTo(beforeSize + 1);
    }

    @Test
    void CardArea_는_추상_클래스이다() {
        // when & then
        assertThat(CardArea.class).isAbstract();
    }

    @ParameterizedTest(name = "{arguments} 카드 영역이 있다.")
    @ValueSource(classes = {DealerCardArea.class, ParticipantCardArea.class})
    void 카드_영역은_두_종류가_있다(final Class<? extends CardArea> type) throws Exception {
        // given
        final CardArea cardArea = type.cast(type.getDeclaredConstructor(Card.class, Card.class)
                .newInstance(new Card(THREE), new Card(TWO)));

        // when & then
        assertThat(cardArea).isInstanceOf(type);
    }

    @ParameterizedTest(name = "카드 목록이 {0} 일 때, 총합은 {1}다.")
    @CsvSource(value = {
            "TWO+THREE = 5",
            "FIVE+SIX = 11",
            "TWO+TWO = 4",
            "TEN+TEN = 20",
    }, delimiterString = " = ")
    void 자신이_가진_카드의_합을_구할_수_있다(final String values, final int totalScore) {
        // given
        final String[] split = values.split("\\+");
        final CardArea cardArea = new CardArea(new Card(valueOf(split[0])), new Card(valueOf(split[1]))) {
        };

        // when & then
        assertThat(cardArea.calculate()).isEqualTo(totalScore);
    }

    @ParameterizedTest(name = "킹, 퀸, 잭은 10으로 계산한다")
    @CsvSource(value = {
            "KING+QUEEN = 20",
            "KING+JACK = 20",
            "QUEEN+JACK = 20",
            "KING+THREE = 13",
            "QUEEN+THREE = 13",
            "JACK+SIX = 16",
    }, delimiterString = " = ")
    void 킹_퀸_잭은_10으로_계산한다(final String values, final int totalScore) {
        // given
        final String[] split = values.split("\\+");
        final CardArea cardArea = new CardArea(new Card(valueOf(split[0])), new Card(valueOf(split[1]))) {
        };

        // when & then
        assertThat(cardArea.calculate()).isEqualTo(totalScore);
    }

    @ParameterizedTest(name = "[{index}] ACE 는 이전까지의 총합이 10 이하면 11로 계산한다")
    @MethodSource("containsAceCardArea")
    void ACE_는_이전까지의_총합이_10_이하면_11로_계산한다(final CardArea cardArea, final int totalScore) {
        // then
        assertThat(cardArea.calculate()).isEqualTo(totalScore);
    }

    static Stream<Arguments> containsAceCardArea() {

        // [11] + 10 = 21
        final CardArea cardArea1 = new CardArea(new Card(ACE), new Card(TEN)) {};

        // 10 + 10 + [1] = 21
        final CardArea cardArea2 = new CardArea(new Card(JACK), new Card(TEN)) {};
        cardArea2.addCard(new Card(ACE));

        // [11] + 9 + [1] = 21
        final CardArea cardArea3 = new CardArea(new Card(ACE), new Card(NINE)) {};
        cardArea3.addCard(new Card(ACE));

        // [11] + 6 + 3 = 20
        final CardArea cardArea4 = new CardArea(new Card(SIX), new Card(THREE)) {};
        cardArea4.addCard(new Card(ACE));

        // 10 + [1] + [1] = 12
        final CardArea cardArea5 = new CardArea(new Card(TEN), new Card(ACE)) {};
        cardArea5.addCard(new Card(ACE));

        return Stream.of(
                Arguments.of(cardArea1, 21),
                Arguments.of(cardArea2, 21),
                Arguments.of(cardArea3, 21),
                Arguments.of(cardArea4, 20),
                Arguments.of(cardArea5, 12)
        );
    }
}