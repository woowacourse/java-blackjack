package blackjack.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

class PlayerTest {
    private final String name = "test";
    private final Card firstCard = new Card(CardShape.SPADE, CardNumber.ACE);
    private final Card seccondCard = new Card(CardShape.CLOVER, CardNumber.EIGHT);
    private CardGroup initialGroup;

    @BeforeEach
    void setUp() {
        initialGroup = new CardGroup(firstCard, seccondCard);
    }

    @Test
    @DisplayName("플레이어 초기화 테스트")
    void initTest() {
        final User player = new Player(name, initialGroup);

        assertSoftly(softly -> {
            softly.assertThat(player.getName()).isEqualTo(name);
            softly.assertThat(player.getCards()).containsExactly(firstCard, seccondCard);
        });
    }

    @Test
    @DisplayName("유저의 점수를 계산하는 기능 테스트")
    void getScoreTest() {
        final User player = new Player(name, initialGroup);

        assertThat(player.getScore())
                .isEqualTo(firstCard.getNumber().getValue() + seccondCard.getNumber().getValue());
    }
}
