package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Deck;
import domain.PlayerCommand;
import domain.card.Shape;
import domain.card.Value;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        List<Card> cards = new ArrayList<>(List.of(new Card(Value.KING, Shape.HEART), new Card(Value.THREE, Shape.SPADE)));
        player = new Player(new Name("깃짱"), cards, new BettingMoney(1000));
    }

    @DisplayName("플레이어 이름 확인을 통해 딜러 생성에 성공하였는지 알 수 있다.")
    @Test
    void createTest() {
        assertThat(player.getName()).isEqualTo("깃짱");
    }

    @DisplayName("플레이어의 점수를 알 수 있다.")
    @Test
    void calculateScoreTest() {
        assertThat(player.calculateScore()).isEqualTo(13);
    }

    @DisplayName("플레이어는 카드를 추가로 받을지 선택할 수 있다.")
    @Test
    void receiveAdditionalCardTest() {
        player.receiveAdditionalCard(PlayerCommand.HIT, new Deck());
        assertThat(player.calculateScore()).isEqualTo(23);
    }
}
