package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ViewRendererTest {

    @Test
    @DisplayName("카드 렌더링 테스트")
    void renderCardToStringTest() {
        final List<Card> cards = List.of(new Card(CardShape.SPADE, CardNumber.ACE));

        List<String> renderedCardNames = ViewRenderer.renderCardsToString(cards);

        assertThat(renderedCardNames).contains("A스페이드");
    }
}
