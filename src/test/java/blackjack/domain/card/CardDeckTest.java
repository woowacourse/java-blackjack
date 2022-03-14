package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class CardDeckTest {

    CardDeck cardDeck;

    @BeforeEach
    void init() {
        cardDeck = CardDeckGenerator.generate();
    }

    @Test
    @DisplayName("카드덱 생성 테스트")
    public void createCardTest() {
//        CardDeck cardDeck = CardDeckGenerator.generate();
        assertThat(cardDeck.size())
            .isEqualTo(52);
    }

    @Test
    @DisplayName("카드지급 후 남은 카드 테스트")
    public void remainCardAmountTest() {
        cardDeck.draw();
        assertThat(cardDeck.size()).isEqualTo(51);
    }

    @Test
    @DisplayName("카드지급 범위초과 에러 테스트")
    public void giveCardIndexExceptionTest() {
        for (int i = 0; i < 52; i++) {
            cardDeck.draw();
        }
        assertThatThrownBy(cardDeck::draw)
            .isInstanceOf(Exception.class)
            .hasMessage("[ERROR] 남은 카드가 존재하지 않습니다.");
    }
}
