package blackjack.domain.participant;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ParticipantsTest {

    @Test
    @DisplayName("참가자들이 존재하지 않을 때 예외를 던지는지 테스트")
    void throwExceptionWhenEmptyNames() {
        String names = ",,";

        Assertions.assertThatThrownBy(() -> new Participants(new Dealer(), List.of("")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("참가자들이 존재하지 않습니다.");
    }
    @Test
    @DisplayName("플레이어가 9명 이상일때 에러 테스트")
    void validatePlayerCountsFail(){
        Assertions.assertThatThrownBy(()->new Participants(new Dealer(),List.of("1","2","3","4","5","6","7","8","9")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("참여자는 8명 이하여야 합니다.");
    }
    @Test
    @DisplayName("플레이어가 9명 이상일때 에러 테스트")
    void validatePlayerCountsSuccess(){
        Assertions.assertThatNoException().isThrownBy(()->new Participants(new Dealer(),List.of("1","2","3","4","5","6","7","8")));
    }

    @Test
    @DisplayName("딜러를 반환하는 테스트")
    void getDealerTest() {
        Dealer dealer = new Dealer();
        Participants participants = new Participants(dealer, List.of("pobi", "crong"));

        assertThat(participants.getDealer()).isEqualTo(dealer);
    }

    @Test
    @DisplayName("플레이어들을 반환하는 테스트")
    void getPlayersTest() {
        Dealer dealer = new Dealer();
        Participants participants = new Participants(dealer, List.of("pobi", "crong"));
        Player expected = new Player(new Name("pobi"));
        assertThat(participants.getPlayers().get(0).getClass()).isEqualTo(expected.getClass());
        assertThat(participants.getPlayers().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("플레이어들의 이름을 반환하는 테스트")
    void getPlayerNames() {
        Dealer dealer = new Dealer();
        Participants participants = new Participants(dealer, List.of("pobi", "crong"));

        Assertions.assertThat(participants.getPlayerNames()).contains("pobi", "crong");
    }
}
