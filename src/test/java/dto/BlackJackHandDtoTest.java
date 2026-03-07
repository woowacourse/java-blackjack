package dto;

import domain.Player;
import domain.WinningStatus;
import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import org.junit.jupiter.api.Test;

import java.util.SortedMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class BlackJackHandDtoTest {

    @Test
    void from() {
        // given
        Player player = new Player("시오");
        player.draw(new Card(Suit.HEARTS, Rank.NUM8));
        player.draw(new Card(Suit.HEARTS, Rank.NUM7));

        // when
        BlackJackHandDto blackJackHandDto = BlackJackHandDto.from(player);

        // then
        assertEquals("시오카드: 8하트, 7하트", blackJackHandDto.handOutput());
    }
}