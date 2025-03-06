import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class CardsTest {

    @Test
    void 카드_덱에서_카드_두_장을_받아온다() {
        //given
        CardDeck cardDeck = CardDeck.createCards();
        Cards cards = new Cards();

        //when
        cards.drawCardWhenStart(cardDeck);

        //then
        assertThat(cards.getCards()).hasSize(2);
    }
}
