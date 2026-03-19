package domain.game;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import domain.card.Card;
import domain.card.Hand;
import domain.card.Rank;
import domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HandStateTest {

    @DisplayName("블랙잭 vs 블랙잭이면 무승부다")
    @Test
    void 블랙잭_vs_블랙잭이면_무승부다() {
        assertThat(createBlackjack().versus(createBlackjack())).isSameAs(Outcome.TIE);
    }

    @DisplayName("블랙잭 vs Stay면 블랙잭 승리다")
    @Test
    void 블랙잭_vs_Stay면_블랙잭_승리다() {
        assertThat(createBlackjack().versus(createStay(Rank.KING, Rank.NINE))).isSameAs(Outcome.BLACKJACK_WIN);
    }

    @DisplayName("블랙잭 vs 버스트면 블랙잭 승리다")
    @Test
    void 블랙잭_vs_버스트면_블랙잭_승리다() {
        assertThat(createBlackjack().versus(createBust())).isSameAs(Outcome.BLACKJACK_WIN);
    }

    @DisplayName("버스트는 상대가 무엇이든 패배한다")
    @Test
    void 버스트는_무조건_패배한다() {
        assertThat(createBust().versus(createBlackjack())).isSameAs(Outcome.LOSE);
        assertThat(createBust().versus(createStay(Rank.KING, Rank.FIVE))).isSameAs(Outcome.LOSE);
        assertThat(createBust().versus(createBust())).isSameAs(Outcome.LOSE);
    }

    @DisplayName("Stay vs 블랙잭이면 패배한다")
    @Test
    void Stay_vs_블랙잭이면_패배한다() {
        assertThat(createStay(Rank.KING, Rank.NINE).versus(createBlackjack())).isSameAs(Outcome.LOSE);
    }

    @DisplayName("Stay vs 버스트면 승리한다")
    @Test
    void Stay_vs_버스트면_승리한다() {
        assertThat(createStay(Rank.KING, Rank.FIVE).versus(createBust())).isSameAs(Outcome.WIN);
    }

    @DisplayName("Stay vs Stay에서 점수가 높으면 승리한다")
    @Test
    void Stay_점수가_높으면_승리한다() {
        assertThat(createStay(Rank.KING, Rank.NINE).versus(createStay(Rank.KING, Rank.EIGHT))).isSameAs(Outcome.WIN);
    }

    @DisplayName("Stay vs Stay에서 점수가 같으면 무승부다")
    @Test
    void Stay_점수가_같으면_무승부다() {
        assertThat(createStay(Rank.KING, Rank.EIGHT).versus(createStay(Rank.KING, Rank.EIGHT))).isSameAs(Outcome.TIE);
    }

    @DisplayName("Stay vs Stay에서 점수가 낮으면 패배한다")
    @Test
    void Stay_점수가_낮으면_패배한다() {
        assertThat(createStay(Rank.KING, Rank.FIVE).versus(createStay(Rank.KING, Rank.EIGHT))).isSameAs(Outcome.LOSE);
    }

    @DisplayName("Hit에서 카드를 뽑아도 버스트/블랙잭이 아니면 Hit을 유지한다")
    @Test
    void Hit에서_일반_카드를_뽑으면_Hit을_유지한다() {
        Hit hit = new Hit(new Hand());
        HandState result = hit.draw(new Card(Rank.FIVE, Suit.SPADE));
        assertThat(result).isInstanceOf(Hit.class);
    }

    @DisplayName("Hit에서 2장으로 21점이 되면 Blackjack으로 전이한다")
    @Test
    void Hit에서_블랙잭이_되면_Blackjack으로_전이한다() {
        Hit hit = new Hit(new Hand());
        hit.draw(new Card(Rank.ACE, Suit.SPADE));
        HandState result = hit.draw(new Card(Rank.KING, Suit.HEART));
        assertThat(result).isInstanceOf(Blackjack.class);
    }

    @DisplayName("Hit에서 점수가 21을 초과하면 Bust로 전이한다")
    @Test
    void Hit에서_버스트가_되면_Bust로_전이한다() {
        Hit hit = new Hit(new Hand());
        hit.draw(new Card(Rank.KING, Suit.SPADE));
        hit.draw(new Card(Rank.KING, Suit.HEART));
        HandState result = hit.draw(new Card(Rank.TWO, Suit.DIAMOND));
        assertThat(result).isInstanceOf(Bust.class);
    }

    @DisplayName("Hit에서 stay하면 Stay로 전이한다")
    @Test
    void Hit에서_stay하면_Stay로_전이한다() {
        Hit hit = new Hit(new Hand());
        hit.draw(new Card(Rank.KING, Suit.SPADE));
        HandState result = hit.stay();
        assertThat(result).isInstanceOf(Stay.class);
    }

    private Blackjack createBlackjack() {
        Hand hand = new Hand();
        hand.addCard(new Card(Rank.ACE, Suit.SPADE));
        hand.addCard(new Card(Rank.KING, Suit.HEART));
        return new Blackjack(hand);
    }

    private Bust createBust() {
        Hand hand = new Hand();
        hand.addCard(new Card(Rank.KING, Suit.SPADE));
        hand.addCard(new Card(Rank.KING, Suit.HEART));
        hand.addCard(new Card(Rank.TWO, Suit.DIAMOND));
        return new Bust(hand);
    }

    private Stay createStay(Rank rank1, Rank rank2) {
        Hand hand = new Hand();
        hand.addCard(new Card(rank1, Suit.SPADE));
        hand.addCard(new Card(rank2, Suit.HEART));
        return new Stay(hand);
    }
}
