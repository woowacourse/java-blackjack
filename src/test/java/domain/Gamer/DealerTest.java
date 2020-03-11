package domain.Gamer;

import domain.Card.Card;
import domain.Card.PlayingCards;
import domain.Card.Symbol;
import domain.Card.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {

    @Test
    @DisplayName("첫턴에 딜러는 2장의 카드를 받는다.")
    void construct() {
        PlayingCards playingCards = new PlayingCards(Arrays.asList(new Card(Symbol.ACE, Type.DIAMOND),
                new Card(Symbol.TEN, Type.HEART)));
        assertThat(new Dealer(playingCards)).isNotNull();
    }
}
