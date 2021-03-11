package blackjack.domain.participant.state;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.carddeck.Card;
import blackjack.domain.carddeck.Number;
import blackjack.domain.carddeck.Pattern;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HitTest {

    private Card toBustCard;
    private Card toHitCard;
    private State hit;

    @BeforeEach
    void setUp() {
        Card firstCard = Card.valueOf(Pattern.HEART, Number.FIVE);
        Card secondCard = Card.valueOf(Pattern.DIAMOND, Number.TEN);
        this.hit = Init.draw(firstCard, secondCard);
        this.toBustCard = Card.valueOf(Pattern.HEART, Number.KING);
        this.toHitCard = Card.valueOf(Pattern.HEART, Number.TWO);
    }

    @Test
    @DisplayName("첫 2장을 받은 후 상태가 Hit인지 테스트")
    void testFromInitDrawToHit() {
        assertThat(this.hit).isInstanceOf(Hit.class);
    }

    @Test
    @DisplayName("Hit 에서 추가로 카드를 받았을 때 합이 21 이하인 경우 Hit이 되는지 테스트")
    void testFromHitToHit() {
        assertThat(this.hit.draw(this.toHitCard)).isInstanceOf(Hit.class);
    }

    @Test
    @DisplayName("Hit 에서 추가로 카드를 받았을 때 합이 21을 넘기는 경우 Bust가 되는지 테스트")
    void testFromHitToBust() {
        assertThat(this.hit.draw(this.toBustCard)).isInstanceOf(Bust.class);
    }

    @Test
    @DisplayName("Hit 에서 추가로 카드를 받고 싶지 않을 때 Stay가 되는지 테스트")
    void testFromHitToStay() {
        assertThat(this.hit.stay()).isInstanceOf(Stay.class);
    }

    @Test
    @DisplayName("Hit 에서 더 이상 뽑을 수 없는 상태인지 질문이 왔을 때 false 를 반환하는지 테스트")
    void testIfAskedFinishedThenReplyNo() {
        assertThat(this.hit.isFinished()).isFalse();
    }

    @Test
    @DisplayName("Hit 에서 카드목록을 요구했을 시 가져갔던 카드개수가 맞는지 확인")
    void testHitCards() {
        assertThat(this.hit.cards()).hasSize(2);
    }
}
