package domain;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JudgeTest {
    @Test
    void 플레이어가_Burst이면_패배한다() {
        // given
        Dealer dealer = new Dealer();
        dealer.draw(new Card(Suit.HEARTS, Rank.NUM10));
        dealer.draw(new Card(Suit.HEARTS, Rank.NUM7));

        Player player = new Player("시오");
        player.draw(new Card(Suit.CLUBS, Rank.KING));
        player.draw(new Card(Suit.CLUBS, Rank.QUEEN));
        player.draw(new Card(Suit.CLUBS, Rank.JACK));

        // when
        Judge judge = Judge.from(dealer, List.of(player));

        // then
        assertEquals(WinningStatus.LOSE, judge.getPlayerResult(player));
    }

    @Test
    void 딜러가_Burst이면_플레이어가_승리한다() {
        // given
        Dealer dealer = new Dealer();
        dealer.draw(new Card(Suit.HEARTS, Rank.KING));
        dealer.draw(new Card(Suit.HEARTS, Rank.QUEEN));
        dealer.draw(new Card(Suit.HEARTS, Rank.JACK));

        Player player = new Player("시오");
        player.draw(new Card(Suit.CLUBS, Rank.NUM10));
        player.draw(new Card(Suit.CLUBS, Rank.NUM8));

        // when
        Judge judge = Judge.from(dealer, List.of(player));

        // then
        assertEquals(WinningStatus.WIN, judge.getPlayerResult(player));
    }

    @Test
    void 플레이어_점수가_더_크면_승리한다() {
        // given
        Dealer dealer = new Dealer();
        dealer.draw(new Card(Suit.HEARTS, Rank.NUM10));
        dealer.draw(new Card(Suit.HEARTS, Rank.NUM7));

        Player player = new Player("시오");
        player.draw(new Card(Suit.CLUBS, Rank.NUM10));
        player.draw(new Card(Suit.CLUBS, Rank.NUM8));

        // when
        Judge judge = Judge.from(dealer, List.of(player));

        // then
        assertEquals(WinningStatus.WIN, judge.getPlayerResult(player));
    }

    @Test
    void 플레이어와_딜러의_점수가_같으면_무승부다() {
        // given
        Dealer dealer = new Dealer();
        dealer.draw(new Card(Suit.HEARTS, Rank.NUM10));
        dealer.draw(new Card(Suit.HEARTS, Rank.NUM8));

        Player player = new Player("시오");
        player.draw(new Card(Suit.CLUBS, Rank.KING));
        player.draw(new Card(Suit.CLUBS, Rank.NUM8));

        // when
        Judge judge = Judge.from(dealer, List.of(player));

        // then
        assertEquals(WinningStatus.DRAW, judge.getPlayerResult(player));
    }

    @Test
    void 플레이어의_시작_손패의_합이_21이면_블랙잭이다() {
        // given
        Dealer dealer = new Dealer();
        dealer.draw(new Card(Suit.HEARTS, Rank.NUM10));
        dealer.draw(new Card(Suit.HEARTS, Rank.NUM8));

        Player player = new Player("시오");
        player.draw(new Card(Suit.CLUBS, Rank.ACE));
        player.draw(new Card(Suit.CLUBS, Rank.KING));

        // when
        Judge judge = Judge.from(dealer, List.of(player));

        // then
        assertEquals(WinningStatus.BLACKJACK_WIN, judge.getPlayerResult(player));
    }

    @Test
    void 딜러의_시작_손패의_합이_21이고_플레이어의_시작_손패의_합이_21이_아니면_패배한다() {
        // given
        Dealer dealer = new Dealer();
        dealer.draw(new Card(Suit.HEARTS, Rank.ACE));
        dealer.draw(new Card(Suit.HEARTS, Rank.JACK));

        Player player = new Player("시오");
        player.draw(new Card(Suit.CLUBS, Rank.ACE));
        player.draw(new Card(Suit.CLUBS, Rank.NUM2));

        // when
        Judge judge = Judge.from(dealer, List.of(player));

        // then
        assertEquals(WinningStatus.LOSE, judge.getPlayerResult(player));
    }

    @Test
    void 딜러의_시작_손패의_합이_21이고_플레이어의_시작_손패의_합이_21이면_무승부다() {
        // given
        Dealer dealer = new Dealer();
        dealer.draw(new Card(Suit.HEARTS, Rank.ACE));
        dealer.draw(new Card(Suit.HEARTS, Rank.JACK));

        Player player = new Player("시오");
        player.draw(new Card(Suit.CLUBS, Rank.ACE));
        player.draw(new Card(Suit.CLUBS, Rank.QUEEN));

        // when
        Judge judge = Judge.from(dealer, List.of(player));

        // then
        assertEquals(WinningStatus.DRAW, judge.getPlayerResult(player));
    }

    @Test
    void 딜러의_승수와_패수를_계산한다() {
        // given
        Dealer dealer = new Dealer();
        dealer.draw(new Card(Suit.HEARTS, Rank.NUM10));
        dealer.draw(new Card(Suit.HEARTS, Rank.NUM8));

        Player winPlayer = new Player("시오");
        winPlayer.draw(new Card(Suit.CLUBS, Rank.KING));
        winPlayer.draw(new Card(Suit.CLUBS, Rank.NUM9));

        Player drawPlayer = new Player("봉구스");
        drawPlayer.draw(new Card(Suit.SPADES, Rank.KING));
        drawPlayer.draw(new Card(Suit.SPADES, Rank.NUM8));

        Player losePlayer = new Player("비밥");
        losePlayer.draw(new Card(Suit.DIAMONDS, Rank.NUM10));
        losePlayer.draw(new Card(Suit.DIAMONDS, Rank.NUM7));

        // when
        Judge judge = Judge.from(dealer, List.of(winPlayer, drawPlayer, losePlayer));

        // then
        assertAll(
                () -> assertEquals(1, judge.judgeDealerWinCount()),
                () -> assertEquals(1, judge.judgeDealerLoseCount())
        );
    }
}