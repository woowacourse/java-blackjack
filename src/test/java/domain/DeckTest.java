package domain;

import java.util.Set;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {

    @DisplayName("52장의 서로 다른 카드가 정상 생성된다.")
    @Test
    void _52장의_서로_다른_카드가_정상_생성된다() {
        Deck deck = new Deck();

        Set<Card> distinctCards = Set.copyOf(deck.getCards());

        Assertions.assertThat(distinctCards.size()).isEqualTo(deck.getCards().size());
        Assertions.assertThat(distinctCards.size()).isEqualTo(52);
    }
}

