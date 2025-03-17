package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.CardDeckGenerator;
import domain.card.TrumpNumber;
import domain.card.TrumpShape;
import java.util.List;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @Test
    void 게임_참가자들을_관리하는_모델을_생성한다() {
        // given
        List<Player> players = List.of(Player.of("pobi1"),
                Player.of("pobi2"),
                Player.of("pobi3"),
                Player.of("pobi4"),
                Player.of("pobi5"));

        // when & then
        assertThatCode(() -> Players.of(players))
                .doesNotThrowAnyException();
    }

    @Test
    void 게임_참가자_최소_인원_미만_시_예외가_발생한다() {
        // given
        List<Player> players = List.of();

        // when & then
        assertThatThrownBy(() -> Players.of(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("참여 가능한 플레이어는 최소 1명, 최대 5명 입니다.");
    }

    @Test
    void 게임_참가자_최대_인원_초과_시_예외가_발생한다() {
        // given
        List<Player> players = List.of(Player.of("pobi1"),
                Player.of("pobi2"),
                Player.of("pobi3"),
                Player.of("pobi4"),
                Player.of("pobi5"),
                Player.of("pobi6"));

        // when & then
        assertThatThrownBy(() -> Players.of(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("참여 가능한 플레이어는 최소 1명, 최대 5명 입니다.");
    }

    @Test
    void 플레이어의_이름이_중복이라면_예외를_발생한다() {
        // given
        List<Player> players = List.of(Player.of("pobi1"),
                Player.of("pobi"),
                Player.of("pobi2"),
                Player.of("pobi"));

        // when & then
        assertThatThrownBy(() -> Players.of(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어의 이름이 중복일 수 없습니다.");
    }

    @Test
    void 참여자들에게_카드를_한_장씩_나눠준다() {
        // given
        CardDeck cardDeck = CardDeck.of(CardDeckGenerator.generateCardDeck());
        Players players = Players.of(
                List.of(
                        Player.of("pobi1"),
                        Player.of("pobi2"),
                        Player.of("pobi3")
                )
        );

        // when
        players.receiveCards(cardDeck);

        // then
        assertThat(cardDeck.getCards()).hasSize(49);
    }

    @Test
    void 사용자의_이름과_카드를_받아_해당_사용자의_카드를_추가한다() {
        // given
        Player target = Player.of("pobi1");
        Players players = Players.of(
                List.of(
                        target,
                        Player.of("pobi2"),
                        Player.of("pobi3")
                )
        );

        // when
        players.passCardByName("pobi1", Card.of(TrumpNumber.SIX, TrumpShape.HEART));
        int score = players.getScoreOf("pobi1");

        // then
        assertThat(score).isEqualTo(6);
    }

    @Test
    void 사용자의_이름으로부터_점수를_반환한다() {
        // given
        Player target = Player.of("pobi1");
        Players players = Players.of(
                List.of(
                        target,
                        Player.of("pobi2"),
                        Player.of("pobi3")
                )
        );
        target.receive(Card.of(TrumpNumber.SIX, TrumpShape.HEART));

        // when
        int score = players.getScoreOf("pobi1");

        // then
        assertThat(score).isEqualTo(6);
    }

    @Test
    void 이름으로_플레이어를_반환한다() {
        // given
        Players players = Players.of(
                List.of(
                        Player.of("pobi1"),
                        Player.of("pobi2"),
                        Player.of("pobi3")
                )
        );

        // when
        Player player = players.findByName("pobi1");

        // then
        assertThat(player.getName()).isEqualTo("pobi1");
    }
}
