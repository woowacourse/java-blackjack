package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class CardGameTest {

    @Test
    void 덱을_생성한다() {
        // given
        CardGame cardGame = new CardGame();

        // when
        List<Card> initialize = cardGame.initialize();

        // then
        assertThat(initialize).hasSize(52);
    }

    @Test
    void 카드_한장을_뽑는다() {
        // given
        CardGame cardGame = new CardGame();

        // when
        List<Card> initialize = cardGame.initialize();
        cardGame.drawOneCard(initialize);

        // then
        Assertions.assertThat(initialize).hasSize(51);
    }
}
