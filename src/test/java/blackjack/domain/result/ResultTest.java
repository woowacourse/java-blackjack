package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Type;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class ResultTest {

    Dealer dealer;
    Player player;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
        player = new Player();
    }

    @DisplayName("승패를 결정한다.")
    @Test
    void calculateResult() {
        Deque<Card> cards = new ArrayDeque<>(Arrays.asList(
                new Card(Type.SPADE, Denomination.ACE),
                new Card(Type.DIAMOND, Denomination.ACE),
                new Card(Type.HEART, Denomination.ACE),
                new Card(Type.CLUB, Denomination.NINE)
        ));
        Deck deck = new Deck(cards);

        dealer.draw(deck); // A 스페이드
        dealer.draw(deck); // A 다이아몬드

        player.draw(deck); // A 하트
        player.draw(deck); // 9 클로버

        assertThat(dealer.compare(player)).isEqualTo(Result.WIN);
    }

    @DisplayName("플레이어가 블랙잭으로 패배한다.")
    @Test
    void loseByBlackjack() {
        Deque<Card> cards = new ArrayDeque<>(Arrays.asList(
                new Card(Type.SPADE, Denomination.ACE),
                new Card(Type.CLUB, Denomination.JACK),
                new Card(Type.HEART, Denomination.ACE),
                new Card(Type.CLUB, Denomination.EIGHT),
                new Card(Type.CLUB, Denomination.TWO)
        ));
        Deck deck = new Deck(cards);

        dealer.draw(deck); // A 스페이드
        dealer.draw(deck); // J 클로버

        player.draw(deck); // A 하트
        player.draw(deck); // 8 클로버
        player.draw(deck); // 2 클로버

        assertThat(dealer.compare(player)).isEqualTo(Result.LOSE);
    }

    @DisplayName("플레이어와 딜러가 블랙잭 무승다.")
    @Test
    void drawByBlackjack() {
        Deque<Card> cards = new ArrayDeque<>(Arrays.asList(
                new Card(Type.SPADE, Denomination.ACE),
                new Card(Type.CLUB, Denomination.JACK),
                new Card(Type.DIAMOND, Denomination.ACE),
                new Card(Type.CLUB, Denomination.TEN)
        ));
        Deck deck = new Deck(cards);

        dealer.draw(deck); // A 스페이드
        dealer.draw(deck); // J 클로버

        player.draw(deck); // A 다이아몬드
        player.draw(deck); // 10 클로버

        assertThat(dealer.compare(player)).isEqualTo(Result.DRAW);
    }

    @DisplayName("플레이어가 블랙잭으로 승리한다.")
    @Test
    void winByBlackjack() {
        Deque<Card> cards = new ArrayDeque<>(Arrays.asList(
                new Card(Type.HEART, Denomination.ACE),
                new Card(Type.CLUB, Denomination.EIGHT),
                new Card(Type.CLUB, Denomination.TWO),
                new Card(Type.SPADE, Denomination.ACE),
                new Card(Type.CLUB, Denomination.JACK)
        ));
        Deck deck = new Deck(cards);

        dealer.draw(deck); // A 하트
        dealer.draw(deck); // 8 클로버
        dealer.draw(deck); // 2 클로버

        player.draw(deck); // A 스페이드
        player.draw(deck); // J 클로버

        assertThat(dealer.compare(player)).isEqualTo(Result.BLACKJACK_WIN);
    }

    @DisplayName("플레이어가 패배한다.")
    @Test
    void lose() {
        Deque<Card> cards = new ArrayDeque<>(Arrays.asList(
                new Card(Type.SPADE, Denomination.FOUR),
                new Card(Type.SPADE, Denomination.EIGHT),
                new Card(Type.DIAMOND, Denomination.FIVE),
                new Card(Type.DIAMOND, Denomination.THREE),
                new Card(Type.DIAMOND, Denomination.EIGHT),
                new Card(Type.DIAMOND, Denomination.FOUR)
        ));
        Deck deck = new Deck(cards);

        dealer.draw(deck); // 4 스페이드
        dealer.draw(deck); // 8 스페이드
        dealer.draw(deck); // 5 다이아몬드

        player.draw(deck); // 3 다이아몬드
        player.draw(deck); // 8 다이아몬드
        player.draw(deck); // 4 다이아몬드

        assertThat(dealer.compare(player)).isEqualTo(Result.LOSE);
    }

    @DisplayName("플레이어와 딜러가 무승부다.")
    @Test
    void draw() {
        Deque<Card> cards = new ArrayDeque<>(Arrays.asList(
                new Card(Type.SPADE, Denomination.FOUR),
                new Card(Type.SPADE, Denomination.EIGHT),
                new Card(Type.DIAMOND, Denomination.FIVE),
                new Card(Type.SPADE, Denomination.ACE),
                new Card(Type.DIAMOND, Denomination.THREE),
                new Card(Type.CLUB, Denomination.ACE),
                new Card(Type.HEART, Denomination.FOUR)
        ));
        Deck deck = new Deck(cards);

        dealer.draw(deck); // 4 스페이드
        dealer.draw(deck); // 8 스페이드
        dealer.draw(deck); // 5 다이아몬드
        dealer.draw(deck); // A 스페이드

        player.draw(deck); // 3 다이아몬드
        player.draw(deck); // A 클로버
        player.draw(deck); // 4 하트

        assertThat(dealer.compare(player)).isEqualTo(Result.DRAW);
    }

    @DisplayName("플레이어가 승리한다.")
    @Test
    void win() {
        Deque<Card> cards = new ArrayDeque<>(Arrays.asList(
                new Card(Type.SPADE, Denomination.NINE),
                new Card(Type.DIAMOND, Denomination.QUEEN),
                new Card(Type.CLUB, Denomination.ACE),
                new Card(Type.SPADE, Denomination.ACE),
                new Card(Type.DIAMOND, Denomination.ACE),
                new Card(Type.HEART, Denomination.ACE),
                new Card(Type.SPADE, Denomination.EIGHT)
        ));
        Deck deck = new Deck(cards);

        dealer.draw(deck); // 9 스페이드
        dealer.draw(deck); // Q 다이아몬드
        dealer.draw(deck); // A 클로버

        player.draw(deck); // A 스페이드
        player.draw(deck); // A 다이아몬드
        player.draw(deck); // A 하트
        player.draw(deck); // 8 스페이드

        assertThat(dealer.compare(player)).isEqualTo(Result.WIN);
    }


    @DisplayName("딜러의 게임 결과를 계산한다.")
    @Test
    void dealerGameResult() {
        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();
        Deque<Card> cards = new ArrayDeque<>(Arrays.asList(
                new Card(Type.SPADE, Denomination.THREE),
                new Card(Type.DIAMOND, Denomination.QUEEN),
                new Card(Type.SPADE, Denomination.ACE),
                new Card(Type.SPADE, Denomination.ACE),
                new Card(Type.DIAMOND, Denomination.ACE),
                new Card(Type.HEART, Denomination.ACE),
                new Card(Type.SPADE, Denomination.EIGHT),
                new Card(Type.CLUB, Denomination.ACE),
                new Card(Type.CLUB, Denomination.ACE),
                new Card(Type.HEART, Denomination.ACE),
                new Card(Type.HEART, Denomination.EIGHT),
                new Card(Type.SPADE, Denomination.ACE),
                new Card(Type.DIAMOND, Denomination.TWO),
                new Card(Type.HEART, Denomination.ACE),
                new Card(Type.CLUB, Denomination.EIGHT),
                new Card(Type.CLUB, Denomination.ACE),
                new Card(Type.CLUB, Denomination.ACE),
                new Card(Type.HEART, Denomination.ACE),
                new Card(Type.HEART, Denomination.EIGHT)
        ));
        Deck deck = new Deck(cards);
        Map<Result, Integer> expecting = new EnumMap<>(Result.class);
        expecting.put(Result.LOSE, 2);
        expecting.put(Result.WIN, 1);

        dealer.draw(deck); // 3 스페이드
        dealer.draw(deck); // Q 다이아몬드
        dealer.draw(deck); // A 클로버

        player.draw(deck); // A 스페이드
        player.draw(deck); // A 다이아몬드
        player.draw(deck); // A 하트
        player.draw(deck); // 8 스페이드

        player1.draw(deck); // A 클로버
        player1.draw(deck); // A 클로버
        player1.draw(deck); // A 하트
        player1.draw(deck); // 8 하트

        player2.draw(deck); // A 스페이드
        player2.draw(deck); // 2 다이아몬드
        player2.draw(deck); // A 하트
        player2.draw(deck); // 8 클로버

        player3.draw(deck); // A 클로버
        player3.draw(deck); // A 클로버
        player3.draw(deck); // A 하트
        player3.draw(deck); // 8 하트

        dealer.compare(player1);
        dealer.compare(player2);
        dealer.compare(player3);

        assertThat(dealer.getDealerResults()).isEqualTo(expecting);
    }

    @DisplayName("딜러와 플레이어가 둘 다 버스트면, 딜러가 이긴다.")
    @Test
    void dealerPlayerBothBust() {
        Deque<Card> cards = new ArrayDeque<>(Arrays.asList(
                new Card(Type.SPADE, Denomination.QUEEN),
                new Card(Type.DIAMOND, Denomination.QUEEN),
                new Card(Type.CLUB, Denomination.QUEEN),
                new Card(Type.HEART, Denomination.SEVEN),
                new Card(Type.CLUB, Denomination.NINE),
                new Card(Type.DIAMOND, Denomination.KING)
        ));
        Deck deck = new Deck(cards);
        Map<Result, Integer> expecting = new EnumMap<>(Result.class);
        expecting.put(Result.WIN, 1);

        dealer.draw(deck);
        dealer.draw(deck);
        dealer.draw(deck);

        player.draw(deck);
        player.draw(deck);
        player.draw(deck);

        dealer.compare(player);

        assertThat(dealer.getDealerResults()).isEqualTo(expecting);
    }

    @DisplayName("딜러만 버스트다.")
    @Test
    void dealerBust() {
        Deque<Card> cards = new ArrayDeque<>(Arrays.asList(
                new Card(Type.SPADE, Denomination.QUEEN),
                new Card(Type.DIAMOND, Denomination.QUEEN),
                new Card(Type.CLUB, Denomination.QUEEN),
                new Card(Type.HEART, Denomination.SEVEN),
                new Card(Type.CLUB, Denomination.NINE)
        ));
        Deck deck = new Deck(cards);
        Map<Result, Integer> expecting = new EnumMap<>(Result.class);
        expecting.put(Result.LOSE, 1);

        dealer.draw(deck);
        dealer.draw(deck);
        dealer.draw(deck);

        player.draw(deck);
        player.draw(deck);

        dealer.compare(player);

        assertThat(dealer.getDealerResults()).isEqualTo(expecting);
    }

    @DisplayName("플레이어만 버스트다.")
    @Test
    void playerBust() {
        Deque<Card> cards = new ArrayDeque<>(Arrays.asList(
                new Card(Type.SPADE, Denomination.QUEEN),
                new Card(Type.DIAMOND, Denomination.QUEEN),
                new Card(Type.HEART, Denomination.SEVEN),
                new Card(Type.CLUB, Denomination.NINE),
                new Card(Type.DIAMOND, Denomination.KING)
        ));
        Deck deck = new Deck(cards);
        Map<Result, Integer> expecting = new EnumMap<>(Result.class);
        expecting.put(Result.WIN, 1);

        dealer.draw(deck);
        dealer.draw(deck);

        player.draw(deck);
        player.draw(deck);
        player.draw(deck);

        dealer.compare(player);

        assertThat(dealer.getDealerResults()).isEqualTo(expecting);
    }

    @DisplayName("플레이어가 승리 했을 경우 딜러는 패배기록이 생긴다.")
    @Test
    void dealerLose() {
        Deque<Card> cards = new ArrayDeque<>(Arrays.asList(
                new Card(Type.SPADE, Denomination.QUEEN),
                new Card(Type.DIAMOND, Denomination.TWO),
                new Card(Type.CLUB, Denomination.NINE),
                new Card(Type.DIAMOND, Denomination.KING)
        ));
        Deck deck = new Deck(cards);
        Map<Result, Integer> expecting = new EnumMap<>(Result.class);
        expecting.put(Result.LOSE, 1);

        dealer.draw(deck);
        dealer.draw(deck);

        player.draw(deck);
        player.draw(deck);

        dealer.compare(player);

        assertThat(dealer.getDealerResults()).isEqualTo(expecting);
    }

    @DisplayName("플레이어가 블랙잭을 했을 경우 딜러는 패배기록이 생긴다.")
    @Test
    void dealerBlackjackLose() {
        Deque<Card> cards = new ArrayDeque<>(Arrays.asList(
                new Card(Type.SPADE, Denomination.QUEEN),
                new Card(Type.DIAMOND, Denomination.QUEEN),

                new Card(Type.CLUB, Denomination.ACE),
                new Card(Type.DIAMOND, Denomination.TEN)
        ));
        Deck deck = new Deck(cards);
        Map<Result, Integer> expecting = new EnumMap<>(Result.class);
        expecting.put(Result.LOSE, 1);

        dealer.draw(deck);
        dealer.draw(deck);

        player.draw(deck);
        player.draw(deck);

        dealer.compare(player);

        assertThat(dealer.getDealerResults()).isEqualTo(expecting);
    }
}