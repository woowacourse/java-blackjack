import domain.TrumpCard;
import domain.CardDeck;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardDeckTest {
    @DisplayName("실행 시점에 서로 다른 카드 52장을 초기화한다.")
    @Test
    void test() {
        // given
        int index = 0;

        // when
        TrumpCard card = CardDeck.getCard(index);

        // then
        Assertions.assertThat(card).isInstanceOf(TrumpCard.class);
    }

    @DisplayName("카드 배부 시 52장의 카드 덱에서 카드를 뽑아서 배부한다.")
    @Test
    void test2() {
        // given
        int originCardDeckSize = CardDeck.getCardDeck().size();
        // when
        CardDeck.drawCard();
        int afterDrawDeckSize = CardDeck.getCardDeck().size();
        // then
        Assertions.assertThat(originCardDeckSize - 1).isEqualTo(afterDrawDeckSize);
    }

    @DisplayName("카드가 다 떨어지면 예외를 발생한다")
    @Test
    void test3() {
        // given
        for (int i = 0; i < 52; i++) {
            CardDeck.drawCard();
        }
        // when & then
        Assertions.assertThatThrownBy(() -> CardDeck.drawCard())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("카드가 다 떨어졌습니다");
    }
}
