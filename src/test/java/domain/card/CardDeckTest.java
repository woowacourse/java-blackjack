package domain.card;

import static domain.card.Denomination.ACE;
import static domain.card.Denomination.SIX;
import static fixture.CardFixture.카드;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.card.deck.CardDeck;
import java.util.List;
import org.junit.jupiter.api.Test;

class CardDeckTest {
    @Test
    void 맨_위에서부터_카드를_한_장씩_드로우한다() {
        Card targetFirst = 카드(SIX);
        Card targetSecond = 카드(ACE);
        CardDeck cardDeck = new CardDeck(List.of(targetSecond, targetFirst));

        assertThat(cardDeck.draw()).isEqualTo(targetFirst);
        assertThat(cardDeck.draw()).isEqualTo(targetSecond);
    }

    @Test
    void 카드가_없을_때_카드를_드로우하면_예외가_발생한다() {
        List<Card> oneCard = List.of(카드());
        CardDeck cardDeck = new CardDeck(oneCard);
        cardDeck.draw();

        assertThatThrownBy(cardDeck::draw)
                .isExactlyInstanceOf(IllegalStateException.class);
    }
}
