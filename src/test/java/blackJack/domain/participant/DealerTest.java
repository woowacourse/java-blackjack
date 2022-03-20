package blackJack.domain.participant;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackJack.domain.card.Card;
import blackJack.domain.card.Denomination;
import blackJack.domain.card.Suit;

public class DealerTest {

    @Test
    @DisplayName("Dealer를 성공적으로 생성할 경우 null 값을 반환하지 않는다.")
    void createValidDealer() {
        assertThat(new Dealer()).isNotNull();
    }

    @Test
    @DisplayName("딜러의 카드 합계의 총 점수를 계산할 수 있다.")
    void calculateScore() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(Card.valueOf(Suit.CLOVER, Denomination.EIGHT));

        assertThat(dealer.getScore()).isEqualTo(8);
    }

    @Test
    @DisplayName("딜러의 카드 추가 분배가 불가능한 경우 False를 반환한다.")
    void hasFalseDealerNextTurn() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(Card.valueOf(Suit.SPADE, Denomination.SEVEN));
        dealer.receiveCard(Card.valueOf(Suit.HEART, Denomination.JACK));

        assertThat(dealer.isAvailableHit()).isFalse();
    }

    @Test
    @DisplayName("딜러의 카드 추가 분배가 가능한 경우 True를 반환한다.")
    void hasTrueDealerNextTurn() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(Card.valueOf(Suit.SPADE, Denomination.SIX));
        dealer.receiveCard(Card.valueOf(Suit.HEART, Denomination.JACK));

        assertThat(dealer.isAvailableHit()).isTrue();
    }
}
