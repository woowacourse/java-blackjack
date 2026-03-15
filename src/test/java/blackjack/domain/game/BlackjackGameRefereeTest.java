package blackjack.domain.game;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import blackjack.domain.participants.Bet;
import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Player;
import java.util.List;
import org.junit.jupiter.api.Test;

class BlackjackGameRefereeTest {
    private final String playerName = "플레이어";
    private final BlackjackGameReferee referee = new BlackjackGameReferee();

    private final Hand lowerScoreHand =
        new Hand(List.of(new Card(Rank.TEN, Suit.DIAMOND)));
    private final Hand defaultScoreHand =
        new Hand(List.of(new Card(Rank.TEN, Suit.DIAMOND), new Card(Rank.FIVE, Suit.CLOVER)));
    private final Hand higherScoreHand =
        new Hand(List.of(new Card(Rank.TEN, Suit.DIAMOND), new Card(Rank.TEN, Suit.CLOVER)));
    private final Hand bustScoreHand =
        new Hand(List.of(new Card(Rank.TEN, Suit.DIAMOND), new Card(Rank.TEN, Suit.CLOVER),
            new Card(Rank.TWO, Suit.CLOVER)));

    @Test
    void 둘_다_버스트가_아니면서_플레이어가_점수가_더_높다면_플레이어가_승리한다() {
        // given
        Dealer dealer = new Dealer(lowerScoreHand);
        Player player = new Player(this.playerName, higherScoreHand, Bet.zero());
        // when
        GameResult result = referee.judge(dealer, player);
        // then
        assertThat(result).isEqualTo(GameResult.PLAYER_WIN);
    }

    @Test
    void 둘_다_버스트가_아니면서_딜러가_점수가_더_높다면_딜러가_승리한다() {
        // given
        Dealer dealer = new Dealer(higherScoreHand);
        Player player = new Player(this.playerName, lowerScoreHand, Bet.zero());
        // when
        GameResult result = referee.judge(dealer, player);
        // then
        assertThat(result).isEqualTo(GameResult.DEALER_WIN);
    }

    @Test
    void 둘_다_버스트가_아니면서_점수가_같다면_무승부한다() {
        // given
        Dealer dealer = new Dealer(defaultScoreHand);
        Player player = new Player(this.playerName, defaultScoreHand, Bet.zero());
        // when
        GameResult result = referee.judge(dealer, player);
        // then
        assertThat(result).isEqualTo(GameResult.PUSH);
    }

    @Test
    void 플레이어가_버스트라면_딜러가_승리한다() {
        // given
        Dealer dealer = new Dealer(defaultScoreHand);
        Player player = new Player(this.playerName, bustScoreHand, Bet.zero());
        // when
        GameResult result = referee.judge(dealer, player);
        // then
        assertThat(result).isEqualTo(GameResult.DEALER_WIN);
    }

    @Test
    void 딜러만_버스트라면_플레이어가_승리한다() {
        // given
        Dealer dealer = new Dealer(bustScoreHand);
        Player player = new Player(this.playerName, defaultScoreHand, Bet.zero());
        // when
        GameResult result = referee.judge(dealer, player);
        // then
        assertThat(result).isEqualTo(GameResult.PLAYER_WIN);
    }

    @Test
    void 둘_다_버스트라면_딜러가_승리한다() {
        // given
        Dealer dealer = new Dealer(bustScoreHand);
        Player player = new Player(this.playerName, bustScoreHand, Bet.zero());
        // when
        GameResult result = referee.judge(dealer, player);
        // then
        assertThat(result).isEqualTo(GameResult.DEALER_WIN);
    }
}