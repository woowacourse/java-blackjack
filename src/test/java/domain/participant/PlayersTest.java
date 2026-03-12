package domain.participant;

import domain.Card;
import domain.dto.GameScoreResultDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static domain.constant.Rank.*;
import static domain.constant.Suit.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.assertAll;

public class PlayersTest {

    @Test
    void 플레이어_저장_테스트() {
        Players players = new Players();
        players.register("pobi", "10000");
        players.register("cary", "10000");

        List<Player> allPlayer = players.getAll();

        assertAll(
                () -> assertThat(allPlayer).hasSize(2),
                () -> assertThat(allPlayer)
                        .extracting(Participant::getName)
                        .containsExactly("pobi", "cary")
        );
    }

    @Test
    void 이름에_해당하는_플레이어에게_카드를_드로우한다() {
        Players players = new Players();
        players.register("pobi", "10000");

        List<String> hand = players.drawCardTo("pobi", new Card(ACE, SPADE));

        assertThat(players.getAll())
                .extracting(
                        Participant::getName,
                        Participant::showHand
                ).containsExactly(tuple("pobi", hand));
    }

    @Test
    void 이름에_해당하는_플레이어가_추가로_카드를_받을_수_있는지_확인한다() {
        Players players = new Players();
        players.register("pobi", "10000");
        players.drawCardTo("pobi", new Card(ACE, SPADE));
        players.drawCardTo("pobi", new Card(QUEEN, SPADE));

        assertThat(players.canReceiveCard("pobi")).isFalse();
    }

    @Test
    void 모든_플레이어의_점수결과를_확인한다() {
        Players players = new Players();
        players.register("pobi", "10000");
        players.drawCardTo("pobi", new Card(ACE, SPADE));
        players.drawCardTo("pobi", new Card(KING, DIAMOND));

        players.register("cary", "10000");
        players.drawCardTo("cary", new Card(SIX, HEART));
        players.drawCardTo("cary", new Card(TEN, CLUB));

        List<GameScoreResultDto> scoreResults = players.getScoreResults();

        assertThat(scoreResults)
                .extracting(
                        GameScoreResultDto::getPlayerName,
                        GameScoreResultDto::getHand,
                        GameScoreResultDto::getScore
                ).containsExactly(
                        tuple("pobi", List.of("A스페이드", "K다이아몬드"), 21),
                        tuple("cary", List.of("6하트", "10클로버"), 16));

    }
}
