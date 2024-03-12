package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @DisplayName("딜러 생성")
    @Test
    public void create() {
        assertThatCode(() -> new Dealer(new Deck(List.of(CardFixture.diamond3()), Collections::shuffle)))
                .doesNotThrowAnyException();
    }

    @DisplayName("덱에서 카드를 한 장 반환한다")
    @Test
    public void draw() {
        Dealer dealer = new Dealer(new Deck(List.of(CardFixture.diamond3()), Collections::shuffle));

        assertThat(dealer.draw()).isEqualTo(new Card(CardSuit.DIAMOND, CardNumber.THREE));
    }
}
