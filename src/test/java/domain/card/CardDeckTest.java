package domain.card;

import static fixture.CardFixture.카드;
import static fixture.CardFixture.카드들;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class CardDeckTest {
    @Test
    void 맨_위에서부터_카드를_한_장씩_드로우한다() {
        Card targetFirst = new Card(Denomination.KING, Emblem.HEART);
        Card targetSecond = new Card(Denomination.ACE, Emblem.CLOVER);
        CardDeck cardDeck = new CardDeck(카드들(targetSecond, targetFirst));

        assertThat(cardDeck.draw()).isEqualTo(targetFirst);
        assertThat(cardDeck.draw()).isEqualTo(targetSecond);
    }

    @Test
    void 카드가_없을_때_카드를_드로우하면_예외가_발생한다() {
        CardDeck cardDeck = new CardDeck(카드들(카드()));
        cardDeck.draw();

        assertThatThrownBy(cardDeck::draw)
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }
}
