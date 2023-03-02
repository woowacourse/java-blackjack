package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    @DisplayName("플레이어 초기화 테스트")
    void initTest() {
        final String name = "test";
        final Card firstCard = new Card(CardShape.SPADE, CardNumber.ACE);
        final Card seccondCard = new Card(CardShape.CLOVER, CardNumber.EIGHT);
        final CardGroup initialGroup = new CardGroup(firstCard, seccondCard);

        final User player = new Player(name, initialGroup);

        assertSoftly(softly -> {
            softly.assertThat(player.getName()).isEqualTo(name);
            softly.assertThat(player.getCards()).containsExactly(firstCard, seccondCard);
        });
    }

}
