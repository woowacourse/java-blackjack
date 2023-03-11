package domain.participant;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Shape;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class PlayerTest {

    private Player player;

    @BeforeEach
    void init() {
        player = Player.create("a");
    }

    @Test
    @DisplayName("create()를 호출하면 Player가 생성된다")
    void create_whenCall_thenSuccess() {
        final Player player = assertDoesNotThrow(() -> Player.create("b"));

        assertThat(player)
                .isExactlyInstanceOf(Player.class);
    }

    @Test
    @DisplayName("isDealerName()은 파라미터로 입력된 name이 '딜러'라면 예외가 발생한다")
    void create_givenInvalidName_thenFail() {
        assertThatThrownBy(() -> Player.create("딜러"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어는 '딜러'라는 이름을 가질 수 없습니다.");
    }

    @Test
    @DisplayName("addCard()는 카드를 건네주면 참가자의 카드에 추가한다")
    void addCard_givenCard_thenSuccess() {
        // when
        final Card card = Card.of(Shape.HEART, Denomination.ACE);
        player.addCard(card);

        // then
        final ParticipantCard participantCard = player.participantCard;
        final List<Card> cards = participantCard.getCards();

        assertThat(cards)
                .hasSize(1);
    }

    @MethodSource(value = "domain.helper.ParticipantArguments#makeCards")
    @ParameterizedTest(name = "calculateScore()는 호출하면 점수를 계산한다")
    void calculateScore_whenCall_thenReturnScore(final List<Card> cards, final int expected) {
        // given
        cards.forEach(player::addCard);

        // when
        final int actual = player.calculateScore();

        // then
        assertThat(actual)
                .isEqualTo(expected);
    }

    @MethodSource(value = "domain.helper.ParticipantArguments#makePlayerCards")
    @ParameterizedTest(name = "canDraw()는 호출하면 플레이어가 카드를 더 뽑을 수 있는지 여부를 반환한다")
    void canDraw_whenCall_thenReturnIsBust(final List<Card> cards, final boolean expected) {
        // given
        cards.forEach(player::addCard);

        // when
        final boolean actual = player.canDraw();

        // then
        assertThat(actual)
                .isSameAs(expected);
    }

    @Test
    @DisplayName("getStartCard()는 호출하면 딜러의 첫 번째 카드를 조회한다")
    void getStartCard_whenCall_thenReturnStartCard() {
        // given
        final Card firstCard = Card.of(Shape.HEART, Denomination.ACE);
        final Card secondCard = Card.of(Shape.HEART, Denomination.TWO);
        player.addCard(firstCard);
        player.addCard(secondCard);

        // when
        final List<Card> actual = player.getStartCard();

        // then
        assertThat(actual.size())
                .isSameAs(2);
    }

    @Test
    @DisplayName("getName()은 호출하면 플레이어의 이름을 반환한다")
    void getName_whenCall_thenReturnPlayerName() {
        // when
        final String actual = player.getName();

        // then
        assertThat(actual)
                .isEqualTo("a");
    }

    @Nested
    @DisplayName("bet() 테스트")
    class BetMethodTest {

        @Test
        @DisplayName("유효한 배팅 금액을 전달하면 PlayerBet을 초기화한다")
        void create_givenValidMoney_thenInitPlayerBet() {
            assertThatCode(() -> player.bet(1000))
                    .doesNotThrowAnyException();
        }

        @Test
        @DisplayName("최소 금액을 배팅하지 않으면 예외가 발생한다")
        void create_givenLessThanMinimumMoney_thenFail() {
            assertThatThrownBy(() -> player.bet(0))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("최소 천 원 이상 배팅해주세요.");
        }

        @Test
        @DisplayName("정해진 금액 단위로 배팅하지 않으면 예외가 발생한다")
        void create_givenInvalidAmountUnit_thenFail() {
            assertThatThrownBy(() -> player.bet(1100))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("천 원 단위로 배팅해주세요.");
        }
    }

    @Test
    @DisplayName("calculateBenefit()은 배당 금액을 건네주면 순이익을 반환한다")
    void calculateBenefit_givenPrizeRatio_thenReturnBenefit() {
        // given
        final PlayerBet playerBet = PlayerBet.create(1000);
        final double prizeRatio = 1.5d;

        // when
        final BigDecimal actual = playerBet.calculateBenefit(prizeRatio);

        // then
        final BigDecimal expected = new BigDecimal("500.0");

        assertThat(actual)
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("calculateResult()는 호출하면 예외가 발생한다.")
    void calculateResult_whenCall_thenFail() {
        assertThatThrownBy(() -> player.calculateResult(player))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("플레이어는 게임의 결과를 계산할 수 없습니다.");
    }
}
