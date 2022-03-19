package blackjack;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.user.Dealer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {
    @DisplayName("초기 state가 true인지 확인하는 테스트")
    @Test
    void isHitTest() {
        assertThat(Dealer.generate().isHit()).isTrue();
    }

    @DisplayName("state가 stay로 설정되었을때 false인지 확인하는 테스트")
    @Test
    void isHitTest2() {
        Dealer dealer = Dealer.generate();
        dealer.addCard(Card.generate(Suit.DIAMOND, Denomination.EIGHT));
        dealer.addCard(Card.generate(Suit.DIAMOND, Denomination.NINE));
        assertThat(dealer.isHit()).isFalse();
    }

    @DisplayName("블랙잭 조건 만족했을때 true인지 확인하는 테스트")
    @Test
    void isBlackjackTest() {
        Dealer dealer = Dealer.generate();
        dealer.addCard(Card.generate(Suit.DIAMOND, Denomination.ACE));
        dealer.addCard(Card.generate(Suit.DIAMOND, Denomination.Q));
        assertThat(dealer.isBlackjack()).isTrue();
    }

    @DisplayName("합은 21이지만 카트가 3장일때 false인지 확인하는 테스트")
    @Test
    void isBlackjackTest2() {
        Dealer dealer = Dealer.generate();
        dealer.addCard(Card.generate(Suit.DIAMOND, Denomination.SIX));
        dealer.addCard(Card.generate(Suit.DIAMOND, Denomination.SEVEN));
        dealer.addCard(Card.generate(Suit.DIAMOND, Denomination.EIGHT));
        assertThat(dealer.isBlackjack()).isFalse();
    }

    @Test
    void isBurstTest_true() {
        Dealer dealer = Dealer.generate();
        dealer.addCard(Card.generate(Suit.DIAMOND, Denomination.Q));
        dealer.addCard(Card.generate(Suit.DIAMOND, Denomination.SEVEN));
        dealer.addCard(Card.generate(Suit.DIAMOND, Denomination.EIGHT));
        assertThat(dealer.isBust()).isTrue();
    }

    @Test
    void isBurstTest_false() {
        Dealer dealer = Dealer.generate();
        dealer.addCard(Card.generate(Suit.DIAMOND, Denomination.TWO));
        dealer.addCard(Card.generate(Suit.DIAMOND, Denomination.SEVEN));
        dealer.addCard(Card.generate(Suit.DIAMOND, Denomination.EIGHT));
        assertThat(dealer.isBust()).isFalse();
    }

    @Test
    void pickOpenCardsTest() {
        Dealer dealer = Dealer.generate();
        dealer.addCard(Card.generate(Suit.DIAMOND, Denomination.EIGHT));
        dealer.addCard(Card.generate(Suit.DIAMOND, Denomination.SEVEN));
        assertThat(dealer.pickOpenCards().numberOfCards()).isEqualTo(1);
    }
}
