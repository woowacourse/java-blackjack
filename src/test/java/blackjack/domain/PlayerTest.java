package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.component.CardFigure;
import blackjack.domain.card.component.CardNumber;
import blackjack.domain.user.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {
    @DisplayName("플레이어의 카드 합이 21이하일 때 확인")
    @Test
    void canReceiveMoreCard_SUM_UNDER_21() {
        Player player = new Player("pobi");
        player.addCard(new Card(CardNumber.ACE, CardFigure.CLOVER));
        player.addCard(new Card(CardNumber.JACK, CardFigure.HEART));

        boolean expected = true;
        assertThat(player.receivable()).isEqualTo(expected);
    }

    @DisplayName("플레이어의 카드 합이 21초과일 때 확인")
    @Test
    void canReceiveMoreCard_SUM_OVER_21() {
        Player player = new Player("pobi");
        player.addCard(new Card(CardNumber.JACK, CardFigure.CLOVER));
        player.addCard(new Card(CardNumber.KING, CardFigure.HEART));
        player.addCard(new Card(CardNumber.FIVE, CardFigure.HEART));

        boolean expected = false;
        assertThat(player.receivable()).isEqualTo(expected);
    }
}
