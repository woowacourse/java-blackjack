package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantsTest {

    @Test
    @DisplayName("게임 참가자들의 수를 확인한다.")
    void sizeTest() {

        Player siso = new Player(new Name("시소"));
        Player tacan = new Player(new Name("타칸"));
        Player dealer = new Player(new Name("딜러"));
        List<Player> players = List.of(siso, tacan);

        Participants participants = new Participants(dealer, players);

        Assertions.assertThat(participants.count()).isEqualTo(3);

    }

}
