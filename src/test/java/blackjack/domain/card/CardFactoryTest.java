package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CardFactoryTest {

    @Test
    @DisplayName("카드 리스트 생성")
    void generate() {
        List<Card> cards = CardFactory.generate();
        assertThat(cards.size()).isEqualTo(52);
    }
}
