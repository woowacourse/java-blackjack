package blackjack.domain.game;

import blackjack.domain.card.AceCard;
import blackjack.domain.card.Pattern;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import java.util.List;

class CardPoolTest {

    @Test
    void drawCardFail() {
        CardPool cardPool = new CardPool((x) -> List.of());

        Assertions.assertThatThrownBy(cardPool::draw)
                .isInstanceOf(IndexOutOfBoundsException.class)
                .hasMessage("더 이상 카드가 없습니다.");
    }

    @Test
    void drawCard() {
        final AceCard target = new AceCard(Pattern.SPADE);
        CardPool cardPool = new CardPool((x) -> (Lists.newArrayList(target)));

        Assertions.assertThat(cardPool.draw()).isEqualTo(target);
        Assertions.assertThat(cardPool.size()).isEqualTo(0);
    }
}