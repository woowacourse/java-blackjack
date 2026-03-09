package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class ShuffledCardsGeneratorTest {

    @Test
    void 덱을_생성하면_모든_경우의_수의_중복이_없는_카드들이_생성한다() {
        // given
        CardsGenerator generator = new ShuffledCardsGenerator();
        // when
        List<Card> cards = generator.create();
        // then
        int rankSize = Rank.values().length;
        int suitSize = Suit.values().length;
        assertThat(cards.size()).isEqualTo(rankSize * suitSize);
    }
}
