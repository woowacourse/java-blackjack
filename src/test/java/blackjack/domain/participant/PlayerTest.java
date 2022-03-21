package blackjack.domain.participant;

import static blackjack.domain.fixture.CardRepository.CLOVER10;
import static blackjack.domain.fixture.CardRepository.CLOVER2;
import static blackjack.domain.fixture.CardRepository.CLOVER3;
import static blackjack.domain.fixture.CardRepository.CLOVER4;
import static blackjack.domain.fixture.CardRepository.CLOVER5;
import static blackjack.domain.fixture.CardRepository.CLOVER6;
import static blackjack.domain.fixture.CardRepository.CLOVER7;
import static blackjack.domain.fixture.CardRepository.CLOVER8;
import static blackjack.domain.fixture.CardRepository.CLOVER_ACE;
import static blackjack.domain.fixture.CardRepository.CLOVER_KING;
import static blackjack.domain.fixture.CardRepository.CLOVER_QUEEN;
import static blackjack.domain.fixture.CardRepository.HEART_ACE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.game.ResultType;
import blackjack.domain.money.Money;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        Hand hand = Hand.of(CLOVER4, CLOVER5);
        Money betMoney = Money.from(1000);
        player = Player.of("hudi", hand, betMoney);
    }

    @DisplayName("Player 인스턴스가 생성된다.")
    @Test
    void of() {
        assertThat(player).isNotNull();
    }

    @DisplayName("Player 의 이름이 비어있을 경우 예외가 발생한다.")
    @Test
    void of_withEmptyNameThrowsIllegalArgumentException() {
        Hand hand = Hand.of(CLOVER4, CLOVER5);
        assertThatThrownBy(() -> Player.of("", hand, Money.from(10000)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 1글자 이상이어야합니다.");
    }

    @DisplayName("Player 의 베팅 금액이 음수면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1000, -1})
    void of_withNegativeBetMoneyThrowsIllegalArgumentException(int input) {
        Hand hand = Hand.of(CLOVER4, CLOVER5);
        assertThatThrownBy(() -> Player.of("hudi", hand, Money.from(input)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("베팅 금액은 음수일 수 없습니다.");
    }

    @DisplayName("Card 를 전달받아 Hand 에 추가할 수 있다.")
    @Test
    void receiveCard() {
        player.receiveCard(CLOVER6);

        List<Card> actual = player.getHand().getCards();

        assertThat(actual).containsExactlyInAnyOrder(CLOVER4, CLOVER5, CLOVER6);
    }

    @DisplayName("Score 가 21을 넘지 않으면 true 를 반환한다.")
    @Test
    void canReceive_returnTrueOnLessThan21() {
        boolean actual = player.canReceive();

        assertThat(actual).isTrue();
    }

    @DisplayName("Score 가 21이면 true 를 반환한다.")
    @Test
    void canReceive_returnTrueOn21() {
        player.receiveCard(CLOVER2);
        player.receiveCard(CLOVER10);

        boolean actual = player.canReceive();

        assertThat(actual).isTrue();
    }

    @DisplayName("Score 가 21을 초과하면 false 를 반환한다.")
    @Test
    void canReceive_returnFalseOnGreaterThan21() {
        player.receiveCard(CLOVER10);
        player.receiveCard(CLOVER_KING);

        boolean actual = player.canReceive();

        assertThat(actual).isFalse();
    }

    // TODO: Nested 사용하여 정리

    @DisplayName("compareWith 메소드는 자신보다 패가 나쁜 Dealer 를 전달받으면 ResultType.WIN 를 반환한다.")
    @Test
    void compareWith_returnsResultTypeWin() {
        // given
        Dealer dealer = Dealer.of(Hand.of(CLOVER2, CLOVER3));

        // when
        ResultType actual = player.compareWith(dealer);
        ResultType expected = ResultType.WIN;

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("compareWith 메소드는 딜러 자신보다 패가 좋은 Dealer 를 전달받으면 ResultType.LOSE 를 반환한다.")
    @Test
    void compareWith_returnsResultTypeLose() {
        // given
        Dealer dealer = Dealer.of(Hand.of(CLOVER5, CLOVER6));

        // when
        ResultType actual = player.compareWith(dealer);
        ResultType expected = ResultType.LOSE;

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("compareWith 메소드는 자신과 대등한 패를 가진 Dealer 를 전달받으면 ResultType.DRAW 를 반환한다.")
    @Test
    void compareWith_returnsResultTypeDraw() {
        // given
        Dealer dealer = Dealer.of(Hand.of(CLOVER3, CLOVER6));

        // when
        ResultType actual = player.compareWith(dealer);
        ResultType expected = ResultType.DRAW;

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("isBlackjack 은 플레이어가 받은 첫 두장의 카드의 합이 21일 경우 true 를 반환한다.")
    @Test
    void isBlackjack_returnsTrueOnBlackjack() {
        // given
        Player player = Player.of("player", Hand.of(CLOVER_ACE, CLOVER10), Money.from(10000));

        // when
        boolean actual = player.isBlackjack();

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("isBlackjack 은 플레이어가 점수가 21이더라도 받은 첫 두장의 카드의 합이 21이 아닐 경우 false 를 반환한다.")
    @Test
    void isBlackjack_returnsFalseIfTotalScoreIs21ButNotBlackjack() {
        // given
        Player player = Player.of("player", Hand.of(CLOVER_ACE, CLOVER4), Money.from(10000));
        player.receiveCard(CLOVER6);

        // when
        boolean actual = player.isBlackjack();

        // then
        assertThat(actual).isFalse();
    }

    @DisplayName("calculateProfit 에 점수가 플레이어보다 낮은 딜러를 전달하면, 베팅 금액만큼의 값을 가진 Money 를 반환한다.")
    @Test
    void calculateProfit_returnsWinProfitIfWin() {
        // given
        Player player = Player.of("player", Hand.of(CLOVER_ACE, CLOVER4), Money.from(10000));
        Dealer dealer = Dealer.of(Hand.of(CLOVER2, CLOVER3));

        // when
        Money actual = player.calculateProfit(dealer);
        Money expected = Money.from(10000);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("calculateProfit 에 점수가 플레이어보다 낮은 딜러를 전달하고, 플레이어는 블랙잭일 경우, 베팅 금액의 1.5배의 값을 가진 Money 를 반환한다.")
    @Test
    void calculateProfit_returnsWinWithBlackjackProfitIfWinWithBlackjack() {
        // given
        Player player = Player.of("player", Hand.of(CLOVER_ACE, CLOVER10), Money.from(10000));
        Dealer dealer = Dealer.of(Hand.of(CLOVER8, CLOVER7));

        // when
        Money actual = player.calculateProfit(dealer);
        Money expected = Money.from(15000);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("calculateProfit 에 점수가 플레이어보다 높은 딜러를 전달하면, 베팅 금액의 반대 부호를 가진 Money 를 반환한다.")
    @Test
    void calculateProfit_returnsNegativeProfitIfLose() {
        // given
        Player player = Player.of("player", Hand.of(CLOVER_ACE, CLOVER4), Money.from(10000));
        Dealer dealer = Dealer.of(Hand.of(CLOVER_QUEEN, CLOVER_KING));

        // when
        Money actual = player.calculateProfit(dealer);
        Money expected = Money.from(-10000);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("calculateProfit 에 점수가 플레이어와 같은 딜러를 전달하면, 0의 값을 가진 Money 를 반환한다.")
    @Test
    void calculateProfit_returnsZeroProfitIfDraw() {
        // given
        Player player = Player.of("player", Hand.of(CLOVER_ACE, CLOVER4), Money.from(10000));
        Dealer dealer = Dealer.of(Hand.of(CLOVER8, CLOVER7));

        // when
        Money actual = player.calculateProfit(dealer);
        Money expected = Money.from(0);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("calculateProfit 에 전달된 딜러도 블랙잭이고, 플레이어도 블랙잭이라면 0의 값을 가진 Money 를 반환한다.")
    @Test
    void calculateProfit_returnsZeroProfitIfBothBlackjack() {
        // given
        Player player = Player.of("player", Hand.of(CLOVER_ACE, CLOVER_KING), Money.from(10000));
        Dealer dealer = Dealer.of(Hand.of(HEART_ACE, CLOVER_QUEEN));

        // when
        Money actual = player.calculateProfit(dealer);
        Money expected = Money.from(0);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("calculateProfit 에 전달된 딜러와 플레이어 둘다 버스트되었다면 0의 값을 가진 Money 를 반환한다.")
    @Test
    void calculateProfit_returnsZeroProfitIfBothBust() {
        // given
        Player player = Player.of("player", Hand.of(CLOVER_ACE, CLOVER_KING), Money.from(10000));
        player.receiveCard(CLOVER2);
        Dealer dealer = Dealer.of(Hand.of(HEART_ACE, CLOVER_QUEEN));
        dealer.receiveCard(CLOVER3);

        // when
        Money actual = player.calculateProfit(dealer);
        Money expected = Money.from(0);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("calculateProfit 에 전달된 딜러의 점수가 플레이어와 동일하지만, 딜러만 블랙잭인 경우, 베팅 금액의 반대 부호를 가진 Money 를 반환한다.")
    @Test
    void calculateProfit_returnsZeroProfitIfSameScoreButDealerOnlyBlackjack() {
        // given
        Player player = Player.of("player", Hand.of(CLOVER_ACE, CLOVER4), Money.from(10000));
        player.receiveCard(CLOVER6);
        Dealer dealer = Dealer.of(Hand.of(HEART_ACE, CLOVER10));

        // when
        Money actual = player.calculateProfit(dealer);
        Money expected = Money.from(-10000);

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
