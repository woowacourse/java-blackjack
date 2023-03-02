package blackjack.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings({"NonAsciiCharacters"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DealerTest {
    private static final List<Card> overDrawPointCards = List.of(
            new Card(Shape.CLOVER, Symbol.ACE),
            new Card(Shape.HEART, Symbol.KING));
    private static final List<Card> underDrawPointCards = List.of(
            new Card(Shape.CLOVER, Symbol.TWO),
            new Card(Shape.HEART, Symbol.EIGHT));

    @Test
    void 딜러의_카드가_16_이하의_점수라면_드로우_합니다() {
        final Dealer dealer = new Dealer();
        underDrawPointCards.forEach(dealer::drawCard);

        assertThat(dealer.isDrawable())
                .isTrue();
    }

    @Test
    void 딜러의_카드가_17_이상의_점수라면_스테이_합니다() {
        final Dealer dealer = new Dealer();
        overDrawPointCards.forEach(dealer::drawCard);

        assertThat(dealer.isDrawable())
                .isFalse();
    }

    @Nested
    @DisplayName("딜러를 통해 결과를 계산하면")
    class CalculateResultTest {
        private final Dealer dealer = new Dealer();
        private Player player;

        @BeforeEach
        void setUp() {
            dealer.drawCard(new Card(Shape.DIAMOND, Symbol.FIVE));
            player = new Player("pobi");
        }

        @Test
        void 딜러가_이긴_경우() {
            player.drawCard(new Card(Shape.DIAMOND, Symbol.FOUR));
            dealer.calculateResult(player);

            assertThat(dealer.getResult())
                    .containsEntry(player.getName(), ResultType.WIN);
        }

        @Test
        void 딜러가_비긴_경우() {
            player.drawCard(new Card(Shape.DIAMOND, Symbol.FIVE));
            dealer.calculateResult(player);

            assertThat(dealer.getResult())
                    .containsEntry(player.getName(), ResultType.TIE);
        }

        @Test
        void 딜러가_진_경우() {
            player.drawCard(new Card(Shape.DIAMOND, Symbol.SIX));
            dealer.calculateResult(player);

            assertThat(dealer.getResult())
                    .containsEntry(player.getName(), ResultType.LOSE);
        }
    }
}
