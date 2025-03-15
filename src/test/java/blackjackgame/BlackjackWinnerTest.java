package blackjackgame;

import static org.assertj.core.api.Assertions.assertThat;

import domain.blackjackgame.BlackjackDeck;
import domain.blackjackgame.BlackjackGame;
import domain.blackjackgame.BlackjackResult;
import domain.blackjackgame.BlackjackWinner;
import domain.blackjackgame.CardValue;
import domain.blackjackgame.DealerWinStatus;
import domain.blackjackgame.ParticipantGameResult;
import domain.blackjackgame.Suit;
import domain.blackjackgame.TrumpCard;
import domain.strategy.BlackjackDrawStrategy;
import domain.strategy.DeckGenerator;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.Test;
import strategy.TestDeckGenerateStrategy;

public class BlackjackWinnerTest {

    @Test
    void 블랙잭_승패_계산() {
        List<TrumpCard> trumpCards = new LinkedList<>(
                List.of(new TrumpCard(Suit.DIAMOND, CardValue.EIGHT), new TrumpCard(Suit.DIAMOND, CardValue.J),
                        new TrumpCard(Suit.HEART, CardValue.K), new TrumpCard(Suit.HEART, CardValue.NINE),
                        new TrumpCard(Suit.CLOVER, CardValue.EIGHT), new TrumpCard(Suit.CLOVER, CardValue.J)));
        BlackjackDeck deck = new DeckGenerator().generateDeck(new BlackjackDrawStrategy(),
                new TestDeckGenerateStrategy(trumpCards));

        List<String> names = List.of("포비", "루키");
        BlackjackGame blackjackGame = BlackjackGame.nonBettingBlackjackGame(deck, names);
        BlackjackResult blackjackDealerResult = blackjackGame.currentDealerBlackjackResult();
        List<BlackjackResult> blackjackPlayerResults = blackjackGame.currentPlayerBlackjackResult();
        BlackjackWinner blackjackWinner = new BlackjackWinner(blackjackDealerResult, blackjackPlayerResults);

        assertThat(blackjackWinner.getDealerWinStatus())
                .isEqualTo(new DealerWinStatus(0, 1));
        assertThat(blackjackWinner.getPlayerWinStatuses().get("포비"))
                .isEqualTo(ParticipantGameResult.DRAW);
        assertThat(blackjackWinner.getPlayerWinStatuses().get("루키"))
                .isEqualTo(ParticipantGameResult.WIN);
    }

    @Test
    void 딜러가_버스트이고_플레이어가_숫자가_작을때_플레이어가_이긴다() {
        List<TrumpCard> trumpCards = new LinkedList<>(
                List.of(new TrumpCard(Suit.DIAMOND, CardValue.EIGHT), new TrumpCard(Suit.DIAMOND, CardValue.J),
                        new TrumpCard(Suit.HEART, CardValue.SEVEN), new TrumpCard(Suit.HEART, CardValue.NINE),
                        new TrumpCard(Suit.CLOVER, CardValue.TWO), new TrumpCard(Suit.CLOVER, CardValue.J)));
        BlackjackDeck deck = new DeckGenerator().generateDeck(new BlackjackDrawStrategy(),
                new TestDeckGenerateStrategy(trumpCards));

        List<String> names = List.of("포비");
        BlackjackGame blackjackGame = BlackjackGame.nonBettingBlackjackGame(deck, names);
        blackjackGame.drawCard("포비");
        blackjackGame.dealerHit();
        BlackjackResult blackjackDealerResult = blackjackGame.currentDealerBlackjackResult();
        List<BlackjackResult> blackjackPlayerResults = blackjackGame.currentPlayerBlackjackResult();
        BlackjackWinner blackjackWinner = new BlackjackWinner(blackjackDealerResult, blackjackPlayerResults);

        assertThat(blackjackWinner.getDealerWinStatus())
                .isEqualTo(new DealerWinStatus(0, 1));
        assertThat(blackjackWinner.getPlayerWinStatuses().get("포비"))
                .isEqualTo(ParticipantGameResult.WIN);
    }

    @Test
    void 딜러와_플레이어가_둘다_버스트면_무승부로_처리한다() {
        List<TrumpCard> trumpCards = new LinkedList<>(
                List.of(new TrumpCard(Suit.DIAMOND, CardValue.EIGHT), new TrumpCard(Suit.DIAMOND, CardValue.J),
                        new TrumpCard(Suit.HEART, CardValue.SEVEN), new TrumpCard(Suit.HEART, CardValue.NINE),
                        new TrumpCard(Suit.CLOVER, CardValue.SEVEN), new TrumpCard(Suit.CLOVER, CardValue.J)));
        BlackjackDeck deck = new DeckGenerator().generateDeck(new BlackjackDrawStrategy(),
                new TestDeckGenerateStrategy(trumpCards));

        List<String> names = List.of("포비");
        BlackjackGame blackjackGame = BlackjackGame.nonBettingBlackjackGame(deck, names);
        blackjackGame.drawCard("포비");

        blackjackGame.dealerHit();
        BlackjackResult blackjackDealerResult = blackjackGame.currentDealerBlackjackResult();
        List<BlackjackResult> blackjackPlayerResults = blackjackGame.currentPlayerBlackjackResult();
        BlackjackWinner blackjackWinner = new BlackjackWinner(blackjackDealerResult, blackjackPlayerResults);

        assertThat(blackjackWinner.getDealerWinStatus()).isEqualTo(new DealerWinStatus(0, 0));
    }

    @Test
    void 딜러와_플레이어가_둘다_버스트면_무승부로_처리한다2() {
        List<TrumpCard> trumpCards = new LinkedList<>(
                List.of(new TrumpCard(Suit.DIAMOND, CardValue.EIGHT), new TrumpCard(Suit.DIAMOND, CardValue.J),
                        new TrumpCard(Suit.HEART, CardValue.A), new TrumpCard(Suit.HEART, CardValue.TWO),
                        new TrumpCard(Suit.HEART, CardValue.SIX), new TrumpCard(Suit.CLOVER, CardValue.J),
                        new TrumpCard(Suit.CLOVER, CardValue.SEVEN), new TrumpCard(Suit.CLOVER, CardValue.THREE),
                        new TrumpCard(Suit.SPADE, CardValue.J)));
        BlackjackDeck deck = new DeckGenerator().generateDeck(new BlackjackDrawStrategy(),
                new TestDeckGenerateStrategy(trumpCards));

        List<String> names = List.of("포비", "투다");
        BlackjackGame blackjackGame = BlackjackGame.nonBettingBlackjackGame(deck, names);
        blackjackGame.drawCard("포비");
        blackjackGame.drawCard("투다");

        blackjackGame.dealerHit();
        BlackjackResult blackjackDealerResult = blackjackGame.currentDealerBlackjackResult();
        List<BlackjackResult> blackjackPlayerResults = blackjackGame.currentPlayerBlackjackResult();
        BlackjackWinner blackjackWinner = new BlackjackWinner(blackjackDealerResult, blackjackPlayerResults);

        assertThat(blackjackWinner.getDealerWinStatus()).isEqualTo(new DealerWinStatus(0, 1));
    }


}
