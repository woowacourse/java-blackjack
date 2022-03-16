package blackjack.model.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.card.Card;
import blackjack.model.card.CardDeck;

import blackjack.model.card.TrumpNumber;
import blackjack.model.card.TrumpSymbol;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @DisplayName("Dealer가 정상적으로 생성되는지 확인한다.")
    @Test
    void construct_Dealer() {
        Participant liver = new Dealer();

        assertThat(liver).isInstanceOf(Dealer.class);
    }

    @DisplayName("Card를 받으면 새로운 Dealer 인스턴스를 반환한다.")
    @Test
    void drawSeveral_new_Participant() {
        Participant dealer = new Dealer();
        Participant otherDealer = dealer.receive(new Card(TrumpNumber.EIGHT, TrumpSymbol.HEART));

        assertThat(dealer).isNotEqualTo(otherDealer);
    }

    @DisplayName("Card를 hit 하면 새로운 Dealer 인스턴스를 반환한다.")
    @Test
    void draw_new_Participant() {
        Participant dealer = new Dealer();
        Participant otherDealer = dealer.hit(new Card(TrumpNumber.ACE, TrumpSymbol.CLOVER));

        assertThat(dealer).isNotEqualTo(otherDealer);
    }

    @DisplayName("카드 숫자의 합이 16을 넘으면 true를 반환한다.")
    @Test
    void isfinish_true() {
        CardDeck cardDeck = new CardDeck();
        Dealer dealer = new Dealer();

    }
}
