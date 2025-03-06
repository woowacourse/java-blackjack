import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
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

    @Test
    void 카드_덱에서_카드_한_장을_받아온다() {
        //given
        CardDeck cardDeck = CardDeck.createCards();
        Cards cards = new Cards();

        //when
        cards.drawCard(cardDeck);

        //then
        assertThat(cards.getCards()).hasSize(1);
    }

    @Test
    void 보유한_카드의_합계가_21이_넘어가는지_판정한다() {
        //given
        CardDeck cardDeck = CardDeck.createCards();
        Cards cards = new Cards();
        List<Card> drawnCards = cards.getCards();
        drawnCards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        drawnCards.add(new Card(Pattern.CLOVER, CardNumber.TEN));
        drawnCards.add(new Card(Pattern.SPADE, CardNumber.TWO));

        //when & then
        assertThat(cards.checkBurst()).isTrue();
    }

}
