import domain.Card;
import domain.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @DisplayName("카드를 저장한다.")
    @Test
    void saveCard() {
        Card card = new Card();
        Player player = new Player();
        Assertions.assertThatCode(() -> {
            player.add(card);
        }).doesNotThrowAnyException();
    }
}
