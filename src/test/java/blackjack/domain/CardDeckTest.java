package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.CardDeck;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class CardDeckTest {

    @Test
    public void 카드덱_생성_테스트() {
        assertThat(CardDeck.get().size())
                .isEqualTo(52);
    }

    @Test
    public void 카드주가_테스트() {
        CardDeck.giveCard();
        assertThat(CardDeck.get().size()).isEqualTo(51);
    }

    @Test
    public void 카드주가_테스트_error() {
        for (int i = 0; i < 52; i++) {
            CardDeck.giveCard();
        }
        assertThatThrownBy(CardDeck::giveCard)
                .isInstanceOf(Exception.class)
                .hasMessage("덱에 남은 카드가 없습니다");
    }
}