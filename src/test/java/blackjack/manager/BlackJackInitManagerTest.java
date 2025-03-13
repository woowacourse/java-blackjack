package blackjack.manager;

import blackjack.domain.Participants;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import org.junit.jupiter.api.Test;

class BlackJackInitManagerTest {

    @Test
    void 덱을_설정한다() {
        // given
        BlackJackInitManager blackJackInitManager = new BlackJackInitManager(new CardsGenerator());

        // when & then
        assertThatCode(blackJackInitManager::generateDeck)
                .doesNotThrowAnyException();
    }

    @Test
    void 이름들을_입력받아서_참가자를_저장한다() {
        // given
        List<String> names = List.of("꾹이", "히로", "비타");
        BlackJackInitManager blackJackInitManager = new BlackJackInitManager(new CardsGenerator());

        // when
        Participants participants = blackJackInitManager.saveParticipants(names);

        // then
        assertThat(participants.getParticipants()).hasSize(names.size() + 1);
    }

    @Test
    void 딜러를_포함하여_참가자들을_저장한다() {
        // given
        List<String> names = List.of("히로", "히포", "조로");

        // when
        BlackJackInitManager blackJackInitManager = new BlackJackInitManager(new CardsGenerator());
        Participants participants = blackJackInitManager.saveParticipants(names);

        // then
        assertThat(participants.getParticipants()).hasSize(4);
    }
}
