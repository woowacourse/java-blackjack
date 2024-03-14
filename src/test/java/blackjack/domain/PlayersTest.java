package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Shape;
import blackjack.domain.participants.Hands;
import blackjack.domain.participants.Name;
import blackjack.domain.participants.Player;
import blackjack.domain.participants.Players;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    private final Player siso = new Player(new Name("시소"));
    private final Player takan = new Player(new Name("타칸"));;
    private final Players players = new Players(List.of(siso, takan));


    @Test
    @DisplayName("손 패를 모든 플레이어들에게 분배한다.")
    void distributeHands() {
        List<Hands> hands = List.of(
                new Hands(List.of(
                        new Card(Shape.HEART, Rank.JACK),
                        new Card(Shape.HEART, Rank.SIX))
                ),
                new Hands(List.of(
                        new Card(Shape.SPADE, Rank.ACE),
                        new Card(Shape.SPADE, Rank.JACK))
                )
        );

        players.distributeHands(hands);

        Assertions.assertThat(siso.getHandsSize()).isEqualTo(2);
        Assertions.assertThat(takan.getHandsSize()).isEqualTo(2);
    }

    @Test
    @DisplayName("플레이어들의 사이즈를 잘 센다.")
    void sizeTest() {
        Assertions.assertThat(players.size()).isEqualTo(2);
    }
}
