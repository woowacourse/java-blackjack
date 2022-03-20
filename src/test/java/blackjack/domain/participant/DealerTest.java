package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.result.BlackjackMatch;
import blackjack.domain.state.Ready;
import blackjack.domain.state.Status;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {

    @Test
    @DisplayName("Dealer 생성 테스트")
    void createValidDealer() {
        assertThat(new Dealer()).isNotNull();
    }

    @Test
    @DisplayName("딜러의 카드 합계 계산 테스트")
    void calculateScore() {
        final Status status = new Ready()
                .draw(Card.from(Suit.CLOVER, Denomination.EIGHT));
        Dealer dealer = new Dealer(status);

        assertThat(dealer.getScore()).isEqualTo(8);
    }

    @Test
    @DisplayName("딜러의 카드 추가 분배가 불가능한 경우 테스트")
    void hasFalseDealerNextTurn() {
        final Status status = new Ready()
                .draw(Card.from(Suit.CLOVER, Denomination.EIGHT))
                .draw(Card.from(Suit.HEART, Denomination.JACK));
        Dealer dealer = new Dealer(status);

        assertThat(dealer.hasNextTurn()).isFalse();
    }

    @Test
    @DisplayName("딜러의 카드 추가 분배가 가능한 경우 테스트")
    void hasTrueDealerNextTurn() {
        final Status status = new Ready()
                .draw(Card.from(Suit.SPADE, Denomination.SIX))
                .draw(Card.from(Suit.HEART, Denomination.JACK));
        Dealer dealer = new Dealer(status);

        assertThat(dealer.hasNextTurn()).isTrue();
    }

    @Test
    @DisplayName("딜러가 버스트이지만 플레이어도 버스트이면 딜러가 이기는지 테스트")
    void isWin() {
        final Status dealerStatus = new Ready()
                .draw(Card.from(Suit.HEART, Denomination.JACK))
                .draw(Card.from(Suit.DIAMOND, Denomination.JACK))
                .draw(Card.from(Suit.SPADE, Denomination.JACK));
        final Status playerStatus = new Ready()
                .draw(Card.from(Suit.HEART, Denomination.KING))
                .draw(Card.from(Suit.DIAMOND, Denomination.KING))
                .draw(Card.from(Suit.SPADE, Denomination.KING));
        Dealer dealer = new Dealer(dealerStatus);

        assertThat(dealer.isWin(new Player("kth", playerStatus))).isEqualTo(BlackjackMatch.WIN);
    }
}
