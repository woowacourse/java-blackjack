package dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import domain.Player;
import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import org.junit.jupiter.api.Test;

class BlackJackHandDtoTest {

    @Test
    void from() {
        // given
        Player player = new Player("시오");
        player.draw(new Card(Suit.HEARTS, Rank.EIGHT));
        player.draw(new Card(Suit.HEARTS, Rank.SEVEN));

        // when
        BlackJackHandDto blackJackHandDto = BlackJackHandDto.from(player);

        // then
        assertEquals("시오카드: 8하트, 7하트", blackJackHandDto.handOutput());
    }
}
