package blackjack.model.gamer;

import blackjack.model.card.Card;
import blackjack.model.card.Pattern;
import blackjack.model.card.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    @DisplayName("딜러가 받은 카드 중 첫 번째 카드를 확인한다.")
    @Test
    void getFirstCard() {
        //given
        Dealer dealer = new Dealer();
        Card card = new Card(Pattern.CLOVER, Rank.FIVE);
        Card card2 = new Card(Pattern.SPADE, Rank.ACE);

        dealer.receiveCard(card);
        dealer.receiveCard(card2);

        //when
        Card firstCard = dealer.getFirstCard();

        //then
        assertThat(firstCard).isEqualTo(card);
    }

    @DisplayName("딜러 카드의 합이 16 이하일 때 히트할 수 있는지 확인한다.")
    @Test
    void canHit() {
        //given
        Dealer dealer = new Dealer();
        Card card = new Card(Pattern.CLOVER, Rank.FIVE);
        Card card2 = new Card(Pattern.DIAMOND, Rank.ACE);

        //when
        dealer.receiveCard(card);
        dealer.receiveCard(card2);

        //then
        assertThat(dealer.canHit()).isTrue();
    }
}
