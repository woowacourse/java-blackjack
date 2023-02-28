package domain.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {

    @Test
    @DisplayName("카드를 추가하는 것을 테스트")
    public void testAddCard() {
        //given
        Set<Card> cardSet = IntStream.range(0, 10)
            .mapToObj(i -> new Card("test" + i))
            .collect(Collectors.toCollection(LinkedHashSet::new));
        Cards cards = new Cards(cardSet);
        Card card = new Card("card");

        //when
        cards.add(card);

        //then
        assertTrue(cards.contains(card));
    }

    @Test
    @DisplayName("보유 여부를 테스트")
    public void testContains() {
        //given
        String name = "test";
        int end = 10;
        Set<Card> cardSet = IntStream.range(0, end + 1)
            .mapToObj(i -> new Card(name + i))
            .collect(Collectors.toCollection(LinkedHashSet::new));
        Cards cards = new Cards(cardSet);

        //when
        boolean result1 = cards.contains(new Card(name + end));
        boolean result2 = cards.contains(new Card(name + (end + 1)));

        //then
        assertTrue(result1);
        assertFalse(result2);
    }
}