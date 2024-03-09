package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @DisplayName("딜러 생성")
    @Test
    public void create() {
        assertThatCode(() -> new Dealer(new Deck(new CardFactory().createBlackJackCard())))
                .doesNotThrowAnyException();
    }

    @DisplayName("덱에서 카드를 한 장 반환한다")
    @Test
    public void draw() {
        Dealer dealer = new Dealer(new Deck(new CardFactory().createBlackJackCard()));

        assertThat(dealer.draw()).isEqualTo(new Card(CardSuit.SPADE, CardNumber.KING));
    }
}
