package blackjack.domain.participant;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatNoException;

public class ParticipantsTest {
    @Test
    @DisplayName("참가자들 생성자 테스트")
    void constructorTest(){
        Dealer dealer = new Dealer();
        String playerNames = "pobi, crong";
        assertThatNoException().isThrownBy(()-> new Participants(dealer,playerNames));
    }

    @Test
    @DisplayName("딜러를 반환하는 테스트")
    void getDealerTest(){
        Dealer dealer = new Dealer();
        String playerNames = "pobi, crong";
        Participants participants = new Participants(dealer,playerNames);

        assertThat(participants.getDealer()).isEqualTo(dealer);
    }

    @Test
    @DisplayName("플레이어들을 반환하는 테스트")
    void getPlayersTest(){
        Dealer dealer = new Dealer();
        String playerNames = "pobi, crong";
        Participants participants = new Participants(dealer,playerNames);
        Player expected = new Player(new Name(""));
        assertThat(participants.getPlayers().get(0).getClass()).isEqualTo(expected.getClass());
        assertThat(participants.getPlayers().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("플레이어들의 이름을 반환하는 테스트")
    void getPlayerNames(){
        Dealer dealer = new Dealer();
        String playerNames = "pobi, crong";
        Participants participants = new Participants(dealer,playerNames);

        Assertions.assertThat(participants.getPlayerNames()).contains("pobi","crong");
    }
}
