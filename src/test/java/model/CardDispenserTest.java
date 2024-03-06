package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardDispenserTest {

    @Test
    @DisplayName("blackJackCards에서 카드를 차례대로 하나씩 뽑는다.")
    void dispenseCard_ShouldReturnSpecificCard_WhenDispenserHasCards() {

        CardDispenser dispenser = new CardDispenser(new FixedCardShuffleMachine());
        Card dispensedcard = dispenser.dispenseCard();

        assertEquals(Card.from(Number.ACE, Emblem.SPADE), dispensedcard);
    }

    @Test
    @DisplayName("blackJackCards에서 꺼낼 카드가 없다면 예외를 던진다.")
    void dispenseCard_ShouldThrowException_WhenDispenseAllCards() {

        CardDispenser dispenser = new CardDispenser(new FixedCardShuffleMachine());
        for (int cardNumber = 0; cardNumber < 52; cardNumber++) {
            dispenser.dispenseCard();
        }

        assertThrows(IllegalStateException.class, () -> dispenser.dispenseCard());
    }

}
