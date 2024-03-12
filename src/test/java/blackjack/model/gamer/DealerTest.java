package blackjack.model.gamer;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.card.Rank;
import blackjack.model.card.Pattern;
import blackjack.model.card.Card;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

    @DisplayName("딜러가 히트할 수 있는지 확인한다.")
    @Test
    void canHit() {
        //given
        Dealer dealer = new Dealer();
        Card card = new Card(Pattern.CLOVER, Rank.FIVE);

        //when
        dealer.receiveCard(card);

        //then
        assertThat(dealer.canHit()).isTrue();
    }
}
