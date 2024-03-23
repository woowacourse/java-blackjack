package blackjackgame.model.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {

    @DisplayName("카드 숫자 합을 계산한다")
    @Test
    void testCalculateTotalCardNumbers() {
        Cards cards = createTotalCardNumbersTestCards();
        assertThat(cards.calculateTotalNumbers()).isEqualTo(16);
    }

    private Cards createTotalCardNumbersTestCards() {
        StaticCardDispenser cardJackDia = new StaticCardDispenser(CardNumber.JACK, CardShape.DIAMOND);
        StaticCardDispenser cardFiveClover = new StaticCardDispenser(CardNumber.FIVE, CardShape.CLOVER);
        StaticCardDispenser cardAceHeart = new StaticCardDispenser(CardNumber.ACE, CardShape.HEART);
        return new Cards(
                List.of(new Card(cardJackDia), new Card(cardFiveClover), new Card(cardAceHeart))
        );
    }

    @DisplayName("중복 카드를 허용하는 카드셋에서 순서가 달라도 카드 개수와 구성이 같으면 카드셋이 같은지 확인")
    @Test
    void testEqualCards() {
        Cards cards1 = createTotalCardNumbersTestCards1();
        Cards cards2 = createTotalCardNumbersTestCards2();
        assertThat(cards1).isEqualTo(cards2);
    }

    @DisplayName("카드 구성은 같지만 개수가 다르면 동일하지 않은 카드셋인지 확인")
    @Test
    void testIsNotEqualCards(){
        Cards cards2 = createTotalCardNumbersTestCards2();
        Cards cards3 = createTotalCardNumbersTestCards3();
        assertThat(cards2).isNotEqualTo(cards3);
    }

    private Cards createTotalCardNumbersTestCards1() {
        StaticCardDispenser cardJackDia = new StaticCardDispenser(CardNumber.JACK, CardShape.DIAMOND);
        StaticCardDispenser cardAceHeart = new StaticCardDispenser(CardNumber.ACE, CardShape.HEART);
        StaticCardDispenser cardJackDia2 = new StaticCardDispenser(CardNumber.JACK, CardShape.DIAMOND);
        StaticCardDispenser cardFiveClover = new StaticCardDispenser(CardNumber.FIVE, CardShape.CLOVER);
        return new Cards(
                List.of(new Card(cardFiveClover), new Card(cardAceHeart), new Card(cardJackDia), new Card(cardJackDia2))
        );
    }

    private Cards createTotalCardNumbersTestCards2() {
        StaticCardDispenser cardJackDia = new StaticCardDispenser(CardNumber.JACK, CardShape.DIAMOND);
        StaticCardDispenser cardFiveClover = new StaticCardDispenser(CardNumber.FIVE, CardShape.CLOVER);
        StaticCardDispenser cardAceHeart = new StaticCardDispenser(CardNumber.ACE, CardShape.HEART);
        StaticCardDispenser cardJackDia2 = new StaticCardDispenser(CardNumber.JACK, CardShape.DIAMOND);
        return new Cards(
                List.of(new Card(cardFiveClover), new Card(cardAceHeart), new Card(cardJackDia), new Card(cardJackDia2))
        );
    }

    private Cards createTotalCardNumbersTestCards3() {
        StaticCardDispenser cardJackDia = new StaticCardDispenser(CardNumber.JACK, CardShape.DIAMOND);
        StaticCardDispenser cardFiveClover = new StaticCardDispenser(CardNumber.FIVE, CardShape.CLOVER);
        StaticCardDispenser cardAceHeart = new StaticCardDispenser(CardNumber.ACE, CardShape.HEART);
        return new Cards(
                List.of(new Card(cardFiveClover), new Card(cardAceHeart), new Card(cardJackDia))
        );
    }
}
