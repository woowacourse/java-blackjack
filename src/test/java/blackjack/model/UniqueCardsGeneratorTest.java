package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UniqueCardsGeneratorTest {

    @Test
    @DisplayName("모든 경우의 수의 중복이 없는 카드들이 생성한다")
    void 덱을_생성하면_모든_경우의_수의_중복이_없는_카드들이_생성한다() {
        // given
        CardsGenerator generator = new UniqueCardsGenerator();

        // when
        List<Card> cards = generator.create();

        // then
        int rankSize = Rank.values().length;
        int suitSize = Suit.values().length;
        assertThat(cards.size()).isEqualTo(rankSize * suitSize);
    }
}
