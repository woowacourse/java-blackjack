package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Card;
import blackjack.domain.Deck;
import blackjack.domain.Denomination;
import blackjack.domain.Suit;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    @DisplayName("딜러가 정상적으로 생성되는지 확인")
    void create() {
    	Dealer dealer = new Dealer();
        assertThat(dealer).isNotNull();
    }

    @Test
    @DisplayName("딜러가 카드를 정상적으로 주는지 확인")
    void drawCard() {
        Dealer dealer = new Dealer();
        Card card = dealer.drawCards();
        assertThat(card).isNotNull();
    }

    @Test
    @DisplayName("딜러는 카드의 수가 16이하 일 때 한 장의 카드를 더 받는다")
    void dealerReceiveCard() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Denomination.JACK, Suit.DIAMOND));
        cards.add(new Card(Denomination.SIX, Suit.DIAMOND));

        Deck deck = new Deck(cards);
        Dealer dealer = new Dealer(deck);
        dealer.selfDraw();
        dealer.selfDraw();

        Assertions.assertThat(dealer.shouldReceive()).isTrue();
    }

    @Test
    @DisplayName("딜러는 카드의 수가 17이상일 떄 카드를 받을 수 없다")
    void dealerCannotReceiveCard() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Denomination.JACK, Suit.DIAMOND));
        cards.add(new Card(Denomination.SEVEN, Suit.DIAMOND));

        Deck deck = new Deck(cards);
        Dealer dealer = new Dealer(deck);
        dealer.selfDraw();
        dealer.selfDraw();

        Assertions.assertThat(dealer.shouldReceive()).isFalse();
    }
}
