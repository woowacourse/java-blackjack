package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CardFactoryTest {
    @Test
    @DisplayName("새로운 카드 뭉치가 52장이 맞는지 확인")
    void issueNewCardsTest() {
        assertThat(CardFactory.getInstance().issueNewCards().size())
                .isEqualTo(52);
    }

    @Test
    @DisplayName("카드가 중복이 되지 않게 생성됐는지 확인")
    void newCardsNotDuplicatedTest() {
        List<Card> cards = CardFactory.getInstance().issueNewCards();
        assertThat(cards.size() == new HashSet<>(cards).size()).isTrue();
    }
}
