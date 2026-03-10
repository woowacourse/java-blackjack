package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.Test;

public class BlackJackJudgeTest {
    @Test
    void 플레이어_버스트로_플레이어_패() {
        // arrange
        Players players = new Players(List.of("이산"));
        Dealer dealer = new Dealer();
        Player player = players.getPlayers().getFirst();
        player.hand.addCard(new Card(CardPoint.TEN, CardPattern.DIAMOND));
        player.hand.addCard(new Card(CardPoint.FIVE, CardPattern.HEART));
        player.hand.addCard(new Card(CardPoint.EIGHT, CardPattern.CLUB));
        dealer.hand.addCard(new Card(CardPoint.TEN, CardPattern.SPADE));
        dealer.hand.addCard(new Card(CardPoint.FIVE, CardPattern.CLUB));
        BlackJackJudge blackJackJudge = new BlackJackJudge();

        // act
        HashMap<Player, GameResult> result = blackJackJudge.judge(players, dealer);

        // assert
        assertThat(result.get(player)).isEqualTo(GameResult.LOSE);
    }

    @Test
    void 딜러_버스트로_플레이어_승() {
        // arrange
        Players players = new Players(List.of("이산"));
        Dealer dealer = new Dealer();
        Player player = players.getPlayers().getFirst();
        player.hand.addCard(new Card(CardPoint.TEN, CardPattern.DIAMOND));
        player.hand.addCard(new Card(CardPoint.FIVE, CardPattern.HEART));
        dealer.hand.addCard(new Card(CardPoint.TEN, CardPattern.SPADE));
        dealer.hand.addCard(new Card(CardPoint.FIVE, CardPattern.CLUB));
        dealer.hand.addCard(new Card(CardPoint.SEVEN, CardPattern.SPADE));
        BlackJackJudge blackJackJudge = new BlackJackJudge();

        // act
        HashMap<Player, GameResult> result = blackJackJudge.judge(players, dealer);

        // assert
        assertThat(result.get(player)).isEqualTo(GameResult.WIN);
    }

    @Test
    void 딜러_플레이어_점수_비교_플레이어_승() {
        // arrange
        Players players = new Players(List.of("이산"));
        Dealer dealer = new Dealer();
        Player player = players.getPlayers().getFirst();
        player.hand.addCard(new Card(CardPoint.TEN, CardPattern.DIAMOND));
        player.hand.addCard(new Card(CardPoint.FIVE, CardPattern.HEART));
        player.hand.addCard(new Card(CardPoint.SIX, CardPattern.DIAMOND));
        dealer.hand.addCard(new Card(CardPoint.TEN, CardPattern.SPADE));
        dealer.hand.addCard(new Card(CardPoint.SEVEN, CardPattern.SPADE));
        BlackJackJudge blackJackJudge = new BlackJackJudge();

        // act
        HashMap<Player, GameResult> result = blackJackJudge.judge(players, dealer);

        // assert
        assertThat(result.get(player)).isEqualTo(GameResult.WIN);
    }

    @Test
    void 딜러_플레이어_점수_비교_플레이어_무() {
        // arrange
        Players players = new Players(List.of("이산"));
        Dealer dealer = new Dealer();
        Player player = players.getPlayers().getFirst();
        player.hand.addCard(new Card(CardPoint.TEN, CardPattern.DIAMOND));
        player.hand.addCard(new Card(CardPoint.FIVE, CardPattern.HEART));
        player.hand.addCard(new Card(CardPoint.SIX, CardPattern.DIAMOND));
        dealer.hand.addCard(new Card(CardPoint.TEN, CardPattern.SPADE));
        dealer.hand.addCard(new Card(CardPoint.ACE, CardPattern.SPADE));
        BlackJackJudge blackJackJudge = new BlackJackJudge();

        // act
        HashMap<Player, GameResult> result = blackJackJudge.judge(players, dealer);

        // assert
        assertThat(result.get(player)).isEqualTo(GameResult.TIE);
    }

    @Test
    void 딜러_플레이어_점수_비교_플레이어_패() {
        // arrange
        Players players = new Players(List.of("이산"));
        Dealer dealer = new Dealer();
        Player player = players.getPlayers().getFirst();
        player.hand.addCard(new Card(CardPoint.TEN, CardPattern.DIAMOND));
        player.hand.addCard(new Card(CardPoint.SEVEN, CardPattern.HEART));
        dealer.hand.addCard(new Card(CardPoint.EIGHT, CardPattern.DIAMOND));
        dealer.hand.addCard(new Card(CardPoint.TEN, CardPattern.SPADE));
        dealer.hand.addCard(new Card(CardPoint.ACE, CardPattern.SPADE));
        BlackJackJudge blackJackJudge = new BlackJackJudge();

        // act
        HashMap<Player, GameResult> result = blackJackJudge.judge(players, dealer);

        // assert
        assertThat(result.get(player)).isEqualTo(GameResult.LOSE);
    }
}
