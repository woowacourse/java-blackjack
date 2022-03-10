package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class CardDeckTest {
    @Test
    public void 카드덱_생성_테스트() {
        CardDeck cardDeck = CardDeck.getInstance();
        assertThat(cardDeck.get().size())
                .isEqualTo(52);
    }

    @Test
    void 카드주가_테스트() {
        CardDeck cardDeck = CardDeck.getInstance();
        cardDeck.giveCard();
        assertThat(cardDeck.get().size()).isEqualTo(51);
    }

    @Test
    void 카드주가_테스트_error() {
        CardDeck cardDeck = CardDeck.getInstance();
        for (int i = 0; i < 52; i++) {
            cardDeck.giveCard();
        }
        assertThatThrownBy(cardDeck::giveCard)
                .isInstanceOf(Exception.class)
                .hasMessage("덱에 남은 카드가 없습니다");
    }
}