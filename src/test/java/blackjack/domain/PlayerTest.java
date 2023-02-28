package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class PlayerTest {

    @Test
    void 플레이어가_정상_생성된다() {
        final Player player = new Player("허브");

        assertThat(player.getName()).isEqualTo("허브");
    }

}
