package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.cards.Card;
import blackjack.domain.cards.Rank;
import blackjack.domain.cards.Shape;
import blackjack.domain.participants.PlayerStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerStatusTest {
    @Test
    @DisplayName("카드를 잘 받는다.")
    void addCardTest() {
        PlayerStatus playerStatus = new PlayerStatus();
        playerStatus.addCard(new Card(Shape.CLOVER, Rank.TWO));

        assertThat(playerStatus.getHand().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("점수를 계산한다")
    void calculateScoreTest() {
        PlayerStatus playerStatus = new PlayerStatus();
        playerStatus.addCard(new Card(Shape.CLOVER, Rank.TWO));
        playerStatus.addCard(new Card(Shape.CLOVER, Rank.ACE));

        assertThat(playerStatus.calculateScore()).isEqualTo(13);
    }

    @Test
    @DisplayName("돈을 추가한다")
    void addMoneyTest() {
        PlayerStatus playerStatus = new PlayerStatus();
        playerStatus.addMoney(3000);

        assertThat(playerStatus.getMoney()).isEqualTo(3000);
    }

    @Test
    @DisplayName("이긴 후 금액을 계산한다")
    void calculateWinMoneyTest() {
        PlayerStatus playerStatus = new PlayerStatus();
        playerStatus.addMoney(3000);
        playerStatus.calculateWinMoney();

        assertThat(playerStatus.getMoney()).isEqualTo(6000);
    }

    @Test
    @DisplayName("진 후 금액을 계산한다")
    void calculateLoseMoneyTest() {
        PlayerStatus playerStatus = new PlayerStatus();
        playerStatus.addMoney(3000);
        playerStatus.calculateLoseMoney();

        assertThat(playerStatus.getMoney()).isEqualTo(-3000);
    }


}
