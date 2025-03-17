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
        Hand dealerHand = createHandWithCards(CardRank.ACE, CardRank.TEN);
        Dealer dealer = new Dealer(dealerHand);

        // when
        Score score = new Score(dealer);

        // then
        assertEquals(21, score.getScoreValue());
    }

    @Test
    void 점수가_21이_넘으면_busted로_판단한다() {
        // given
        Hand dealerHand = createHandWithCards(CardRank.TWO, CardRank.TEN, CardRank.TEN);
        Dealer dealer = new Dealer(dealerHand);
        Score score = new Score(dealer);

        // when
        boolean isBusted = score.isBusted();

        // then
        assertTrue(isBusted);
    }

    @Test
    void 카드가_2장이고_점수가_21이라면_블랙잭이다() {
        // given
        Hand dealerHand = createHandWithCards(CardRank.ACE, CardRank.TEN);
        Dealer dealer = new Dealer(dealerHand);
        Score score = new Score(dealer);

        // when
        boolean isBlackjack = score.isBlackJack();

        // then
        assertTrue(isBlackjack);
    }

    @Test
    void 둘_다_블랙잭인_경우_무승부를_저장한다() {
        Hand comparedHand = createHandWithCards(CardRank.ACE, CardRank.TEN);
        Hand hand = createHandWithCards(CardRank.ACE, CardRank.TEN);

        Dealer dealer = new Dealer(comparedHand);
        Score dealerScore = new Score(dealer);

        Player player = new Player("히로", hand, new BetAmount(1_000));
        Score playerScore = new Score(player);

        GameResult gameResult = playerScore.calculateGameResult(dealerScore);
        assertTrue(gameResult.isTie());
    }

    @Test
    void 상대만_블랙잭인_경우_패한다() {
        Hand comparedHand = createHandWithCards(CardRank.ACE, CardRank.TEN);
        Hand hand = createHandWithCards(CardRank.TWO, CardRank.TEN);

        Dealer dealer = new Dealer(comparedHand);
        Score dealerScore = new Score(dealer);

        Player player = new Player("히로", hand, new BetAmount(1_000));
        Score playerScore = new Score(player);

        GameResult gameResult = playerScore.calculateGameResult(dealerScore);
        assertTrue(gameResult.isLose());
    }

    @Test
    void 둘_다_버스트_된_경우_무승부를_저장한다() {
        Hand comparedHand = createHandWithCards(CardRank.TEN, CardRank.TEN, CardRank.TEN);
        Hand hand = createHandWithCards(CardRank.TEN, CardRank.TEN, CardRank.TEN);

        Dealer dealer = new Dealer(comparedHand);
        Score dealerScore = new Score(dealer);

        Player player = new Player("히로", hand, new BetAmount(1_000));
        Score playerScore = new Score(player);

        GameResult gameResult = playerScore.calculateGameResult(dealerScore);
        assertTrue(gameResult.isTie());
    }

    @Test
    void 해당_스코어만_버스트된_경우_진다() {
        Hand comparedHand = createHandWithCards(CardRank.TEN, CardRank.TEN);
        Hand hand = createHandWithCards(CardRank.TEN, CardRank.TEN, CardRank.TEN);

        Dealer dealer = new Dealer(comparedHand);
        Score dealerScore = new Score(dealer);

        Player player = new Player("히로", hand, new BetAmount(1_000));
        Score playerScore = new Score(player);

        GameResult gameResult = playerScore.calculateGameResult(dealerScore);
        assertTrue(gameResult.isLose());
    }

    @Test
    void 상대만_버스트인_경우_이긴다() {
        Hand comparedHand = createHandWithCards(CardRank.TEN, CardRank.TEN, CardRank.TEN);
        Hand hand = createHandWithCards(CardRank.TEN, CardRank.TEN);

        Dealer dealer = new Dealer(comparedHand);
        Score dealerScore = new Score(dealer);

        Player player = new Player("히로", hand, new BetAmount(1_000));
        Score playerScore = new Score(player);

        GameResult gameResult = playerScore.calculateGameResult(dealerScore);
        assertTrue(gameResult.isWinByNotBlackJack());
    }

    @Test
    void 상대의_결과가_더_클_경우_진다() {
        Hand comparedHand = createHandWithCards(CardRank.TEN, CardRank.FIVE, CardRank.TWO);
        Hand hand = createHandWithCards(CardRank.TEN, CardRank.FIVE);

        Dealer dealer = new Dealer(comparedHand);
        Score dealerScore = new Score(dealer);

        Player player = new Player("히로", hand, new BetAmount(1_000));
        Score playerScore = new Score(player);

        GameResult gameResult = playerScore.calculateGameResult(dealerScore);
        assertTrue(gameResult.isLose());
    }

    @Test
    void 해당_스코어의_결과가_더_큰_경우_이긴다() {
        Hand comparedHand = createHandWithCards(CardRank.TEN, CardRank.FIVE);
        Hand hand = createHandWithCards(CardRank.TEN, CardRank.FIVE, CardRank.TWO);

        Dealer dealer = new Dealer(comparedHand);
        Score dealerScore = new Score(dealer);

        Player player = new Player("히로", hand, new BetAmount(1_000));
        Score playerScore = new Score(player);

        GameResult gameResult = playerScore.calculateGameResult(dealerScore);
        assertTrue(gameResult.isWinByNotBlackJack());
    }

    @Test
    void 결과가_같을_경우_무승부이다() {
        Hand comparedHand = createHandWithCards(CardRank.TEN, CardRank.FIVE, CardRank.TWO);
        Hand hand = createHandWithCards(CardRank.TEN, CardRank.FIVE, CardRank.TWO);

        Dealer dealer = new Dealer(comparedHand);
        Score dealerScore = new Score(dealer);

        Player player = new Player("히로", hand, new BetAmount(1_000));
        Score playerScore = new Score(player);

        GameResult gameResult = playerScore.calculateGameResult(dealerScore);
        assertTrue(gameResult.isTie());
    }

    private Hand createHandWithCards(CardRank... ranks) {
        Hand hand = new Hand();
        CardSuit[] suits = CardSuit.values();

        for (int i = 0; i < ranks.length; i++) {
            hand.takeCard(new Card(suits[i % suits.length], ranks[i]));
        }
        return hand;
    }
}
