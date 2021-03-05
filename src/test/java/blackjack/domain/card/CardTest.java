package blackjack.domain.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CardTest {

    private Card card;

    @BeforeEach
    void setUp() {
        card = new Card(Suit.CLUB, CardNumber.ACE);
    }

    @DisplayName("카드 점수 반환 확인")
    @Test
    void getScoreTest() {
        assertThat(card.getScore()).isEqualTo(1);
    }

    @DisplayName("ACE카드 판별 확인")
    @Test
    void isAce() {
        assertTrue(card.isAce());
    }

    @DisplayName("카드 정보 확인")
    @Test
    void makeString() {
        assertThat(card.toString()).isEqualTo("1클로버");

    }
}
