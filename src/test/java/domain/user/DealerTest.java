package domain.user;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.deck.Card;
import domain.deck.Symbol;
import domain.deck.Type;

class DealerTest {

    @Test
    @DisplayName("생성 확인")
    void create() {
        assertThat(Dealer.appoint()).isNotNull();
    }

    @Test
    @DisplayName("첫 카드 분배 결과")
    void getFirstDrawResult() {
        Dealer dealer = Dealer.appoint();
        dealer.draw(new Card(Symbol.CLOVER, Type.EIGHT));
        dealer.draw(new Card(Symbol.DIAMOND, Type.ACE));

        assertThat(dealer.getFirstDrawResult()).isEqualTo("딜러카드: 8클로버");
    }
}