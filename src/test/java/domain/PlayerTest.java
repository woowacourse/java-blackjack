package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    private Player player;
    private Card card;

    @BeforeEach
    void setUp() {
        player = new Player(new Nickname("hihi"), new Hand(new ArrayList<>()));
        card = new Card(Rank.SIX, Shape.CLOVER);
    }

    @DisplayName("플레이어의 카드 합을 구한다.")
    @Test
    void 플레이어의_카드_합을_구한다() {

        // given

        // when & then
        assertThat(player.calculateSumOfRank()).isZero();
    }

    @DisplayName("플레이어는 카드를 한 장 받는다.")
    @Test
    void 플레이어는_카드를_한_장_받는다() {

        // given
        final Player player = new Player(new Nickname("hihi"), new Hand(new ArrayList<>()));
        final Card card = new Card(Rank.SIX, Shape.CLOVER);

        // when
        int previousResult = player.calculateSumOfRank();
        player.receiveCard(card);

        // then
        assertThat(player.calculateSumOfRank()).isEqualTo(previousResult + card.getScore());
    }
}
