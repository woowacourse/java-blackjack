import static org.assertj.core.api.Assertions.assertThat;

import domain.Card;
import domain.Cards;
import domain.TrumpEmblem;
import domain.TrumpNumber;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    void 문양과_숫자로_카드를_생성한다() {
        // given
        Card card = createCard(TrumpEmblem.DIAMOND, TrumpNumber.ACE);

        // then
        assertThat(card.getNumber()).isEqualTo(TrumpNumber.ACE);
        assertThat(card.getEmblem()).isEqualTo(TrumpEmblem.DIAMOND);
    }

    @Test
    void 카드의_총합을_계산한다() {
        // given
        List<Card> emptyCards = new ArrayList<>();
        emptyCards.add(createCard(TrumpEmblem.DIAMOND, TrumpNumber.ACE));
        emptyCards.add(createCard(TrumpEmblem.SPADE, TrumpNumber.TWO));
        emptyCards.add(createCard(TrumpEmblem.CLOVER, TrumpNumber.THREE));
        emptyCards.add(createCard(TrumpEmblem.HEART, TrumpNumber.FOUR));
        Cards cards = new Cards(emptyCards);

        // when
        int sum = cards.calculateTotalSum();

        // then
        assertThat(sum).isEqualTo(20);
    }

    private static Card createCard(TrumpEmblem emblem, TrumpNumber number) {
        return new Card(number, emblem);
    }

}
