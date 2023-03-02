package blackjack.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;

class PlayersTest {

    private Player player1;
    private Player player2;

    @BeforeEach
    void setUp() {
        player1 = new Player("1");
        player2 = new Player("2");
    }

    @Test
    @DisplayName("플레이어는 게임 시작 시 두 장의 카드를 받는다.")
    void receiveSettingCards() {
        Players players = new Players(List.of(player1, player2));
        List<Card> settingCards = List.of(
                new Card(Number.THREE, Pattern.DIAMOND),
                new Card(Number.J, Pattern.DIAMOND),
                new Card(Number.Q, Pattern.DIAMOND),
                new Card(Number.K, Pattern.DIAMOND)
        );

        players.receiveSettingCards(settingCards);

        assertAll(
                () -> assertThat(player1.getCards().getCards().size()).isEqualTo(2),
                () -> assertThat(player2.getCards().getCards().size()).isEqualTo(2)
        );
    }

    @Test
    @DisplayName("최종 승패를 결정한다.")
    void decideResult() {
        Player player3 = new Player("3");
        Players players = new Players(List.of(player1, player2, player3));
        int dealerScore = 20;

        player1.receiveCard(new Card(Number.TWO, Pattern.HEART));
        player1.receiveCard(new Card(Number.EIGHT, Pattern.SPADE));
        player1.receiveCard(new Card(Number.ACE, Pattern.CLUB));

        player2.receiveCard(new Card(Number.SEVEN, Pattern.CLUB));
        player2.receiveCard(new Card(Number.K, Pattern.SPADE));

        player3.receiveCard(new Card(Number.THREE, Pattern.HEART));
        player3.receiveCard(new Card(Number.NINE, Pattern.SPADE));
        player3.receiveCard(new Card(Number.EIGHT, Pattern.CLUB));

        Map<Player, Result> result = players.decideResults(dealerScore);
        assertAll(
                () -> assertThat(result.get(player1)).isEqualTo(Result.WIN),
                () -> assertThat(result.get(player2)).isEqualTo(Result.LOSE),
                () -> assertThat(result.get(player3)).isEqualTo(Result.DRAW)
        );
    }
}
