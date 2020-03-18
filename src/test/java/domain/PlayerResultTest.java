package domain;

import domain.card.*;
import domain.user.Dealer;
import domain.user.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerResultTest {
    @Test
    @DisplayName("player와 dealer의 점수를 비교해 승리 결과를 생성한다 ")
    void isWin() {
        Player player = new Player(new PlayingCards(Arrays.asList(
                new Card(Symbol.QUEEN, Type.CLOVER),
                new Card(Symbol.QUEEN, Type.DIAMOND)
        )), "testUser");
        Dealer dealer = new Dealer(new PlayingCards(Arrays.asList(
                new Card(Symbol.TEN, Type.CLOVER),
                new Card(Symbol.NINE, Type.CLOVER))));
        assertThat(PlayerResult.WIN.isMatch(dealer, player)).isTrue();
    }

    @Test
    @DisplayName("player와 dealer의 점수를 비교해 무승부 결과를 생성한다 ")
    void isDraw() {
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
    @DisplayName("player와 dealer의 점수를 비교해 패배 결과를 성한다 ")
    void isLose() {
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
    @DisplayName("player와 dealer의 점수를 비교해 승리 결과를 생성한다 ")
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
    @DisplayName("player와 dealer의 점수를 비교해 무승부 결과를 생성한다 ")
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
    @DisplayName("player와 dealer의 점수를 비교해 패배 결과를 성한다 ")
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
