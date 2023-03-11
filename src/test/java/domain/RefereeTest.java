package domain;

import domain.user.Dealer;
import domain.user.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static domain.Referee.*;
import static domain.Result.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static utils.Assistant.addCards;

class RefereeTest {
    private Player player;
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        player = new Player("조이", new Money(1_000));
        dealer = new Dealer();
    }

    @Test
    @DisplayName("점수가 더 높은 User의 결과는 WIN 이다.")
    void test_win() {
        addCards(player, "10", "8");
        addCards(dealer, "10", "7");

        assertThat(getResult(player, dealer)).isEqualTo(WIN);
    }

    @Test
    @DisplayName("블랙잭으로 승리하면 User의 결과는 WIN_BY_BLACKJACK 이다.")
    void test_win_by_blackjack() {
        addCards(player, "10", "A");
        addCards(dealer, "10", "J", "A");

        assertThat(getResult(player, dealer)).isEqualTo(WIN_BY_BLACKJACK);
    }

    @Test
    @DisplayName("점수가 같으면 User의 결과는 DRAW 이다.")
    void test_draw() {
        addCards(player, "10", "7");
        addCards(dealer, "10", "7");

        assertThat(getResult(player, dealer)).isEqualTo(DRAW);
    }

    @Test
    @DisplayName("둘 다 bust 이면 User의 결과는 DRAW 이다.")
    void test_draw_bust() {
        addCards(player, "10", "K", "2");
        addCards(dealer, "10", "J", "5");

        assertThat(getResult(player, dealer)).isEqualTo(DRAW);
    }

    @Test
    @DisplayName("둘 다 blackjack 이면 User의 결과는 DRAW 이다.")
    void test_draw_blackjack() {
        addCards(player, "10", "A");
        addCards(dealer, "A", "J");

        assertThat(getResult(player, dealer)).isEqualTo(DRAW);
    }

    @Test
    @DisplayName("점수가 더 낮은 User의 결과는 LOSE 이다.")
    void test_lose() {
        addCards(player, "5", "7");
        addCards(dealer, "5", "J");

        assertThat(getResult(player, dealer)).isEqualTo(LOSE);
    }

    @Test
    @DisplayName("한 사람만 bust 라면 그 User의 결과는 LOSE 이다.")
    void test_lose_bust() {
        addCards(player, "10", "K", "2");
        addCards(dealer, "5", "J");

        assertThat(getResult(player, dealer)).isEqualTo(LOSE);
    }

    @Test
    @DisplayName("한 사람만 blackjack 이라면 다른 User의 결과는 LOSE 이다.")
    void test_lose_blackjack() {
        addCards(player, "10", "K", "A");
        addCards(dealer, "A", "J");

        assertThat(getResult(player, dealer)).isEqualTo(LOSE);
    }

}