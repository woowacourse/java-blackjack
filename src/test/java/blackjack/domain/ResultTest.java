package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ResultTest {
    private Dealer dealer;
    private List<Player> players = new ArrayList<>();

    @BeforeEach
    void setUp() {
        dealer = new Dealer(Arrays.asList(Card.of("스페이드", "10"),
                Card.of("하트", "4")));
        // 딜러 14

        Player player = new Player("pobi", 1000,
                Arrays.asList(Card.of("스페이드", "10"),
                        Card.of("하트", "5")));
        players.add(player);
        // 플레이어1 15

        Player player2 = new Player("jason", 2000,
                Arrays.asList(Card.of("스페이드", "2"),
                        Card.of("하트", "3")));
        players.add(player2);
        // 플레이어2 5

        Player player3 = new Player("brown", 10000,
                Arrays.asList(Card.of("클로버", "A"), Card.of("다이아몬드", "10")));
        players.add(player3);
        // 플레이어3 21
    }

    @DisplayName("딜러 : 버스트, 플레이어1 : 버스트, 플레이어2 : 스테이, 플레이어3: 블랙잭")
    @Test
    void result_buster_test() {
        dealer.addCard(Card.of("스페이드", "10"));  // 딜러 24
        dealer.stayIfNotFinished();

        players.get(0).addCard(Card.of("스페이드", "9")); // 플레이어1 24

        for (int i = 0; i < players.size(); i++) {
            players.get(i).stayIfNotFinished();
        }

        Result result = new Result(dealer, players);
        assertThat(result.getDealerProfit()).isEqualTo(-16000.000000);
        assertThat(players.get(0).getProfit(dealer).getMoney()).isEqualTo(-1000.000000);
        assertThat(players.get(1).getProfit(dealer).getMoney()).isEqualTo(2000.000000);
        assertThat(players.get(2).getProfit(dealer).getMoney()).isEqualTo(15000.000000);
    }

    @DisplayName("딜러 : 블랙잭, 플레이어1 : 버스트, 플레이어2 : 스테이, 플레이어3 : 블랙잭")
    @Test
    void result_test() {
        dealer = new Dealer(Arrays.asList(Card.of("클로버", "10"),
                Card.of("하트", "A")));               // 딜러 블랙잭
        dealer.stayIfNotFinished();

        players.get(0).addCard(Card.of("스페이드", "9")); // 플레이어1 24

        for (int i = 0; i < players.size(); i++) {
            players.get(i).stayIfNotFinished();
        }

        Result result = new Result(dealer, players);
        assertThat(result.getDealerProfit()).isEqualTo(3000.000000);
        assertThat(players.get(0).getProfit(dealer).getMoney()).isEqualTo(-1000.000000);
        assertThat(players.get(1).getProfit(dealer).getMoney()).isEqualTo(-2000.000000);
        assertThat(players.get(2).getProfit(dealer).getMoney()).isEqualTo(0);
    }

    @DisplayName("딜러 : 스테이(17), 플레이어1 : 버스트, 플레이어2 : 스테이(5), 플레이어3 : 블랙잭, 플레이어4: 스테이(18)")
    @Test
    void name() {
        dealer.addCard(Card.of("클로버", "3"));
        dealer.stayIfNotFinished();

        players.get(0).addCard(Card.of("스페이드", "9"));  // 플레이어1 24

        players.add(new Player("canny", 5000,
                Arrays.asList(Card.of("하트", "A"), Card.of("클로버", "7"))));

        for (int i = 0; i < players.size(); i++) {
            players.get(i).stayIfNotFinished();
        }

        Result result = new Result(dealer, players);
        assertThat(players.get(0).getProfit(dealer).getMoney()).isEqualTo(-1000.000000);
        assertThat(players.get(1).getProfit(dealer).getMoney()).isEqualTo(-2000.000000);
        assertThat(players.get(2).getProfit(dealer).getMoney()).isEqualTo(15000.000000);
        assertThat(players.get(3).getProfit(dealer).getMoney()).isEqualTo(5000.000000);
        assertThat(result.getDealerProfit()).isEqualTo(-17000.000000);
    }
}