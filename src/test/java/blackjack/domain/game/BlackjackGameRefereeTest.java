package blackjack.domain.game;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.participants.Hand;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import blackjack.domain.participants.Bet;
import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Player;
import java.util.List;
import org.junit.jupiter.api.Test;

class BlackjackGameRefereeTest {
    private final String playerName = "플레이어";
    private final Bet bet = new Bet(1000L);

    private final Hand lowerScoreHand =
        new Hand(List.of(new Card(Rank.TEN, Suit.DIAMOND)));
    private final Hand defaultScoreHand =
        new Hand(List.of(new Card(Rank.TEN, Suit.DIAMOND), new Card(Rank.FIVE, Suit.CLOVER)));
    private final Hand higherScoreHand =
        new Hand(List.of(new Card(Rank.TEN, Suit.DIAMOND), new Card(Rank.TEN, Suit.CLOVER)));
    private final Hand bustScoreHand =
        new Hand(List.of(new Card(Rank.TEN, Suit.DIAMOND), new Card(Rank.TEN, Suit.CLOVER),
            new Card(Rank.TWO, Suit.CLOVER)));
    private final Hand blackjackHand =
        new Hand(List.of(new Card(Rank.TEN, Suit.DIAMOND), new Card(Rank.ACE, Suit.CLOVER)));

    @Test
    void 둘_다_버스트가_아니면서_플레이어가_점수가_더_높다면_플레이어가_승리한다() {
        // given
        Dealer dealer = new Dealer(lowerScoreHand);
        Player player = new Player(playerName, higherScoreHand, bet);
        // when
        GameResult result = BlackjackGameReferee.judge(dealer, player);
        // then
        assertThat(result).isEqualTo(GameResult.PLAYER_WIN);
    }

    @Test
    void 둘_다_버스트가_아니면서_딜러가_점수가_더_높다면_딜러가_승리한다() {
        // given
        Dealer dealer = new Dealer(higherScoreHand);
        Player player = new Player(playerName, lowerScoreHand, bet);
        // when
        GameResult result = BlackjackGameReferee.judge(dealer, player);
        // then
        assertThat(result).isEqualTo(GameResult.DEALER_WIN);
    }

    @Test
    void 둘_다_버스트가_아니면서_점수가_같다면_무승부한다() {
        // given
        Dealer dealer = new Dealer(defaultScoreHand);
        Player player = new Player(playerName, defaultScoreHand, bet);
        // when
        GameResult result = BlackjackGameReferee.judge(dealer, player);
        // then
        assertThat(result).isEqualTo(GameResult.PUSH);
    }

    @Test
    void 플레이어가_버스트라면_딜러가_승리한다() {
        // given
        Dealer dealer = new Dealer(defaultScoreHand);
        Player player = new Player(playerName, bustScoreHand, bet);
        // when
        GameResult result = BlackjackGameReferee.judge(dealer, player);
        // then
        assertThat(result).isEqualTo(GameResult.DEALER_WIN);
    }

    @Test
    void 딜러만_버스트라면_플레이어가_승리한다() {
        // given
        Dealer dealer = new Dealer(bustScoreHand);
        Player player = new Player(playerName, defaultScoreHand, bet);
        // when
        GameResult result = BlackjackGameReferee.judge(dealer, player);
        // then
        assertThat(result).isEqualTo(GameResult.PLAYER_WIN);
    }

    @Test
    void 둘_다_버스트라면_딜러가_승리한다() {
        // given
        Dealer dealer = new Dealer(bustScoreHand);
        Player player = new Player(playerName, bustScoreHand, bet);
        // when
        GameResult result = BlackjackGameReferee.judge(dealer, player);
        // then
        assertThat(result).isEqualTo(GameResult.DEALER_WIN);
    }

    @Test
    void 플레이어만_블랙잭이면_플레이어가_블랙잭으로_승리한다() {
        // given
        Dealer dealer = new Dealer(defaultScoreHand);
        Player player = new Player(playerName, blackjackHand, bet);
        // when
        GameResult result = BlackjackGameReferee.judge(dealer, player);
        // then
        assertThat(result).isEqualTo(GameResult.PLAYER_BLACKJACK);
    }

    @Test
    void 딜러만_블랙잭이면_딜러가_승리한다() {
        // given
        Dealer dealer = new Dealer(blackjackHand);
        Player player = new Player(playerName, defaultScoreHand, bet);
        // when
        GameResult result = BlackjackGameReferee.judge(dealer, player);
        // then
        assertThat(result).isEqualTo(GameResult.DEALER_WIN);
    }

    @Test
    void 플레이어와_딜러_모두_블랙잭이면_무승부한다() {
        // given
        Dealer dealer = new Dealer(blackjackHand);
        Player player = new Player(playerName, blackjackHand, bet);
        // when
        GameResult result = BlackjackGameReferee.judge(dealer, player);
        // then
        assertThat(result).isEqualTo(GameResult.PUSH);
    }
}