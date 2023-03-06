package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantGeneratorTest {

    @DisplayName("플레이어 생성에 성공한다.")
    @Test
    void create_players_success() {
        // given
        List<Name> names = List.of(new Name("name"), new Name("pobi"));
        // when && then
        assertThatNoException().isThrownBy(() -> ParticipantGenerator.createPlayers(names));
    }

    @DisplayName("생성된 플레이어들은 모두 기본적으로 0장의 카드를 가지고 있다.")
    @Test
    void players_drawn_cards_size_zero() {
        // given
        List<Name> names = List.of(new Name("name"), new Name("pobi"));
        Players players = ParticipantGenerator.createPlayers(names);
        // when
        boolean result = players.stream()
                .map(player -> player.getDrawnCards())
                .allMatch(drawnCard -> drawnCard.size() == 0);
        // then
        assertThat(result).isTrue();
    }

    @DisplayName("딜러 생성에 성공한다.")
    @Test
    void create_dealer_success() {
        // when && then
        assertThatNoException().isThrownBy(() -> ParticipantGenerator.createDealer());
    }

    @DisplayName("생성된 딜러는 기본적으로 0장의 카드를 가지고 있다.")
    @Test
    void dealer_drawn_cards_size_zero() {
        // given
        Dealer dealer = ParticipantGenerator.createDealer();
        // when
        int actual = dealer.getDrawnCards()
                .size();
        // then
        assertThat(actual).isEqualTo(0);
    }
}
