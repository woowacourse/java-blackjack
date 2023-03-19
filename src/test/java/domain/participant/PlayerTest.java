package domain.participant;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardPattern;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class PlayerTest {

    private String playerName;
    private Card card;
    private Player player;

    @BeforeEach
    void init() {
        // given
        playerName = "pobi";
        card = Card.create(CardPattern.HEART, CardNumber.ACE);
        player = Player.create(playerName);
    }

    @Test
    @DisplayName("create()를 호출하면, Player가 생성된다")
    void create_whenCall_thenSuccess() {
        final Player player = assertDoesNotThrow(() -> Player.create(playerName));

        assertThat(player)
                .isExactlyInstanceOf(Player.class);
    }

    @Test
    @DisplayName("플레이어의 이름이 '딜러'라면, 예외가 발생한다.")
    void create_givenInvalidName_thenFail() {
        assertThatThrownBy(() -> Player.create("딜러"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어는 '딜러'라는 이름을 가질 수 없습니다.");
    }

    @Test
    @DisplayName("addCard()는 카드를 건네주면 참가자의 카드에 추가한다")
    void addCard_givenCard_thenSuccess() {
        // when
        player.addCard(card);

        // then
        final List<Card> cards = player.getHand();

        assertThat(cards)
                .hasSize(1);
    }

    @Test
    @DisplayName("canDrawCard()는 플레이어가 버스트나 블랙잭이 아니면 true를 반환한다.")
    void canDrawCard_whenCall_thenReturnCanGiveCard() {
        // when
        boolean actual = player.canDrawCard();

        // then
        assertThat(actual)
                .isTrue();
    }

    @Test
    @DisplayName("bet()는 받은 돈만큼 베팅한다.")
    void bet_givenMoney_thenSuccess() {
        // when
        player.bet(ParticipantMoney.create(10000));

        // then
        assertThat(player.getMoney())
                .isEqualTo(10000);
    }

    @MethodSource(value = "domain.helper.ParticipantTestHelper#makeBlackJackCard")
    @ParameterizedTest(name = "isBlackJack()은 호출하면 블랙잭인지 확인한다")
    void isBlackJack_whenCall_thenReturnIsBust(final List<Card> cards, final boolean expected) {
        // given
        cards.forEach(player::addCard);

        // when
        final boolean actual = player.isBlackJack();

        // then
        assertThat(actual)
                .isSameAs(expected);
    }

    @MethodSource(value = "domain.helper.ParticipantTestHelper#makeBustCard")
    @ParameterizedTest(name = "isBust()는 호출하면 버스트인지 확인한다")
    void isBust_whenCall_thenReturnIsBust(final List<Card> cards, final boolean expected) {
        // given
        cards.forEach(player::addCard);

        // when
        final boolean actual = player.isBust();

        // then
        assertThat(actual)
                .isSameAs(expected);
    }

    @MethodSource(value = "domain.helper.ParticipantTestHelper#makeCards")
    @ParameterizedTest(name = "calculateScore()는 호출하면 점수를 계산한다")
    void calculateScore_whenCall_thenReturnScore(final List<Card> cards, final int expected) {
        // given
        cards.forEach(player::addCard);

        // when
        final int score = player.calculateScore();

        // then
        assertThat(score)
                .isSameAs(expected);
    }

    @Test
    @DisplayName("losePlayerMoney()는 플레이어의 돈을 -1배 한다.")
    void losePlayerMoney_whenCall_thenLoseMoney() {
        // given
        player.bet(ParticipantMoney.create(10000));

        // when
        player.loseMoney();

        // then
        assertThat(player.getMoney())
                .isEqualTo(-10000);
    }

    @Test
    @DisplayName("earnPlayerMoney()는 플레이어의 돈을 1배한다.")
    void earnPlayerMoney_whenCall_thenKeepMoney() {
        // given
        player.bet(ParticipantMoney.create(10000));

        // when
        player.earnMoney();

        // then
        assertThat(player.getMoney())
                .isEqualTo(10000);
    }

    @Test
    @DisplayName("earnPlayerBonusMoney()는 플레이어에게 보너스를 주고, 그만큼 딜러의 돈에서 제거한다.")
    void earnPlayerBonusMoney_whenCall_thenBonusMoney() {
        // given
        player.bet(ParticipantMoney.create(10000));

        // when
        player.earnBonusMoney();

        // then
        assertThat(player.getMoney())
                .isEqualTo(15000);
    }

    @Test
    @DisplayName("resetMoney()는 플레이어의 돈을 초기화한다.")
    void resetMoney_whenCall_thenMakeInitMoney() {
        // when
        player.bet(ParticipantMoney.create(10000));
        player.resetMoney(ParticipantMoney.create(10000));

        // then
        assertThat(player.getMoney())
                .isEqualTo(10000);
    }
}
