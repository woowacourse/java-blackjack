package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Shape;
import blackjack.domain.participants.Hands;
import blackjack.domain.participants.Name;
import blackjack.domain.participants.Player;
import blackjack.domain.participants.Players;
import blackjack.domain.participants.WinOrLose;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
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

        Hands sisoHands = new Hands(List.of(
                new Card(Shape.HEART, Rank.JACK),
                new Card(Shape.HEART, Rank.SIX))
        );

        Hands takanHands = new Hands(List.of(
                new Card(Shape.SPADE, Rank.ACE),
                new Card(Shape.SPADE, Rank.JACK))
        );

        siso.receiveHands(sisoHands);
        takan.receiveHands(takanHands);

        List<Player> playerList = List.of(siso, takan);
        players = new Players(playerList);

    }

    @Test
    @DisplayName("플레이어들의 사이즈를 잘 센다.")
    void sizeTest() {
        Assertions.assertThat(players.size()).isEqualTo(2);
    }
}
