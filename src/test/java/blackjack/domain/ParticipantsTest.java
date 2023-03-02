package blackjack.domain;

import static blackjack.domain.Number.ACE;
import static blackjack.domain.Number.FOUR;
import static blackjack.domain.Number.THREE;
import static blackjack.domain.Number.TWO;
import static blackjack.domain.Symbol.DIAMOND;
import static blackjack.domain.Symbol.HEART;
import static blackjack.domain.Symbol.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ParticipantsTest {

    @DisplayName("참가자 중 플레이어는 중복인 이름을 허용하지 않는다.")
    @Test
    void should_ThrowException_When_PlayerNameDuplicated() {
        List<String> playerNames = List.of("pobi", "pobi", "jason");

        Assertions.assertThatThrownBy(() -> Participants.of(playerNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("플레이어 이름은 중복될 수 없습니다.");
    }

    @DisplayName("모든 참가자에게 카드를 2장씩 나눠준다.")
    @Test
    void should_AllParticipantsHas2Cards_When_HandOut() {
        List<String> playerNames = List.of("pobi", "odo", "jason");
        Participants participants = Participants.of(playerNames);

        participants.handOut(new BlackJackDeckGenerator().generate());

        assertThat(participants.getDealerCards()).hasSize(2);
        List<List<Card>> playersCards = participants.getPlayersCards();
        for (List<Card> cards : playersCards) {
            assertThat(cards).hasSize(2);
        }
    }

    @DisplayName("플레이어의 보유 카드를 모두 확인한다")
    @Test
    void should_OpenCards_Of_AllPlayers() {
        List<String> playerNames = List.of("odo", "doy");
        Participants participants = Participants.of(playerNames);

        DeckGenerator mockGenerator = new MockDeckGenerator((List.of(
                new Card(SPADE, ACE), new Card(SPADE, TWO),
                new Card(DIAMOND, THREE), new Card(DIAMOND, FOUR),
                new Card(HEART, THREE), new Card(HEART, FOUR)
        )));
        participants.handOut(mockGenerator.generate());

        Map<String, List<Card>> cardsByParticipants = participants.openCards();
        assertThat(cardsByParticipants)
                .containsAllEntriesOf(Map.of("딜러", List.of(new Card(SPADE, ACE), new Card(SPADE, TWO)),
                        "odo", List.of(new Card(DIAMOND, THREE), new Card(DIAMOND, FOUR)),
                        "doy", List.of(new Card(HEART, THREE), new Card(HEART, FOUR))
                ));
    }
}
