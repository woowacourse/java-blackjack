package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Figure;
import blackjack.domain.card.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static blackjack.domain.participant.Dealer.DEALER_NAME;
import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {

    @DisplayName("name 속성의 값 \"딜러\"로 초기화 확인")
    @Test
    void test1() {
        Dealer dealer = new Dealer();

        String dealerName = dealer.name();

        assertThat(dealerName).isEqualTo(DEALER_NAME);
    }

    @DisplayName("추가로 카드를 받을 수 있는지 여부 확인")
    @ParameterizedTest
    @CsvSource(value = {"SIX, true", "SEVEN, false"})
    void test2(Type type, boolean expectedPossibility) {
        Dealer dealer = new Dealer();
        dealer.addCard(Card.of(Type.TEN, Figure.CLOVER));
        dealer.addCard(Card.of(type, Figure.HEART));

        boolean actualPossibility = dealer.canGetMoreCard();

        assertThat(actualPossibility).isEqualTo(expectedPossibility);
    }

    @DisplayName("첫 번째 카드를 보여주는지 확인")
    @Test
    void test3() {
        Dealer dealer = new Dealer();
        dealer.addCard(Card.of(Type.FIVE, Figure.CLOVER));
        dealer.addCard(Card.of(Type.EIGHT, Figure.HEART));

        List<String> firstCardInfo = dealer.showFirstCard();
        int cardInfoSize = firstCardInfo.size();

        assertThat(cardInfoSize).isEqualTo(1);
        assertThat(firstCardInfo).contains("5클로버");
    }
}
