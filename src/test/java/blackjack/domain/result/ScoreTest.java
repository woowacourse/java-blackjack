package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardSuit;
import blackjack.domain.game.Dealer;
import blackjack.domain.game.Hand;
import blackjack.domain.game.Player;
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

    @Test
    void 둘_다_블랙잭인_경우_무승부를_저장한다() {
        testGameResult(
                createHandWithCards(CardRank.ACE, CardRank.TEN),
                createHandWithCards(CardRank.ACE, CardRank.TEN),  // 플레이어 블랙잭
                GameResultType.TIE
        );
    }

    @Test
    void 상대만_블랙잭인_경우_패한다() {
        testGameResult(
                createHandWithCards(CardRank.ACE, CardRank.TEN),  // 딜러 블랙잭
                createHandWithCards(CardRank.TWO, CardRank.TEN),  // 플레이어 일반
                GameResultType.LOSE
        );
    }

    @Test
    void 둘_다_버스트_된_경우_무승부를_저장한다() {
        testGameResult(
                createHandWithCards(CardRank.TEN, CardRank.TEN, CardRank.TEN),  // 딜러 일반
                createHandWithCards(CardRank.TEN, CardRank.TEN, CardRank.TEN),  // 플레이어 블랙잭
                GameResultType.TIE
        );
    }

    @Test
    void 해당_스코어만_버스트된_경우_진다() {
        testGameResult(
                createHandWithCards(CardRank.TEN, CardRank.TEN),  // 딜러 일반
                createHandWithCards(CardRank.TEN, CardRank.TEN, CardRank.TEN),
                GameResultType.LOSE
        );
    }

    @Test
    void 상대만_버스트인_경우_이긴다() {
        testGameResult(
                createHandWithCards(CardRank.TEN, CardRank.TEN, CardRank.TEN),  // 딜러 일반
                createHandWithCards(CardRank.TEN, CardRank.TEN),
                GameResultType.WIN
        );
    }

    @Test
    void 상대의_결과가_더_클_경우_진다() {
        testGameResult(
                createHandWithCards(CardRank.TEN, CardRank.FIVE, CardRank.TWO),  // 딜러 일반
                createHandWithCards(CardRank.TEN, CardRank.FIVE),
                GameResultType.LOSE
        );
    }

    @Test
    void 해당_스코어의_결과가_더_큰_경우_이긴다() {
        testGameResult(
                createHandWithCards(CardRank.TEN, CardRank.FIVE),
                createHandWithCards(CardRank.TEN, CardRank.FIVE, CardRank.TWO),
                GameResultType.WIN
        );
    }

    @Test
    void 결과가_같을_경우_무승부이다() {
        testGameResult(
                createHandWithCards(CardRank.TEN, CardRank.FIVE, CardRank.TWO),
                createHandWithCards(CardRank.TEN, CardRank.FIVE, CardRank.TWO),
                GameResultType.TIE
        );
    }

    private Hand createHandWithCards(CardRank... ranks) {
        Hand hand = new Hand();
        CardSuit[] suits = CardSuit.values();

        for (int i = 0; i < ranks.length; i++) {
            hand.takeCard(new Card(suits[i % suits.length], ranks[i]));
        }
        return hand;
    }

    private void testGameResult(Hand dealerHand, Hand playerHand, GameResultType expectedPlayerResult) {
        Player player = new Player("히로", playerHand, new BetAmount(1_000));
        Dealer dealer = new Dealer(dealerHand);

        Score playerScore = new Score(player);
        Score dealerScore = new Score(dealer);

        GameResultType actual = playerScore.calculateGameResult(dealerScore);

        assertEquals(actual, expectedPlayerResult);
    }

}
