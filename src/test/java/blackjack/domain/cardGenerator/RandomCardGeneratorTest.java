package blackjack.domain.cardGenerator;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.PlayingCard;
import java.util.Stack;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RandomCardGeneratorTest {


    @DisplayName("각 카드덱 생성시 이미 생성되어 있는 카드덱을 copy 후 반환하는지 확인한다.")
    @Test
    void copied_stack() {
        //given
        final RandomCardGenerator randomCardGenerator = new RandomCardGenerator();
        final Stack<PlayingCard> deckFirst = randomCardGenerator.generate();
        final Stack<PlayingCard> deckSecond = randomCardGenerator.generate();

        //when
        deckFirst.pop();
        final boolean isCopied = deckFirst.size() < deckSecond.size();

        //then
        assertThat(isCopied).isTrue();
    }
}
