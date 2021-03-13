package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MatchResultTest {

    private static final double money = 10000;

    @DisplayName("플레이어 결과 테스트 : 플레이어가 블랙잭 이외로 이긴 경우")
    @Test
    void calculateResult_WinNormal() {
        Player player = mockNormalPlayer();
        Dealer dealer = mockLosingDealer();
        assertEquals(MatchResult.WIN_NORMAL, MatchResult.calculateResult(player, dealer));
    }

    @DisplayName("플레이어 결과 테스트 : 플레이어가 블랙잭으로 이긴 경우")
    @Test
    void calculateResult_WinBlackjack() {
        Player player = mockBlackjackPlayer();
        Dealer dealer = mockLosingDealer();
        assertEquals(MatchResult.WIN_BLACKJACK, MatchResult.calculateResult(player, dealer));
    }

    @DisplayName("플레이어 결과 테스트 : 플레이어가 진 경우")
    @Test
    void calculateResult_Lose() {
        Player player = mockNormalPlayer();
        Dealer dealer = mockBlackjackDealer();
        assertEquals(MatchResult.LOSE, MatchResult.calculateResult(player, dealer));
    }

    @DisplayName("플레이어 결과 테스트 : 블랙잭으로 비긴 경우")
    @Test
    void calculateResult_DrawWithBlackjack() {
        Player player = mockBlackjackPlayer();
        Dealer dealer = mockBlackjackDealer();
        assertEquals(MatchResult.DRAW, MatchResult.calculateResult(player, dealer));
    }

    @DisplayName("플레이어 결과 테스트 : 동점으로 비긴 경우")
    @Test
    void calculateResult_DrawWithScore() {
        Player player = mockNormalPlayer();
        Dealer dealer = mockNormalDealer();
        assertEquals(MatchResult.DRAW, MatchResult.calculateResult(player, dealer));
    }

    private Player mockNormalPlayer() {
        return new Player("A", money, getCards());
    }

    private Player mockBlackjackPlayer() {
        return new Player("A", money, getBlackjackCards());
    }

    private Dealer mockLosingDealer() {
        return new Dealer(getLosingCards());
    }

    private Dealer mockNormalDealer() {
        return new Dealer(getCards());
    }

    private Dealer mockBlackjackDealer() {
        return new Dealer(getBlackjackCards());
    }

    private List<Card> getCards() {
        return Arrays.asList(
            new Card(Denomination.EIGHT, Suit.SPADES),
            new Card(Denomination.JACK, Suit.SPADES)
        );
    }

    private List<Card> getLosingCards() {
        return Arrays.asList(
            new Card(Denomination.EIGHT, Suit.HEARTS),
            new Card(Denomination.NINE, Suit.HEARTS)
        );
    }

    private List<Card> getBlackjackCards() {
        return Arrays.asList(
            new Card(Denomination.ACE, Suit.HEARTS),
            new Card(Denomination.QUEEN, Suit.HEARTS)
        );
    }
}