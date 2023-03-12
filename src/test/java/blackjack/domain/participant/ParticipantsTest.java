package blackjack.domain.participant;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ParticipantsTest {

    @Test
    @DisplayName("참가자들이 존재하지 않을 때 예외를 던지는지 테스트")
    void throwExceptionWhenEmptyNames() {
        String names = ",,";
        Map<String,Integer> players = new HashMap<>();
        Assertions.assertThatThrownBy(() -> new Participants(new Dealer(), players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("참가자들이 존재하지 않습니다.");
    }

    @Test
    @DisplayName("플레이어가 9명 이상일때 에러 테스트")
    void validatePlayerCountsFail() {
        Map<String,Integer> players = new HashMap<>();
        for (int i = 0; i < 9; i++) {
            players.put("test",0);
        }
        Assertions.assertThatThrownBy(() -> new Participants(new Dealer(), players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("참여자는 8명 이하여야 합니다.");
    }

    @Test
    @DisplayName("플레이어가 9명 이하일때 에러 테스트")
    void validatePlayerCountsSuccess() {
        Map<String,Integer> players = new HashMap<>();
        for (int i = 0; i < 8; i++) {
            players.put("test",0);
        }
        Assertions.assertThatNoException().isThrownBy(() -> new Participants(new Dealer(), players));
    }

    @Test
    @DisplayName("딜러를 반환하는 테스트")
    void getDealerTest() {
        Dealer dealer = new Dealer();
        Map<String,Integer> players = new HashMap<>();
        players.put("test",0);
        Participants participants = new Participants(dealer, players);

        assertThat(participants.getDealer()).isEqualTo(dealer);
    }

    @Test
    @DisplayName("플레이어들을 반환하는 테스트")
    void getPlayersTest() {
        Dealer dealer = new Dealer();
        Map<String,Integer> players = new HashMap<>();
        for (int i = 0; i <8; i++) {
            players.put("test",0);
        }
        Participants participants = new Participants(dealer, players);
        assertThat(participants.getPlayers().get(0).getClass()).isEqualTo(Player.class);
        assertThat(participants.getPlayers().size()).isEqualTo(8);
    }

    @Test
    @DisplayName("플레이어들의 이름을 반환하는 테스트")
    void getPlayerNames() {
        Dealer dealer = new Dealer();
        Map<String,Integer> players = new HashMap<>();
        players.put("pobi",0);
        players.put("crong",0);
        Participants participants = new Participants(dealer,players);

        Assertions.assertThat(participants.getPlayerNames()).contains("pobi", "crong");
    }
}
