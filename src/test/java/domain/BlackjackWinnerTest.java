package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.fixture.BlackjackGameTestFixture;
import java.util.List;
import org.junit.jupiter.api.Test;

class BlackjackWinnerTest {

    @Test
    void 블랙잭_승패_결과를_계산한다() {
        // given
        List<TrumpCard> pobiCards = List.of(new TrumpCard(Suit.DIAMOND, CardValue.EIGHT),
                new TrumpCard(Suit.DIAMOND, CardValue.J));
        List<TrumpCard> rookieCards = List.of(new TrumpCard(Suit.HEART, CardValue.K),
                new TrumpCard(Suit.HEART, CardValue.NINE));
        List<TrumpCard> dealerCards = List.of(new TrumpCard(Suit.CLOVER, CardValue.EIGHT),
                new TrumpCard(Suit.CLOVER, CardValue.J));
        List<String> names = List.of("포비", "루키");
        BlackjackGame blackjackGame = BlackjackGameTestFixture.createTestGame(names, pobiCards, rookieCards,
                dealerCards);

        // when
        BlackjackResult blackjackDealerResult = blackjackGame.currentDealerBlackjackResult();
        List<BlackjackResult> blackjackPlayerResults = blackjackGame.currentPlayerBlackjackResult();
        BlackjackWinner blackjackWinner = new BlackjackWinner(blackjackDealerResult, blackjackPlayerResults);

        // then
        assertThat(blackjackWinner.getDealerWinStatus())
                .isEqualTo(new DealerWinStatus(0, 1));
        assertThat(blackjackWinner.getPlayerWinStatuses().get("포비"))
                .isEqualTo(WinStatus.DRAW);
        assertThat(blackjackWinner.getPlayerWinStatuses().get("루키"))
                .isEqualTo(WinStatus.WIN);
    }

    @Test
    void 플레이어의_결과로_승패횟수를_계산한다() {
        // given
        BlackjackResult playerResult1 = new BlackjackResult("포비", List.of(new TrumpCard(Suit.CLOVER, CardValue.TEN)),
                10);
        BlackjackResult playerResult2 = new BlackjackResult("루키", List.of(new TrumpCard(Suit.DIAMOND, CardValue.NINE)),
                9);
        BlackjackResult playerResult3 = new BlackjackResult("투다", List.of(new TrumpCard(Suit.HEART, CardValue.TWO)), 2);
        List<BlackjackResult> playerResults = List.of(playerResult1, playerResult2, playerResult3);

        BlackjackResult dealerResult = new BlackjackResult("딜러", List.of(new TrumpCard(Suit.DIAMOND, CardValue.FIVE)),
                5);

        // when
        BlackjackWinner winnerResult = new BlackjackWinner(dealerResult, playerResults);
        DealerWinStatus dealerWinStatus = winnerResult.getDealerWinStatus();

        // then
        DealerWinStatus expected = new DealerWinStatus(1, 2);
        assertThat(dealerWinStatus).isEqualTo(expected);
    }

    @Test
    void 딜러와_플레이어가_둘다_버스트면_무승부로_처리한다() {
        // given
        List<TrumpCard> pobiInitCards = List.of(new TrumpCard(Suit.DIAMOND, CardValue.EIGHT),
                new TrumpCard(Suit.DIAMOND, CardValue.J));
        List<TrumpCard> dealerInitCards = List.of(new TrumpCard(Suit.HEART, CardValue.SEVEN),
                new TrumpCard(Suit.HEART, CardValue.NINE));
        List<TrumpCard> pobiDrawCards = List.of(new TrumpCard(Suit.CLOVER, CardValue.SEVEN));
        List<TrumpCard> dealerDrawCards = List.of(new TrumpCard(Suit.CLOVER, CardValue.J));
        List<String> names = List.of("포비");
        BlackjackGame blackjackGame = BlackjackGameTestFixture.createTestGame(names, pobiInitCards, dealerInitCards,
                pobiDrawCards, dealerDrawCards);

        // when
        blackjackGame.drawCard("포비");
        blackjackGame.dealerHit();

        BlackjackResult blackjackDealerResult = blackjackGame.currentDealerBlackjackResult();
        List<BlackjackResult> blackjackPlayerResults = blackjackGame.currentPlayerBlackjackResult();
        BlackjackWinner blackjackWinner = new BlackjackWinner(blackjackDealerResult, blackjackPlayerResults);

        // then
        assertThat(blackjackWinner.getDealerWinStatus()).isEqualTo(new DealerWinStatus(0, 0));
    }

    @Test
    void 딜러와_플레이어가_둘다_버스트면_무승부로_처리한다2() {
        // given
        List<TrumpCard> pobiInitCards = List.of(new TrumpCard(Suit.DIAMOND, CardValue.EIGHT),
                new TrumpCard(Suit.DIAMOND, CardValue.J));
        List<TrumpCard> tudaInitCards = List.of(new TrumpCard(Suit.HEART, CardValue.A),
                new TrumpCard(Suit.HEART, CardValue.TWO));
        List<TrumpCard> dealerInitCards = List.of(new TrumpCard(Suit.HEART, CardValue.SIX),
                new TrumpCard(Suit.CLOVER, CardValue.J));
        List<TrumpCard> pobiDrawCards = List.of(new TrumpCard(Suit.CLOVER, CardValue.SEVEN));
        List<TrumpCard> tudaDrawCards = List.of(new TrumpCard(Suit.CLOVER, CardValue.THREE));
        List<TrumpCard> dealerDrawCards = List.of(new TrumpCard(Suit.SPADE, CardValue.J));
        List<String> names = List.of("포비", "투다");

        BlackjackGame blackjackGame = BlackjackGameTestFixture.createTestGame(names, pobiInitCards, tudaInitCards,
                dealerInitCards, pobiDrawCards, tudaDrawCards, dealerDrawCards);

        // when
        blackjackGame.drawCard("포비");
        blackjackGame.drawCard("투다");
        blackjackGame.dealerHit();

        BlackjackResult blackjackDealerResult = blackjackGame.currentDealerBlackjackResult();
        List<BlackjackResult> blackjackPlayerResults = blackjackGame.currentPlayerBlackjackResult();
        BlackjackWinner blackjackWinner = new BlackjackWinner(blackjackDealerResult, blackjackPlayerResults);

        //then
        assertThat(blackjackWinner.getDealerWinStatus()).isEqualTo(new DealerWinStatus(0, 1));
    }


}
