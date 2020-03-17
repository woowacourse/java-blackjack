package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    @DisplayName("해당 카드가 A인지 확인 테스트")
    void isAce() {
        Card aceCard = new Card(Symbol.ACE, Type.CLUB);
        Card nonAceCard = new Card(Symbol.QUEEN, Type.CLUB);
        assertThat(aceCard.isAce()).isTrue();
        assertThat(nonAceCard.isAce()).isFalse();
    }
}
