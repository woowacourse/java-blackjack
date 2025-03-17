package card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardDeckTest {
    @DisplayName("카드 배부 시 52장의 카드 덱에서 카드를 뽑아서 배부한다.")
    @Test
    void test2() {
        // given
        CardDeck cardDeck = new CardDeck();
        int originCardDeckSize = cardDeck.getCardDeck().size();

        // when
        cardDeck.drawCard();
        int afterDrawDeckSize = cardDeck.getCardDeck().size();

        // then
        Assertions.assertThat(originCardDeckSize - 1).isEqualTo(afterDrawDeckSize);
    }

    @DisplayName("카드가 다 떨어지면 예외를 발생한다")
    @Test
    void test3() {
        // given
        CardDeck cardDeck = new CardDeck();
        int count = 0;
        for (int i = 0; i < 52; i++) {
            cardDeck.drawCard();
            count++;
        }

        // when & then
        Assertions.assertThatThrownBy(cardDeck::drawCard)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("카드가 다 떨어졌습니다");
    }

    @DisplayName("카드의 합이 21 초과 시 패배한다")
    @Test
    void test4() {
        //given
        Card trumpCard1 = new Card(CardShape.CLOVER, CardRank.K);
        Card trumpCard2 = new Card(CardShape.CLOVER, CardRank.J);
        Card trumpCard3 = new Card(CardShape.CLOVER, CardRank.FIVE);
        CardHand cardDeck = new CardHand();

        cardDeck.add(trumpCard1);
        cardDeck.add(trumpCard2);
        cardDeck.add(trumpCard3);

        // when
        boolean isOver = cardDeck.checkOverScore();

        // then
        Assertions.assertThat(isOver).isTrue();
    }
}
