package domain.card;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.HashSet;
import java.util.Set;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class CardTest {
    private final int MAX_CARD_AMOUNT = 52;

    @Test
    void 카드가_정상적으로_생성되어야_한다() {
        assertDoesNotThrow(() -> Card.of(Suit.SPADE, Rank.ACE));
    }

    @Test
    void Suit의_매개변수로_NULL을_넣으면_예외가_발생해야_한다() {
        Assertions.assertThatThrownBy(() -> Card.of(null, Rank.ACE)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void Rank의_매개변수로_NULL을_넣으면_예외가_발생해야_한다() {
        Assertions.assertThatThrownBy(() -> Card.of(Suit.SPADE, null)).isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    void 카드가_에이스인_경우_참을_반환한다() {
        Card card = Card.of(Suit.DIAMOND, Rank.ACE);

        Assertions.assertThat(card.isAce()).isTrue();
    }

    @Test
    void 카드가_에이스가_아닌_경우_거짓을_반환한다() {
        Card card = Card.of(Suit.DIAMOND, Rank.FOUR);

        Assertions.assertThat(card.isAce()).isFalse();
    }

    @Test
    void 모든_종류의_카드를_받을_땐_52장의_고유한_카드가_생성되어야_한다() {
        Set<Card> cards = new HashSet<>(Card.getAllTypesOfCard());

        Assertions.assertThat(cards).hasSize(MAX_CARD_AMOUNT);
    }

}
