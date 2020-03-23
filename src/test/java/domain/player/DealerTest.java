package domain.player;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardSuitSymbol;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class DealerTest {
    @DisplayName("생성자 테스트")
    @Test
    void constructorTest() {
        Assertions.assertThat(new Dealer(new ArrayList<>(Arrays.asList(
                Card.of(CardNumber.THREE, CardSuitSymbol.DIAMOND),
                Card.of(CardNumber.FOUR, CardSuitSymbol.CLUB)
        )))).isInstanceOf(Dealer.class);
    }

    @DisplayName("딜러가 16 초과의 카드를 갖고 있을 때 카드를 더 받지 않는다고 반환하는지 테스트")
    @Test
    void isHeatOverSixteenTest() {
        Dealer dealer = new Dealer(new ArrayList<>(Arrays.asList(
                Card.of(CardNumber.KING, CardSuitSymbol.DIAMOND),
                Card.of(CardNumber.SEVEN, CardSuitSymbol.CLUB)
        )));

        Assertions.assertThat(dealer.isHit()).isFalse();
    }

    @DisplayName("딜러가 16 이하의 카드를 갖고 있을 때 카드를 받겠다고 반환하는지 테스트")
    @Test
    void isHeatUnderSixteenTest() {
        Dealer dealer = new Dealer(new ArrayList<>(Arrays.asList(
                Card.of(CardNumber.KING, CardSuitSymbol.DIAMOND),
                Card.of(CardNumber.FIVE, CardSuitSymbol.CLUB)
        )));

        Assertions.assertThat(dealer.isHit()).isTrue();
    }

    @DisplayName("딜러가 추가로 지급 받은 카드의 수를 반환하는지 테스트")
    @Test
    void getHitCardsCountTest() {
        Dealer noHitDealer = new Dealer(new ArrayList<>(Arrays.asList(
                Card.of(CardNumber.ACE, CardSuitSymbol.SPACE),
                Card.of(CardNumber.KING, CardSuitSymbol.SPACE)
        )));
        Dealer hitDealer = new Dealer(new ArrayList<>(Arrays.asList(
                Card.of(CardNumber.ACE, CardSuitSymbol.SPACE),
                Card.of(CardNumber.TWO, CardSuitSymbol.SPACE),
                Card.of(CardNumber.KING, CardSuitSymbol.SPACE),
                Card.of(CardNumber.FOUR, CardSuitSymbol.SPACE)
        )));

        Assertions.assertThat(noHitDealer.getHitCardsCount()).isEqualTo(0);
        Assertions.assertThat(hitDealer.getHitCardsCount()).isEqualTo(2);
    }
}
