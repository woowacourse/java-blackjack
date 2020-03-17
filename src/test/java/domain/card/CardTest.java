package domain.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardTest {
    private Card aceCard;
    private Card kingCard;

    @BeforeEach
    void setUp() {
        aceCard = new Card(Symbol.valueOf("ACE"), Type.valueOf("HEART"));
        kingCard = new Card(Symbol.valueOf("KING"), Type.valueOf("HEART"));
    }

    @Test
    @DisplayName("Symbol과 Type을 인자로 갖는 Card가 생성된다.")
    void card() {
        assertThat(new Card(Symbol.valueOf("ACE"), Type.valueOf("HEART"))).isNotNull();
    }

    @Test
    @DisplayName("카드가 Ace인지 확인한다.")
    void isAce() {
        assertThat(aceCard.isAce()).isTrue();
        assertThat(kingCard.isAce()).isFalse();
    }
}
