package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    @Test
    @DisplayName("추가 카드가 필요한 경우 참을 반환한다")
    void needMoreCardWhenTrue(){
        Dealer dealer = new Dealer(List.of(
                new Card(Symbol.HEART,Denomination.TWO),
                new Card(Symbol.SPADE,Denomination.FIVE)
        ));

        assertThat(dealer.needMoreCard()).isTrue();
    }

    @Test
    @DisplayName("추가 카드가 필요없는 경우 거짓을 반환한다")
    void needMoreCardWhenFalse() {
        Dealer dealer = new Dealer(List.of(
                new Card(Symbol.HEART,Denomination.NINE),
                new Card(Symbol.SPADE,Denomination.NINE)
        ));

        assertThat(dealer.needMoreCard()).isFalse();
    }
}
