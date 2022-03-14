package blackJack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackJack.domain.card.Card;
import blackJack.domain.card.Denomination;
import blackJack.domain.card.Symbol;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    @DisplayName("Dealer 생성 테스트")
    void createValidDealer() {
        assertThat(new Dealer()).isNotNull();
    }

    @Test
    @DisplayName("딜러의 카드 합계 계산 테스트")
    void calculateScore() {
        Dealer dealer = new Dealer();
        dealer.hit(Card.from(Symbol.CLOVER, Denomination.EIGHT));

        assertThat(dealer.getScore()).isEqualTo(8);
    }

    @Test
    @DisplayName("딜러의 카드 추가 분배가 불가능한 경우 테스트")
    void hasFalseDealerNextTurn() {
        Dealer dealer = new Dealer();
        dealer.hit(Card.from(Symbol.SPADE, Denomination.SEVEN));
        dealer.hit(Card.from(Symbol.HEART, Denomination.JACK));

        assertThat(dealer.canHit()).isFalse();
    }

    @Test
    @DisplayName("딜러의 카드 추가 분배가 가능한 경우 테스트")
    void hasTrueDealerNextTurn() {
        Dealer dealer = new Dealer();
        dealer.hit(Card.from(Symbol.SPADE, Denomination.SIX));
        dealer.hit(Card.from(Symbol.HEART, Denomination.JACK));

        assertThat(dealer.canHit()).isTrue();
    }
}
