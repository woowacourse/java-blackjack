package blackjack.domain.participant.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.Result;
import blackjack.domain.carddeck.Card;
import blackjack.domain.carddeck.Number;
import blackjack.domain.carddeck.Pattern;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackTest {

    private Card card;
    private State blackjack;

    @BeforeEach
    void setUp() {
        this.card = Card.valueOf(Pattern.HEART, Number.KING);
        Card firstCard = Card.valueOf(Pattern.HEART, Number.ACE);
        Card secondCard = Card.valueOf(Pattern.DIAMOND, Number.KING);
        this.blackjack = Init.draw(firstCard, secondCard);
    }

    @Test
    @DisplayName("첫 2장을 받은 후 상태가 Blackjack인지 테스트")
    void testFromInitDrawToHit() {
        assertThat(this.blackjack).isInstanceOf(Blackjack.class);
    }

    @Test
    @DisplayName("Blackjack 에서 draw 요청시 에러처리")
    void testDrawException() {
        assertThatThrownBy(() -> this.blackjack.draw(this.card))
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("Blackjack 에서 stay 요청시 에러처리")
    void testStayException() {
        assertThatThrownBy(() -> this.blackjack.stay())
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("Blackjack 가 종료된 상태임을 확인")
    void testIsFinishedTrue() {
        assertThat(this.blackjack.isFinished()).isTrue();
    }

    @Test
    @DisplayName("Blackjack 에서 카드목록을 요구했을 시 가져갔던 카드개수가 맞는지 확인")
    void testHitCards() {
        assertThat(this.blackjack.cards()).hasSize(2);
    }

    @Test
    @DisplayName("플레이어 Blackjack기준 딜러의 Blackjack을 받았을 때 무승부 반환")
    void testPlayerBlackjackVersusDealerBlackjack() {
        Card firstCard = Card.valueOf(Pattern.DIAMOND, Number.ACE);
        Card secondCard = Card.valueOf(Pattern.DIAMOND, Number.TEN);
        State dealerBlackjack = Init.draw(firstCard, secondCard);
        assertThat(this.blackjack.calculatePlayerResult(dealerBlackjack)).isEqualTo(Result.DRAW);
    }

    @Test
    @DisplayName("플레이어 Blackjack기준 딜러의 Stay를 받았을 때 승리 반환")
    void testPlayerBlackjackVersusDealerStay() {
        Card firstCard = Card.valueOf(Pattern.DIAMOND, Number.TWO);
        Card secondCard = Card.valueOf(Pattern.DIAMOND, Number.TEN);
        State dealerHit = Init.draw(firstCard, secondCard);
        State dealerStay = dealerHit.stay();
        assertThat(this.blackjack.calculatePlayerResult(dealerStay))
            .isEqualTo(Result.BLACKJACK_WIN);
    }

    @Test
    @DisplayName("플레이어 Blackjack기준 딜러의 Bust를 받았을 때 승리 반환")
    void testPlayerBlackjackVersusDealerBust() {
        Card firstCard = Card.valueOf(Pattern.DIAMOND, Number.KING);
        Card secondCard = Card.valueOf(Pattern.DIAMOND, Number.TEN);
        State dealerHit = Init.draw(firstCard, secondCard);
        State dealerBust = dealerHit.draw(Card.valueOf(Pattern.HEART, Number.JACK));
        assertThat(this.blackjack.calculatePlayerResult(dealerBust))
            .isEqualTo(Result.BLACKJACK_WIN);
    }
}