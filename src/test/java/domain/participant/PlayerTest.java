package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    private Player player;

    @BeforeEach
    void init() {
        this.player = new Player(new Name("seongha"), new Hand(Collections.emptyList()));
    }

    @Test
    @DisplayName("가지고 있는 카드의 합이 21 이하면 true를 반환한다.")
    void isCardValueBelow21() {
        player.takeCard(new Card(Suit.DIAMOND, Denomination.TEN));
        player.takeCard(new Card(Suit.DIAMOND, Denomination.SIX));
        assertThat(player.canHit()).isTrue();
    }

    @Test
    @DisplayName("가지고 있는 카드의 합이 22 이상이면 false를 반환한다.")
    void isCardValueOver21() {
        player.takeCard(new Card(Suit.DIAMOND, Denomination.TEN));
        player.takeCard(new Card(Suit.DIAMOND, Denomination.SIX));
        player.takeCard(new Card(Suit.DIAMOND, Denomination.EIGHT));
        assertThat(player.canHit()).isFalse();
    }
}
