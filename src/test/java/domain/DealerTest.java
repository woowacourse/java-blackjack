package domain;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {
    Hand dealerHand;
    Participant dealer;

    @BeforeEach
    void setUp() {
        List<Card> testCards = new ArrayList<>();
        testCards.add(new Card(Rank.EIGHT, Suit.HEART));
        testCards.add(new Card(Rank.EIGHT, Suit.CLOVER));
        dealerHand = new Hand(testCards);
        dealer = new Dealer(dealerHand);
    }

    @Test
    void 딜러는_카드의_합이_16_이하인_경우_카드를_한_장_받는다(){
        List<Card> cards = Deck.prepareCards();
        Deck deck = new Deck(cards);

        dealer.shouldReceive(deck);
        int handSize = dealer.handSize();

        assertThat(handSize).isEqualTo(3);
    }
}