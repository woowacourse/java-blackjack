package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

class BlackJackParticipantsTest {
    @Test
    @DisplayName("생성한다.")
    void create() {
        final Deck deck = new Deck();
        final List<String> nameValues = List.of("헤나", "시카");

        assertThatNoException()
                .isThrownBy(() -> new BlackJackParticipants(deck, nameValues));
    }

    @Test
    @DisplayName("블랙잭 결과를 가져온다.")
    void createBlackJackResults() {
        final Deck deck = new Deck();
        final List<String> nameValues = List.of("헤나", "시카");
        final BlackJackParticipants participants = new BlackJackParticipants(deck, nameValues);
        final BlackJackResults blackJackResults = participants.createBlackJackResults();
        final Map<Name, BlackJackResult> blackJackGameResults = blackJackResults.getParticipants();

        final List<String> participantNames = blackJackGameResults.keySet()
                .stream()
                .map(Name::getValue)
                .collect(Collectors.toList());

        assertThat(participantNames)
                .containsExactlyElementsOf(List.of("딜러", "헤나", "시카"));
    }
}
