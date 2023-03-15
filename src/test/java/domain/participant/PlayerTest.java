package domain.participant;

import static domain.Fixtures.ACE_CLOVER;
import static domain.Fixtures.FOUR_HEART;
import static domain.Fixtures.KING_HEART;
import static domain.Fixtures.NINE_DIAMOND;
import static domain.Fixtures.TEN_HEART;
import static domain.Fixtures.THREE_SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Cards;
import domain.result.WinningStatus;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    private Player gitJjang;
    private Player kyle;

    @BeforeEach
    void setUp() {
        gitJjang = new Player(new Name("깃짱"));
        Cards gitJjangCards = new Cards(List.of(KING_HEART, THREE_SPADE));
        gitJjang.receiveInitialCards(gitJjangCards);

        kyle = new Player(new Name("카일"));
        Cards kyleCards = new Cards(List.of(FOUR_HEART, ACE_CLOVER));
        kyle.receiveInitialCards(kyleCards);
    }

    @DisplayName("플레이어의 점수를 알 수 있다.")
    @Test
    void calculateScoreTest() {
        assertThat(gitJjang.getScore()).isEqualTo(13);
        assertThat(kyle.getScore()).isEqualTo(15);
    }

    @DisplayName("플레이어는 카드를 추가로 받을지 선택할 수 있다.")
    @Test
    void receiveAdditionalCardTest() {
        gitJjang.receiveCard(TEN_HEART);
        assertThat(gitJjang.getScore()).isEqualTo(23);

        kyle.receiveCard(FOUR_HEART);
        assertThat(kyle.getScore()).isEqualTo(19);

        kyle.receiveCard(ACE_CLOVER);
        assertThat(kyle.getScore()).isEqualTo(20);

        kyle.receiveCard(NINE_DIAMOND);
        assertThat(kyle.getScore()).isEqualTo(19);
    }

    @Test
    void compete() {
        gitJjang.selectStand();

        Dealer dealer = new Dealer();
        dealer.receiveInitialCards(new Cards(List.of(FOUR_HEART, NINE_DIAMOND)));
        dealer.receiveCard(KING_HEART);

        assertThat(gitJjang.compete(dealer)).isEqualTo(WinningStatus.WIN);
    }
}
