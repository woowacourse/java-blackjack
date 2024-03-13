package domain.participant;

import domain.card.Card;
import domain.card.Cards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static domain.card.CardNumber.ACE;
import static domain.card.CardNumber.KING;
import static domain.card.CardShape.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    @DisplayName("딜러는 카드를 한 장 뽑을 수 있다.")
    @Test
    void drawCard() {
        Card card = new Card(SPADE, ACE);
        Dealer dealer = new Dealer(new Cards(List.of(card)));
        assertThat(dealer.drawCard()).isEqualTo(card);
    }

    @DisplayName("딜러는 카드를 참가자에게 줄 수 있다.")
    @Test
    void deal() {
        Card card = new Card(SPADE, ACE);
        Dealer dealer = new Dealer(new Cards(List.of(card)));
        Player player = new Player(new Name("Zeus"));
        dealer.deal(player);
        Cards cards = player.hand().getCards();
        assertThat(cards.draw()).isEqualTo(card);
    }

    @DisplayName("딜러가 카드를 더 받을 수 있는 상황인지 판단할 수 있다.")
    @Test
    void canReceiveMoreCard() {
        Card cardAce = new Card(SPADE, ACE);
        Card cardKing = new Card(SPADE, KING);
        Dealer dealer = new Dealer(new Cards());
        dealer.receive(cardAce);
        dealer.receive(cardKing);
        assertThat(dealer.canReceiveMoreCard()).isEqualTo(false);
    }
}
