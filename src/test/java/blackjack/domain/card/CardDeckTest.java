package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.cards.CardDeck;
import blackjack.domain.cards.card.Card;
import java.util.Queue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardDeckTest {

    @Test
    @DisplayName("카드덱 생성되는지 테스트")
    public void createTest() {
        CardDeck cardDeck = new CardDeck();
        assertThat(cardDeck)
                .extracting("value")
                .isInstanceOf(Queue.class);
    }

    @Test
    @DisplayName("카드 주기 기능 테스트")
    void drawTest() {
        // given
        CardDeck cardDeck = new CardDeck();
        // then
        assertThat(cardDeck.pop())
                .isInstanceOf(Card.class);
    }

    @Test
    @DisplayName("카드 주기 예외처리 테스트")
    void drawErrorTest() {
        // given
        CardDeck cardDeck = new CardDeck();

        // when
        for (int i = 0; i < 52; i++) {
            cardDeck.pop();
        }

        // then
        assertThatThrownBy(cardDeck::pop)
                .isInstanceOf(Exception.class)
                .hasMessage("덱에 남은 카드가 없습니다");
    }

    @Test
    @DisplayName("카드 두개 주기 기능 테스트")
    void popCardsTest() {
        // given
        CardDeck cardDeck = new CardDeck();

        // when
        cardDeck.popCards(2);

        // then
        assertThat(cardDeck.size())
                .isEqualTo(50);
    }
}
