package blackjack;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denominations;
import blackjack.domain.card.Suits;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class CardsTest {
    @Test
    @DisplayName("생성")
    void create() {
        List<Card> cards = Arrays.asList(
            Card.from(Suits.DIAMOND, Denominations.ACE),
            Card.from(Suits.HEART, Denominations.ACE));
        
        assertThatCode(() -> new Cards(cards)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("실패 - 초기 리스트 2 초과")
    void create1() {
        List<Card> cards = Arrays.asList(
            Card.from(Suits.DIAMOND, Denominations.ACE),
            Card.from(Suits.HEART, Denominations.ACE),
            Card.from(Suits.SPADE, Denominations.ACE));

        assertThatThrownBy(() -> new Cards(cards)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("생성된 리스트에 카드 포함여부 확인")
    void create2() {
        List<Card> cards = Arrays.asList(
            Card.from(Suits.DIAMOND, Denominations.ACE),
            Card.from(Suits.HEART, Denominations.ACE));

        assertThat(new Cards(cards).getUnmodifiableList())
            .contains(Card.from(Suits.DIAMOND, Denominations.ACE),
                Card.from(Suits.HEART, Denominations.ACE));
    }

    @Test
    @DisplayName("카드추가")
    void add() {
        List<Card> cards = Arrays.asList(
            Card.from(Suits.DIAMOND, Denominations.ACE),
            Card.from(Suits.HEART, Denominations.ACE));
        final Cards cardsValue = new Cards(cards);

        cardsValue.add(Card.from(Suits.SPADE, Denominations.ACE));

        assertThat(cardsValue.getUnmodifiableList())
            .contains(Card.from(Suits.SPADE, Denominations.ACE));
    }
}