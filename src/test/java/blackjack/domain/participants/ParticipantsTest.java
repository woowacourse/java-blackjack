package blackjack.domain.participants;

import static blackjack.domain.ParticipantFixtures.BETTING_MONEY_1000;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ParticipantsTest {

    private static final Player PLAYER_POBI = new Player("pobi", BETTING_MONEY_1000);
    private static final Player PLAYER_POBI_2 = new Player("pobi", BETTING_MONEY_1000);
    private static final Player PLAYER_JASON = new Player("jason", BETTING_MONEY_1000);
    private static final Player PLAYER_WITH_DEALER_NAME = new Player("딜러", BETTING_MONEY_1000);

    private static final Dealer DEALER = new Dealer();

    @DisplayName("플레이어의 이름 간 중복을 허용하지 않는다.")
    @Test
    void should_ThrowException_When_PlayerNamesDuplicated() {
        final List<Player> players = List.of(PLAYER_POBI, PLAYER_POBI_2, PLAYER_JASON);

        assertThatThrownBy(() -> Participants.of(DEALER, players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("플레이어 이름은 중복될 수 없습니다.");
    }

    @DisplayName("플레이어의 이름과 딜러의 이름 간 중복을 허용하지 않는다.")
    @Test
    void should_ThrowException_When_PlayerNameDuplicatedWithDealerName() {
        final List<Player> players = List.of(PLAYER_POBI, PLAYER_WITH_DEALER_NAME);

        assertThatThrownBy(() -> Participants.of(DEALER, players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("플레이어 이름은 딜러 이름(딜러)과 중복될 수 없습니다.");
    }
}
