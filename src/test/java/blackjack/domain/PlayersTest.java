package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.cards.Card;
import blackjack.domain.cards.Rank;
import blackjack.domain.cards.Shape;
import blackjack.domain.participants.GamblingMoney;
import blackjack.domain.participants.Name;
import blackjack.domain.participants.Player;
import blackjack.domain.participants.Players;
import blackjack.domain.participants.Victory;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    private Player siso;
    private Player takan;
    private Players players;

    @BeforeEach
    void beforeEach() {
        siso = new Player(new Name("시소"));
        takan = new Player(new Name("타칸"));

        siso.receiveCard(new Card(Shape.HEART, Rank.JACK));
        siso.receiveCard(new Card(Shape.HEART, Rank.SIX)); // 16

        takan.receiveCard(new Card(Shape.SPADE, Rank.ACE));
        takan.receiveCard(new Card(Shape.SPADE, Rank.JACK)); // 21

        List<Player> playerList = List.of(siso, takan);
        players = new Players(playerList);

    }

    @Test
    @DisplayName("딜러보다 점수가 낮은 플레이어는 패배한다.")
    void calculateVictoryFailTest() {
        Map<Player, Victory> result = players.calculateVictory(20, false);

        assertThat(result.get(siso)).isEqualTo(Victory.LOSE);
    }

    @Test
    @DisplayName("딜러보다 점수가 높은 플레이어는 승리한다.")
    void calculateResultSuccessTest() {
        Map<Player, Victory> result = players.calculateVictory(20, false);

        assertThat(result.get(takan)).isEqualTo(Victory.BLACKJACK_WIN);
    }

    @Test
    @DisplayName("한 플레이어는 돈을 배팅한다.")
    void betOnePlayerMoneyTest() {
        players.betOnePlayerMoney(new GamblingMoney(3000), 0);
        assertThat(siso.getMoney().equals(new GamblingMoney(3000))).isTrue();
    }


    @Test
    @DisplayName("한 플레이어는 하나의 카드를 받는다.")
    void receiveOnePlayerCardTest() {
        players.receiveOnePlayerCard(new Card(Shape.DIAMOND, Rank.TWO), 0);

        assertThat(siso.getHand().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("한 플레이어는 기준 점수보다 낮은 점수다.")
    void isOnePlayerNotO0verTest() {
        assertThat(players.isOnePlayerNotOver(0)).isTrue();
    }

    @Test
    @DisplayName("플레이어들의 사이즈를 잘 센다.")
    void sizeTest() {
        assertThat(players.size()).isEqualTo(2);
    }
}
