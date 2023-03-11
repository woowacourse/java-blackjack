package blackjack.domain.game;

import blackjack.domain.card.AceCard;
import blackjack.domain.card.Pattern;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import java.util.List;

class DeckTest {

    @Test
    void drawCardFail() {
        Deck deck = new Deck((x) -> List.of());

        Assertions.assertThatThrownBy(deck::draw)
                .isInstanceOf(IndexOutOfBoundsException.class)
                .hasMessage("더 이상 카드가 없습니다.");
    }

    @Test
    void drawCard() {
        final AceCard target = new AceCard(Pattern.SPADE);
        Deck deck = new Deck((x) -> (Lists.newArrayList(target)));

        Assertions.assertThat(deck.draw()).isEqualTo(target);
        Assertions.assertThat(deck.size()).isEqualTo(0);
    }
}
