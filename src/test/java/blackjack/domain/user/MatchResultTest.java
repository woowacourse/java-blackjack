package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MatchResultTest {

    private static final double money = 10000;

    @DisplayName("플레이어 결과 테스트 : 플레이어가 블랙잭 이외로 이긴 경우")
    @Test
    void calculateResult_WinNormal() {
        Player player = new Player("A", money, getPlayerCards());
        Dealer dealer = new Dealer(getDealerLosingCards());
        assertEquals(MatchResult.WIN_NORMAL, MatchResult.calculateResult(player, dealer));
    }

    @DisplayName("플레이어 결과 테스트 : 플레이어가 블랙잭으로 이긴 경우")
    @Test
    void calculateResult_WinBlackjack() {
        Player player = new Player("A", money, getPlayerBlackjackCards());
        Dealer dealer = new Dealer(getDealerLosingCards());
        assertEquals(MatchResult.WIN_BLACKJACK, MatchResult.calculateResult(player, dealer));
    }

    @DisplayName("플레이어 결과 테스트 : 플레이어가 진 경우")
    @Test
    void calculateResult_Lose() {
        Player player = new Player("A", money, getPlayerCards());
        Dealer dealer = new Dealer(getDealerBlackjackCards());
        assertEquals(MatchResult.LOSE, MatchResult.calculateResult(player, dealer));
    }

    @DisplayName("플레이어 결과 테스트 : 블랙잭으로 비긴 경우")
    @Test
    void calculateResult_DrawWithBlackjack() {
        Player player = new Player("A", money, getPlayerBlackjackCards());
        Dealer dealer = new Dealer(getDealerBlackjackCards());
        assertEquals(MatchResult.DRAW, MatchResult.calculateResult(player, dealer));
    }

    @DisplayName("플레이어 결과 테스트 : 동점으로 비긴 경우")
    @Test
    void calculateResult_DrawWithScore() {
        Player player = new Player("A", money, getPlayerCards());
        Dealer dealer = new Dealer(getDealerDrawCards());
        assertEquals(MatchResult.DRAW, MatchResult.calculateResult(player, dealer));
    }

    private List<Card> getPlayerCards() {
        return Stream.<Card>builder()
            .add(new Card(Denomination.EIGHT, Suit.SPADES)).add(new Card(Denomination.JACK, Suit.SPADES))
            .build()
            .collect(Collectors.toList());
    }

    private List<Card> getDealerLosingCards() {
        return Stream.<Card>builder()
            .add(new Card(Denomination.EIGHT, Suit.HEARTS)).add(new Card(Denomination.NINE, Suit.HEARTS))
            .build()
            .collect(Collectors.toList());
    }

    private List<Card> getDealerBlackjackCards() {
        return Stream.<Card>builder()
            .add(new Card(Denomination.ACE, Suit.HEARTS)).add(new Card(Denomination.QUEEN, Suit.HEARTS))
            .build()
            .collect(Collectors.toList());
    }

    private List<Card> getPlayerBlackjackCards() {
        return Stream.<Card>builder()
            .add(new Card(Denomination.ACE, Suit.SPADES)).add(new Card(Denomination.QUEEN, Suit.SPADES))
            .build()
            .collect(Collectors.toList());
    }


    private List<Card> getDealerDrawCards() {
        return Stream.<Card>builder()
            .add(new Card(Denomination.EIGHT, Suit.HEARTS)).add(new Card(Denomination.TEN, Suit.HEARTS))
            .build()
            .collect(Collectors.toList());
    }
}