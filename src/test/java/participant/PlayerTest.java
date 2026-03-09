package participant;

import domain.card.Card;
import domain.card.Pattern;
import domain.card.Rank;
import domain.participant.Hand;
import domain.participant.Player;
import domain.participant.PlayerName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PlayerTest {

    Hand dummyHand;
    PlayerName name;

    @BeforeEach
    void init() {
        dummyHand = new Hand();
        name = new PlayerName("제발");
    }

    @Test
    @DisplayName("히트")
    void 플레이어는_카드를_뽑아_핸드에_저장한다() {
        Player player = new Player(name, dummyHand);
        int beforeSize = player.handSize();
        player.keepCard(new Card(Rank.EIGHT, Pattern.CLOVER));
        int afterSize = player.handSize();

        assertThat(beforeSize + 1).isEqualTo(afterSize);
    }

    @Test
    @DisplayName("스탠드")
    void 플레이어는_카드를_뽑지_않고_카드_총합을_계산한다() {
        Player player = new Player(name, dummyHand);
        Card card1 = new Card(Rank.FOUR, Pattern.CLOVER);
        Card card2 = new Card(Rank.SIX, Pattern.CLOVER);
        player.keepCard(card1);
        player.keepCard(card2);

        int expectedScore = card1.getCardScore() + card2.getCardScore();
        int playerScore = player.getTotalCardScore();

        assertThat(playerScore).isEqualTo(expectedScore);
    }
}
