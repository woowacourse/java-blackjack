import domain.card.Card;
import domain.card.Pattern;
import domain.card.Rank;
import domain.participant.Hand;
import domain.participant.ParticipantInfo;
import domain.participant.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PlayerTest {

    Hand dummyHand;
    String name;

    @BeforeEach
    void init() {
        dummyHand = new Hand();
        name = "제발";
    }

    @Test
    @DisplayName("플레이어는 히트 시 카드를 뽑아 핸드에 저장한다")
    void 플레이어는_카드를_뽑아_핸드에_저장한다() {
        Player player = new Player(new ParticipantInfo(name, dummyHand));
        int beforeSize = player.handSize();
        player.keepCard(new Card(Rank.EIGHT, Pattern.CLOVER));
        int afterSize = player.handSize();

        assertThat(beforeSize + 1).isEqualTo(afterSize);
    }

    @Test
    @DisplayName("플레이어는 스탠드 시 카드를 뽑지 않고 핸드의 총합을 계산한다")
    void 플레이어는_카드를_뽑지_않고_카드_총합을_계산한다() {
        Player player = new Player(new ParticipantInfo(name, dummyHand));
        Card card1 = new Card(Rank.FOUR, Pattern.CLOVER);
        Card card2 = new Card(Rank.SIX, Pattern.CLOVER);
        player.keepCard(card1);
        player.keepCard(card2);

        int expectedScore = card1.getCardScore() + card2.getCardScore();
        int playerScore = player.getTotalCardScore();

        assertThat(playerScore).isEqualTo(expectedScore);
    }
}
