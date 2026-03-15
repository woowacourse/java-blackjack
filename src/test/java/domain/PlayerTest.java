package domain;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private Player createPlayerWithCards(String name, Card... cards) {
        Player player = new Player(name);
        for (Card card : cards) {
            player.draw(card);
        }
        return player;
    }

    @Test
    void 플레이어가_카드를_뽑으면_손패의_크기가_1_증가한다() {
        // given
        Player player = new Player("봉구스");

        // when
        player.draw(new Card(Suit.CLUBS, Rank.ACE));

        // then
        assertEquals(1, player.getHandSize());
    }

    @Test
    void 플레이어의_카드_점수_합이_21을_초과하면_버스트_상태가_된다() {
        // given
        Player player = new Player("봉구스");

        // when
        player.draw(new Card(Suit.CLUBS, Rank.KING));
        player.draw(new Card(Suit.CLUBS, Rank.QUEEN));
        player.draw(new Card(Suit.CLUBS, Rank.JACK));

        // then
        assertTrue(player.isBurst());
    }

    @Test
    void 플레이어만_블랙잭이면_블랙잭_승리다() {
        Player player = createPlayerWithCards("시오",
                new Card(Suit.CLUBS, Rank.ACE),
                new Card(Suit.DIAMONDS, Rank.KING)
        );

        WinningStatus result = player.calculateResult(new Score(20, false));

        assertEquals(WinningStatus.BLACKJACK_WIN, result);
    }

    @Test
    void 딜러만_블랙잭이면_플레이어는_패배다() {
        Player player = createPlayerWithCards("시오",
                new Card(Suit.CLUBS, Rank.NUM10),
                new Card(Suit.DIAMONDS, Rank.NUM9)
        );

        WinningStatus result = player.calculateResult(new Score(21, true));

        assertEquals(WinningStatus.LOSE, result);
    }

    @Test
    void 플레이어가_버스트면_패배다() {
        Player player = createPlayerWithCards("시오",
                new Card(Suit.CLUBS, Rank.KING),
                new Card(Suit.DIAMONDS, Rank.QUEEN),
                new Card(Suit.HEARTS, Rank.JACK)
        );

        WinningStatus result = player.calculateResult(new Score(20, false));

        assertEquals(WinningStatus.LOSE, result);
    }

    @Test
    void 딜러가_버스트면_플레이어가_승리다() {
        Player player = createPlayerWithCards("시오",
                new Card(Suit.CLUBS, Rank.NUM10),
                new Card(Suit.DIAMONDS, Rank.NUM8)
        );

        WinningStatus result = player.calculateResult(new Score(22, false));

        assertEquals(WinningStatus.WIN, result);
    }

    @Test
    void 점수비교시_플레이어가_더_크면_승리다() {
        Player player = createPlayerWithCards("시오",
                new Card(Suit.CLUBS, Rank.NUM10),
                new Card(Suit.DIAMONDS, Rank.NUM9)
        );

        WinningStatus result = player.calculateResult(new Score(18, false));

        assertEquals(WinningStatus.WIN, result);
    }

    @Test
    void 점수비교시_같으면_무승부다() {
        Player player = createPlayerWithCards("시오",
                new Card(Suit.CLUBS, Rank.NUM10),
                new Card(Suit.DIAMONDS, Rank.NUM8)
        );

        WinningStatus result = player.calculateResult(new Score(18, false));

        assertEquals(WinningStatus.DRAW, result);
    }

    @Test
    void 점수비교시_플레이어가_더_작으면_패배다() {
        Player player = createPlayerWithCards("시오",
                new Card(Suit.CLUBS, Rank.NUM10),
                new Card(Suit.DIAMONDS, Rank.NUM7)
        );

        WinningStatus result = player.calculateResult(new Score(18, false));

        assertEquals(WinningStatus.LOSE, result);
    }
}