package blackjack.domain.participant.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.Result;
import blackjack.domain.carddeck.Card;
import blackjack.domain.carddeck.Number;
import blackjack.domain.carddeck.Pattern;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BustTest {

    private State bust;
    private Card card;

    @BeforeEach
    void setUp() {
        Card firstCard = Card.valueOf(Pattern.HEART, Number.TEN);
        Card secondCard = Card.valueOf(Pattern.HEART, Number.KING);
        this.card = Card.valueOf(Pattern.HEART, Number.FIVE);
        this.bust = new Bust(new Hand(Arrays.asList(firstCard, secondCard)));
    }

    @Test
    @DisplayName("Bust 에서 draw 요청시 에러처리")
    void testDrawException() {
        assertThatThrownBy(() -> this.bust.draw(this.card))
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("Bust 에서 stay 요청시 에러처리")
    void testStayException() {
        assertThatThrownBy(() -> this.bust.stay())
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("Bust 가 종료된 상태임을 확인")
    void testIsFinishedTrue() {
        assertThat(this.bust.isFinished()).isTrue();
    }

    @Test
    @DisplayName("Bust 에서 카드목록을 요구했을 시 가져갔던 카드개수가 맞는지 확인")
    void testHitCards() {
        assertThat(this.bust.cards()).hasSize(2);
    }

    @Test
    @DisplayName("플레이어 Bust기준 딜러의 Blackjack을 받았을 때 패배 반환")
    void testPlayerBlackjackVersusDealerBlackjack() {
        Card firstCard = Card.valueOf(Pattern.DIAMOND, Number.ACE);
        Card secondCard = Card.valueOf(Pattern.DIAMOND, Number.TEN);
        State dealerBlackjack = Init.draw(firstCard, secondCard);
        assertThat(this.bust.calculatePlayerResult(dealerBlackjack)).isEqualTo(Result.LOSE);
    }

    @Test
    @DisplayName("플레이어 Bust기준 딜러의 Stay를 받았을 때 패배 반환")
    void testPlayerBlackjackVersusDealerStay() {
        Card firstCard = Card.valueOf(Pattern.DIAMOND, Number.TWO);
        Card secondCard = Card.valueOf(Pattern.DIAMOND, Number.TEN);
        State dealerHit = Init.draw(firstCard, secondCard);
        State dealerStay = dealerHit.stay();
        assertThat(this.bust.calculatePlayerResult(dealerStay)).isEqualTo(Result.LOSE);
    }

    @Test
    @DisplayName("플레이어 Bust기준 딜러의 Bust를 받았을 때 패배 반환")
    void testPlayerBlackjackVersusDealerBust() {
        Card firstCard = Card.valueOf(Pattern.DIAMOND, Number.KING);
        Card secondCard = Card.valueOf(Pattern.DIAMOND, Number.TEN);
        State dealerHit = Init.draw(firstCard, secondCard);
        State dealerBust = dealerHit.draw(Card.valueOf(Pattern.HEART, Number.JACK));
        assertThat(this.bust.calculatePlayerResult(dealerBust)).isEqualTo(Result.LOSE);
    }
}