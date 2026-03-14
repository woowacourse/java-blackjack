package blackjack.domain.result;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.List;
import org.junit.jupiter.api.Test;

class GameJudgeTest {
    private static final String PLAYER_NAME = "플레이어";

    private static final Hand LOWER_SCORE_HAND =
        new Hand(List.of(new Card(Rank.TEN, Suit.DIAMOND)));
    private static final Hand DEFAULT_SCORE_HAND =
        new Hand(List.of(new Card(Rank.TEN, Suit.DIAMOND), new Card(Rank.FIVE, Suit.CLOVER)));
    private static final Hand HIGHER_SCORE_HAND =
        new Hand(List.of(new Card(Rank.TEN, Suit.DIAMOND), new Card(Rank.TEN, Suit.CLOVER)));
    private static final Hand BUST_SCORE_HAND =
        new Hand(List.of(new Card(Rank.TEN, Suit.DIAMOND), new Card(Rank.TEN, Suit.CLOVER),
            new Card(Rank.TWO, Suit.CLOVER)));

    @Test
    void 둘_다_버스트가_아니면서_플레이어가_점수가_더_높다면_플레이어가_승리한다() {
        // given
        Dealer dealer = new Dealer(LOWER_SCORE_HAND);
        Player player = new Player(PLAYER_NAME, HIGHER_SCORE_HAND);
        // when
        GameResult result = GameJudge.of(dealer, player);
        // then
        assertThat(result).isEqualTo(GameResult.PLAYER_WIN);
    }

    @Test
    void 둘_다_버스트가_아니면서_딜러가_점수가_더_높다면_딜러가_승리한다() {
        // given
        Dealer dealer = new Dealer(HIGHER_SCORE_HAND);
        Player player = new Player(PLAYER_NAME, LOWER_SCORE_HAND);
        // when
        GameResult result = GameJudge.of(dealer, player);
        // then
        assertThat(result).isEqualTo(GameResult.DEALER_WIN);
    }

    @Test
    void 둘_다_버스트가_아니면서_점수가_같다면_무승부한다() {
        // given
        Dealer dealer = new Dealer(DEFAULT_SCORE_HAND);
        Player player = new Player(PLAYER_NAME, DEFAULT_SCORE_HAND);
        // when
        GameResult result = GameJudge.of(dealer, player);
        // then
        assertThat(result).isEqualTo(GameResult.PUSH);
    }

    @Test
    void 플레이어가_버스트라면_딜러가_승리한다() {
        // given
        Dealer dealer = new Dealer(DEFAULT_SCORE_HAND);
        Player player = new Player(PLAYER_NAME, BUST_SCORE_HAND);
        // when
        GameResult result = GameJudge.of(dealer, player);
        // then
        assertThat(result).isEqualTo(GameResult.DEALER_WIN);
    }

    @Test
    void 딜러만_버스트라면_플레이어가_승리한다() {
        // given
        Dealer dealer = new Dealer(BUST_SCORE_HAND);
        Player player = new Player(PLAYER_NAME, DEFAULT_SCORE_HAND);
        // when
        GameResult result = GameJudge.of(dealer, player);
        // then
        assertThat(result).isEqualTo(GameResult.PLAYER_WIN);
    }

    @Test
    void 둘_다_버스트라면_딜러가_승리한다() {
        // given
        Dealer dealer = new Dealer(BUST_SCORE_HAND);
        Player player = new Player(PLAYER_NAME, BUST_SCORE_HAND);
        // when
        GameResult result = GameJudge.of(dealer, player);
        // then
        assertThat(result).isEqualTo(GameResult.DEALER_WIN);
    }

}