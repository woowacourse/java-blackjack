package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

class ParticipantsTest {

    @Test
    void 게임_참가자들을_관리하는_모델을_생성한다() {
        // given
        Participant dealer = Dealer.of();
        List<Participant> players = List.of(Player.of("pobi1"),
                Player.of("pobi2"),
                Player.of("pobi3"),
                Player.of("pobi4"),
                Player.of("pobi5"));

        // when & then
        assertThatCode(() -> Participants.of(dealer, players))
                .doesNotThrowAnyException();
    }

    @Test
    void 게임_참가자_최소_인원_미만_시_예외가_발생한다() {
        // given
        Participant dealer = Dealer.of();
        List<Participant> players = List.of();

        // when & then
        assertThatThrownBy(() -> Participants.of(dealer, players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("참여 가능한 플레이어는 최소 1명, 최대 5명 입니다.");
    }

    @Test
    void 게임_참가자_최대_인원_초과_시_예외가_발생한다() {
        // given
        Participant dealer = Dealer.of();
        List<Participant> players = List.of(Player.of("pobi1"),
                Player.of("pobi2"),
                Player.of("pobi3"),
                Player.of("pobi4"),
                Player.of("pobi5"),
                Player.of("pobi6"));

        // when & then
        assertThatThrownBy(() -> Participants.of(dealer, players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("참여 가능한 플레이어는 최소 1명, 최대 5명 입니다.");
    }

    @Test
    void 참여자들에게_카드를_한_장씩_나눠준다() {
        // given
        CardDeck cardDeck = CardDeck.of();
        Participants participants = Participants.of(
                Dealer.of(), List.of(
                        Player.of("pobi1"),
                        Player.of("pobi2"),
                        Player.of("pobi3")
                )
        );

        // when
        participants.receiveCards(cardDeck);

        // then
        assertThat(cardDeck.getCards()).hasSize(48);
    }

    @Test
    void 이름으로_플레이어를_반환한다() {
        // given
        Participants participants = Participants.of(
                Dealer.of(), List.of(
                        Player.of("pobi1"),
                        Player.of("pobi2"),
                        Player.of("pobi3")
                )
        );

        // when
        Player player = participants.findByName("pobi1");

        // then
        assertThat(player.getName()).isEqualTo("pobi1");
    }
}