package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Suit;
import blackjack.domain.card.Value;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {
    @DisplayName("딜러의 카드 뽑기 테스트")
    @Test
    void testDrawCard() {
        Dealer dealer = new Dealer();

        dealer.drawCard(new Card(Suit.SPADE, Value.EIGHT));
        dealer.drawCard(new Card(Suit.HEART, Value.SIX));

        assertThat(dealer.getCards())
                .containsExactly(new Card(Suit.SPADE, Value.EIGHT), new Card(Suit.HEART, Value.SIX));
    }

    @DisplayName("딜러가 최초로 받은 한 장을 공개 테스트")
    @Test
    void testGetFirstCard() {
        //given
        Dealer dealer = new Dealer();

        //when
        dealer.drawCard(new Card(Suit.SPADE, Value.TEN));
        dealer.drawCard(new Card(Suit.HEART, Value.SEVEN));

        //then
        assertThat(dealer.getFirstCard()).isEqualTo(new Card(Suit.SPADE, Value.TEN));
    }
}

