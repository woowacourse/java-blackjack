package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        Dealer dealer = new Dealer();

        String result = dealer.getName();

        assertThat(result).isEqualTo("딜러");
    }

    @DisplayName("딜러는 카드를 한 장 받을 수 있다.")
    @Test
    void drawOneCard() {
        Dealer dealer = new Dealer();
        Card card = new Card(CardRank.EIGHT, CardShape.DIAMOND);

        dealer.draw(card);

        assertThat(dealer.getCards()).isEqualTo(List.of(card));
    }
}
