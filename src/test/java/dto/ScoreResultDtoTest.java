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

class ScoreResultDtoTest {
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
    void ScoreResultDto가_올바른_값을_가진다() {
        // given
        ScoreResultDto scoreResultDto = ScoreResultDto.of(dealer, players);

        // when, then
        assertEquals(List.of("4하트", "8클로버"), scoreResultDto.dealerHandDto().cards());
        assertEquals(12, scoreResultDto.dealerScore());

        PlayerHandScoreDto first = scoreResultDto.playerHandScoreDtos().getFirst();
        assertEquals("봉구스", first.name());
        assertEquals(List.of("A클로버", "K다이아몬드"), first.hand().cards());
        assertEquals(21, first.score());

        PlayerHandScoreDto second = scoreResultDto.playerHandScoreDtos().get(1);
        assertEquals("시오", second.name());
        assertEquals(List.of("5하트", "J스페이드"), second.hand().cards());
        assertEquals(15, second.score());
    }
}
