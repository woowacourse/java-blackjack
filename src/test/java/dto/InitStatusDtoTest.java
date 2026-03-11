package dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import domain.Dealer;
import domain.Player;
import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InitStatusDtoTest {

    List<Player> players;
    Dealer dealer;

    @BeforeEach
    void setUp() {
        players = new ArrayList<>();
        Player player1 = new Player("봉구스");
        player1.draw(new Card(Suit.CLUBS, Rank.ACE));
        player1.draw(new Card(Suit.DIAMONDS, Rank.KING));

        Player player2 = new Player("시오");
        player2.draw(new Card(Suit.HEARTS, Rank.FIVE));
        player2.draw(new Card(Suit.SPADES, Rank.JACK));
        players.add(player1);
        players.add(player2);

        dealer = new Dealer();
        dealer.draw(new Card(Suit.HEARTS, Rank.FOUR));
        dealer.draw(new Card(Suit.CLUBS, Rank.EIGHT));
    }

    @Test
    void initStatus() {
        // given, when
        InitStatusDto initStatusDto = InitStatusDto.of(dealer, players);

        // then
        assertEquals(List.of("봉구스", "시오"), initStatusDto.names());
        assertEquals(List.of("4하트"), initStatusDto.dealerHandDto().cards());
        List<String> firstCards = initStatusDto.playerHandDtos().getFirst().handDto().cards();
        assertEquals(List.of("A클로버", "K다이아몬드"), firstCards);
        List<String> secondCards = initStatusDto.playerHandDtos().get(1).handDto().cards();
        assertEquals(List.of("5하트", "J스페이드"), secondCards);
    }
}
