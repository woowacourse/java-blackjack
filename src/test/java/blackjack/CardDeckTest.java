package blackjack;

import blackjack.domain.GameTable;
import blackjack.domain.card.Card;
import blackjack.domain.card.Denominations;
import blackjack.domain.card.Suits;
import blackjack.utils.CardDeck;
import blackjack.utils.FixedCardDeck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CardDeckTest {
    @Test
    @DisplayName("카트 뽑기")
    void pop() {
        GameTable gameTable = new GameTable(new FixedCardDeck());
        Card card = gameTable.pop();
        assertThat(card).isEqualTo(Card.from(Suits.CLOVER, Denominations.ACE));
    }

    @Test
    @DisplayName("연속 카트 뽑기")
    void pop2() {
        GameTable gameTable = new GameTable(new FixedCardDeck());
        Card card = gameTable.pop();
        assertThat(card).isEqualTo(Card.from(Suits.CLOVER, Denominations.ACE));
        card = gameTable.pop();
        assertThat(card).isEqualTo(Card.from(Suits.CLOVER, Denominations.TWO));
    }

    @Test
    @DisplayName("52번 pop empty 확인")
    void size() {
        GameTable gameTable = new GameTable(new FixedCardDeck());

        for (int i = 0; i < 52; i++) {
            gameTable.pop();
        }

        assertThat(gameTable.isEmpty()).isEqualTo(true);
    }

    @Test
    @DisplayName("51번 pop notEmpty 확인")
    void size2() {
        GameTable gameTable = new GameTable(new FixedCardDeck());


        for (int i = 0; i < 51; i++) {
            gameTable.pop();
        }

        assertThat(gameTable.isEmpty()).isEqualTo(false);
    }

    @Test
    @DisplayName("53번 pop exception 확인")
    void size3() {
        GameTable gameTable = new GameTable(new FixedCardDeck());

        for (int i = 0; i < 52; i++) {
            gameTable.pop();
        }

        assertThatThrownBy(gameTable::pop).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("초기 2장 확인")
    void initCards() {
        GameTable gameTable = new GameTable(new FixedCardDeck());

        List<Card> cards = gameTable.initCards();

        assertThat(cards.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("초기 2장 확인 일치")
    void initCards2() {
        GameTable gameTable = new GameTable(new FixedCardDeck());

        List<Card> cards = gameTable.initCards();

        assertThat(cards).contains(Card.from(Suits.CLOVER, Denominations.ACE), Card.from(Suits.CLOVER, Denominations.TWO));
    }
}
