import static org.assertj.core.api.Assertions.assertThat;

import domain.BlackjackDeck;
import domain.BlackjackDeckGenerator;
import domain.BlackjackGame;
import domain.BlackjackResult;
import domain.BlackjackWinner;
import domain.CardValue;
import domain.Dealer;
import domain.DealerWinStatus;
import domain.Suit;
import domain.TrumpCard;
import domain.WinStatus;
import domain.strategy.TestDrawStrategy;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class BlackjackWinnerTest {

    @Test
    void 블랙잭_승패_계산() {
        Deque<TrumpCard> trumpCards = new LinkedList<>(
                List.of(new TrumpCard(Suit.DIAMOND, CardValue.EIGHT), new TrumpCard(Suit.DIAMOND, CardValue.J),
                        new TrumpCard(Suit.HEART, CardValue.K), new TrumpCard(Suit.HEART, CardValue.NINE),
                        new TrumpCard(Suit.CLOVER, CardValue.EIGHT), new TrumpCard(Suit.CLOVER, CardValue.J)));
        BlackjackDeck deck = BlackjackDeckGenerator.generateDeck(new TestDrawStrategy(trumpCards));

        Dealer dealer = new Dealer();
        List<String> names = List.of("포비", "루키");
        BlackjackGame blackjackGame = new BlackjackGame(names, deck, dealer);
        BlackjackResult blackjackDealerResult = blackjackGame.currentDealerBlackjackResult();
        List<BlackjackResult> blackjackPlayerResults = blackjackGame.currentPlayerBlackjackResult();
        BlackjackWinner blackjackWinner = new BlackjackWinner(blackjackDealerResult, blackjackPlayerResults);

        assertThat(blackjackWinner.getDealerWinStatus())
                .isEqualTo(new DealerWinStatus(0, 1));
        assertThat(blackjackWinner.getPlayerWinStatuses().get("포비"))
                .isEqualTo(WinStatus.DRAW);
        assertThat(blackjackWinner.getPlayerWinStatuses().get("루키"))
                .isEqualTo(WinStatus.WIN);
    }
}
