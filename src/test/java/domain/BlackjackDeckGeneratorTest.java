package domain;

import domain.BlackjackDeck;
import domain.BlackjackDeckGenerator;
import domain.BlackjackGame;
import domain.BlackjackResult;
import domain.CardValue;
import domain.Dealer;
import domain.Suit;
import domain.TrumpCard;
import domain.strategy.TestDrawStrategy;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class BlackjackDeckGeneratorTest {

    @Test
    void 블랙잭_승리_() {
        Deque<TrumpCard> trumpCards = new LinkedList<>(
                List.of(new TrumpCard(Suit.DIAMOND, CardValue.EIGHT), new TrumpCard(Suit.DIAMOND, CardValue.J),
                        new TrumpCard(Suit.HEART, CardValue.K), new TrumpCard(Suit.HEART, CardValue.NINE),
                        new TrumpCard(Suit.CLOVER, CardValue.EIGHT), new TrumpCard(Suit.CLOVER, CardValue.J)));

        BlackjackDeck deck = BlackjackDeckGenerator.generateDeck(new TestDrawStrategy(trumpCards));

        Dealer dealer = new Dealer();
        List<String> names = List.of("포비", "루키");
        BlackjackGame blackjackGame = new BlackjackGame(names, deck, dealer);
        BlackjackResult dealerBlackjackResult = blackjackGame.currentDealerBlackjackResult();
        List<BlackjackResult> playerBlackjackResult = blackjackGame.currentPlayerBlackjackResult();

    }
}
