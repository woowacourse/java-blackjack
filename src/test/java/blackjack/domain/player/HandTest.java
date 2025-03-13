package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.Cards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

@DisplayName("소지품 테스트")
class HandTest {

    private static final Card ACE = new Card(CardNumber.ACE, CardShape.CLOVER);
    private static final Card FIVE = new Card(CardNumber.FIVE, CardShape.CLOVER);
    private static final Card TEN = new Card(CardNumber.TEN, CardShape.CLOVER);

    @Test
    @DisplayName("배팅 금액으로 객체를 생성한다")
    void createObjectWithBetAmount() {
        assertThatNoException()
                .isThrownBy(() -> new Hand(5_000));
    }

    @Test
    @DisplayName("카드를 추가한다")
    void addCard() {
        Hand hand = new Hand(0);

        List<Card> cards = List.of(ACE);
        Cards newCards = new Cards(cards);
        hand.addCards(newCards);

        List<Card> result = hand.getCards().getCards();

        assertThat(result).containsExactlyElementsOf(cards);
    }

    @Test
    @DisplayName("카드 점수를 계산해 반환한다")
    void calculateAndReturnCardScore() {
        Hand hand = new Hand(0);

        Cards newCards = new Cards(List.of(ACE, TEN));

        hand.addCards(newCards);

        int result = hand.calculateScore();

        assertThat(result).isEqualTo(21);
    }

    @Test
    @DisplayName("에이스가 2장이면 1, 11로 계산한다")
    void calculateAceAsOneOrElevenWhenTwoAces() {
        Hand hand = new Hand(0);

        Cards newCards = new Cards(List.of(ACE, ACE));

        hand.addCards(newCards);

        int result = hand.calculateScore();

        assertThat(result).isEqualTo(12);
    }

    @ParameterizedTest
    @CsvSource({
            "5_000, 1.5, 7_500",
            "5_000, -1.5, -7_500",
            "10_000, 1.5, 15_000",
            "10_000, 2.0, 20_000",
            "10_000, 2.5, 25_000",
    })
    @DisplayName("배수의 따라 계산해 반환한다")
    void calculateAndReturnBasedOnMultiple(int batAmount, double multiple, int excepted) {
        Hand hand = new Hand(batAmount);

        int result = hand.calculateWinningAmount(multiple);

        assertThat(result).isEqualTo(excepted);
    }

    @ParameterizedTest
    @MethodSource("blackJackHandTestCases")
    @DisplayName("블랙잭을 판단해 반환한다")
    void returnIfBlackJack(Cards cards, boolean excepted) {
        Hand hand = new Hand(0);
        hand.addCards(cards);

        boolean result = hand.isBlackJack();

        assertThat(result).isEqualTo(excepted);
    }

    private static Stream<Arguments> blackJackHandTestCases() {
        return Stream.of(
                Arguments.of(new Cards(List.of(ACE)), false),
                Arguments.of(new Cards(List.of(ACE, FIVE)), false),
                Arguments.of(new Cards(List.of(ACE, FIVE, FIVE)), false),
                Arguments.of(new Cards(List.of(ACE, TEN)), true)
        );
    }
}
