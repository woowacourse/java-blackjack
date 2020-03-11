package blackjack;

import blackjack.domain.Card;
import blackjack.domain.CardFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class CardFactoryTest {
    @DisplayName("카드 덱 생성 테스트")
    @Test
    void createTest() {
        List<Card> cards = CardFactory.getCardDeck();
        Set<Card> deduplicatedCards = new HashSet<>(cards);
        assertThat(cards.size()).isEqualTo(52);
        assertThat(cards.size() == deduplicatedCards.size()).isTrue();
    }
}
