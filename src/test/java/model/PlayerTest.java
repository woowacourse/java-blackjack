package model;

import model.card.Card;
import model.user.Hand;
import model.user.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static model.card.Shape.DIAMOND;
import static model.card.Value.KING;
import static model.card.Value.SEVEN;
import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    @Test
    @DisplayName("플레이어의 카드 총 합이 21 이하인지 판단한다.")
    void canReceiveCardTest() {
        // given
        final Player player = new Player("ethan", Hand.create());
        player.receiveCard(new Card(DIAMOND, SEVEN));
        player.receiveCard(new Card(DIAMOND, KING));
        player.receiveCard(new Card(DIAMOND, KING));

        // when, then
        assertThat(player.canReceiveCard()).isFalse();
    }

}
