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
    @DisplayName("이름으로 null을 받았을 경우 오류")
    void createPlayerNullNameFail() {
        assertThatThrownBy(() -> {
            new Player(null);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("이름으로 Empty 값을 받았을 경우 오류")
    void createPlayerEmptyNameFail() {
        assertThatThrownBy(() -> {
            new Player("");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("플레이어가 초기 카드 2장을 받는다")
    void receiveInitCard() {
        Player player = new Player("president");

        player.initCards(List.of(new Card(Suit.DIAMOND, Denomination.ACE),
            new Card(Suit.HEART, Denomination.THREE)));

        assertThat(player.getCards().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("플레이어가 카드를 받는다")
    void receiveCard() {
        Player player = new Player("president");

        player.initCards(List.of(new Card(Suit.DIAMOND, Denomination.ACE),
            new Card(Suit.HEART, Denomination.THREE)));

        player.addCard(new Card(Suit.DIAMOND, Denomination.TWO));

        assertThat(player.getCards().size()).isEqualTo(3);
    }
}
