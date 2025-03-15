package blackjackgame;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIterable;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.blackjackgame.BlackjackDeck;
import domain.blackjackgame.BlackjackGame;
import domain.blackjackgame.BlackjackResult;
import domain.blackjackgame.CardValue;
import domain.blackjackgame.Suit;
import domain.blackjackgame.TrumpCard;
import domain.strategy.BlackjackDeckGenerateStrategy;
import domain.strategy.BlackjackDrawStrategy;
import domain.strategy.DeckGenerator;
import exception.BlackJackException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import strategy.TestDeckGenerateStrategy;


@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class BlackjackGameTest {

    @Test
    void 플레이어_수는_딜러_포함_2명_이상이여야_한다() {
        List<String> names = List.of("");
        BlackjackDeck blackjackDeck = new DeckGenerator().generateDeck(new BlackjackDrawStrategy(),
                new BlackjackDeckGenerateStrategy());

        assertThatThrownBy(() -> BlackjackGame.nonBettingBlackjackGame(blackjackDeck, names))
                .isInstanceOf(BlackJackException.class);
    }

    @Test
    void 플레이어_수는_8명_초과하면_예외가_발생한다() {
        List<String> names = List.of("포비", "포비2", "포비3", "포비4", "포비5", "포비6", "포비7", "포비8", "포비9");
        BlackjackDeck deck = new DeckGenerator().generateDeck(new BlackjackDrawStrategy(),
                new BlackjackDeckGenerateStrategy());
        assertThatThrownBy(() -> BlackjackGame.nonBettingBlackjackGame(deck, names))
                .isInstanceOf(BlackJackException.class);
    }

    @Test
    void 게임을_시작하면_플레이어는_두_장의_카드를_지급_받는다() {
        List<TrumpCard> trumpCards = new LinkedList<>(
                List.of(new TrumpCard(Suit.DIAMOND, CardValue.EIGHT), new TrumpCard(Suit.DIAMOND, CardValue.J),
                        new TrumpCard(Suit.DIAMOND, CardValue.K), new TrumpCard(Suit.SPADE, CardValue.EIGHT),
                        new TrumpCard(Suit.HEART, CardValue.EIGHT), new TrumpCard(Suit.HEART, CardValue.J),
                        new TrumpCard(Suit.HEART, CardValue.K), new TrumpCard(Suit.HEART, CardValue.EIGHT)));
        BlackjackDeck deck = new DeckGenerator().generateDeck(new BlackjackDrawStrategy(),
                new TestDeckGenerateStrategy(trumpCards));

        List<String> names = List.of("포비", "포비2");
        BlackjackGame blackjackGame = BlackjackGame.nonBettingBlackjackGame(deck, names);
        List<TrumpCard> expectedCards = List.of(new TrumpCard(Suit.DIAMOND, CardValue.EIGHT),
                new TrumpCard(Suit.DIAMOND, CardValue.J));
        assertThatIterable(blackjackGame.playerCards("포비")).containsExactlyInAnyOrderElementsOf(expectedCards);
    }

    @Test
    void 게임을_시작하면_딜러는_두_장을_받고_한장을_오픈한다() {
        List<TrumpCard> trumpCards = new LinkedList<>(
                List.of(new TrumpCard(Suit.DIAMOND, CardValue.EIGHT), new TrumpCard(Suit.DIAMOND, CardValue.J),
                        new TrumpCard(Suit.HEART, CardValue.K), new TrumpCard(Suit.HEART, CardValue.EIGHT)));
        BlackjackDeck deck = new DeckGenerator().generateDeck(new BlackjackDrawStrategy(),
                new TestDeckGenerateStrategy(trumpCards));
        List<String> names = List.of("포비");
        BlackjackGame blackjackGame = BlackjackGame.nonBettingBlackjackGame(deck, names);
        TrumpCard expectedCards = new TrumpCard(Suit.HEART, CardValue.K);
        assertThat(blackjackGame.dealerCardFirst()).isEqualTo(expectedCards);
    }

    @Test
    void 플레이어_블랙잭_결과() {
        List<TrumpCard> trumpCards = new LinkedList<>(
                List.of(new TrumpCard(Suit.DIAMOND, CardValue.EIGHT), new TrumpCard(Suit.DIAMOND, CardValue.J),
                        new TrumpCard(Suit.HEART, CardValue.K), new TrumpCard(Suit.HEART, CardValue.NINE),
                        new TrumpCard(Suit.CLOVER, CardValue.EIGHT), new TrumpCard(Suit.CLOVER, CardValue.J)));
        BlackjackDeck deck = new DeckGenerator().generateDeck(new BlackjackDrawStrategy(),
                new TestDeckGenerateStrategy(trumpCards));

        List<String> names = List.of("포비", "루키");
        BlackjackGame blackjackGame = BlackjackGame.nonBettingBlackjackGame(deck, names);
        List<Integer> expectedPlayersCardSum = List.of(18, 19);
        assertThatIterable(blackjackGame.currentPlayerBlackjackResult()
                .stream()
                .map(BlackjackResult::cardSum)
                .collect(Collectors.toList())
        ).containsExactlyInAnyOrderElementsOf(expectedPlayersCardSum);
    }

    @Test
    void 딜러_블랙잭_현재_현황_확인() {
        List<TrumpCard> trumpCards = new LinkedList<>(
                List.of(new TrumpCard(Suit.DIAMOND, CardValue.EIGHT), new TrumpCard(Suit.DIAMOND, CardValue.J),
                        new TrumpCard(Suit.CLOVER, CardValue.TWO), new TrumpCard(Suit.CLOVER, CardValue.THREE)));
        BlackjackDeck deck = new DeckGenerator().generateDeck(new BlackjackDrawStrategy(),
                new TestDeckGenerateStrategy(trumpCards));

        List<String> names = List.of("포비");
        BlackjackGame blackjackGame = BlackjackGame.nonBettingBlackjackGame(deck, names);
        int expectedDealerCardSum = 5;
        BlackjackResult blackjackResult = blackjackGame.currentDealerBlackjackResult();
        assertThat(blackjackResult.cardSum())
                .isEqualTo(expectedDealerCardSum);
    }
}
