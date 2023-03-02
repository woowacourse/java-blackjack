package blackjack.domain;

import static blackjack.domain.Rank.ACE;
import static blackjack.domain.Rank.JACK;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.util.FixedDeck;
import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class GamblerTest {

    @Test
    void 겜블러가_정상_생성된다() {
        final Gambler gambler = new Gambler("허브");

        assertThat(gambler.getName()).isEqualTo("허브");
    }

    @Test
    void 게임_시작_시_카드를_뽑는다() {
        final Gambler gambler = new Gambler("허브");
        final Deck deck = new FixedDeck(List.of(
                new Card(ACE, Shape.DIAMOND),
                new Card(JACK, Shape.DIAMOND)
        ));

        gambler.initialDraw(deck);

        assertThat(gambler.getCardLetters()).containsExactly("A다이아몬드", "J다이아몬드");
    }

    @Test
    void 카드를_뽑는다() {
        final Gambler gambler = new Gambler("허브");
        final Deck deck = new FixedDeck(List.of(
                new Card(ACE, Shape.DIAMOND)
        ));

        gambler.draw(deck);

        assertThat(gambler.getCardLetters()).containsExactly("A다이아몬드");
    }

}
