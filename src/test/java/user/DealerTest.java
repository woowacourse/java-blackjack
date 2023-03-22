package user;

import static testCards.TestCards.ACE_DIAMOND;
import static testCards.TestCards.SEVEN_DIAMOND;
import static testCards.TestCards.TWO_DIAMOND;

import card.Card;
import card.Denomination;
import card.Hand;
import card.ShuffleMachine;
import card.Suit;
import gameState.BettingAmount;
import gameState.Playing;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import playerState.dealerState.UnderSixteen;

class DealerTest {

    static class BrokenShuffleMachine implements ShuffleMachine {

        @Override
        public void shuffle(List<Card> cards) {

        }
    }

    @DisplayName("딜러가 제일 앞에서 카드 한장을 뽑아 반환한다.")
    @Test
    void draw() {
        Dealer dealer = new Dealer(
            UnderSixteen.of(new Playing(new BettingAmount(1000)), new Hand(null)),
            new BrokenShuffleMachine());
        Assertions.assertThat(dealer.draw()).isEqualTo(new Card(Denomination.ACE, Suit.CLOVER));
    }

    @DisplayName("딜러가 가진 모든 카드를 반환한다.")
    @Test
    void getCards() {
        Dealer dealer = new Dealer(
            UnderSixteen.of(new Playing(new BettingAmount(1000)), new Hand(null)),
            new BrokenShuffleMachine());
        dealer.addCard(TWO_DIAMOND);
        dealer.addCard(ACE_DIAMOND);
        Assertions.assertThat(dealer.getCards()).containsExactly(TWO_DIAMOND, ACE_DIAMOND);
    }

    @DisplayName("딜러의 점수가 16이하일 경우 true를 반환한다.")
    @Test
    void isUnderSixteen() {
        Dealer dealer = new Dealer(
            UnderSixteen.of(new Playing(new BettingAmount(1000)), new Hand(null)),
            new BrokenShuffleMachine());
        dealer.addCard(TWO_DIAMOND);
        dealer.addCard(ACE_DIAMOND);
        Assertions.assertThat(dealer.isUnderSixteen()).isTrue();
    }

    @DisplayName("딜러의 점수가 16보다 클 경우 rue를 반환한다.")
    @Test
    void isNotUnderSixteen() {
        Dealer dealer = new Dealer(
            UnderSixteen.of(new Playing(new BettingAmount(1000)), new Hand(null)),
            new BrokenShuffleMachine());
        dealer.addCard(TWO_DIAMOND);
        dealer.addCard(SEVEN_DIAMOND);
        dealer.addCard(ACE_DIAMOND);
        Assertions.assertThat(dealer.isUnderSixteen()).isFalse();
    }

    @DisplayName("딜러의 점수를 조회한다.")
    @Test
    void getPoint() {
        Dealer dealer = new Dealer(
            UnderSixteen.of(new Playing(new BettingAmount(1000)), new Hand(null)),
            new BrokenShuffleMachine());
        dealer.addCard(TWO_DIAMOND);
        dealer.addCard(SEVEN_DIAMOND);
        Assertions.assertThat(dealer.getPoint()).isEqualTo(9);
    }

    @DisplayName("딜러의 현 상태를 조회한다.")
    @Test
    void getDealerStatus() {
        Dealer dealer = new Dealer(
            UnderSixteen.of(new Playing(new BettingAmount(1000)), new Hand(null)),
            new BrokenShuffleMachine());
        Assertions.assertThat(dealer.getDealerStatus()).isInstanceOf(UnderSixteen.class);
    }
}
