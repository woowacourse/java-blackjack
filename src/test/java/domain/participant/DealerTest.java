package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Shape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(ReplaceUnderscores.class)
class DealerTest {

    private Dealer dealer;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
    }

    @Test
    void 딜러는_카드를_받을_수_있다() {
        //given
        //when
        dealer.takeCards(Card.SPADE_2, Card.HEART_3);
        //then
        assertThat(dealer.getCards()).contains(Card.SPADE_2, Card.HEART_3);
    }

    @Test
    void 딜러는_오픈카드는_가장_처음_받은_카드이다() {
        //given
        dealer.takeCards(Card.SPADE_2, Card.HEART_3);

        //when
        var card = dealer.getOpenCard();

        //then
        assertThat(card).isEqualTo(Card.SPADE_2);
    }

    @Test
    void 딜러의_카드의_점수_합을_구할_수_있다() {
        //given
        dealer.takeCards(Card.SPADE_2, Card.HEART_3);

        //when
        int score = dealer.calculateScore();

        assertThat(score).isEqualTo(5);
    }

    @Test
    void 딜러의_점수가_16점_이하일_때_카드를_더_받을수있다() {
        //given
        dealer.takeCards(Card.SPADE_J, Card.SPADE_6);

        //when
        boolean canTakeMore = dealer.canTakeMoreCard();

        //then
        assertThat(canTakeMore).isTrue();
    }

    @Test
    void 딜러의_점수가_17점_이상이면_카드를_더_받을수없다() {
        //given
        dealer.takeCards(Card.SPADE_J, Card.SPADE_7);

        //when
        boolean canTakeMore = dealer.canTakeMoreCard();

        //then
        assertThat(canTakeMore).isFalse();
    }
}
