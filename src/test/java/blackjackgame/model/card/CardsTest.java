package blackjackgame.model.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {

    @DisplayName("카드 숫자 합을 계산한다")
    @Test
    void testCalculateTotalCardNumbers() {
        Cards cards = createTotalCardNumbersTestCards();
        assertThat(cards.calculateTotalNumbers()).isEqualTo(16);
    }

    private Cards createTotalCardNumbersTestCards() {
        StaticCardDispenser cardJackDia = new StaticCardDispenser(CardNumber.JACK, CardShape.DIAMOND);
        StaticCardDispenser cardFiveClover = new StaticCardDispenser(CardNumber.FIVE, CardShape.CLOVER);
        StaticCardDispenser cardAceHeart = new StaticCardDispenser(CardNumber.ACE, CardShape.HEART);
        return new Cards(
                List.of(new Card(cardJackDia), new Card(cardFiveClover), new Card(cardAceHeart))
        );
    }
}
