import controller.dto.CardStatus;
import controller.dto.CardsStatus;
import domain.Game;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameTest {
    @DisplayName("참가자들에게 2장 씩 카드를 분배한다.")
    @Test
    void startBlackJack() {
        List<String> playerNames = Arrays.asList("pobi", "jason");
        Game game = new Game(playerNames);

        CardsStatus cardsStatus = game.start();
        List<CardStatus> statuses = cardsStatus.status();

        for (CardStatus status : statuses) {
            Assertions.assertThat(status.cards().size()).isEqualTo(2);
        }
    }
}
