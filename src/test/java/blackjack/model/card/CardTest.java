package blackjack.model.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

    @DisplayName("같은 TrumpNumber를 가지고 있지않으면 false를 반환한다.")
    @Test
    void hasSameNumber_false() {
        Card card = new Card(TrumpNumber.ACE, TrumpSymbol.CLOVER);

        assertThat(card.hasSameNumber(TrumpNumber.JACK)).isFalse();
    }

    @DisplayName("같은 TrumpNumber를 가지고 있으면 true를 반환한다.")
    @Test
    void hasSameNumber_true() {
        Card card = new Card(TrumpNumber.JACK, TrumpSymbol.CLOVER);

        assertThat(card.hasSameNumber(TrumpNumber.JACK)).isTrue();
    }
}
