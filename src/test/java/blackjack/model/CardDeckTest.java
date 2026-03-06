package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardDeckTest {

    PickStrategy mockStrategy = cards -> Card.opened(Rank.ACE, Suit.CLOVER);

    @Test
    @DisplayName("카드 덱을 생성한다.")
    void ofCardDeck() {
        // when & then
        assertThatCode(() -> CardDeck.of(mockStrategy)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("카드를 한 장 드로우 한다.")
    void drawTest() {
        // given
        CardDeck cardDeck = CardDeck.of(mockStrategy);

        // when
        List<Card> draw = cardDeck.draw(1);

        // then
        assertThat(draw.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("카드 덱에 남아있는 카드보다 더 많이 드로우를 시도하면 예외가 발생한다.")
    void drawExceptionTest() {
        // given
        CardDeck cardDeck = CardDeck.of(new RandomPickStrategy());

        // when & then
        assertThatThrownBy(() -> cardDeck.draw(53))
                .isInstanceOf(IllegalStateException.class);
    }
}