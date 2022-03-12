package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Symbol;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    @Test
    @DisplayName("현재 점수가 16점 이하인 경우 참을 반환한다")
    void needMoreCardWhenTrue(){
        Dealer dealer = new Dealer(Cards.of(List.of(
                new Card(Symbol.HEART, Denomination.SIX),
                new Card(Symbol.SPADE,Denomination.JACK)
        )));

        assertThat(dealer.doesNeedToDraw()).isTrue();
    }

    @Test
    @DisplayName("현재 점수가 16점 초과인 경우 거짓을 반환한다")
    void needMoreCardWhenFalse() {
        Dealer dealer = new Dealer(Cards.of(List.of(
                new Card(Symbol.HEART,Denomination.NINE),
                new Card(Symbol.SPADE,Denomination.EIGHT)
        )));

        assertThat(dealer.doesNeedToDraw()).isFalse();
    }

    @Test
    @DisplayName("첫 공개카드는 한 장을 반환한다")
    void testOpenCards() {
        Dealer dealer = new Dealer(Cards.of(List.of(
                new Card(Symbol.HEART,Denomination.NINE),
                new Card(Symbol.SPADE,Denomination.EIGHT)
        )));

        assertThat(dealer.openCards().size()).isEqualTo(1);
    }
}
