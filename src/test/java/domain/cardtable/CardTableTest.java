package domain.cardtable;

import domain.area.CardArea;
import domain.deck.CardDeck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardTableTest {

    @Test
    @DisplayName("createCardArea() : 각 player의 카드 영역을 생성해준다.")
    void test_dealCard() throws Exception {
        //given
        final CardDeck cardDeck = CardDeck.shuffledFullCardDeck();
        final CardTable cardTable = CardTable.readyToPlayBlackjack(cardDeck);

        //when
        final CardArea cardArea = cardTable.createCardArea();

        //then
        assertThat(cardArea.cards()).hasSize(2);
    }
}
