package blackjack.domain.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CardTest {

    private static final int DECK_SIZE = 52;

    @DisplayName("createCards() 메서드가 필요한 카드들을 생성하는지 테스트")
    @Test
    void createCardsTest() {
        List<Card> cards = CardFactory.createCards();
        Assertions.assertThat(cards.size()).isEqualTo(DECK_SIZE);
    }
}
