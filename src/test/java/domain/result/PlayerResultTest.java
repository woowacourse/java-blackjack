package domain.result;

import domain.card.Card;
import domain.card.PlayingCards;
import domain.card.Symbol;
import domain.card.Type;
import domain.gamer.Dealer;
import domain.gamer.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerResultTest {
    @Test
    @DisplayName("플레이어가 블랙잭일 때 승리인지 확인한다.")
    void isBlackWin() {
        Player player = new Player(new PlayingCards(Arrays.asList(
                new Card(Symbol.QUEEN, Type.CLOVER),
                new Card(Symbol.ACE, Type.DIAMOND)
        )), "testUser");
        Dealer dealer = new Dealer(new PlayingCards(Arrays.asList(
                new Card(Symbol.TEN, Type.CLOVER),
                new Card(Symbol.NINE, Type.CLOVER),
                new Card(Symbol.THREE, Type.CLOVER))));
        assertThat(PlayerResult.BLACKJACK_WIN.isMatch(dealer, player)).isTrue();
    }

    @Test
    @DisplayName("player와 dealer의 점수를 비교해 승리인지 확인한다.")
    void isWin1() {
        Player player = new Player(new PlayingCards(Arrays.asList(
                new Card(Symbol.QUEEN, Type.CLOVER),
                new Card(Symbol.QUEEN, Type.DIAMOND)
        )), "testUser");
        Dealer dealer = new Dealer(new PlayingCards(Arrays.asList(
                new Card(Symbol.TEN, Type.CLOVER),
                new Card(Symbol.NINE, Type.CLOVER))));
        Assertions.assertThat(PlayerResult.WIN.isMatch(dealer, player)).isTrue();
    }

    @Test
    @DisplayName("딜러가 버스트일 때 승리인지 확인한다.")
    void isWin2() {
        Player player = new Player(new PlayingCards(Arrays.asList(
                new Card(Symbol.ACE, Type.CLOVER),
                new Card(Symbol.TWO, Type.DIAMOND)
        )), "testUser");
        Dealer dealer = new Dealer(new PlayingCards(Arrays.asList(
                new Card(Symbol.TEN, Type.CLOVER),
                new Card(Symbol.NINE, Type.CLOVER),
                new Card(Symbol.KING, Type.CLOVER))));
        assertThat(PlayerResult.WIN.isMatch(dealer, player)).isTrue();
    }

    @Test
    @DisplayName("player와 dealer의 점수를 비교해 무승부인지 확인한다.")
    void isDraw1() {
        Player player = new Player(new PlayingCards(Arrays.asList(
                new Card(Symbol.QUEEN, Type.CLOVER),
                new Card(Symbol.QUEEN, Type.DIAMOND)
        )), "testUser");
        Dealer dealer = new Dealer(new PlayingCards(Arrays.asList(
                new Card(Symbol.TEN, Type.CLOVER),
                new Card(Symbol.KING, Type.CLOVER))));
        assertThat(PlayerResult.DRAW.isMatch(dealer, player)).isTrue();
    }

    @Test
    @DisplayName("둘 다 블랙잭일때 무승부인지 확인한다.")
    void isDraw2() {
        Player player = new Player(new PlayingCards(Arrays.asList(
                new Card(Symbol.QUEEN, Type.CLOVER),
                new Card(Symbol.ACE, Type.DIAMOND)
        )), "testUser");
        Dealer dealer = new Dealer(new PlayingCards(Arrays.asList(
                new Card(Symbol.TEN, Type.CLOVER),
                new Card(Symbol.ACE, Type.CLOVER))));
        assertThat(PlayerResult.DRAW.isMatch(dealer, player)).isTrue();
    }

    @Test
    @DisplayName("player와 dealer의 점수를 비교해 패배인지 확인한다.")
    void isLose1() {
        Player player = new Player(new PlayingCards(Arrays.asList(
                new Card(Symbol.QUEEN, Type.CLOVER),
                new Card(Symbol.TWO, Type.DIAMOND)
        )), "testUser");
        Dealer dealer = new Dealer(new PlayingCards(Arrays.asList(
                new Card(Symbol.TEN, Type.CLOVER),
                new Card(Symbol.NINE, Type.CLOVER))));
        assertThat(PlayerResult.LOSE.isMatch(dealer, player)).isTrue();
    }

    @Test
    @DisplayName("player가 버스트이면 패배한다.")
    void isLose2() {
        Player player = new Player(new PlayingCards(Arrays.asList(
                new Card(Symbol.QUEEN, Type.CLOVER),
                new Card(Symbol.TWO, Type.DIAMOND),
                new Card(Symbol.TEN, Type.DIAMOND)
        )), "testUser");
        Dealer dealer = new Dealer(new PlayingCards(Arrays.asList(
                new Card(Symbol.TEN, Type.CLOVER),
                new Card(Symbol.NINE, Type.CLOVER),
                new Card(Symbol.KING, Type.CLOVER))));
        assertThat(PlayerResult.LOSE.isMatch(dealer, player)).isTrue();
    }

    @Test
    @DisplayName("같은 21이어도 딜러가 블랙잭이면 패배한다.")
    void isLose3() {
        Player player = new Player(new PlayingCards(Arrays.asList(
                new Card(Symbol.QUEEN, Type.CLOVER),
                new Card(Symbol.TWO, Type.DIAMOND),
                new Card(Symbol.NINE, Type.DIAMOND)
        )), "testUser");
        Dealer dealer = new Dealer(new PlayingCards(Arrays.asList(
                new Card(Symbol.TEN, Type.CLOVER),
                new Card(Symbol.ACE, Type.CLOVER))));
        assertThat(PlayerResult.LOSE.isMatch(dealer, player)).isTrue();
    }

    @Test
    @DisplayName("player와 dealer의 점수를 비교해 블랙잭 승리 결과를 생성한다.")
    void matchBlackWin() {
        Player player = new Player(new PlayingCards(Arrays.asList(
                new Card(Symbol.QUEEN, Type.CLOVER),
                new Card(Symbol.ACE, Type.DIAMOND)
        )), "testUser");
        Dealer dealer = new Dealer(new PlayingCards(Arrays.asList(
                new Card(Symbol.TEN, Type.CLOVER),
                new Card(Symbol.NINE, Type.CLOVER))));
        assertThat(PlayerResult.match(dealer, player)).isEqualTo(PlayerResult.BLACKJACK_WIN);
    }

    @Test
    @DisplayName("player와 dealer의 점수를 비교해 승리 결과를 생성한다.")
    void matchWin() {
        Player player = new Player(new PlayingCards(Arrays.asList(
                new Card(Symbol.QUEEN, Type.CLOVER),
                new Card(Symbol.QUEEN, Type.DIAMOND)
        )), "testUser");
        Dealer dealer = new Dealer(new PlayingCards(Arrays.asList(
                new Card(Symbol.TEN, Type.CLOVER),
                new Card(Symbol.NINE, Type.CLOVER))));
        assertThat(PlayerResult.match(dealer, player)).isEqualTo(PlayerResult.WIN);
    }

    @Test
    @DisplayName("player와 dealer의 점수를 비교해 무승부 결과를 생성한다.")
    void matchDraw() {
        Player player = new Player(new PlayingCards(Arrays.asList(
                new Card(Symbol.QUEEN, Type.CLOVER),
                new Card(Symbol.QUEEN, Type.DIAMOND)
        )), "testUser");
        Dealer dealer = new Dealer(new PlayingCards(Arrays.asList(
                new Card(Symbol.TEN, Type.CLOVER),
                new Card(Symbol.KING, Type.CLOVER))));
        assertThat(PlayerResult.match(dealer, player)).isEqualTo(PlayerResult.DRAW);
    }

    @Test
    @DisplayName("player와 dealer의 점수를 비교해 패배 결과를 생성한다.")
    void matchLose() {
        Player player = new Player(new PlayingCards(Arrays.asList(
                new Card(Symbol.QUEEN, Type.CLOVER),
                new Card(Symbol.TWO, Type.DIAMOND)
        )), "testUser");
        Dealer dealer = new Dealer(new PlayingCards(Arrays.asList(
                new Card(Symbol.TEN, Type.CLOVER),
                new Card(Symbol.NINE, Type.CLOVER))));
        assertThat(PlayerResult.match(dealer, player)).isEqualTo(PlayerResult.LOSE);
    }
}
