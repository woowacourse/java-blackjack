package blackjack.domain.game;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import blackjack.domain.user.name.PlayerName;
import blackjack.domain.user.name.UserName;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ScoreBoardTest {

    @Test
    void 점수판은_플레이어와_해당_플레이어의_점수를_가진다() {
        // given
        int score = 10;
        Player user = new Player(new PlayerName("dummy"));

        // when
        ScoreBoard scoreBoard = new ScoreBoard(new Dealer(), new Players(List.of(user)));
        scoreBoard.writeScore(user, score);

        // then
        Assertions.assertThat(scoreBoard.get(user.getName())).isEqualTo(score);
    }

    @Test
    void 참가하지_않은_플레이어의_이름을_검색할_경우_예외() {
        // given
        int score = 10;
        Player user = new Player(new PlayerName("dummy"));
        UserName noParticipant = new UserName("dummy2");

        // when
        ScoreBoard scoreBoard = new ScoreBoard(new Dealer(), new Players(List.of(user)));
        scoreBoard.writeScore(user, score);

        // then
        Assertions.assertThatThrownBy(() -> scoreBoard.get(noParticipant))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
