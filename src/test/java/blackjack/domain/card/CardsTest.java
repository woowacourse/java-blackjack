package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CardsTest {

    @DisplayName("Ace 점수 계산은 플레이어에게 유리하게 된다.")
    @Test
    void name() {
        List<Card> playerCards = new ArrayList<>();
        playerCards.add(new Card(Type.SPADE, Denomination.ACE));
        playerCards.add(new Card(Type.HEART, Denomination.ACE));
        playerCards.add(new Card(Type.DIAMOND, Denomination.ACE));

        Cards cards = new Cards(playerCards);

        assertThat(cards.calculateScore()).isEqualTo(13);
    }
}