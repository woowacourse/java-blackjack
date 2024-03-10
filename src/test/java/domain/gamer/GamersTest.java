package domain.gamer;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.cards.gamercards.DealerCards;
import domain.cards.gamercards.PlayerCards;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GamersTest {

    private Dealer dealer;

    @BeforeEach
    void setUp() {
        dealer = new Dealer(new DealerCards(new ArrayList<>()));
    }

    @DisplayName("플레이어들 간 중복된 이름을 가질 경우 예외를 발생시킨다.")
    @Test
    void createDomainByDuplicatedNames() {
        Player player1 = new Player("p", new PlayerCards(new ArrayList<>()));
        Player player2 = new Player("p", new PlayerCards(new ArrayList<>()));
        Player player3 = new Player("p1", new PlayerCards(new ArrayList<>()));

        assertThatThrownBy(() -> new Gamers(List.of(player1, player2, player3), dealer))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 플레이어 간 중복된 이름을 가질 수 없습니다.");
    }

    @DisplayName("플레이어가 1명보다 적을 경우 예외를 발생시킨다.")
    @Test
    void createDomainByInvalidMinSize() {
        assertThatThrownBy(() -> new Gamers(new ArrayList<>(), dealer))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 플레이어는 최소 1명에서 최대 8명까지 참여할 수 있습니다.");
    }

    @DisplayName("플레이어가 8명이 넘어갈 경우 예외를 발생시킨다.")
    @Test
    void createDomainByInvalidMaxSize() {
        Player player1 = new Player("p1", new PlayerCards(new ArrayList<>()));
        Player player2 = new Player("p2", new PlayerCards(new ArrayList<>()));
        Player player3 = new Player("p3", new PlayerCards(new ArrayList<>()));
        Player player4 = new Player("p4", new PlayerCards(new ArrayList<>()));
        Player player5 = new Player("p5", new PlayerCards(new ArrayList<>()));
        Player player6 = new Player("p6", new PlayerCards(new ArrayList<>()));
        Player player7 = new Player("p7", new PlayerCards(new ArrayList<>()));
        Player player8 = new Player("p8", new PlayerCards(new ArrayList<>()));
        Player player9 = new Player("p9", new PlayerCards(new ArrayList<>()));

        assertThatThrownBy(() -> new Gamers(
                List.of(player1, player2, player3, player4, player5, player6, player7, player8, player9), dealer))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 플레이어는 최소 1명에서 최대 8명까지 참여할 수 있습니다.");
    }

    @DisplayName("올바른 조건의 플레이어가 전달된 경우 성공적으로 도메인을 생성한다.")
    @Test
    void createDomainSuccessfully() {
        Player player1 = new Player("p1", new PlayerCards(new ArrayList<>()));
        Player player2 = new Player("p2", new PlayerCards(new ArrayList<>()));

        assertThatCode(() -> new Gamers(List.of(player1, player2), dealer))
                .doesNotThrowAnyException();
    }

    @DisplayName("플레이어의 이름이 \"딜러\"면 예외를 발생시킨다.")
    @Test
    void createDomainByInvalidName() {
        Player player = new Player("딜러", new PlayerCards(new ArrayList<>()));

        assertThatThrownBy(() -> new Gamers(List.of(player), dealer))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 플레이어의 이름은 \"딜러\"가 될 수 없습니다.");
    }
}
