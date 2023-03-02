package domain;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("딜러는 ")
class DealerTest {

    @Test
    void 카드를_가질_수_있다() {
        //given
        Dealer dealer = new Dealer();
        int beforeCount = dealer.getCards().size();

        //when
        dealer.addCard(new Card(Rank.ACE, Suit.CLUB));

        //then
        int afterCount = dealer.getCards().size();

        assertThat(beforeCount + 1).isEqualTo(afterCount);
    }
}