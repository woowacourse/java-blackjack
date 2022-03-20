package blackjack.domain.entry;

import static blackjack.fixtures.BlackjackFixtures.CLUB_ACE;
import static blackjack.fixtures.BlackjackFixtures.CLUB_EIGHT;
import static blackjack.fixtures.BlackjackFixtures.CLUB_KING;
import static blackjack.fixtures.BlackjackFixtures.SPADE_ACE;
import static blackjack.fixtures.BlackjackFixtures.SPADE_EIGHT;
import static blackjack.fixtures.BlackjackFixtures.SPADE_FOUR;
import static blackjack.fixtures.BlackjackFixtures.SPADE_KING;
import static blackjack.fixtures.BlackjackFixtures.SPADE_NINE;
import static blackjack.fixtures.BlackjackFixtures.SPADE_SEVEN;
import static blackjack.fixtures.BlackjackFixtures.SPADE_TWO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import blackjack.domain.card.HoldCards;
import blackjack.domain.entry.vo.BettingMoney;
import blackjack.domain.entry.vo.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    @DisplayName("플레이어의 이름이 딜러인 경우 예외를 발생한다.")
    void throwExceptionContainsDealerName() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> new Player(new Name("딜러"), new BettingMoney(10000)))
            .withMessage("플레이어의 이름은 딜러의 이름이 될 수 없습니다.");
    }

    @Test
    @DisplayName("플레이어의 이름이 같은지 비교할 수 있다.")
    void equalsName() {
        Player player = new Player(new Name("hoho"), new BettingMoney(10000));

        assertThat(player.equalsName(new Name("hoho"))).isTrue();
    }

    @Test
    @DisplayName("플레이어는 딜러가 아니다.")
    void isNotDealer() {
        Player player = new Player(new Name("hoho"), new BettingMoney(10000));

        assertThat(player.isDealer()).isFalse();
    }

    @Test
    @DisplayName("두 장의 카드로 게임을 준비한다.")
    void ready() {
        Player player = new Player(new Name("hoho"), new BettingMoney(10000));
        player.ready(SPADE_ACE, SPADE_KING);

        assertThat(player.getScore()).isEqualTo(21);
    }

    @Test
    @DisplayName("Stay를 할 경우 Finished 상태가 된다.")
    void finished() {
        Player player = new Player(new Name("hoho"), new BettingMoney(10000));
        player.ready(SPADE_ACE, SPADE_SEVEN);
        player.stay();

        assertThat(player.isFinished()).isTrue();
    }

    @Test
    @DisplayName("추가로 카드를 뽑아 21이 넘지않는 경우 Running 상태가 된다.")
    void running() {
        Player player = new Player(new Name("hoho"), new BettingMoney(10000));
        player.ready(SPADE_ACE, SPADE_FOUR);
        player.draw(SPADE_TWO);

        assertThat(player.isFinished()).isFalse();
    }

    @Test
    @DisplayName("딜러와 플레이어 모두 블랙잭이 경우 배팅금액을 얻는다.")
    void allBlackjack() {
        Player player = new Player(new Name("hoho"), new BettingMoney(10000));
        player.ready(SPADE_ACE, SPADE_KING);

        Dealer dealer = new Dealer(HoldCards.initTwoCards(CLUB_ACE, CLUB_KING));

        assertThat(player.profit(dealer)).isEqualTo(10000);
    }

    @Test
    @DisplayName("딜러와 플레이어가 블랙잭이 아니고 동점인 경우 배팅 금액을 잃는다.")
    void isSameScore() {
        Player player = new Player(new Name("hoho"), new BettingMoney(10000));
        player.ready(SPADE_ACE, SPADE_EIGHT);
        player.stay();
        Dealer dealer = new Dealer(HoldCards.initTwoCards(CLUB_ACE, CLUB_EIGHT));

        assertThat(player.profit(dealer)).isEqualTo(-10000);
    }

    @Test
    @DisplayName("플레이어가 21점이 넘는 경우 배팅 금액을 잃는다.")
    void isBust() {
        Player player = new Player(new Name("hoho"), new BettingMoney(10000));
        player.ready(SPADE_FOUR, SPADE_EIGHT);
        player.draw(SPADE_KING);

        Dealer dealer = new Dealer(HoldCards.initTwoCards(CLUB_ACE, CLUB_EIGHT));

        assertThat(player.profit(dealer)).isEqualTo(-10000);
    }

    @Test
    @DisplayName("플레이어가 딜러의 점수보다 높은 경우 배팅 금액을 얻는다.")
    void isWin() {
        Player player = new Player(new Name("hoho"), new BettingMoney(10000));
        player.ready(SPADE_FOUR, SPADE_EIGHT);
        player.draw(SPADE_NINE);
        player.stay();

        Dealer dealer = new Dealer(HoldCards.initTwoCards(CLUB_ACE, CLUB_EIGHT));

        assertThat(player.profit(dealer)).isEqualTo(10000);
    }

    @Test
    @DisplayName("플레이어가 stay 상태고, 딜러가 bust 된 경우 배팅 금액을 돌려받는다.")
    void dealerBust() {
        Player player = new Player(new Name("hoho"), new BettingMoney(10000));
        player.ready(SPADE_FOUR, SPADE_EIGHT);
        player.stay();

        Dealer dealer = new Dealer(HoldCards.initTwoCards(SPADE_NINE, SPADE_EIGHT));
        dealer.addCard(SPADE_KING);

        assertThat(player.profit(dealer)).isEqualTo(10000);
    }

    @Test
    @DisplayName("플레이어보다 딜러의 점수가 높을 경우 배팅 금액을 잃는다.")
    void isLose() {
        Player player = new Player(new Name("hoho"), new BettingMoney(10000));
        player.ready(SPADE_FOUR, SPADE_EIGHT);
        player.stay();

        Dealer dealer = new Dealer(HoldCards.initTwoCards(SPADE_NINE, SPADE_EIGHT));

        assertThat(player.profit(dealer)).isEqualTo(-10000);
    }
}
