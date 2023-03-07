package blackjack.domain.card;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class CardGroupTest {

    private final Card spadeAceCard = new Card(CardShape.SPADE, CardNumber.ACE);
    private final Card spadeEightCard = new Card(CardShape.SPADE, CardNumber.EIGHT);

    private CardGroup cards;

    @BeforeEach
    void setup() {
        cards = new CardGroup(spadeAceCard, spadeEightCard);
    }

    // TODO: nested test 또는 parameterized 로 여러 경우 테스트 되도록 (ace의 갯수같이)
    @Test
    @DisplayName("카드패의 총 점수를 구하는 테스트")
    void getScoreTest() {
        final int expectedScore = 11 + 8;

        int score = cards.getScore();

        assertThat(score).isEqualTo(expectedScore);
    }

    @Test
    @DisplayName("카드패의 총 점수를 구하는 테스트 - Ace하나는 1점 처리")
    void getScoreWithAceTest() {
        final int expectedScore = 11 + 8 + 1;

        cards.add(new Card(CardShape.HEART, CardNumber.ACE));
        int score = cards.getScore();

        assertThat(score).isEqualTo(expectedScore);
    }

    @Test
    @DisplayName("카드 그룹에 카드 하나를 추가하는 기능 테스트")
    void addCardTest() {
        final Card heartTwoCard = new Card(CardShape.HEART, CardNumber.TWO);

        cards.add(heartTwoCard);

        assertThat(cards).extracting("cards", InstanceOfAssertFactories.list(Card.class))
                .contains(spadeAceCard, spadeEightCard, heartTwoCard);
    }

    @DisplayName("버스트 확인 테스트")
    @ParameterizedTest(name = "isBust() - {1}")
    @MethodSource("provideForIsBustTest")
    void isBustTest(final Card firstCard, final Card secondCard, final boolean expectedIsBustValue) {

        cards.add(firstCard);
        cards.add(secondCard);

        assertThat(cards.isBust()).isEqualTo(expectedIsBustValue);
    }

    private static Stream<Arguments> provideForIsBustTest() {
        return Stream.of(
                Arguments.of(new Card(CardShape.HEART, CardNumber.TWO), new Card(CardShape.DIAMOND, CardNumber.TWO), false),
                Arguments.of(new Card(CardShape.HEART, CardNumber.TEN), new Card(CardShape.DIAMOND, CardNumber.JACK), true)
        );
    }

    // TODO: nested test
    @Test
    @DisplayName("블랙잭인지 확인 테스트 - blackjack인경우")
    void isBlackJackTrueTest() {
        CardGroup blackjackGroup = new CardGroup(new Card(CardShape.SPADE, CardNumber.ACE), new Card(CardShape.SPADE, CardNumber.JACK));

        assertThat(blackjackGroup.isBlackJack()).isTrue();
    }

    @ParameterizedTest(name = "블랙잭인지 확인 테스트 - 블랙잭이 아닌경우 {index}")
    @CsvSource(value = {"SEVEN", "EIGHT"})
    void isBlackJackFalseTest(CardNumber addedCardNumber) {
        Card addedCard = new Card(CardShape.DIAMOND, addedCardNumber);

        cards.add(addedCard);

        assertThat(cards.isBlackJack()).isFalse();
    }

}
