package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardFactoryTest {
    @Test
    @DisplayName("새로운 카드 뭉치가 52장이 맞는지 확인")
    void issueNewCardsTest() {
        assertThat(CardFactory.getInstance().issueNewCards().size())
                .isEqualTo(52);
    }
}
