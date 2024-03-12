package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("카드 생성 팩토리")
class TrumpCardFactoryTest {

    @DisplayName("52개의 카드를 생성해 반환한다.")
    @Test
    void createCards() {
        assertThat(new TrumpCardFactory().createCards().size())
                .isEqualTo(52);
    }
}