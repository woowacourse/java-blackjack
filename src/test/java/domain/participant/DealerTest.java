package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.card.Card;
import domain.card.Deck;
import domain.card.Denomination;
import domain.card.Symbol;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.ExceptionMessages;

class DealerTest {

    private Dealer dealer;
    private Deck deck;

    @BeforeEach
    void makePlayer() {
        dealer = new Dealer();
        deck = Deck.initDeck(Card.values());
    }

    @Test
    @DisplayName("블랙잭이거나 16이 넘은 상태에서 카드를 뽑을 경우 에러를 발생시킨다.")
    void hitCardOverLimitError() {
        while (dealer.canHit()) {
            dealer.hit(deck);
        }

        assertThatThrownBy(() -> dealer.hit(deck))
            .isInstanceOf(IllegalStateException.class)
            .hasMessage(ExceptionMessages.DEALER_CAN_NOT_HIT_ERROR);
    }

    @Test
    @DisplayName("숫자가 16이 넘는 경우 False를 반환한다.")
    void canDrawCardFalseTest() {
        while (dealer.canHit()) {
            dealer.hit(deck);
        }

        assertThat(dealer.canHit()).isFalse();
    }

    @Test
    @DisplayName("숫자가 16이 넘지 않는 경우 True를 반환한다.")
    void canDrawCardTrueTest() {
        dealer.hit(deck);

        assertThat(dealer.canHit()).isTrue();
    }

    @Test
    @DisplayName("플레이어의 수익을 종합해 딜러의 수익을 계산한다.")
    void calculateIncomeTest() {
        List<Integer> playerIncomes = Arrays.asList(3000, 4000, -2000);
        int actual = dealer.calculateIncome(playerIncomes);
        int expected = -5000;

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("딜러가 카드를 뽑을 때 잘 뽑는지 확인한다.")
    void hitTest() {
        Card card = Card.valueOf(Symbol.CLOVER, Denomination.THREE);
        Deck testDeck = Deck.initDeck(List.of(card));

        dealer.hit(testDeck);

        assertThat(dealer.getFirstCard()).isSameAs(card);
    }
}