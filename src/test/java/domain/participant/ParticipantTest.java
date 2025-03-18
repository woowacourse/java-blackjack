package domain.participant;

import static domain.participant.ParticipantFactory.createParticipantCardsOfRanks;
import static domain.participant.ParticipantFactory.createRanks;
import static org.junit.jupiter.api.Assertions.assertEquals;

import domain.card.Rank;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ParticipantTest {

    @ParameterizedTest
    @CsvSource(value = {"ACE,JACK:true", "ACE,ACE,NINE:false", "JACK,TWO,NINE:false"}, delimiterString = ":")
    @DisplayName("블랙잭 상태 계산 기능 테스트")
    void determineBlackjackCardsTest(String rankNames, boolean isBlackjack) {
        // given
        List<Rank> ranks = createRanks(rankNames);
        Participant participant = createParticipantCardsOfRanks(ranks);
        // then & when
        assertEquals(isBlackjack, participant.isBlackjack());
    }

    @ParameterizedTest
    @CsvSource(value = {"JACK,QUEEN,TWO:true", "ACE,JACK,TWO,NINE:true"}, delimiterString = ":")
    @DisplayName("버스트 상태 계산 기능 테스트")
    void determineBustCardsTest(String rankNames, boolean isBust) {
        // given
        List<Rank> ranks = createRanks(rankNames);
        Participant participant = createParticipantCardsOfRanks(ranks);
        // then & when
        assertEquals(isBust, participant.isBust());
    }
}
