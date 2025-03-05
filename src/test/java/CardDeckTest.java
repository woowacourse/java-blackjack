import domain.CardDeck;
import domain.CardNumber;
import domain.CardSetting;
import domain.CardShape;
import domain.TrumpCard;
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
        TrumpCard card = CardSetting.getCard(index);

        // then
        Assertions.assertThat(card).isInstanceOf(TrumpCard.class);
    }

    @DisplayName("카드 배부 시 52장의 카드 덱에서 카드를 뽑아서 배부한다.")
    @Test
    void test2() {
        // given
        int originCardDeckSize = CardSetting.getCardDeck().size();
        // when
        CardSetting.drawCard();
        int afterDrawDeckSize = CardSetting.getCardDeck().size();
        // then
        Assertions.assertThat(originCardDeckSize - 1).isEqualTo(afterDrawDeckSize);
    }

    @DisplayName("카드가 다 떨어지면 예외를 발생한다")
    @Test
    void test3() {
        // given
        for (int i = 0; i < 52; i++) {
            CardSetting.drawCard();
        }
        // when & then
        Assertions.assertThatThrownBy(() -> CardSetting.drawCard())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("카드가 다 떨어졌습니다");
    }

    @DisplayName("카드의 합이 21 초과 시 패배한다")
    @Test
    void test4() {
        //given
        TrumpCard trumpCard1 = new TrumpCard(CardShape.CLOVER, CardNumber.K);
        TrumpCard trumpCard2 = new TrumpCard(CardShape.CLOVER, CardNumber.J);
        TrumpCard trumpCard3 = new TrumpCard(CardShape.CLOVER, CardNumber.FIVE);
        CardDeck cardDeck = new CardDeck();

        cardDeck.addTrumpCard(trumpCard1);
        cardDeck.addTrumpCard(trumpCard2);
        cardDeck.addTrumpCard(trumpCard1);

        boolean isOver = cardDeck.sumAllCardValue();
        Assertions.assertThat(isOver).isTrue();
    }

}
