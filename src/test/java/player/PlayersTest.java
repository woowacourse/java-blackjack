package player;

import card.Deck;
import card.DeckGenerator;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class PlayersTest {
    @Test
    void 참여자_인원이_6인_초과인_경우_예외가_발생한다() {
        // given
        List<Player> players = List.of(
                new Dealer(),
                new Participant("시소"),
                new Participant("헤일러"),
                new Participant("부기"),
                new Participant("사나"),
                new Participant("수양"),
                new Participant("포스티")
        );

        // when & then
        Assertions.assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 플레이어_이름이_중복될_경우_예외가_발생한다() {
        // given
        List<Player> players = List.of(
                new Dealer(),
                new Participant("시소"),
                new Participant("헤일러"),
                new Participant("사나"),
                new Participant("사나"),
                new Participant("히스타"),
                new Participant("포스티")
        );

        // when & then
        Assertions.assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 참여자_모두에게_카드를_2장씩_분배한다() {
        // given
        Players players = new Players(List.of(
                new Dealer(),
                new Participant("시소"),
                new Participant("헤일러"),
                new Participant("부기"),
                new Participant("사나")
        ));
        Deck deck = DeckGenerator.generateDeck();

        // when
        players.distributeInitialCards(deck);

        // then
        Assertions.assertThat(players.getPlayers().stream()
                .filter(player -> player.getHandCards().size() == 2)
                .count()).isEqualTo(5);
    }

    @Test
    void 이름으로_참여자를_찾아_반환한다() {
        // given
        final String targetName = "시소";
        Participant participant = new Participant(targetName);

        Players players = new Players(List.of(
                new Dealer(),
                participant,
                new Participant("헤일러"),
                new Participant("부기"),
                new Participant("사나")
        ));

        // when & then
        Assertions.assertThat(players.getPlayerByName(targetName))
                .isEqualTo(participant);
    }
}
