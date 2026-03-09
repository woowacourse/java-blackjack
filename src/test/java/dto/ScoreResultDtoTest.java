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
        player2.draw(new Card(Suit.HEARTS, Rank.NUM5));
        player2.draw(new Card(Suit.SPADES, Rank.JACK));
        players.add(player1);
        players.add(player2);

        dealer = new Dealer();
        dealer.draw(new Card(Suit.HEARTS, Rank.NUM4));
        dealer.draw(new Card(Suit.CLUBS, Rank.NUM8));
    }

    @Test
    void 점수_결과_DTO_생성() {
        // given
        ScoreResultDto scoreResultDto = new ScoreResultDto(dealer, players);

        // when, then
        assertEquals("딜러카드: 4하트, 8클로버 - 결과: 12", scoreResultDto.scoreResults().get(0));
        assertEquals("봉구스카드: A클로버, K다이아몬드 - 결과: 21", scoreResultDto.scoreResults().get(1));
        assertEquals("시오카드: 5하트, J스페이드 - 결과: 15", scoreResultDto.scoreResults().get(2));
    }
}
