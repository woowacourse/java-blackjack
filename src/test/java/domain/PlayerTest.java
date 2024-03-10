package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Score;
import domain.card.Shape;
import domain.participant.Player;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @DisplayName("카드를 저장한다.")
    @Test
    void saveCard() {
        Player player = new Player("pobi");
        player.pickCard(new Deck(List.of(new Card(Score.ACE, Shape.CLOVER))), 1);
        int totalSize = player.getCardSize();
        assertThat(totalSize).isEqualTo(1);
    }

    @DisplayName("카드를 더 받을 수 있는지 확인한다.")
    @Test
    void isAbleToDrawCard() {
        Player player = new Player("pobi");
        player.drawCard(new Card(Score.TEN, Shape.CLOVER));
        player.drawCard(new Card(Score.TEN, Shape.DIAMOND));
        player.drawCard(new Card(Score.THREE, Shape.CLOVER));

        boolean ableToDrawCard = player.cannotDraw();
        assertThat(ableToDrawCard).isTrue();
    }
}
