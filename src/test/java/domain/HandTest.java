package domain;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Rank;
import domain.card.Suit;
import domain.participant.Hand;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class HandTest {

    @Test
    void 참가자가_가진_카드_목록을_반환한다() {
        Hand hand = new Hand();

        Card card1 = new Card(Rank.TWO, Suit.HEART);
        Card card2 = new Card(Rank.ACE, Suit.HEART);

        hand.add(card1);
        hand.add(card2);

        List<Card> cards = hand.cards();
        assertThat(cards).containsExactly(card1, card2);
    }

    @Test
    void 참가자가_가진_카드_두_장의_합이_21인지_반환한다() {
        Cards cards = CardFixture.blackjackCards();
        Hand hand = createHand(cards);

        assertThat(hand.isBlackjack()).isTrue();
    }

    @Test
    void 참가자가_가진_카드의_합이_21을_초과하는지_반환한다() {
        Cards cards = CardFixture.twentyTwoCards();
        Hand hand = createHand(cards);

        assertThat(hand.isBust()).isTrue();
    }

    @Test
    void 카드의_합을_계산한다() {
        Cards cards = CardFixture.seventeenCards();
        Hand hand = createHand(cards);

        assertThat(hand.score()).isEqualTo(17);
    }

    @Nested
    class 참가자가_가진_카드_중_에이스가_존재하는_경우_카드_합을_21을_초과하지_않되_21에_근접하도록_계산한다 {
        @Test
        void 에이스_카드가_여러장일_경우(){
            Cards cards = CardFixture.seventeenCardsWithAces();
            Hand hand = createHand(cards);

            assertThat(hand.score()).isEqualTo(17);
        }

        @Test
         void 에이스_카드가_한_장일_경우(){
             Cards cards = CardFixture.sixteenCardsWithAce();
             Hand hand = createHand(cards);

             int score = hand.score();

             assertThat(score).isEqualTo(16);
         }

    }

    private Hand createHand(Cards cards) {
        Hand hand = new Hand();
        cards.cards().forEach(hand::add);
        return hand;
    }
}
