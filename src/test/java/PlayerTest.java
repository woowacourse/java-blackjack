import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Cards;
import domain.card.shuffler.FixedCardsShuffler;
import domain.participant.Name;
import domain.participant.Player;
import domain.PlayerCommand;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        List<Card> cards = new ArrayList<>(List.of(new Card("K", "하트"), new Card("3", "스페이드")));
        player = new Player(new Name("깃짱"), cards);
    }

    @DisplayName("플레이어의 점수를 알 수 있다.")
    @Test
    void calculateScoreTest() {
        assertThat(player.calculateScore()).isEqualTo(13);
    }

    @DisplayName("플레이어는 카드를 추가로 받을지 선택할 수 있다.")
    @Test
    void receiveAdditionalCardTest() {
        player.receiveAdditionalCard(PlayerCommand.HIT, new Cards(new FixedCardsShuffler()));
        assertThat(player.calculateScore()).isEqualTo(23);
    }
}
