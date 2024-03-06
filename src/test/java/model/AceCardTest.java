package model;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AceCardTest {

    @Test
    @DisplayName("소프트 에이스를 하드 에이스로 변경하고, 요청시 변경된 액면가를 반환한다.")
    void changeToHardAce_WhenChangeSoftAceToHardAceCard_ThenChangeActualValue11To1() {
        AceCard testdefaultAceCard = (AceCard) Card.from(Number.ACE, Emblem.SPADE);
        AceCard testAceCard = (AceCard) Card.from(Number.ACE, Emblem.SPADE);

        testAceCard.changeToHardAce();
        assertAll(() -> {
            assertEquals(11, testdefaultAceCard.getCardActualValue());
            assertEquals(1, testAceCard.getCardActualValue());
        });
    }

    @Test
    @DisplayName("하드 에이스 상태에서 하드 에이스로 변경요청을 하면 예외를 발생시킨다.")
    void changeToHardAce_WhenChangeHardAceToHardAceCard_ThenThrowException() {
        AceCard testAceCard = (AceCard) Card.from(Number.ACE, Emblem.SPADE);

        testAceCard.changeToHardAce();

        assertThrows(IllegalStateException.class, testAceCard::changeToHardAce);
    }


}
