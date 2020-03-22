package domain;

import domain.card.Card;
import domain.card.PlayingCards;
import domain.card.Symbol;
import domain.card.Type;
import domain.gamer.Dealer;
import domain.gamer.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerResultTest {
    @Test
    @DisplayName("player와 dealer의 점수를 비교해 승리 결과를 생성한다 ")
    void isWin() {
        Player player = new Player("testUser", 10000, new PlayingCards(Arrays.asList(
                new Card(Symbol.QUEEN, Type.CLOVER),
                new Card(Symbol.QUEEN, Type.DIAMOND)
        )));
        Dealer dealer = new Dealer(new PlayingCards(Arrays.asList(
                new Card(Symbol.TEN, Type.CLOVER),
                new Card(Symbol.NINE, Type.CLOVER))));
        assertThat(dealer.compare(player)).isEqualTo(PlayerResult.WIN);
    }

    @Test
    @DisplayName("player와 dealer의 점수를 비교해 무승부 결과를 생성한다 ")
    void isDraw() {
        Player player = new Player("testUser", 15000, new PlayingCards(Arrays.asList(
                new Card(Symbol.QUEEN, Type.CLOVER),
                new Card(Symbol.QUEEN, Type.DIAMOND)
        )));
        Dealer dealer = new Dealer(new PlayingCards(Arrays.asList(
                new Card(Symbol.TEN, Type.CLOVER),
                new Card(Symbol.KING, Type.CLOVER))));
        assertThat(dealer.compare(player)).isEqualTo(PlayerResult.DRAW);
    }

    @Test
    @DisplayName("player와 dealer의 점수를 비교해 패배 결과를 성한다 ")
    void isLose() {
        Player player = new Player("testUser", 10000, new PlayingCards(Arrays.asList(
                new Card(Symbol.QUEEN, Type.CLOVER),
                new Card(Symbol.TWO, Type.DIAMOND)
        )));
        Dealer dealer = new Dealer(new PlayingCards(Arrays.asList(
                new Card(Symbol.TEN, Type.CLOVER),
                new Card(Symbol.NINE, Type.CLOVER))));
        assertThat(dealer.compare(player)).isEqualTo(PlayerResult.LOSE);
    }

    @Test
    @DisplayName("player와 dealer의 점수를 비교해 승리 결과를 생성한다 ")
    void matchWin() {
        Player player = new Player("testUser", 20000, new PlayingCards(Arrays.asList(
                new Card(Symbol.QUEEN, Type.CLOVER),
                new Card(Symbol.QUEEN, Type.DIAMOND)
        )));
        Dealer dealer = new Dealer(new PlayingCards(Arrays.asList(
                new Card(Symbol.TEN, Type.CLOVER),
                new Card(Symbol.NINE, Type.CLOVER))));
        assertThat(dealer.compare(player)).isEqualTo(PlayerResult.WIN);
    }

    @Test
    @DisplayName("player와 dealer의 점수를 비교해 무승부 결과를 생성한다 ")
    void matchDraw() {
        Player player = new Player("testUser", 30000, new PlayingCards(Arrays.asList(
                new Card(Symbol.QUEEN, Type.CLOVER),
                new Card(Symbol.QUEEN, Type.DIAMOND)
        )));
        Dealer dealer = new Dealer(new PlayingCards(Arrays.asList(
                new Card(Symbol.TEN, Type.CLOVER),
                new Card(Symbol.KING, Type.CLOVER))));
        assertThat(dealer.compare(player)).isEqualTo(PlayerResult.DRAW);
    }

    @Test
    @DisplayName("player와 dealer의 점수를 비교해 패배 결과를 생성한다 ")
    void matchLose() {
        Player player = new Player("testUser", 40000, new PlayingCards(Arrays.asList(
                new Card(Symbol.QUEEN, Type.CLOVER),
                new Card(Symbol.TWO, Type.DIAMOND)
        )));
        Dealer dealer = new Dealer(new PlayingCards(Arrays.asList(
                new Card(Symbol.TEN, Type.CLOVER),
                new Card(Symbol.NINE, Type.CLOVER))));
        assertThat(dealer.compare(player)).isEqualTo(PlayerResult.LOSE);
    }

    @Test
    @DisplayName("player와 dealer의 점수를 비교해 블랙잭 승 결과를 생성한다 ")
    void matchBlackJackWin() {
        Player player = new Player("testUser", 40000, new PlayingCards(Arrays.asList(
                new Card(Symbol.QUEEN, Type.CLOVER),
                new Card(Symbol.ACE, Type.DIAMOND)
        )));
        Dealer dealer = new Dealer(new PlayingCards(Arrays.asList(
                new Card(Symbol.TEN, Type.CLOVER),
                new Card(Symbol.NINE, Type.CLOVER))));
        assertThat(dealer.compare(player)).isEqualTo(PlayerResult.BLACKJACKWIN);
    }
}
