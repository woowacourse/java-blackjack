package blackjack.domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.CardValue;
import blackjack.domain.game.GameRule;
import blackjack.exception.ExceptionMessage;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    static final Card FIRST_CARD = new Card(CardShape.SPADE, CardValue.EIGHT);
    static final Card SECOND_CARD = new Card(CardShape.SPADE, CardValue.EIGHT);
    Dealer dealer;

    @BeforeEach
    void beforeEach() {
        dealer = new Dealer();
    }

    @Test
    @DisplayName("자신의 초기 카드를 추가할 수 있다.")
    void canAddInitialCards() {
        List<Card> cards = List.of(FIRST_CARD, SECOND_CARD);
        dealer.addInitialCards(cards);

        assertThat(dealer.getHand()).hasSize(GameRule.INITIAL_CARD_COUNT.getValue());
    }

    @Test
    @DisplayName("드로잉 가능한 경우를 체크할 수 있다.")
    void canDraw() {
        Boolean isPossible = dealer.canDraw();
        assertThat(isPossible).isTrue();
    }

    @Test
    @DisplayName("드로잉 불가능한 경우를 체크할 수 있다.")
    void canNotDraw() {
        dealer.addInitialCards(List.of(
                new Card(CardShape.HEART, CardValue.JACK),
                new Card(CardShape.HEART, CardValue.SEVEN)));

        Boolean isPossible = dealer.canDraw();
        assertThat(isPossible).isFalse();
    }

    @Test
    @DisplayName("가능할 때까지 자신의 카드를 드로잉할 수 있다.")
    void canAddCardUntilLimit() {
        dealer.addCardUntilLimit(new Card(CardShape.HEART, CardValue.JACK));
        dealer.addCardUntilLimit(new Card(CardShape.HEART, CardValue.SIX));
        dealer.addCardUntilLimit(new Card(CardShape.HEART, CardValue.ACE));
        dealer.addCardUntilLimit(new Card(CardShape.HEART, CardValue.ACE));
        assertThat(dealer.getHand()).hasSize(3);
    }

    @Test
    @DisplayName("자신의 첫번째 카드를 출력할 수 있다.")
    void canFindFirstCard() {
        dealer.addCardUntilLimit(FIRST_CARD);
        dealer.addCardUntilLimit(SECOND_CARD);
        Card firstCard = dealer.findFirstCard();

        assertThat(firstCard).isEqualTo(FIRST_CARD);
    }

    @Test
    @DisplayName("초기 카드 분배를 수행하지 않고 첫번재 카드를 출력할 경우 예외를 발생시킨다.")
    void cannotFindFirstCardBeforeDistributionInitialCards() {
        assertThatCode(() -> dealer.findFirstCard())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.BEFORE_CARD_DISTRIBUTION.getContent());
    }
}