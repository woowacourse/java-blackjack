package blackjack.domain.participants;

import static blackjack.domain.card.Denomination.ACE;
import static blackjack.domain.card.Denomination.FOUR;
import static blackjack.domain.card.Denomination.THREE;
import static blackjack.domain.card.Denomination.TWO;
import static blackjack.domain.card.Suit.DIAMOND;
import static blackjack.domain.card.Suit.HEART;
import static blackjack.domain.card.Suit.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.BlackJackDeckGenerator;
import blackjack.domain.DeckGenerator;
import blackjack.domain.MockDeckGenerator;
import blackjack.domain.card.Card;
import blackjack.domain.result.FinalCards;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ParticipantsTest {

    private final String dealerName = "딜러";

    @DisplayName("참가자 중 플레이어는 중복인 이름을 허용하지 않는다.")
    @Test
    void should_ThrowException_When_PlayerNameDuplicated() {
        List<String> playerNames = List.of("pobi", "pobi", "jason");

        Assertions.assertThatThrownBy(() -> Participants.of(dealerName, playerNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("플레이어 이름은 중복될 수 없습니다.");
    }

    @DisplayName("모든 참가자에게 카드를 2장씩 나눠준다.")
    @Test
    void should_AllParticipantsHas2Cards_When_HandOut() {
        Participants participants = Participants.of(dealerName, List.of("pobi", "odo", "jason"));

        participants.handOut(new BlackJackDeckGenerator().generate());

        List<List<Card>> participantCards = participants.openFinalCardsByName()
                .values()
                .stream().map(FinalCards::getCards)
                .collect(Collectors.toList());
        for (List<Card> cards : participantCards) {
            assertThat(cards).hasSize(2);
        }
    }

    @DisplayName("참가자의 보유 카드를 모두 확인하되, 딜러 카드는 첫 카드만 확인한다.")
    @Test
    void should_OpenCards_Of_AllParticipants() {
        Participants participants = Participants.of(dealerName, List.of("odo", "doy"));

        DeckGenerator mockGenerator = new MockDeckGenerator((List.of(
                new Card(SPADE, ACE), new Card(SPADE, TWO),
                new Card(DIAMOND, THREE), new Card(DIAMOND, FOUR),
                new Card(HEART, THREE), new Card(HEART, FOUR)
        )));
        participants.handOut(mockGenerator.generate());

        Map<String, List<Card>> cardsByParticipants = participants.openHandOutCardsByName();
        assertThat(cardsByParticipants)
                .containsAllEntriesOf(Map.of(
                        "딜러", List.of(new Card(SPADE, ACE)),
                        "odo", List.of(new Card(DIAMOND, THREE), new Card(DIAMOND, FOUR)),
                        "doy", List.of(new Card(HEART, THREE), new Card(HEART, FOUR))
                ));
    }
}
