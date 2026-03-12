package blackjack.domain.participant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.result.Hand;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import blackjack.domain.result.GameResult;
import java.util.List;
import org.junit.jupiter.api.Test;

class DealerTest {
    private static final Hand EMPTY_HAND = new Hand();

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
    void 점수가_임계점을_초과하면_스탠드한다() {
        // given
        Dealer dealer = new Dealer("딜러", EMPTY_HAND);
        dealer.hit(new Card(Rank.SEVEN, Suit.CLOVER));
        dealer.hit(new Card(Rank.TEN, Suit.CLOVER));
        // when
        boolean canHit = dealer.canHit();
        // then
        assertThat(canHit).isFalse();
    }

    @Test
    void 점수가_임계점_이하면_힛한다() {
        // given
        Dealer dealer = new Dealer("딜러", EMPTY_HAND);
        dealer.hit(new Card(Rank.FIVE, Suit.CLOVER));
        dealer.hit(new Card(Rank.TEN, Suit.CLOVER));
        // when
        boolean shouldHit = dealer.canHit();
        // then
        assertThat(shouldHit).isTrue();
    }

    @Test
    void 둘_다_버스트가_아니면서_플레이어가_점수가_더_높다면_플레이어가_승리한다() {
        // given
        Dealer dealer = new Dealer("딜러", LOWER_SCORE_HAND);
        Player player = new Player("플레이어", HIGHER_SCORE_HAND);
        // when
        GameResult result = dealer.judgeAgainst(player);
        // then
        assertThat(result).isEqualTo(GameResult.PLAYER_WIN);
    }

    @Test
    void 둘_다_버스트가_아니면서_딜러가_점수가_더_높다면_딜러가_승리한다() {
        // given
        Dealer dealer = new Dealer("딜러", HIGHER_SCORE_HAND);
        Player player = new Player("플레이어", LOWER_SCORE_HAND);
        // when
        GameResult result = dealer.judgeAgainst(player);
        // then
        assertThat(result).isEqualTo(GameResult.DEALER_WIN);
    }

    @Test
    void 둘_다_버스트가_아니면서_점수가_같다면_무승부한다() {
        // given
        Dealer dealer = new Dealer("딜러", DEFAULT_SCORE_HAND);
        Player player = new Player("플레이어", DEFAULT_SCORE_HAND);
        // when
        GameResult result = dealer.judgeAgainst(player);
        // then
        assertThat(result).isEqualTo(GameResult.PUSH);
    }

    @Test
    void 플레이어가_버스트라면_딜러가_승리한다() {
        // given
        Dealer dealer = new Dealer("딜러", DEFAULT_SCORE_HAND);
        Player player = new Player("플레이어", BUST_SCORE_HAND);
        // when
        GameResult result = dealer.judgeAgainst(player);
        // then
        assertThat(result).isEqualTo(GameResult.DEALER_WIN);
    }

    @Test
    void 딜러만_버스트라면_플레이어가_승리한다() {
        // given
        Dealer dealer = new Dealer("딜러", BUST_SCORE_HAND);
        Player player = new Player("플레이어", DEFAULT_SCORE_HAND);
        // when
        GameResult result = dealer.judgeAgainst(player);
        // then
        assertThat(result).isEqualTo(GameResult.PLAYER_WIN);
    }

    @Test
    void 둘_다_버스트라면_딜러가_승리한다() {
        // given
        Dealer dealer = new Dealer("딜러", BUST_SCORE_HAND);
        Player player = new Player("플레이어", BUST_SCORE_HAND);
        // when
        GameResult result = dealer.judgeAgainst(player);
        // then
        assertThat(result).isEqualTo(GameResult.DEALER_WIN);
    }
}
