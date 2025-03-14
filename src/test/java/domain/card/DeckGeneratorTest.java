package domain.card;

import static domain.card.DeckGenerator.generateDeck;
import static org.assertj.core.api.Assertions.assertThatCode;

import domain.card.strategy.BlackjackDrawStrategy;
import domain.card.strategy.DrawStrategy;
import org.junit.jupiter.api.Test;

class DeckGeneratorTest {

    @Test
    void 블랙잭_카드_덱을_생성한다() {
        //given
        DrawStrategy drawStrategy = new BlackjackDrawStrategy();

        // when & then
        assertThatCode(() -> generateDeck(drawStrategy))
                .doesNotThrowAnyException();
    }
}
