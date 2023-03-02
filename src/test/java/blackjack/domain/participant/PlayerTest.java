package blackjack.domain.participant;

import static blackjack.domain.card.Number.ACE;
import static blackjack.domain.card.Number.KING;
import static blackjack.domain.card.Number.QUEEN;
import static blackjack.domain.card.Number.TWO;
import static blackjack.domain.card.Suit.CLOVER;
import static blackjack.domain.card.Suit.DIAMOND;
import static blackjack.domain.card.Suit.HEART;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.ArrayList;
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
                new Card(QUEEN, CLOVER),
                new Card(QUEEN, HEART)
        ));
        final Player player = new Player("dazzle", cards);

        assertThat(player.getName()).isEqualTo("dazzle");
    }

    @Test
    void 카드를_뽑을_수_있으면_true_반환한다() {
        final Cards cards = new Cards(List.of(
                new Card(QUEEN, CLOVER),
                new Card(QUEEN, HEART)
        ));
        final Player player = new Player("kokodak", cards);

        assertThat(player.isDrawable()).isTrue();
    }

    @Test
    void 카드를_뽑을_수_없으면_false_반환한다() {
        final Cards cards = new Cards(List.of(
                new Card(QUEEN, CLOVER),
                new Card(ACE, HEART)
        ));
        final Player player = new Player("kokodak", cards);

        assertThat(player.isDrawable()).isFalse();
    }

    @Test
    void 카드를_받는다() {
        //given
        final List<Card> cardPack = new ArrayList<>(List.of(
                new Card(QUEEN, CLOVER),
                new Card(KING, HEART)
        ));
        final Cards cards = new Cards(cardPack);
        final Player player = new Player("dazzle", cards);

        //when
        player.drawCard(new Card(TWO, DIAMOND));

        //then
        assertThat(player.isDrawable()).isFalse();
    }

    @Test
    void 점수를_확인한다() {
        final List<Card> cardPack = new ArrayList<>(List.of(
                new Card(QUEEN, CLOVER),
                new Card(KING, HEART)
        ));
        final Cards cards = new Cards(cardPack);
        final Player player = new Player("dazzle", cards);

        assertThat(player.getScore()).isEqualTo(20);
    }
}
