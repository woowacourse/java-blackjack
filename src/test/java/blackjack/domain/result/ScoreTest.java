package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardSuit;
import blackjack.domain.game.Dealer;
import blackjack.domain.game.Hand;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class ScoreTest {

    @Test
    void 참가자의_정보를_토대로_점수를_저장한다() {
        // given
        Dealer dealer = new Dealer(new Hand());
        dealer.takeCard(new Card(CardSuit.HEART, CardRank.ACE));
        dealer.takeCard(new Card(CardSuit.DIAMOND, CardRank.TEN));

        // when
        Score score = new Score(dealer);

        // then
        assertEquals(21, score.getScoreValue());
    }

    @Test
    void 점수가_21이_넘으면_busted로_판단한다() {
        // given
        Dealer dealer = new Dealer(new Hand());
        dealer.takeCard(new Card(CardSuit.HEART, CardRank.TWO));
        dealer.takeCard(new Card(CardSuit.DIAMOND, CardRank.TEN));
        dealer.takeCard(new Card(CardSuit.CLUB, CardRank.TEN));

        Score score = new Score(dealer);

        // when
        boolean isBusted = score.isBusted();

        // then
        assertTrue(isBusted);
    }

    @Test
    void 카드가_2장이고_점수가_21이라면_블랙잭이다() {
        // given
        Dealer dealer = new Dealer(new Hand());
        dealer.takeCard(new Card(CardSuit.HEART, CardRank.ACE));
        dealer.takeCard(new Card(CardSuit.DIAMOND, CardRank.TEN));

        Score score = new Score(dealer);

        // when
        boolean isBusted = score.isBlackJack();

        // then
        assertTrue(isBusted);
    }

}
