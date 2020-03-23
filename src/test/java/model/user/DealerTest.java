package model.user;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Arrays;
import model.card.Card;
import model.card.Symbol;
import model.card.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    @DisplayName("Hit 인지 확인")
    void isNotHitBound_Test() {
        Dealer dealer = new Dealer(
            Arrays.asList(new Card(Symbol.JACK, Type.CLUB), new Card(Symbol.EIGHT, Type.DIAMOND)));
        assertThat(dealer.isHitBound()).isFalse();
    }

    @Test
    @DisplayName("Hit 인지 확인")
    void isHitBound_Test() {
        Dealer dealer = new Dealer(
            Arrays.asList(new Card(Symbol.JACK, Type.CLUB), new Card(Symbol.TWO, Type.DIAMOND)));
        assertThat(dealer.isHitBound()).isTrue();
    }
}