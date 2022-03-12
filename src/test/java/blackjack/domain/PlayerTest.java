package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.participant.Player;

public class PlayerTest {

    @Test
    @DisplayName("버스트가 나지 않은 플레이어는 hit가 가능하다")
    void canHit() {
        Player player = new Player("player");

        player.initCards(List.of(new Card(Suit.DIAMOND, Denomination.TEN),
            new Card(Suit.HEART, Denomination.JACK)));

        assertThat(player.canHit()).isTrue();
    }

    @Test
    @DisplayName("버스트가 나면 플레이어는 hit 할 수 없다")
    void canNotHit() {
        Player player = new Player("player");

        player.initCards(List.of(new Card(Suit.DIAMOND, Denomination.TEN),
            new Card(Suit.HEART, Denomination.JACK)));

        player.addCard(new Card(Suit.DIAMOND, Denomination.FIVE));

        assertThat(player.canHit()).isFalse();
    }
}
