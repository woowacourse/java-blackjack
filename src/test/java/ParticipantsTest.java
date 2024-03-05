import domain.Name;
import domain.Participants;
import domain.Player;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ParticipantsTest {

    @Test
    @DisplayName("52장 카드 생성") // TODO 바꿈
    void t() { // TODO 메서드이름 바꿈
        List<Player> participantList = List.of(new Player(new Name("시소")), new Player(new Name("타칸")));
        Player dealer = new Player(new Name("딜러"));
        Participants participants = new Participants(dealer, participantList);

        int result = participants.getDeck().getCards().size();

        Assertions.assertThat(result).isEqualTo(52);
    }


    @Test
    @DisplayName("초기에 딜러와 플레이어는 카드 두 장을 받는다.")
    void initialSetting() {
        List<Player> participantList = List.of(new Player(new Name("시소")), new Player(new Name("타칸")));
        Player dealer = new Player(new Name("딜러"));
        Participants participants = new Participants(dealer, participantList);

        participants.initialSetting();

        Assertions.assertThat(dealer.getDeck().getCards().size()).isEqualTo(2);
    }

}
