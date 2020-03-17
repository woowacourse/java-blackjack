package second.domain.card;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class HandCardsTest {
    @Test
    void 생성자_테스트() {
        HandCards handCards = new HandCards(Collections.emptyList());
        assertThat(handCards).isInstanceOf(HandCards.class);
    }

    @Test
    void drawCard() {
        HandCards handCards = new HandCards(new ArrayList<>());

        handCards.drawCard(new CardDeck());
        List<Card> result = handCards.getCards();

        assertThat(result.size()).isEqualTo(1);
    }
}
