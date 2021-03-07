package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.painting.Suit;
import blackjack.domain.card.painting.Symbol;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {
    @DisplayName("딜러의 카드 뽑기 테스트")
    @Test
    void testDrawCard() {
        Dealer dealer = new Dealer();

        dealer.drawCard(new Card(Suit.SPADE, Symbol.EIGHT));
        dealer.drawCard(new Card(Suit.HEART, Symbol.SIX));

        List<Card> cards = dealer.getCards().stream()
                .collect(toList());
        assertThat(cards)
                .containsExactly(new Card(Suit.SPADE, Symbol.EIGHT), new Card(Suit.HEART, Symbol.SIX));
    }

    @DisplayName("딜러가 지닌 카드의 합이 16이하인 경우 카드를 한 장 더 뽑는지 테스트")
    @Test
    void testIsUnderSixteen() {
        //given
        Dealer dealer = new Dealer();

        //when
        dealer.drawCard(new Card(Suit.SPADE, Symbol.EIGHT));
        dealer.drawCard(new Card(Suit.HEART, Symbol.SIX));

        //then
        assertThat(dealer.isUnderSixteen()).isTrue();
    }

    @DisplayName("딜러가 최초로 받은 한 장을 공개 테스트")
    @Test
    void testGetFirstCard() {
        //given
        Dealer dealer = new Dealer();

        //when
        dealer.drawCard(new Card(Suit.SPADE, Symbol.TEN));
        dealer.drawCard(new Card(Suit.HEART, Symbol.SEVEN));

        //then
        assertThat(dealer.getFirstCard()).isEqualTo(new Card(Suit.SPADE, Symbol.TEN));
    }
}

