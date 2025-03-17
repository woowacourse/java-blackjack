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
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("플레이어 테스트")
class PlayerTest {

    private static final Card ACE = new Card(CardNumber.ACE, CardShape.CLOVER);
    private static final Card FIVE = new Card(CardNumber.FIVE, CardShape.CLOVER);
    private static final Card TEN = new Card(CardNumber.TEN, CardShape.CLOVER);

    @Test
    @DisplayName("플레이어 객체를 생성한다")
    void createPlayerObject() {
        Player player = new TestPlayer();

        assertAll(
                () -> assertThat(player.getName().getName()).isEqualTo("Test"),
                () -> assertThat(player.getHand().getCards().getCards().size()).isEqualTo(0),
                () -> assertThat(player.getHand().getBetAmount()).isEqualTo(1_000)
        );
    }

    @Test
    @DisplayName("플레이어의 카드를 추가한다")
    void addCardsToPlayer() {
        Player player = new TestPlayer();

        List<Card> cards = List.of(ACE);
        Cards newCards = new Cards(cards);
        player.addCards(newCards);

        List<Card> result = player.getHand().getCards().getCards();

        assertThat(result).containsExactlyElementsOf(cards);
    }

    @Test
    @DisplayName("플레이어의 카드가 버스트인지 판단한다")
    void determineIfPlayerIsBust() {
        Player player = new TestPlayer();
        player.addCards(new Cards(List.of(ACE)));

        boolean result = player.isBust();
        boolean notResult = player.isNotBust();

        assertAll(
                () -> assertThat(result).isFalse(),
                () -> assertThat(notResult).isTrue()
        );
    }

    @Test
    @DisplayName("플레이어의 카드가 히트인지 판단한다")
    void determineIfPlayerIsHit() {
        Player player = new TestPlayer();
        player.addCards(new Cards(List.of(ACE)));

        boolean result = player.isHit();

        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @MethodSource("CardSumTestCases")
    @DisplayName("카드의 합을 계산해 반환한다")
    void calculate_calculation_check_rot(Cards cards, int excepted) {
        Player player = new TestPlayer();
        player.addCards(cards);

        int result = player.getCardScore();

        assertThat(result).isEqualTo(excepted);
    }

    @ParameterizedTest
    @MethodSource("blackJackHandTestCases")
    @DisplayName("플레이어의 블랙잭을 판단해 결과를 반환한다")
    void determineAndReturnIfPlayerIsBlackJack(Cards cards, boolean excepted) {
        Player player = new TestPlayer();
        player.addCards(cards);

        boolean result = player.isBlackJack();

        assertThat(result).isEqualTo(excepted);
    }

    @ParameterizedTest
    @CsvSource({
            "1.5, 1_500",
            "-1.5, -1_500",
            "2.0, 2_000",
            "2.5, 2_500",
    })
    @DisplayName("배수의 따라 계산해 반환한다")
    void calculateAndReturnBasedOnMultiple(double multiple, int excepted) {
        Player player = new TestPlayer();

        int result = player.calculateBetAmount(multiple);

        assertThat(result).isEqualTo(excepted);
    }

    private static Stream<Arguments> CardSumTestCases() {
        return Stream.of(
                Arguments.of(new Cards(List.of(ACE)), 11),
                Arguments.of(new Cards(List.of(ACE, FIVE)), 16),
                Arguments.of(new Cards(List.of(ACE, FIVE, FIVE)), 21),
                Arguments.of(new Cards(List.of(ACE, TEN)), 21)
        );
    }

    private static Stream<Arguments> blackJackHandTestCases() {
        return Stream.of(
                Arguments.of(new Cards(List.of(ACE)), false),
                Arguments.of(new Cards(List.of(ACE, FIVE)), false),
                Arguments.of(new Cards(List.of(ACE, FIVE, FIVE)), false),
                Arguments.of(new Cards(List.of(ACE, TEN)), true)
        );
    }

    static class TestPlayer extends Player {

        public TestPlayer() {
            super("Test", 1_000);
        }

        @Override
        public List<Card> getOpenedCards() {
            return this.getHand().getCards().getCards();
        }
    }
}
