package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Deck;
import domain.card.Denomination;
import domain.card.Symbol;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ResultTest {

    private Player player;
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        player = Player.of(Name.from("kun"), 3000);
        dealer = new Dealer();
    }

    @Test
    @DisplayName("플레이어 점수가 딜러 점수보다 높을 때 WIN을 반환한다.")
    void judgeResultTest_WIN() {
        Deck deck1 = Deck.initDeck(Arrays.asList(Card.valueOf(Symbol.DIAMOND, Denomination.TEN),
            Card.valueOf(Symbol.CLOVER, Denomination.SEVEN)));
        Deck deck2 = Deck.initDeck(Arrays.asList(Card.valueOf(Symbol.CLOVER, Denomination.SEVEN),
            Card.valueOf(Symbol.HEART, Denomination.FOUR)));
        player.receiveInitialTwoCards(deck1);
        dealer.receiveInitialTwoCards(deck2);
        assertThat(Result.judgeResult(player, dealer)).isEqualTo(Result.WIN);
    }

    @Test
    @DisplayName("플레이어 점수가 딜러 점수보다 낮을 때 LOSE를 반환한다.")
    void judgeResultTest_LOSE() {
        Deck deck1 = Deck.initDeck(Arrays.asList(Card.valueOf(Symbol.DIAMOND, Denomination.THREE),
            Card.valueOf(Symbol.CLOVER, Denomination.SEVEN)));
        Deck deck2 = Deck.initDeck(Arrays.asList(Card.valueOf(Symbol.CLOVER, Denomination.SEVEN),
            Card.valueOf(Symbol.HEART, Denomination.FOUR)));
        player.receiveInitialTwoCards(deck1);
        dealer.receiveInitialTwoCards(deck2);
        assertThat(Result.judgeResult(player, dealer)).isEqualTo(Result.LOSE);
    }

    @Test
    @DisplayName("플레이어와 딜러 점수가 같을 때 PUSH를 반환한다.")
    void judgeResultTest_PUSH() {
        Deck deck1 = Deck.initDeck(Arrays.asList(Card.valueOf(Symbol.DIAMOND, Denomination.THREE),
            Card.valueOf(Symbol.CLOVER, Denomination.SEVEN)));
        Deck deck2 = Deck.initDeck(Arrays.asList(Card.valueOf(Symbol.CLOVER, Denomination.SEVEN),
            Card.valueOf(Symbol.HEART, Denomination.THREE)));
        player.receiveInitialTwoCards(deck1);
        dealer.receiveInitialTwoCards(deck2);
        assertThat(Result.judgeResult(player, dealer)).isEqualTo(Result.PUSH);
    }

    @Test
    @DisplayName("플레이어가 Bust일 경우에 LOSE를 반환한다.")
    void judgeResultTest_playerIsBust() {
        Deck deck1 = Deck.initDeck(Arrays.asList(Card.valueOf(Symbol.DIAMOND, Denomination.JACK),
            Card.valueOf(Symbol.CLOVER, Denomination.SEVEN),
            Card.valueOf(Symbol.DIAMOND, Denomination.QUEEN)));
        Deck deck2 = Deck.initDeck(Arrays.asList(Card.valueOf(Symbol.CLOVER, Denomination.SEVEN),
            Card.valueOf(Symbol.HEART, Denomination.THREE),
            Card.valueOf(Symbol.SPADE, Denomination.FIVE)));
        player.receiveInitialTwoCards(deck1);
        dealer.receiveInitialTwoCards(deck2);
        player.hit(deck1);
        dealer.hit(deck2);
        assertThat(Result.judgeResult(player, dealer)).isEqualTo(Result.LOSE);
    }

    @Test
    @DisplayName("딜러가 Bust일 경우에 WIN을 반환한다.")
    void judgeResultTest_dealerIsBust() {
        Deck deck1 = Deck.initDeck(Arrays.asList(Card.valueOf(Symbol.DIAMOND, Denomination.JACK),
            Card.valueOf(Symbol.CLOVER, Denomination.SEVEN),
            Card.valueOf(Symbol.DIAMOND, Denomination.TWO)));
        Deck deck2 = Deck.initDeck(Arrays.asList(Card.valueOf(Symbol.CLOVER, Denomination.SEVEN),
            Card.valueOf(Symbol.HEART, Denomination.FIVE),
            Card.valueOf(Symbol.SPADE, Denomination.QUEEN)));
        player.receiveInitialTwoCards(deck1);
        dealer.receiveInitialTwoCards(deck2);
        player.hit(deck1);
        dealer.hit(deck2);
        assertThat(Result.judgeResult(player, dealer)).isEqualTo(Result.WIN);
    }

    @Test
    @DisplayName("딜러가 BLACKJACK이고 플레이어가 BLACKJACK이 아닌 경우에 LOSE를 반환한다.")
    void judgeResultTest_dealerIsOnlyBlackJack() {
        Deck deck1 = Deck.initDeck(Arrays.asList(Card.valueOf(Symbol.DIAMOND, Denomination.JACK),
            Card.valueOf(Symbol.CLOVER, Denomination.NINE)));
        Deck deck2 = Deck.initDeck(Arrays.asList(Card.valueOf(Symbol.CLOVER, Denomination.QUEEN),
            Card.valueOf(Symbol.HEART, Denomination.ACE)));
        player.receiveInitialTwoCards(deck1);
        dealer.receiveInitialTwoCards(deck2);
        assertThat(Result.judgeResult(player, dealer)).isEqualTo(Result.LOSE);
    }

    @Test
    @DisplayName("플레이어가 BLACKJACK이고 딜러가 BLACKJACK이 아닌 경우에 WIN을 반환한다.")
    void judgeResultTest_playerIsOnlyBlackJack() {
        Deck deck1 = Deck.initDeck(Arrays.asList(Card.valueOf(Symbol.DIAMOND, Denomination.JACK),
            Card.valueOf(Symbol.CLOVER, Denomination.ACE)));
        Deck deck2 = Deck.initDeck(Arrays.asList(Card.valueOf(Symbol.CLOVER, Denomination.QUEEN),
            Card.valueOf(Symbol.HEART, Denomination.THREE)));
        player.receiveInitialTwoCards(deck1);
        dealer.receiveInitialTwoCards(deck2);
        assertThat(Result.judgeResult(player, dealer)).isEqualTo(Result.WIN);
    }

    @Test
    @DisplayName("플레이어와 딜러가 모두 BLACKJACK인 경우에 PUSH를 반환한다.")
    void judgeResultTest_allBlackJack() {
        Deck deck1 = Deck.initDeck(Arrays.asList(Card.valueOf(Symbol.DIAMOND, Denomination.JACK),
            Card.valueOf(Symbol.CLOVER, Denomination.ACE)));
        Deck deck2 = Deck.initDeck(Arrays.asList(Card.valueOf(Symbol.CLOVER, Denomination.QUEEN),
            Card.valueOf(Symbol.HEART, Denomination.ACE)));
        player.receiveInitialTwoCards(deck1);
        dealer.receiveInitialTwoCards(deck2);
        assertThat(Result.judgeResult(player, dealer)).isEqualTo(Result.PUSH);
    }
}
