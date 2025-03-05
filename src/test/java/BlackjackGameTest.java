import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.BlackjackGame;
import except.BlackJackException;
import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;


@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class BlackjackGameTest {

    @Test
    void 플레이어_수는_딜러_포함_2명_이상이여야_한다() {
        List<String> names = List.of("");
        assertThatThrownBy(() -> new BlackjackGame(names))
                .isInstanceOf(BlackJackException.class);
    }

    @Test
    void 플레이어_수는_8명_초과하면_예외가_발생한다() {
        List<String> names = List.of("포비", "포비2", "포비3", "포비4", "포비5", "포비6", "포비7", "포비8", "포비9");
        assertThatThrownBy(() -> new BlackjackGame(names))
                .isInstanceOf(BlackJackException.class);
    }

    @Test
    void 게임을_시작하면_플레이어는_두_장의_카드를_지급_받는다() {
        List<String> names = List.of("포비", "포비2");
        BlackjackGame blackjackGame = new BlackjackGame(names);
    }
}
