package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandCardsTest {

    @Test
    @DisplayName("패에 카드를 추가한다.")
    public void 패에_카드_추가_성공() throws Exception {
        // given
        Card card = new Card(CardSuit.SPADE, CardRank.ACE);
        HandCards handCards = new HandCards(new ArrayList<>());

        // when
        handCards.addCard(card);

        // then
        assertThat(handCards.getHandCards()).contains(card);
    }
}