package blackjack.domain;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("딜러는 유일한 카드들을 만들 수 있어야 한다.")
    void createUniqueCards_success() {
        // given
        Dealer dealer = new Dealer();

        // when
        List<Card> cards = dealer.createUniqueCards().getCards();
        List<Card> uniqueCards = cards.stream()
                .distinct()
                .collect(toList());

        // then
        assertThat(uniqueCards)
                .hasSize(48);
    }
}
