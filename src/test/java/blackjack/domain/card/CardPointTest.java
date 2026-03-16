package blackjack.domain.card;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CardPointTest {

    @Test
    void 각_카드는_점수를_가진다() {

        assertAll(
                () -> assertEquals(2, CardPoint.TWO.getPoint()),
                () -> assertEquals(3, CardPoint.THREE.getPoint()),
                () -> assertEquals(4, CardPoint.FOUR.getPoint()),
                () -> assertEquals(5, CardPoint.FIVE.getPoint()),
                () -> assertEquals(6, CardPoint.SIX.getPoint()),
                () -> assertEquals(7, CardPoint.SEVEN.getPoint()),
                () -> assertEquals(8, CardPoint.EIGHT.getPoint()),
                () -> assertEquals(9, CardPoint.NINE.getPoint()),
                () -> assertEquals(10, CardPoint.TEN.getPoint()),
                () -> assertEquals(10, CardPoint.JACK.getPoint()),
                () -> assertEquals(10, CardPoint.QUEEN.getPoint()),
                () -> assertEquals(10, CardPoint.KING.getPoint()),
                () -> assertEquals(11, CardPoint.ACE.getPoint())
        );

    }

    @Test
    void 각_카드는_고유한_이름을_가진다() {

        assertAll(
                () -> assertEquals("2", CardPoint.TWO.getName()),
                () -> assertEquals("3", CardPoint.THREE.getName()),
                () -> assertEquals("4", CardPoint.FOUR.getName()),
                () -> assertEquals("5", CardPoint.FIVE.getName()),
                () -> assertEquals("6", CardPoint.SIX.getName()),
                () -> assertEquals("7", CardPoint.SEVEN.getName()),
                () -> assertEquals("8", CardPoint.EIGHT.getName()),
                () -> assertEquals("9", CardPoint.NINE.getName()),
                () -> assertEquals("10", CardPoint.TEN.getName()),
                () -> assertEquals("J", CardPoint.JACK.getName()),
                () -> assertEquals("Q", CardPoint.QUEEN.getName()),
                () -> assertEquals("K", CardPoint.KING.getName()),
                () -> assertEquals("A", CardPoint.ACE.getName())
        );

    }

}
