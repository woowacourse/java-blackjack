package dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.TestUtil.createPlayer;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import domain.participant.Player;
import java.util.List;
import org.junit.jupiter.api.Test;

class HandDtoTest {

    @Test
    void from() {
        // given
        Player player = createPlayer("시오", new Card(Suit.HEARTS, Rank.EIGHT), new Card(Suit.HEARTS, Rank.SEVEN));

        // when
        HandDto handDto = HandDto.from(player.getHand());

        // then
        assertEquals(List.of("8하트", "7하트"), handDto.cards());
    }
}
