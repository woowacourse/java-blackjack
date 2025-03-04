package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardDeckTest {

    @DisplayName("가지고있는 카드들의 총합 가능성을 계산한다.")
    @Test
    void testCards() {
        CardDeck cardDeck = new CardDeck();
        cardDeck.add(new Card("스페이드", "2"));
        cardDeck.add(new Card("스페이드", "8"));

        Set<Integer> possibleSums = cardDeck.calculatePossibleSum();

        assertThat(possibleSums).isEqualTo(Set.of(10));
    }

    @DisplayName("총합 가능성 계산 시 에이스가 1인 경우와 11인 경우 모두 계산한다.")
    @Test
    void testCards_ace() {
        CardDeck cardDeck = new CardDeck();
        cardDeck.add(new Card("스페이드", "A"));
        cardDeck.add(new Card("스페이드", "8"));

        Set<Integer> possibleSums = cardDeck.calculatePossibleSum();

        assertThat(possibleSums).isEqualTo(Set.of(9, 19));
    }
}
