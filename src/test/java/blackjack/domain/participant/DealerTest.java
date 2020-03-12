package blackjack.domain.participant;

import blackjack.domain.card.CardFactory;
import blackjack.domain.card.Figure;
import blackjack.domain.card.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {
    @DisplayName("딜러의 카드 합이 16 초과하지 않는 것 확인")
    @Test
    void getMoreCardTest() {
        Dealer dealer = new Dealer();
        dealer.addCard(CardFactory.of(Type.FIVE, Figure.CLOVER));
        dealer.addCard(CardFactory.of(Type.FIVE, Figure.HEART));

        assertThat(dealer.canGetMoreCard()).isTrue();
    }

    @DisplayName("딜러의 카드 합이 16 초과할 때 확인")
    @Test
    void getMoreCardTest2() {
        Dealer dealer = new Dealer();
        dealer.addCard(CardFactory.of(Type.QUEEN, Figure.CLOVER));
        dealer.addCard(CardFactory.of(Type.KING, Figure.CLOVER));

        assertThat(dealer.canGetMoreCard()).isFalse();
    }


}
