package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

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
        Dealer dealer = new Dealer(Set.of(Card.from(Suit.CLOVER, Denomination.EIGHT)));

        assertThat(dealer.getScore()).isEqualTo(8);
    }

    @Test
    @DisplayName("딜러의 카드 추가 분배가 불가능한 경우 테스트")
    void hasFalseDealerNextTurn() {
        Card card1 = Card.from(Suit.SPADE, Denomination.SEVEN);
        Card card2 = Card.from(Suit.HEART, Denomination.JACK);
        Dealer dealer = new Dealer(Set.of(card1, card2));

        assertThat(dealer.hasNextTurn()).isFalse();
    }

    @Test
    @DisplayName("딜러의 카드 추가 분배가 가능한 경우 테스트")
    void hasTrueDealerNextTurn() {
        Card card1 = Card.from(Suit.SPADE, Denomination.SIX);
        Card card2 = Card.from(Suit.HEART, Denomination.JACK);
        Dealer dealer = new Dealer(Set.of(card1, card2));

        assertThat(dealer.hasNextTurn()).isTrue();
    }
}
