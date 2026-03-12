package blackjack.dto;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Participants;
import blackjack.domain.Players;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Role;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerNicknamesResultTest {

    @Test
    @DisplayName("플레이어들의 닉네임 dto 생성읋 확인한다.")
    void makePlayerNicknameResult() {
        // given
        Player playerA = new Player("boye", Role.PLAYER, 1000);
        Player playerB = new Player("sumin", Role.PLAYER, 2000);
        Players players = Players.from(List.of(playerA, playerB));
        Dealer dealer = Dealer.from();
        Participants participants = new Participants(players, dealer);

        // when
        PlayerNicknamesResult playerNicknamesResult = PlayerNicknamesResult.from(participants);

        // then
        assertThat(playerNicknamesResult.nicknames()).containsExactly("boye", "sumin");
    }

}
