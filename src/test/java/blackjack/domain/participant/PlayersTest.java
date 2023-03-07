package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.Result;
import blackjack.domain.card.Card;
import blackjack.domain.card.Number;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;

class PlayersTest {

    private Player player1;
    private Player player2;
    private Player player3;
    private Players players;

    @BeforeEach
    void setUp() {
        players = new Players(List.of("1", "2", "3"));
        player1 = players.getPlayers().get(0);
        player2 = players.getPlayers().get(1);
        player3 = players.getPlayers().get(2);
    }

    @Test
    @DisplayName("플레이어는 게임 시작 시 두 장의 카드를 받는다.")
    void receiveSettingCards() {
        List<Card> settingCards = List.of(
                new Card(Number.THREE, Suit.DIAMOND),
                new Card(Number.J, Suit.DIAMOND),
                new Card(Number.Q, Suit.DIAMOND),
                new Card(Number.K, Suit.DIAMOND),
                new Card(Number.ACE, Suit.HEART),
                new Card(Number.J, Suit.DIAMOND)
        );

        players.receiveSettingCards(settingCards);

        assertAll(
                () -> assertThat(player1.getCards().size()).isEqualTo(2),
                () -> assertThat(player2.getCards().size()).isEqualTo(2),
                () -> assertThat(player3.getCards().size()).isEqualTo(2)
        );
    }

    @Test
    @DisplayName("최종 승패를 결정한다.")
    void decideResult() {
        int dealerScore = 20;

        player1.receiveCard(new Card(Number.TWO, Suit.HEART));
        player1.receiveCard(new Card(Number.EIGHT, Suit.SPADE));
        player1.receiveCard(new Card(Number.ACE, Suit.CLOVER));

        player2.receiveCard(new Card(Number.SEVEN, Suit.CLOVER));
        player2.receiveCard(new Card(Number.K, Suit.SPADE));

        player3.receiveCard(new Card(Number.THREE, Suit.HEART));
        player3.receiveCard(new Card(Number.NINE, Suit.SPADE));
        player3.receiveCard(new Card(Number.EIGHT, Suit.CLOVER));

        Map<Player, Result> result = players.makeResult(dealerScore);
        assertAll(
                () -> assertThat(result.get(player1)).isEqualTo(Result.WIN),
                () -> assertThat(result.get(player2)).isEqualTo(Result.LOSE),
                () -> assertThat(result.get(player3)).isEqualTo(Result.DRAW)
        );
    }
}
