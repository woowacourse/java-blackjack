package blackjack.domain;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

@Nested
class BlackJackManagerTest {

    @Nested
    @DisplayName("카드 배부 테스트")
    class DistributeCardTest {

        @Test
        @DisplayName("딜러와 플레이어에게 카드를 2장씩 배부할 수 있다.")
        void distributeCardsToDealerAndPlayer() {
            List<String> names = List.of("sana");
            BlackJackManager manager = new BlackJackManager(
                    CardDeck.createCardDeck(),
                    Participants.createParticipantsByNames(names)
            );

            assertThatCode(manager::initCardsToParticipants)
                    .doesNotThrowAnyException();
        }
    }
}