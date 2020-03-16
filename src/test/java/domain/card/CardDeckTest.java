package domain.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CardDeckTest {
    private CardDeck cardDeck;

    @BeforeEach
    private void setUp() {
        cardDeck = new CardDeck();
    }

    @DisplayName("Cards 객체 생성 테스트")
    @Test
    void CardsTest() {
        Assertions.assertThat(cardDeck).isInstanceOf(CardDeck.class);
    }

    @DisplayName("객체의 복사 테스트")
    @Test
    void deepDuplicateTest() {
        Assertions.assertThat(cardDeck).extracting("cardDeck").asList().size().isEqualTo(Card.getCards().size());
    }

    @DisplayName("카드 덱에서 카드를 한 장 꺼내는 기능 테스트")
    @Test
    void drawCardTest() {
        Card popCard = cardDeck.drawCard();

        Assertions.assertThat(popCard).isInstanceOf(Card.class);
        Assertions.assertThat(cardDeck).extracting("cardDeck").asList().size().isNotEqualTo(Card.getCards().size());
    }

    @DisplayName("카드 덱을 모두 소모했을 때 예외 처리 테스트")
    @Test
    void hitWhenEmptyDeckTest() {
        Assertions.assertThatThrownBy(() -> {
            for (int i = 0; i < 53; i++) {
                Card card = cardDeck.drawCard();
            }
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("플레이어를 생성할 때 기본으로 카드 두 장을 지급하는 기능 테스트")
    @Test
    void initialDrawTest() {
        List<Card> initialDrawCards = cardDeck.initialDraw();

        Assertions.assertThat(initialDrawCards.size()).isEqualTo(2);
        Assertions.assertThat(cardDeck).extracting("cardDeck").asList().size().isEqualTo(46);
    }
}
