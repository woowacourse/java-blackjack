package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.cards.Card;
import blackjack.domain.cards.Rank;
import blackjack.domain.cards.Shape;
import blackjack.domain.participants.GamblingMoney;
import blackjack.domain.participants.PlayerStatus;
import blackjack.domain.participants.Result;
import org.assertj.core.api.Assertions;
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
    @DisplayName("점수를 계산한다.")
    void calculateScoreTest() {
        PlayerStatus playerStatus = new PlayerStatus();
        playerStatus.addCard(new Card(Shape.CLOVER, Rank.TWO));
        playerStatus.addCard(new Card(Shape.CLOVER, Rank.ACE));

        assertThat(playerStatus.calculateScore()).isEqualTo(13);
    }

    @Test
    @DisplayName("돈을 추가한다.")
    void addMoneyTest() {
        PlayerStatus playerStatus = new PlayerStatus();
        playerStatus.addMoney(new GamblingMoney(3000));

        assertThat(playerStatus.getGamblingMoney()).isEqualTo(new GamblingMoney(3000));
    }

    @Test
    @DisplayName("돈을 뺀다.")
    void subtractMoney() {
        PlayerStatus playerStatus = new PlayerStatus();
        playerStatus.subtractMoney(new GamblingMoney(3000));

        assertThat(playerStatus.getGamblingMoney()).isEqualTo(new GamblingMoney(-3000));
    }

    @Test
    @DisplayName("블랙잭으로 이겼을 때 배팅 금액을 계산한다.")
    void calculateBettingMoneyWhenBlackjackWin() {
        PlayerStatus playerStatus = new PlayerStatus();
        playerStatus.addMoney(new GamblingMoney(3000));

        playerStatus.calculateBettingMoney(Result.BLACKJACK_WIN.getBenefit());

        Assertions.assertThat(playerStatus.getGamblingMoney().getValue()).isEqualTo(4500);
    }

    @Test
    @DisplayName("이겼을 때 배팅 금액을 계산한다.")
    void calculateBettingMoneyWhenWin() {
        PlayerStatus playerStatus = new PlayerStatus();
        playerStatus.addMoney(new GamblingMoney(3000));

        playerStatus.calculateBettingMoney(Result.WIN.getBenefit());

        Assertions.assertThat(playerStatus.getGamblingMoney().getValue()).isEqualTo(3000);
    }

    @Test
    @DisplayName("비겼을 때 배팅 금액을 계산한다.")
    void calculateBettingMoneyWhenTie() {
        PlayerStatus playerStatus = new PlayerStatus();
        playerStatus.addMoney(new GamblingMoney(3000));

        playerStatus.calculateBettingMoney(Result.TIE.getBenefit());

        Assertions.assertThat(playerStatus.getGamblingMoney().getValue()).isEqualTo(0);
    }

    @Test
    @DisplayName("졌을 때 배팅 금액을 계산한다.")
    void calculateBettingMoneyWhenLose() {
        PlayerStatus playerStatus = new PlayerStatus();
        playerStatus.addMoney(new GamblingMoney(3000));

        playerStatus.calculateBettingMoney(Result.LOSE.getBenefit());

        Assertions.assertThat(playerStatus.getGamblingMoney().getValue()).isEqualTo(-3000);
    }
}
