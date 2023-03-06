package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    private Player gitJjang;
    private Player kyle;

    @BeforeEach
    void setUp() {
        List<Card> gitJjangCards = new ArrayList<>(List.of(new Card("K", "하트"), new Card("3", "스페이드")));
        List<Card> kyleCards = new ArrayList<>(List.of(new Card("4", "스페이드"), new Card("A", "클로버")));
        gitJjang = new Player(new Name("깃짱"), gitJjangCards);
        kyle = new Player(new Name("카일"), kyleCards);
    }

    @DisplayName("플레이어의 점수를 알 수 있다.")
    @Test
    void calculateScoreTest() {
        assertThat(gitJjang.calculateScore()).isEqualTo(13);
        assertThat(kyle.calculateScore()).isEqualTo(15);
    }

    @DisplayName("플레이어는 카드를 추가로 받을지 선택할 수 있다.")
    @Test
    void receiveAdditionalCardTest() {
        gitJjang.receiveCard(new Card("10", "하트"));
        assertThat(gitJjang.calculateScore()).isEqualTo(23);

        kyle.receiveCard(new Card("4", "하트"));
        assertThat(kyle.calculateScore()).isEqualTo(19);

        kyle.receiveCard(new Card("A", "다이아몬드"));
        assertThat(kyle.calculateScore()).isEqualTo(20);

        kyle.receiveCard(new Card("9", "스페이드"));
        assertThat(kyle.calculateScore()).isEqualTo(19);
    }
}
