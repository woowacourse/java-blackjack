package config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardDeckFactoryTest {
    @Test
    @DisplayName("카드 덱 생성 테스트")
    void createCardDeckTest() {
        // given
        CardDeckFactory cardDeckFactory = new CardDeckFactory();

        // when-then
        assertThat(cardDeckFactory.create().getCards().size()).isEqualTo(52);
    }
}
