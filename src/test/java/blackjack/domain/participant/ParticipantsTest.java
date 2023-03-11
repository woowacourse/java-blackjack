package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ParticipantsTest {

    @Test
    void 중복된_플레이어_이름이_존재하면_예외를_던진다() {
        final Dealer dealer = new Dealer();
        final List<Player> players = List.of(new Player("toney"), new Player("toney"));

        assertThatThrownBy(() -> new Participants(dealer, players)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 플레이어가_5명_초과라면_예외를_던진다() {
        final Dealer dealer = new Dealer();
        final List<Player> players = List.of(
                new Player("dazzle"),
                new Player("kokodak"),
                new Player("toney"),
                new Player("pobi"),
                new Player("crong"),
                new Player("jason")
        );

        assertThatThrownBy(() -> new Participants(dealer, players)).isInstanceOf(IllegalArgumentException.class);
    }
}
