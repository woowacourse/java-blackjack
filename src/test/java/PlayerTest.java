import domain.Card;
import domain.Player;
import domain.constants.CardValue;
import domain.constants.Shape;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @DisplayName("카드를 저장한다.")
    @Test
    void saveCard() {
        Player player = new Player("pobi");
        player.saveCard(new Card(CardValue.ACE, Shape.CLOVER));
        int totalSize = player.getTotalSize();
        Assertions.assertThat(totalSize).isEqualTo(1);
    }
}
