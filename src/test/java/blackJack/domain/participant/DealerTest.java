package blackJack.domain.participant;

import blackJack.domain.card.Card;
import blackJack.domain.card.Denomination;
import blackJack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
        dealer.receiveCard(new Card(Suit.CLOVER, Denomination.EIGHT));

        assertThat(dealer.getScore()).isEqualTo(8);
    }

    @Test
    @DisplayName("딜러의 카드 추가 분배가 불가능한 경우 테스트")
    void hasFalseDealerNextTurn() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Suit.SPADE, Denomination.SEVEN));
        dealer.receiveCard(new Card(Suit.HEART, Denomination.JACK));

        assertThat(dealer.hasNextTurn()).isFalse();
    }

    @Test
    @DisplayName("딜러의 카드 추가 분배가 가능한 경우 테스트")
    void hasTrueDealerNextTurn() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Suit.SPADE, Denomination.SIX));
        dealer.receiveCard(new Card(Suit.HEART, Denomination.JACK));

        assertThat(dealer.hasNextTurn()).isTrue();
    }
}
