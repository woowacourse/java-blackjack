import domain.*;
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
        name="제발";
    }

    @Test
    @DisplayName("히트")
    void 플레이어는_카드를_뽑아_핸드에_저장한다() {
        Player player = new Player(name, dummyHand);
        Integer beforeSize = player.handSize();
        player.keepCard(new Card(Rank.EIGHT, Pattern.CLOVER));
        Integer afterSize = player.handSize();

        assertThat(beforeSize + 1).isEqualTo(afterSize);
    }

    @Test
    @DisplayName("스탠드")
    void 플레이어는_카드를_뽑지_않고_카드_총합을_계산한다() {
        Player player=new Player(name, dummyHand);
        Card card1=new Card(Rank.FOUR, Pattern.CLOVER);
        Card card2=new Card(Rank.SIX, Pattern.CLOVER);
        player.keepCard(card1); player.keepCard(card2);

        Integer expectedScore=card1.getCardScore()+card2.getCardScore();
        Integer playerScore= player.getTotalCardScore();

        assertThat(playerScore).isEqualTo(expectedScore);
    }
}
