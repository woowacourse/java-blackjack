package blackjack.domain.card;

import static blackjack.domain.card.CardNumber.A;
import static blackjack.domain.card.CardNumber.NINE;
import static blackjack.domain.card.CardNumber.TEN;
import static blackjack.domain.card.CardNumber.THREE;
import static blackjack.domain.card.CardNumber.TWO;
import static blackjack.domain.card.CardPattern.SPADE;
import static blackjack.testutil.CardFixtureGenerator.createCards;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.CardDeck;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {

    @Test
    @DisplayName("카드의 합을 계산할 수 있다.")
    void calculateScore() {
        final Cards cards = new Cards(Arrays.asList(Card.of(SPADE, TWO), Card.of(SPADE, THREE)));
        assertThat(cards.calculateScore()).isEqualTo(5);
    }

    @Test
    @DisplayName("생성 시 카드에는 null이 들어올 경우 예외를 발생해야 한다.")
    void createNullException() {
        assertThatThrownBy(() -> new Cards(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("카드에는 null이 들어올 수 없습니다.");
    }

    @Test
    @DisplayName("생성 시 카드에는 2장의 카드가 들어오지 않을 경우 예외를 발생해야 한다.")
    void createExceptionBySize() {
        final List<Card> cards = Arrays.asList(Card.of(SPADE, A));
        assertThatThrownBy(() -> new Cards(cards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("카드 2장으로 생성해야 합니다.");
    }

    @Test
    @DisplayName("중복된 카드를 더할 경우 예외가 발생해야 한다.")
    void addExceptionBySumIsLargerThanBlackJack() {
        final Cards cards = createCards(Card.of(SPADE, TEN), Card.of(SPADE, NINE));
        assertThatThrownBy(() -> cards.addCard(Card.of(SPADE, TEN)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 카드를 추가할 수 없습니다.");
    }

    @Test
    @DisplayName("null인 카드를 더할 경우 예외가 발생해야 한다.")
    void addCardExceptionByNullCard() {
        final Cards cards = createCards(Card.of(SPADE, TEN), Card.of(SPADE, NINE));
        assertThatThrownBy(() -> cards.addCard(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("null인 카드는 들어올 수 없습니다.");
    }

    @Test
    @DisplayName("카드덱을 받아서 Cards를 생성할 수 있다.")
    void createCardsByCardDeck() {
        final CardDeck cardDeck = CardDeck.createNewCardDek();
        final Cards cards = Cards.createByCardDeck(cardDeck);

        assertThat(cards.cards()).hasSize(2);
    }
}
