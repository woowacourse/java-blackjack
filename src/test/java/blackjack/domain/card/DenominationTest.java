package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DenominationTest {

    @Test
    @DisplayName("카드 숫자 생성 성공")
    void createDenomination() {
        Denomination denomination = Denomination.valueOf("ACE");
        assertThat(denomination).isEqualTo(Denomination.ACE);
    }

    @Test
    @DisplayName("Ace 점수 계산")
    void calculateAceScore() {
        int score = 10;
        long aceCount = 1;
        assertThat(Denomination.plusRemainAceScore(score, aceCount)).isEqualTo(20);
    }

    @Test
    @DisplayName("Ace 여부 확인")
    void checkAce() {
        assertTrue(Denomination.ACE.isAce());
    }

    @Test
    @DisplayName("Ace가 아닌 경우 확인")
    void checkNoAce() {
        assertFalse(Denomination.KING.isAce());
    }
}
