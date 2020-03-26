package domain.result;

import domain.card.*;
import domain.user.Dealer;
import domain.user.Money;
import domain.user.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class DefaultMatchRuleTest {
    private DefaultMatchRule defaultMatchRule;
    private PlayingCards blackjack = PlayingCards.of(Arrays.asList(new Card(Symbol.QUEEN, Type.SPADE), new Card(Symbol.ACE, Type.SPADE)));
    private PlayingCards bust = PlayingCards.of(Arrays.asList(new Card(Symbol.QUEEN, Type.SPADE), new Card(Symbol.TEN, Type.SPADE), new Card(Symbol.JACK, Type.SPADE)));
    private PlayingCards _20 = PlayingCards.of(Arrays.asList(new Card(Symbol.QUEEN, Type.SPADE), new Card(Symbol.JACK, Type.SPADE)));
    private PlayingCards _19 = PlayingCards.of(Arrays.asList(new Card(Symbol.QUEEN, Type.SPADE), new Card(Symbol.NINE, Type.SPADE)));
    private PlayingCards _18 = PlayingCards.of(Arrays.asList(new Card(Symbol.QUEEN, Type.SPADE), new Card(Symbol.EIGHT, Type.SPADE)));


    @BeforeEach
    void setUp() {
        defaultMatchRule = new DefaultMatchRule();
    }

    @Test
    @DisplayName("#match() : should return Result.DEALER_WIN because Player bust")
    void matchDealerWinBecausePlayerBust() {
        Player player = Player.of("testPlayer", bust, Money.of(1000));
        Deck deck = createNormalDeckforTest(2);
        Dealer dealer = Dealer.shuffle(deck);
        Result result = defaultMatchRule.match(player, dealer);
        assertThat(result).isEqualTo(Result.DEALER_WIN);
    }

    @Test
    @DisplayName("#match() : should return Result.PLAYER_WIN_WITH_BLACkJACK")
    void matchPlayerWinWithBlackjackDealerBustPlayerBlackjack() {
        Player player = Player.of("testPlayer", blackjack, Money.of(1000));
        Deck deck = createNormalDeckforTest(3);
        Dealer dealer = Dealer.shuffle(deck);
        dealer.confirmCards(1);
        Result result = defaultMatchRule.match(player, dealer);
        assertThat(result).isEqualTo(Result.PLAYER_WIN_WITH_BLACKJACK);
    }

    @Test
    @DisplayName("#match() : should return Result.PLAYER_WIN_WITHOUT_BLACkJACK")
    void matchPlayerWinWithoutBlackjackDealerBustPlayerNormal() {
        Player player = Player.of("testPlayer", _20, Money.of(1000));
        Deck deck = createNormalDeckforTest(3);
        Dealer dealer = Dealer.shuffle(deck);
        dealer.confirmCards(1);
        Result result = defaultMatchRule.match(player, dealer);
        assertThat(result).isEqualTo(Result.PLAYER_WIN_WITHOUT_BLACKJACk);
    }

    @Test
    @DisplayName("#match() : should return Result.DRAW Dealer Blackjack Player Blackjack")
    void matchDrawBothBlackjack() {
        Player player = Player.of("testPlayer", blackjack, Money.of(1000));
        Deck deck = createBlackjackDeckForTest();
        Dealer dealer = Dealer.shuffle(deck);
        Result result = defaultMatchRule.match(player, dealer);
        assertThat(result).isEqualTo(Result.DRAW);
    }

    @Test
    @DisplayName("#match() : should return Result.PLAYER_WIN_WITH_BLACKACK")
    void matchPlayerWinWithBlackjack() {
        Player player = Player.of("testPlayer", blackjack, Money.of(1000));
        Deck deck = createNormalDeckforTest(2);
        Dealer dealer = Dealer.shuffle(deck);
        Result result = defaultMatchRule.match(player, dealer);
        assertThat(result).isEqualTo(Result.PLAYER_WIN_WITH_BLACKJACK);
    }

    @Test
    @DisplayName("#match() : should return Result.DEALER_WIN with score")
    void matchDealerWinWithScore() {
        Player player = Player.of("testPlayer", _18, Money.of(1000));
        Deck deck = createNormalDeckforTest(2);
        Dealer dealer = Dealer.shuffle(deck);
        Result result = defaultMatchRule.match(player, dealer);
        assertThat(result).isEqualTo(Result.DEALER_WIN);
    }

    @Test
    @DisplayName("#match() : should return Result.DRAW with score")
    void matchDrawWithScore() {
        Player player = Player.of("testPlayer", _19, Money.of(1000));
        Deck deck = createNormalDeckforTest(2);
        Dealer dealer = Dealer.shuffle(deck);
        Result result = defaultMatchRule.match(player, dealer);
        assertThat(result).isEqualTo(Result.DRAW);
    }

    @Test
    @DisplayName("#match() : should return Result.PLAYER_WIN_WITHOUT_BLACKJACK with score")
    void matcPlayerWinWithScore() {
        Player player = Player.of("testPlayer", _20, Money.of(1000));
        Deck deck = createNormalDeckforTest(2);
        Dealer dealer = Dealer.shuffle(deck);
        Result result = defaultMatchRule.match(player, dealer);
        assertThat(result).isEqualTo(Result.PLAYER_WIN_WITHOUT_BLACKJACk);
    }

    private Deck createBlackjackDeckForTest() {
        final Integer[] countOfPops = new Integer[2];

        class TestDeck implements Deck {

            @Override
            public Deck shuffle() {
                return this;
            }

            @Override
            public Card pop() {
                if (countOfPops[0] == null) {
                    countOfPops[0] = 1;
                    return new Card(Symbol.QUEEN, Type.SPADE);
                } else if(countOfPops[1] == null) {
                    return new Card(Symbol.ACE, Type.CLOVER);
                }
                return new Card(Symbol.KING, Type.SPADE);
            }
        }

        return new TestDeck();
    }

    private Deck createNormalDeckforTest(int countOfPop) {
        final Integer[] countOfPops = new Integer[countOfPop];

        class TestDeck implements Deck {

            @Override
            public Deck shuffle() {
                return this;
            }

            @Override
            public Card pop() {
                if (countOfPops[0] == null) {
                    countOfPops[0] = 1;
                    return new Card(Symbol.QUEEN, Type.SPADE);
                } else if(countOfPops[1] == null) {
                    return new Card(Symbol.NINE, Type.CLOVER);
                } else if(countOfPops[2] == null) {
                    new Card(Symbol.QUEEN, Type.HEART);
                } else if (countOfPops[3] == null) {
                    return new Card(Symbol.QUEEN, Type.DIAMOND);
                }
                return new Card(Symbol.KING, Type.SPADE);
            }
        }

        return new TestDeck();
    }
}