package blackjack.domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.CardValue;
import blackjack.domain.game.GameRule;
import blackjack.mock.CardDeckMock;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    static final Card FIRST_CARD = new Card(CardShape.SPADE, CardValue.EIGHT);
    static final Card SECOND_CARD = new Card(CardShape.SPADE, CardValue.EIGHT);
    static final Card THIRD_CARD = new Card(CardShape.SPADE, CardValue.EIGHT);

    CardDeck cardDeck;
    Dealer dealer;

    @BeforeEach
    void beforeEach() {
        Queue<Card> cards = new ArrayDeque<>();
        cards.add(FIRST_CARD);
        cards.add(SECOND_CARD);
        cards.add(THIRD_CARD);
        cardDeck = new CardDeckMock(cards);
        dealer = new Dealer(cardDeck);
    }

    @Test
    @DisplayName("자신의 초기 카드를 드로잉할 수 있다.")
    void canDrawSelfInitialCard() {
        dealer.drawSelfInitialCard();
        assertThat(dealer.getHand()).hasSize(GameRule.INITIAL_CARD_COUNT.getValue());
    }

    @Test
    @DisplayName("플레이어에게 초기카드를 분배할 수 있다.")
    void canDistributePlayerInitialCard() {
        List<Card> drawnCards = dealer.distributePlayerInitialCard();
        assertThat(drawnCards).hasSize(GameRule.INITIAL_CARD_COUNT.getValue());
    }

    @Test
    @DisplayName("가능할 때까지 자신의 카드를 드로잉할 수 있다.")
    void canDrawSelfCardUntilLimit() {
        dealer.drawSelfCardUntilLimit();
        assertThat(dealer.getHand()).hasSize(3);
    }

    @Test
    @DisplayName("가능할 때까지 자신의 카드를 드로잉할 때 드로잉 횟수를 알 수 있다.")
    void canCalculateSelfDrawingCount() {
        int count = dealer.drawSelfCardUntilLimit();
        assertThat(count).isEqualTo(3);
    }

    @Test
    @DisplayName("자신의 첫번째 카드를 출력할 수 있다.")
    void canFindFirstCard() {
        dealer.drawSelfInitialCard();
        Card firstCard = dealer.findFirstCard();
        assertThat(firstCard).isEqualTo(FIRST_CARD);
    }

    @Test
    @DisplayName("초기 카드 분배를 수행하지 않고 첫번재 카드를 출력할 경우 예외를 발생시킨다.")
    void cannotFindFirstCardBeforeDistributionInitialCards() {
        assertThatCode(() -> dealer.findFirstCard())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("초기 카드 분배를 먼저 수행해야합니다.");
    }
}