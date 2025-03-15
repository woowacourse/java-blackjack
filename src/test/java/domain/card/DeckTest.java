package domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static util.ExceptionConstants.*;

import card.Card;
import card.CardNumberType;
import card.CardType;
import card.Deck;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {
    @DisplayName("카드를 정상적으로 뽑는다.")
    @Test
    void test19() {
        //given
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(CardNumberType.ACE, CardType.CLOVER));
        Deck deck = new Deck(cards);

        //when
        Card card = deck.drawCard();

        //then
        assertThat(card).isEqualTo(new Card(CardNumberType.ACE, CardType.CLOVER));
    }

    @DisplayName("덱에 카드가 남아있지 않을 때 카드를 뽑을 시 예외가 발생한다.")
    @Test
    void test20() {
        //given
        List<Card> noCards = new ArrayList<>();
        Deck emptyDeck = new Deck(noCards);

        //when & then
        assertThatThrownBy(emptyDeck::drawCard)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining(ERROR_HEADER);
    }
}
