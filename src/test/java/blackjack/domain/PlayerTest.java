package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.cards.Card;
import blackjack.domain.cards.Rank;
import blackjack.domain.cards.Shape;
import blackjack.domain.participants.Name;
import blackjack.domain.participants.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    @Test
    @DisplayName("플레이어가 잘 생성된다.")
    void playerConstructSuccessTest() {
        Assertions.assertThatNoException()
                .isThrownBy(() -> new Player(new Name("이름")));
    }

    @Test
    @DisplayName("플레이어가 카드를 잘 받는다.")
    void playerReceiveCardTest() {
        Player player = new Player(new Name("이름"));
        Assertions.assertThatNoException()
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

        player.betMoney(3000);

        assertThat(player.getMoney()).isEqualTo(3000);
    }

    @Test
    @DisplayName("플레이어가 돈을 잃었다.")
    void loseMoneyTest() {
        Player player = new Player(new Name("이름"));

        player.betMoney(3000);
        player.loseMoney(2000);

        assertThat(player.getMoney()).isEqualTo(1000);
    }

    @Test
    @DisplayName("플레이어가 배팅에 성공했다.")
    void earnBetSuccessMoneyTest() {
        Player player = new Player(new Name("이름"));

        player.betMoney(3000);
        player.earnBetSuccessMoney();

        assertThat(player.getMoney()).isEqualTo(3000);
    }

    @Test
    @DisplayName("플레이어가 배팅에 실패했다.")
    void payBetFailMoneyTest() {
        Player player = new Player(new Name("이름"));

        player.betMoney(3000);
        player.earnBetSuccessMoney();

        assertThat(player.getMoney()).isEqualTo(3000);
    }
}
