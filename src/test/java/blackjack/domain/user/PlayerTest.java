package blackjack.domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardGroup;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    private final String name = "test";
    private final Card cardKing = new Card(CardShape.SPADE, CardNumber.KING);
    private final Card cardEight = new Card(CardShape.CLOVER, CardNumber.EIGHT);
    private CardGroup initialCardGroup;

    @BeforeEach
    void setUp() {
        initialCardGroup = new CardGroup(cardKing, cardEight);
    }

    @Test
    @DisplayName("플레이어 초기화 테스트")
    void initTest() {
        final User player = new Player(name, initialCardGroup);
        assertSoftly(softly -> {
            softly.assertThat(player.getName().getValue()).isEqualTo(name);
            softly.assertThat(player.getCardGroups().getCards()).containsExactly(cardKing, cardEight);
        });
    }

    @Test
    @DisplayName("유저의 점수를 계산하는 기능 테스트")
    void getScoreTest() {
        final User player = new Player(name, initialCardGroup);

        assertThat(player.getScore().getValue())
                .isEqualTo(cardKing.getNumber().getValue() + cardEight.getNumber().getValue());
    }

    @Test
    @DisplayName("유저가 카드를 하나 뽑는 기능 테스트")
    void drawCardTest() {
        final Card card = new Card(CardShape.HEART, CardNumber.JACK);
        final User player = new Player(name, initialCardGroup);

        player.drawCard(card);

        Assertions.assertThat(player.getCardGroups().getCards()).containsExactly(cardKing, cardEight, card);
    }

    @Test
    @DisplayName("첫 패 확인 테스트")
    void getInitialStatus() {
        final User player = new Player(name, initialCardGroup);

        assertThat(player.getFirstOpenCardGroup().getCards()).containsExactly(cardKing, cardEight);
    }
}
