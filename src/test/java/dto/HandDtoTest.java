package dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import domain.Player;
import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import java.util.List;
import org.junit.jupiter.api.Test;

class HandDtoTest {

    @Test
    void from() {
        // given
        Player player = new Player("시오");
        player.draw(new Card(Suit.HEARTS, Rank.EIGHT));
        player.draw(new Card(Suit.HEARTS, Rank.SEVEN));

        // when
        HandDto handDto = HandDto.from(player.getHand());

        // then
        assertEquals(List.of("8하트", "7하트"), handDto.cards());
    }
}
