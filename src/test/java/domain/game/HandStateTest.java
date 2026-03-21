package domain.game;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import domain.card.Card;
import domain.card.Hand;
import domain.card.Rank;
import domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandStateTest {

    @DisplayName("블랙잭 vs 블랙잭이면 무승부다")
    @Test
    void 블랙잭_vs_블랙잭이면_무승부다() {
        assertThat(createBlackjack().calculateOutcome(createBlackjack())).isSameAs(Outcome.TIE);
    }

    @DisplayName("블랙잭 vs Stay면 블랙잭 승리다")
    @Test
    void 블랙잭_vs_Stay면_블랙잭_승리다() {
        assertThat(createBlackjack().calculateOutcome(createStay(Rank.KING, Rank.NINE))).isSameAs(Outcome.BLACKJACK_WIN);
    }

    @DisplayName("블랙잭 vs 버스트면 블랙잭 승리다")
    @Test
    void 블랙잭_vs_버스트면_블랙잭_승리다() {
        assertThat(createBlackjack().calculateOutcome(createBust())).isSameAs(Outcome.BLACKJACK_WIN);
    }

    @DisplayName("버스트 vs 블랙잭이면 패배한다")
    @Test
    void 버스트_vs_블랙잭이면_패배한다() {
        assertThat(createBust().calculateOutcome(createBlackjack())).isSameAs(Outcome.LOSE);
    }

    @DisplayName("버스트 vs Stay면 패배한다")
    @Test
    void 버스트_vs_Stay면_패배한다() {
        assertThat(createBust().calculateOutcome(createStay(Rank.KING, Rank.FIVE))).isSameAs(Outcome.LOSE);
    }

    @DisplayName("버스트 vs 버스트면 패배한다")
    @Test
    void 버스트_vs_버스트면_패배한다() {
        assertThat(createBust().calculateOutcome(createBust())).isSameAs(Outcome.LOSE);
    }

    @DisplayName("Stay vs 블랙잭이면 패배한다")
    @Test
    void Stay_vs_블랙잭이면_패배한다() {
        assertThat(createStay(Rank.KING, Rank.NINE).calculateOutcome(createBlackjack())).isSameAs(Outcome.LOSE);
    }

    @DisplayName("Stay vs 버스트면 승리한다")
    @Test
    void Stay_vs_버스트면_승리한다() {
        assertThat(createStay(Rank.KING, Rank.FIVE).calculateOutcome(createBust())).isSameAs(Outcome.WIN);
    }

    @DisplayName("Stay vs Stay에서 점수가 높으면 승리한다")
    @Test
    void Stay_점수가_높으면_승리한다() {
        assertThat(createStay(Rank.KING, Rank.NINE).calculateOutcome(createStay(Rank.KING, Rank.EIGHT))).isSameAs(Outcome.WIN);
    }

    @DisplayName("Stay vs Stay에서 점수가 같으면 무승부다")
    @Test
    void Stay_점수가_같으면_무승부다() {
        assertThat(createStay(Rank.KING, Rank.EIGHT).calculateOutcome(createStay(Rank.KING, Rank.EIGHT))).isSameAs(Outcome.TIE);
    }

    @DisplayName("Stay vs Stay에서 점수가 낮으면 패배한다")
    @Test
    void Stay_점수가_낮으면_패배한다() {
        assertThat(createStay(Rank.KING, Rank.FIVE).calculateOutcome(createStay(Rank.KING, Rank.EIGHT))).isSameAs(Outcome.LOSE);
    }

    @DisplayName("Hit에서 카드를 뽑아도 버스트/블랙잭이 아니면 Hit을 유지한다")
    @Test
    void Hit에서_일반_카드를_뽑으면_Hit을_유지한다() {
        HandState hit = new Hit(new Hand());
        HandState result = hit.draw(new Card(Rank.FIVE, Suit.SPADE));
        assertThat(result).isInstanceOf(Hit.class);
    }

    @DisplayName("Hit에서 2장으로 21점이 되면 Blackjack으로 전이한다")
    @Test
    void Hit에서_블랙잭이_되면_Blackjack으로_전이한다() {
        HandState hit = new Hit(new Hand());
        HandState afterFirst = hit.draw(new Card(Rank.ACE, Suit.SPADE));
        HandState result = afterFirst.draw(new Card(Rank.KING, Suit.HEART));
        assertThat(result).isInstanceOf(Blackjack.class);
    }

    @DisplayName("Hit에서 점수가 21을 초과하면 Bust로 전이한다")
    @Test
    void Hit에서_버스트가_되면_Bust로_전이한다() {
        HandState state = new Hit(new Hand());
        state = state.draw(new Card(Rank.KING, Suit.SPADE));
        state = state.draw(new Card(Rank.KING, Suit.HEART));
        state = state.draw(new Card(Rank.TWO, Suit.DIAMOND));
        assertThat(state).isInstanceOf(Bust.class);
    }

    @DisplayName("Hit에서 stay하면 Stay로 전이한다")
    @Test
    void Hit에서_stay하면_Stay로_전이한다() {
        HandState state = new Hit(new Hand());
        state = state.draw(new Card(Rank.KING, Suit.SPADE));
        HandState result = state.stay();
        assertThat(result).isInstanceOf(Stay.class);
    }

    @DisplayName("Hit은 진행 중 상태다")
    @Test
    void Hit은_진행_중_상태다() {
        assertThat(new Hit(new Hand()).isFinished()).isFalse();
    }

    @DisplayName("Stay는 완료된 상태다")
    @Test
    void Stay는_완료된_상태다() {
        assertThat(createStay(Rank.KING, Rank.FIVE).isFinished()).isTrue();
    }

    @DisplayName("Blackjack은 완료된 상태다")
    @Test
    void Blackjack은_완료된_상태다() {
        assertThat(createBlackjack().isFinished()).isTrue();
    }

    @DisplayName("Bust는 완료된 상태다")
    @Test
    void Bust는_완료된_상태다() {
        assertThat(createBust().isFinished()).isTrue();
    }

    @DisplayName("Blackjack 상태에서 카드를 뽑으면 예외가 발생한다")
    @Test
    void Blackjack_상태에서_카드를_뽑으면_예외가_발생한다() {
        assertThatThrownBy(() -> createBlackjack().draw(new Card(Rank.TWO, Suit.SPADE)))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("Bust 상태에서 카드를 뽑으면 예외가 발생한다")
    @Test
    void Bust_상태에서_카드를_뽑으면_예외가_발생한다() {
        assertThatThrownBy(() -> createBust().draw(new Card(Rank.TWO, Suit.SPADE)))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("Stay 상태에서 카드를 뽑으면 예외가 발생한다")
    @Test
    void Stay_상태에서_카드를_뽑으면_예외가_발생한다() {
        assertThatThrownBy(() -> createStay(Rank.KING, Rank.FIVE).draw(new Card(Rank.TWO, Suit.SPADE)))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("Blackjack 상태에서 stay하면 예외가 발생한다")
    @Test
    void Blackjack_상태에서_stay하면_예외가_발생한다() {
        assertThatThrownBy(() -> createBlackjack().stay())
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("Bust 상태에서 stay하면 예외가 발생한다")
    @Test
    void Bust_상태에서_stay하면_예외가_발생한다() {
        assertThatThrownBy(() -> createBust().stay())
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("Stay 상태에서 stay하면 예외가 발생한다")
    @Test
    void Stay_상태에서_stay하면_예외가_발생한다() {
        assertThatThrownBy(() -> createStay(Rank.KING, Rank.FIVE).stay())
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("진행 중인 상태에서 승패를 판단하면 예외가 발생한다")
    @Test
    void 진행_중인_상태에서_승패를_판단하면_예외가_발생한다() {
        HandState hit = new Hit(new Hand());
        HandState dealerState = createStay(Rank.KING, Rank.FIVE);
        assertThatThrownBy(() -> hit.calculateOutcome(dealerState))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    private HandState createBlackjack() {
        Hand hand = new Hand()
                .addCard(new Card(Rank.ACE, Suit.SPADE))
                .addCard(new Card(Rank.KING, Suit.HEART));
        return new Blackjack(hand);
    }

    private HandState createBust() {
        Hand hand = new Hand()
                .addCard(new Card(Rank.KING, Suit.SPADE))
                .addCard(new Card(Rank.KING, Suit.HEART))
                .addCard(new Card(Rank.TWO, Suit.DIAMOND));
        return new Bust(hand);
    }

    private HandState createStay(Rank rank1, Rank rank2) {
        Hand hand = new Hand()
                .addCard(new Card(rank1, Suit.SPADE))
                .addCard(new Card(rank2, Suit.HEART));
        return new Stay(hand);
    }
}
