package blackjack.domain.participant;

import static blackjack.domain.card.CardFixture.CLOVER_ACE;
import static blackjack.domain.card.CardFixture.CLOVER_TWO;
import static blackjack.domain.card.CardFixture.HEART_EIGHT;
import static blackjack.domain.card.CardFixture.HEART_KING;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PlayerTest {

    private static final List<Card> overDrawPointCards = List.of(
            CLOVER_ACE,
            HEART_KING);
    private static final List<Card> underDrawPointCards = List.of(
            CLOVER_TWO,
            HEART_EIGHT);

    private Player player;

    @BeforeEach
    void setup() {
        player = new Player("pobi", 10000);
    }

    @Test
    void 플레이어_카드의_점수가_21미만이면_드로우_할수있다() {
        underDrawPointCards.forEach(player::drawCard);
        assertThat(player.isDrawable())
                .isTrue();
    }

    @Test
    void 플레이어_카드_점수가_21이상이면_드로우_할수없다() {
        overDrawPointCards.forEach(player::drawCard);
        assertThat(player.isDrawable())
                .isFalse();
    }

    @Test
    void 플레이어는_베팅_금액을_가진다() {
        assertThat(player.calculateProfit(1))
                .isEqualTo(10000);
    }
}
