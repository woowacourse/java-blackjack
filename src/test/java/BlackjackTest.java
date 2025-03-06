import blackjack.domain.BlackjackShuffle;
import blackjack.domain.Card;
import blackjack.domain.CardPack;
import blackjack.domain.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BlackjackTest {

    @DisplayName("에이스가 2장이면 12로 계산한다")
    @Test
    void ace_test() {
        Player player = new Player("두리");
        player.pushDealCard(new CardPack(new ReversedSortShuffle()), 2);

        // when
        int result = player.calculateCardNumber();

        // then
        assertThat(result).isEqualTo(12);
    }

    private static class ReversedSortShuffle implements BlackjackShuffle {

        @Override
        public void shuffle(List<Card> cards) {
            cards.sort(Comparator
                    .comparing(Card::getNumber)
                    .thenComparing(Card::getShape));
            Collections.reverse(cards);
        }
    }
}
