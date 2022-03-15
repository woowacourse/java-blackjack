package blackjack.model.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.card.CardDeck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @DisplayName("Dealer가 정상적으로 생성되는지 확인한다.")
    @Test
    void construct_Dealer() {
        Participant liver = new Dealer();

        assertThat(liver).isInstanceOf(Dealer.class);
    }

    @DisplayName("Card를 여러번 뽑으면 새로운 Dealer 인스턴스를 반환한다.")
    @Test
    void drawSeveral_new_Participant() {
        CardDeck deck = new CardDeck();
        Participant dealer = new Dealer();
        Participant otherDealer = dealer.drawCardsBy(deck);

        assertThat(dealer).isNotEqualTo(otherDealer);
    }

    @DisplayName("Card를 뽑으면 새로운 Dealer 인스턴스를 반환한다.")
    @Test
    void draw_new_Participant() {
        CardDeck deck = new CardDeck();
        Participant dealer = new Dealer();
        Participant otherDealer = dealer.drawBy(deck);

        assertThat(dealer).isNotEqualTo(otherDealer);
    }
}
