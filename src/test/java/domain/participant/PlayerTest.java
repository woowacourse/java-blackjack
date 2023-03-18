package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Shape;
import domain.game.GameResult;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PlayerTest {

    private Player player;

    @Nested
    @DisplayName("create() 테스트")
    class CreateMethodTest {

        @Test
        @DisplayName("유효한 배팅 금액을 입력하면 Player를 생성한다")
        void create_givenValidBetAmount_thenSuccess() {
            final Player actual = assertDoesNotThrow(() -> Player.create("a", 1000));

            assertThat(actual)
                    .isExactlyInstanceOf(Player.class);
        }

        @ParameterizedTest(name = "유효하지 않은 배팅 금액을 입력하면 예외가 발생한다")
        @CsvSource(value = {"1:최소 천 원 이상 배팅해주세요.", "1100:천 원 단위로 배팅해주세요."}, delimiter = ':')
        void create_givenInvalidBetAmount_thenFail(final int betAmount, final String message) {
            assertThatThrownBy(() -> Player.create("a", betAmount))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(message);
        }
    }

    @BeforeEach
    void beforeEach() {
        player = Player.create("a", 1000);
    }

    @Test
    @DisplayName("addCard()는 카드를 전달하면 손패에 해당 카드를 추가한다")
    void addCard_givenCard_thenSuccess() {
        // when
        player.addCard(Card.of(Shape.HEART, Denomination.ACE));

        // then
        final ParticipantCard participantCard = player.participantCard();
        final List<Card> actual = participantCard.getCards();

        assertThat(actual).hasSize(1);
    }

    @Test
    @DisplayName("calculateScore()는 호출하면 현재 손패의 모든 카드의 점수 합을 반환한다")
    void calculateScore_whenCall_thenReturnTotalCardScore() {
        // when
        final int actual = player.calculateScore();

        // then
        assertThat(actual).isEqualTo(0);
    }

    @ParameterizedTest(name = "calculateProfit()은 플레이어의 게임 결과를 전달하면 순이익을 반환한다")
    @CsvSource(value = {"LOSE:-1000", "WIN:1000", "BLACKJACK_WIN:1500.0", "DRAW:0"}, delimiter = ':')
    void calculateProfit_givenGameResult_thenReturnProfit(final GameResult result, final String expected) {
        // when
        final BigDecimal actual = player.calculateProfit(result);

        // then
        assertThat(actual).isEqualTo(new BigDecimal(expected));
    }

    @Nested
    @DisplayName("canDraw() 테스트")
    class CanDrawMethodTest {

        @Test
        @DisplayName("카드의 점수 합이 21 이하라면 true를 반환한다")
        void canDraw_whenTotalCardScoreUnder21_thenReturnTrue() {
            // when
            final boolean actual = player.canDraw();

            // then
            assertThat(actual).isTrue();
        }

    }

    @Test
    @DisplayName("카드의 점수 합이 21 초과라면 false를 반환한다")
    void canDraw_whenTotalCardScoreOver21_thenReturnFalse() {
        // given
        player.addCard(Card.of(Shape.HEART, Denomination.QUEEN));
        player.addCard(Card.of(Shape.CLOVER, Denomination.QUEEN));
        player.addCard(Card.of(Shape.DIAMOND, Denomination.QUEEN));

        // when
        final boolean actual = player.canDraw();

        // then
        assertThat(actual).isFalse();
    }

    @ParameterizedTest(name = "matchByName()은 이름을 전달하면 플레이어의 이름과 일치하는지 여부를 반환한다")
    @CsvSource(value = {"a:true", "b:false"}, delimiter = ':')
    void matchByName_givenName_thenReturnMatchPlayerName(final String name, final boolean expected) {
        // when
        final boolean actual = player.matchByName(name);

        // then
        assertThat(actual).isSameAs(expected);
    }
}
