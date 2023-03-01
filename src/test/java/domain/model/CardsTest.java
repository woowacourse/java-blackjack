package domain.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domain.type.Letter;
import domain.type.Suit;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {

    @Test
    @DisplayName("카드를 추가하는 것을 테스트")
    public void testAddCard() {
        //given
        Set<Card> cardSet = Arrays.stream(Letter.values())
            .map(i -> new Card(Suit.DIAMOND, i))
            .collect(Collectors.toSet());
        Cards cards = new Cards(cardSet);
        Card card = new Card(Suit.SPADE, Letter.ACE);

        //when
        cards.add(card);

        //then
        assertTrue(cards.contains(Letter.ACE));
    }

    @Test
    @DisplayName("보유 여부를 테스트")
    public void testContains() {
        //given
        Set<Card> cardSet = Set.of(new Card(Suit.CLUB, Letter.ACE));
        Cards cards = new Cards(cardSet);

        //when
        boolean result1 = cards.contains(Letter.ACE);
        boolean result2 = cards.contains(Letter.TWO);

        //then
        assertTrue(result1);
        assertFalse(result2);
    }
}