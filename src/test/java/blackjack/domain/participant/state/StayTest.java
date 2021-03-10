package blackjack.domain.participant.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.Result;
import blackjack.domain.carddeck.Card;
import blackjack.domain.carddeck.Number;
import blackjack.domain.carddeck.Pattern;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StayTest {

    private State stay;
    private Card card;

    @BeforeEach
    void setUp() {
        this.card = Card.valueOf(Pattern.HEART, Number.FIVE);
        this.stay = new Stay(new Hand(Collections.singletonList(this.card)));
    }

    @Test
    @DisplayName("Stay 에서 draw 요청시 에러처리")
    void testDrawException() {
        assertThatThrownBy(() -> this.stay.draw(this.card))
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("Stay 에서 stay 요청시 에러처리")
    void testStayException() {
        assertThatThrownBy(() -> this.stay.stay())
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("Stay 가 종료된 상태임을 확인")
    void testIsFinishedTrue() {
        assertThat(this.stay.isFinished()).isTrue();
    }

    @Test
    @DisplayName("Stay 에서 카드목록을 요구했을 시 가져갔던 카드개수가 맞는지 확인")
    void testHitCards() {
        assertThat(this.stay.cards()).hasSize(1);
    }

    @Test
    @DisplayName("플레이어 Stay기준 딜러의 Blackjack을 받았을 때 패배 반환")
    void testPlayerBlackjackVersusDealerBlackjack() {
        Card firstCard = Card.valueOf(Pattern.DIAMOND, Number.ACE);
        Card secondCard = Card.valueOf(Pattern.DIAMOND, Number.TEN);
        State dealerBlackjack = Init.draw(firstCard, secondCard);
        assertThat(this.stay.calculatePlayerResult(dealerBlackjack)).isEqualTo(Result.LOSE);
    }

    @Test
    @DisplayName("플레이어 Stay기준 딜러의 Bust를 받았을 때 승리 반환")
    void testPlayerBlackjackVersusDealerBust() {
        Card firstCard = Card.valueOf(Pattern.DIAMOND, Number.KING);
        Card secondCard = Card.valueOf(Pattern.DIAMOND, Number.TEN);
        State dealerHit = Init.draw(firstCard, secondCard);
        State dealerBust = dealerHit.draw(Card.valueOf(Pattern.HEART, Number.JACK));
        assertThat(this.stay.calculatePlayerResult(dealerBust)).isEqualTo(Result.WIN);
    }

    @Test
    @DisplayName("플레이어 Stay기준 딜러의 Stay를 받았을 때 점수비교 결과 반환")
    void testPlayerBlackjackVersusDealerStay() {
        Card firstCard = Card.valueOf(Pattern.DIAMOND, Number.TWO);
        Card secondCard = Card.valueOf(Pattern.DIAMOND, Number.TEN);
        State dealerHit = Init.draw(firstCard, secondCard);
        State dealerStay = dealerHit.stay();
        assertThat(this.stay.calculatePlayerResult(dealerStay)).isEqualTo(Result.LOSE); // 5 vs 12

        firstCard = Card.valueOf(Pattern.DIAMOND, Number.TWO);
        secondCard = Card.valueOf(Pattern.DIAMOND, Number.THREE);
        dealerHit = Init.draw(firstCard, secondCard);
        dealerStay = dealerHit.stay();
        assertThat(this.stay.calculatePlayerResult(dealerStay)).isEqualTo(Result.DRAW); // 5 vs 5

        firstCard = Card.valueOf(Pattern.DIAMOND, Number.TWO);
        secondCard = Card.valueOf(Pattern.HEART, Number.TWO);
        dealerHit = Init.draw(firstCard, secondCard);
        dealerStay = dealerHit.stay();
        assertThat(this.stay.calculatePlayerResult(dealerStay)).isEqualTo(Result.WIN); // 5 vs 4
    }
}