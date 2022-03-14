package blackjack.domain.card;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class CardDeckTest {

    @Test
    @DisplayName("CardDeck에서 카드 뽑는 기능이 정상 동작하는지")
    void drawCardDeckTest() {
        CardDeck cardDeck = CardDeckGenerator.generate();
        Card card = cardDeck.draw();
        Denomination denomination = card.getDenomination();
        Symbol symbol = card.getSymbol();
        assertThatCode(() -> Card.of(denomination, symbol))
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("카드지급 범위초과 에러 테스트")
    public void giveCardIndexExceptionTest() {
        CardDeck cardDeck = CardDeckGenerator.generate();

        for (int i = 0; i < 52; i++) {
            cardDeck.draw();
        }
        assertThatThrownBy(cardDeck::draw)
            .isInstanceOf(Exception.class)
            .hasMessage("[ERROR] 남은 카드가 존재하지 않습니다.");
    }
}
