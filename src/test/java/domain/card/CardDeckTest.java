package domain.card;

import static domain.card.Number.TWO;
import static domain.card.Shape.DIAMOND;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardDeckTest {

    @Test
    @DisplayName("카드 한 장 뽑기 테스트")
    void hitCardTest() {
        // given
        CardDeck cardDeck = new CardDeck(List.of(new Card(DIAMOND, TWO)));

        // when
        Card card = cardDeck.hitCard();

        // then
        assertSoftly(softly -> {
            softly.assertThat(card.getShape()).isEqualTo(DIAMOND);
            softly.assertThat(card.getNumber()).isEqualTo(TWO);
        });
    }
}
