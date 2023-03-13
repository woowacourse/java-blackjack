package blackjack.domain.participants;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Shape;
import blackjack.domain.card.Symbol;
import blackjack.domain.game.ResultType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
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
    @DisplayName("플레이어의 승패 결과와 딜러의 승패는 반대이다")
    class CalculateResultTest {

        private final Dealer dealer = new Dealer();
        private Map<String, ResultType> playerResult;


        @BeforeEach
        void setUp() {
            dealer.drawCard(new Card(Shape.DIAMOND, Symbol.FIVE));
            playerResult = Map.of("ire", ResultType.WIN, "kme", ResultType.LOSE, "SKFKS", ResultType.LOSE);
        }

        @Test
        void 딜러가_이긴_횟수는_2이다() {
            final int winCount = Dealer.calculateResult(playerResult)
                    .getOrDefault(ResultType.WIN, 0);

            assertThat(winCount)
                    .isEqualTo(2);
        }

        @Test
        void 딜러가_비긴_횟수는_0이다() {
            final int winCount = Dealer.calculateResult(playerResult)
                    .getOrDefault(ResultType.TIE, 0);

            assertThat(winCount)
                    .isEqualTo(0);
        }

        @Test
        void 딜러가_진_횟수는_1이다() {
            final int winCount = Dealer.calculateResult(playerResult)
                    .getOrDefault(ResultType.LOSE, 0);

            assertThat(winCount)
                    .isEqualTo(1);
        }
    }
}
