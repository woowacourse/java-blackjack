package dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.TestUtil.createDealer;
import static util.TestUtil.createPlayer;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import domain.participant.Dealer;
import domain.participant.Player;
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
        Player player1 = createPlayer("봉구스", new Card(Suit.CLUBS, Rank.ACE), new Card(Suit.DIAMONDS, Rank.KING));
        Player player2 = createPlayer("시오", new Card(Suit.HEARTS, Rank.FIVE), new Card(Suit.SPADES, Rank.JACK));

        players = List.of(player1, player2);
        dealer = createDealer(new Card(Suit.HEARTS, Rank.FOUR), new Card(Suit.CLUBS, Rank.EIGHT));
    }

    @Test
    void ScoreResultDto가_올바른_값을_가진다() {
        // given
        ScoreResultDto scoreResultDto = ScoreResultDto.of(dealer, players);

        // when, then
        assertEquals(List.of("4하트", "8클로버"), scoreResultDto.dealerHandDto().cards());
        assertEquals(12, scoreResultDto.dealerScore());

        PlayerHandScoreDto first = scoreResultDto.playerHandScoreDtos().getFirst();
        assertEquals("봉구스", first.getName());
        assertEquals(List.of("A클로버", "K다이아몬드"), first.getHandDto().cards());
        assertEquals(21, first.score());

        PlayerHandScoreDto second = scoreResultDto.playerHandScoreDtos().get(1);
        assertEquals("시오", second.getName());
        assertEquals(List.of("5하트", "J스페이드"), second.getHandDto().cards());
        assertEquals(15, second.score());
    }
}
