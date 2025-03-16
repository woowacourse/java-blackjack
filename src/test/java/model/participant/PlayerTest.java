package model.participant;

import static org.assertj.core.api.Assertions.assertThat;

import model.card.Card;
import model.card.CardRank;
import model.card.CardSuit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {
    @DisplayName("플레이어가 카드 한장을 받는다.")
    @Test
    void 플레이어_카드한장을_받는다() {
        //given
        Player player = new Player("pobi");
        Card card = new Card(CardRank.EIGHT, CardSuit.HEART);

        //when
        player.receiveCard(card);

        //then
        assertThat(player.getHandCards()).contains(card);
    }
}