package blackjack.domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.game.Score;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class HandTest {

    @Test
    void Hand_는_카드를_받아_생성된다() {
        //given
        Card card1 = new Card(CardNumber.QUEEN, CardShape.SPADE);
        Card card2 = new Card(CardNumber.QUEEN, CardShape.SPADE);

        //then
        assertDoesNotThrow(() -> new Hand(card1, card2));
    }

    @Test
    void Hand_는_자신이_들고있는_카드의_점수를_계산한다() {
        //given
        Card card1 = new Card(CardNumber.QUEEN, CardShape.SPADE);
        Card card2 = new Card(CardNumber.FIVE, CardShape.SPADE);
        Hand hand = new Hand(card1, card2);

        //when
        Score score = hand.getScore();

        //then
        assertThat(score).isEqualTo(new Score(15));
    }

    @Test
    void Hand_ACE_가있을_시_21을_넘으면_점수에서_10을_뺸다() {
        //given
        Card card1 = new Card(CardNumber.QUEEN, CardShape.SPADE);
        Card card2 = new Card(CardNumber.JACK, CardShape.SPADE);
        Card ace = new Card(CardNumber.ACE, CardShape.SPADE);
        Hand hand = new Hand(card1, card2, ace);

        //when
        Score score = hand.getScore();

        //then
        assertThat(score).isEqualTo(new Score(21));
    }
}
