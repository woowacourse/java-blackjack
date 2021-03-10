package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MatchResultTest {

    public User player;
    public User dealer;

    @BeforeEach
    void setUp() {
        this.player = new Player("A");
        this.dealer = new Dealer();
    }

    @DisplayName("MatchResult 반대 이름 가져오기 테스트")
    @Test
    void getReverseName() {
        assertAll(
            () -> assertEquals(MatchResult.WIN.getReverseName(), "패"),
            () -> assertEquals(MatchResult.LOSE.getReverseName(), "승"),
            () -> assertEquals(MatchResult.DRAW.getReverseName(), "무")
        );
    }

    @DisplayName("플레이어 결과 테스트 : 플레이어가 이긴 경우")
    @Test
    void calculateResult_Win() {
        player.initialHands(getPlayerCards(), 21);
        dealer.initialHands(getDealerLosingCards(), 16);
        assertEquals(MatchResult.WIN, MatchResult.calculateResult(player, dealer));
    }

    @DisplayName("플레이어 결과 테스트 : 플레이어가 진 경우")
    @Test
    void calculateResult_Lose() {
        player.initialHands(getPlayerCards(), 21);
        dealer.initialHands(getDealerBlackjackCards(), 16);
        assertEquals(MatchResult.LOSE, MatchResult.calculateResult(player, dealer));
    }

    @DisplayName("플레이어 결과 테스트 : 블랙잭으로 비긴 경우")
    @Test
    void calculateResult_DrawWithBlackjack() {
        player.initialHands(getPlayerBlackjackCards(), 21);
        dealer.initialHands(getDealerBlackjackCards(), 16);
        assertEquals(MatchResult.DRAW, MatchResult.calculateResult(player, dealer));
    }

    @DisplayName("플레이어 결과 테스트 : 동점으로 비긴 경우")
    @Test
    void calculateResult_DrawWithScore() {
        player.initialHands(getPlayerCards(), 21);
        dealer.initialHands(getDealerDrawCards(), 16);
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