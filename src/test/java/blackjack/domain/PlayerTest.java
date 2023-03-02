package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PlayerTest {

    @Test
    void 이름을_확인한다() {
        final Cards cards = new Cards(List.of(
                new Card(Number.QUEEN, Suit.CLOVER),
                new Card(Number.QUEEN, Suit.HEART)
        ));
        final Player player = new Player("dazzle", cards);

        assertThat(player.getName()).isEqualTo("dazzle");
    }

    @Test
    void 카드를_뽑을_수_있으면_true_반환한다() {
        final Cards cards = new Cards(List.of(
                new Card(Number.QUEEN, Suit.CLOVER),
                new Card(Number.QUEEN, Suit.HEART)
        ));
        final Player player = new Player("kokodak", cards);

        assertThat(player.isHittable()).isTrue();
    }

    @Test
    void 카드를_뽑을_수_없으면_false_반환한다() {
        final Cards cards = new Cards(List.of(
                new Card(Number.QUEEN, Suit.CLOVER),
                new Card(Number.ACE, Suit.HEART)
        ));
        final Player player = new Player("kokodak", cards);

        assertThat(player.isHittable()).isFalse();
    }
}

class Player {

    private final Name name;
    private final Cards cards;

    public Player(final String name, final Cards cards) {
        this.name = new Name(name);
        this.cards = cards;
    }

    public String getName() {
        return name.getValue();
    }

    public boolean isHittable() {
        return !cards.isMaximumScore() && !cards.isTotalScoreOver();
    }
}
