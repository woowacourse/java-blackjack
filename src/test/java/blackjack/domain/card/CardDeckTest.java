package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CardDeckTest {

    @Test
    @DisplayName("카드 52개 생성한다")
    void createCardDeckTest() {
        CardDeck cardDeck = new CardDeck();

        assertThat(cardDeck.getCards()).hasSize(52);
    }

    @Test
    @DisplayName("카드 한 장을 나눠준다")
    void pickCardTest() {
        CardDeck cardDeck = new CardDeck();

        cardDeck.pick();

        assertThat(cardDeck.getCards()).hasSize(51);
    }

    @Test
    @DisplayName("카드 두 장을 나눠준다")
    void pickTwiceCardTest() {
        CardDeck cardDeck = new CardDeck();

        List<Card> cards = cardDeck.pickTwice();

        assertThat(cards).hasSize(2);
    }

    @Test
    @DisplayName("52장의 카드를 모두 소진한 경우 오류를 반환한다.")
    void pickCardMoreThan52Test() {
        CardDeck cardDeck = new CardDeck();

        assertThatThrownBy(() -> {
            for (int count = 0; count < 53; count++) {
                cardDeck.pick();
            }
        }).isInstanceOf(IllegalArgumentException.class)
          .hasMessage("모든 카드가 소진되어 게임을 종료합니다.");
    }
}
