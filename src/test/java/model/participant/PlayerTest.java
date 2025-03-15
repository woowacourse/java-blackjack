package model.participant;

import static org.assertj.core.api.Assertions.assertThat;

import model.betting.Bet;
import model.deck.Card;
import model.deck.CardRank;
import model.deck.CardSuit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("pobi");
    }

    @DisplayName("플레이어가 카드 한장을 받는다.")
    @Test
    void 플레이어_카드한장을_받는다() {
        //given
        Card card = new Card(CardRank.EIGHT, CardSuit.HEART);

        //when
        player.receiveCard(card);

        //then
        assertThat(player.getHandCards()).contains(card);
    }
}
