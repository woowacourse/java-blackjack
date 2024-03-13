package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

import blackjack.domain.cards.Card;
import blackjack.domain.cards.Rank;
import blackjack.domain.cards.Shape;
import blackjack.domain.participants.Money;
import blackjack.domain.participants.Name;
import blackjack.domain.participants.Player;
import blackjack.domain.participants.Victory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    @Test
    @DisplayName("플레이어가 잘 생성된다.")
    void playerConstructSuccessTest() {
        assertThatNoException()
                .isThrownBy(() -> new Player(new Name("이름")));
    }

    @Test
    @DisplayName("플레이어가 카드를 잘 받는다.")
    void playerReceiveCardTest() {
        Player player = new Player(new Name("이름"));
        assertThatNoException()
                .isThrownBy(() -> player.receiveCard(new Card(Shape.HEART, Rank.ACE)));
    }

    @Test
    @DisplayName("플레이어 점수를 계산한다.")
    void calculateScoreTest() {
        Player player = new Player(new Name("이름"));
        player.receiveCard(new Card(Shape.HEART, Rank.TEN));
        player.receiveCard(new Card(Shape.HEART, Rank.TWO));

        int result = player.calculateScore();

        assertThat(result).isEqualTo(12);
    }

    @Test
    @DisplayName("플레이어가 버스트일 때 진다.")
    void checkVictoryWhenPlayerBust() {
        Player player = new Player(new Name("이름"));
        player.receiveCard(new Card(Shape.HEART, Rank.TEN));
        player.receiveCard(new Card(Shape.HEART, Rank.TWO));
        player.receiveCard(new Card(Shape.DIAMOND, Rank.TEN));

        assertThat(player.checkVictory(20, false)).isEqualTo(Victory.LOSE);
    }

    @Test
    @DisplayName("딜러가 버스트일 때 이긴다.")
    void checkVictoryWhenDealerBust() {
        Player player = new Player(new Name("이름"));
        player.receiveCard(new Card(Shape.HEART, Rank.TEN));
        player.receiveCard(new Card(Shape.HEART, Rank.TWO));

        assertThat(player.checkVictory(25, false)).isEqualTo(Victory.WIN);
    }

    @Test
    @DisplayName("플레이어가 블랙잭으로 이긴다.")
    void checkVictoryWhenBlackjackWin() {
        Player player = new Player(new Name("이름"));
        player.receiveCard(new Card(Shape.HEART, Rank.TEN));
        player.receiveCard(new Card(Shape.HEART, Rank.ACE));

        assertThat(player.checkVictory(20, false)).isEqualTo(Victory.BLACKJACK_WIN);
    }

    @Test
    @DisplayName("플레이어가 이긴다.")
    void checkVictoryWhenWin() {
        Player player = new Player(new Name("이름"));
        player.receiveCard(new Card(Shape.HEART, Rank.TEN));
        player.receiveCard(new Card(Shape.DIAMOND, Rank.TEN));

        assertThat(player.checkVictory(18, false)).isEqualTo(Victory.WIN);
    }

    @Test
    @DisplayName("플레이어가 비긴다.")
    void checkVictoryWhenTie() {
        Player player = new Player(new Name("이름"));
        player.receiveCard(new Card(Shape.HEART, Rank.TEN));
        player.receiveCard(new Card(Shape.DIAMOND, Rank.ACE));

        assertThat(player.checkVictory(21, true)).isEqualTo(Victory.TIE);
    }

    @Test
    @DisplayName("플레이어가 진다.")
    void checkVictoryWhenLose() {
        Player player = new Player(new Name("이름"));
        player.receiveCard(new Card(Shape.HEART, Rank.TEN));

        assertThat(player.checkVictory(20, false)).isEqualTo(Victory.LOSE);
    }

    @Test
    @DisplayName("블랙잭이 맞음을 확인한다.")
    void isBlackjackTrueTest() {
        Player player = new Player(new Name("이름"));
        player.receiveCard(new Card(Shape.HEART, Rank.TEN));
        player.receiveCard(new Card(Shape.DIAMOND, Rank.ACE));

        assertThat(player.isBlackjack()).isTrue();
    }

    @Test
    @DisplayName("블랙잭이 아님을 확인한다.")
    void isBlackjackFalseTest() {
        Player player = new Player(new Name("이름"));
        player.receiveCard(new Card(Shape.HEART, Rank.TEN));
        player.receiveCard(new Card(Shape.DIAMOND, Rank.TEN));

        assertThat(player.isBlackjack()).isFalse();
    }

    @Test
    @DisplayName("플레이어 카드가 21이 넘지 않는다.")
    void isNotOverTrueTest() {
        Player player = new Player(new Name("이름"));

        player.receiveCard(new Card(Shape.HEART, Rank.ACE));
        player.receiveCard(new Card(Shape.HEART, Rank.NINE));

        assertThat(player.isNotOver(21)).isTrue();
    }

    @Test
    @DisplayName("플레이어 카드가 21이 넘는다.")
    void isNotOverFalseTest() {
        Player player = new Player(new Name("이름"));

        player.receiveCard(new Card(Shape.HEART, Rank.NINE));
        player.receiveCard(new Card(Shape.SPADE, Rank.NINE));
        player.receiveCard(new Card(Shape.DIAMOND, Rank.FOUR));

        assertThat(player.isNotOver(21)).isFalse();
    }

    @Test
    @DisplayName("플레이어가 돈을 배팅한다.")
    void betMoneyTest() {
        Player player = new Player(new Name("이름"));

        player.betMoney(new Money(3000));

        assertThat(player.getMoney()).isEqualTo(new Money(3000));
    }

    @Test
    @DisplayName("플레이어가 돈을 잃었다.")
    void loseMoneyTest() {
        Player player = new Player(new Name("이름"));

        player.betMoney(new Money(3000));
        player.loseMoney(new Money(2000));

        assertThat(player.getMoney()).isEqualTo(new Money(1000));
    }

    @Test
    @DisplayName("플레이어가 배팅에 성공했다.")
    void earnBetSuccessMoneyTest() {
        Player player = new Player(new Name("이름"));

        player.betMoney(new Money(3000));
        player.checkBettingMoney(1);

        assertThat(player.getMoney()).isEqualTo(new Money(3000));
    }

    @Test
    @DisplayName("플레이어가 배팅에 실패했다.")
    void payBetFailMoneyTest() {
        Player player = new Player(new Name("이름"));

        player.betMoney(new Money(3000));
        player.checkBettingMoney(-1);

        assertThat(player.getMoney().equals(new Money(-3000))).isTrue();
    }
}
