package blackjack.domain.player;

import static blackjack.util.CardFixtures.ACE_DIAMOND;
import static blackjack.util.CardFixtures.ACE_SPADE;
import static blackjack.util.CardFixtures.KING_HEART;
import static blackjack.util.CardFixtures.KING_SPADE;
import static blackjack.util.CardFixtures.TEN_SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;
import blackjack.util.FixedDeck;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class AbstractPlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        player = new AbstractPlayer(Name.from("허브"), new Hand()) {
            @Override
            public boolean isDrawable() {
                return hand().isPlayable();
            }

            @Override
            public boolean isDealer() {
                return false;
            }
        };
    }

    @Test
    void 카드를_초기에_두장_뽑는다() {
        final Deck deck = new FixedDeck(ACE_DIAMOND, KING_HEART);

        player.initialDraw(deck);

        assertThat(player.getSymbols()).containsExactly("A다이아몬드", "K하트");
    }

    @Test
    void 카드를_뽑는다() {
        final Deck deck = new FixedDeck(ACE_DIAMOND);

        player.draw(deck);

        assertThat(player.getSymbols()).containsExactly("A다이아몬드");
    }

    @Test
    void 점수를_반환한다() {
        player.draw(new FixedDeck(ACE_SPADE));

        assertThat(player.calculateScore()).isEqualTo(11);
    }

    @Test
    void 카드를_더_뽑을_수_없는_상태로_변경한다() {
        player.stay();

        assertThat(player.isDrawable()).isFalse();
    }

    @Test
    void 게임의_결과를_반환한다() {
        player.initialDraw(new FixedDeck(ACE_DIAMOND, KING_HEART));
        final Hand hand = new Hand(List.of(KING_SPADE));

        final Result result = player.play(hand);

        assertThat(result).isEqualTo(Result.BLACKJACK_WIN);
    }

    @ParameterizedTest(name = "이름이 같은지 확인한다. 대상: 허브, 입력:{0}, 결과:{1}")
    @CsvSource({"허브, true", "허브아님, false"})
    void 이름이_같은지_확인한다(final String name, final boolean result) {
        assertThat(player.isSameName(Name.from(name))).isEqualTo(result);
    }

    @Test
    void 이름을_반환한다() {
        assertThat(player.name()).isEqualTo(Name.from("허브"));
    }

    @Test
    void 이름값을_반환한다() {
        assertThat(player.getNameValue()).isEqualTo("허브");
    }

    @Test
    void 보유하고_있는_모든_카드의_정보를_반환한다() {
        player.draw(new FixedDeck(TEN_SPADE));

        assertThat(player.getSymbols()).containsExactly("10스페이드");
    }
}
