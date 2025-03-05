package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ParticipantTest {

    @ParameterizedTest
    @CsvSource(value = {"TWO:WIN", "JACK,QUEEN,KING:TIE"}, delimiterString = ":")
    @DisplayName("GameOver인 참여자가 있는 GameStatus 계산 기능 테스트")
    void determineCardsOver21Test(String rankNames, String gameStatusName) {
        // given
        List<Rank> ranks = List.of(Rank.JACK, Rank.QUEEN, Rank.KING);
        Participant participant = createParticipantCardsOfRanks(ranks);
        List<Rank> otherRanks = createRanks(rankNames);
        Participant otherParticipant = createParticipantCardsOfRanks(otherRanks);
        // then & when
        assertEquals(GameStatus.valueOf(gameStatusName), otherParticipant.determineGameStatus(participant));
    }

    @ParameterizedTest
    @CsvSource(value = {"JACK,QUEEN:WIN", "QUEEN,TWO:TIE", "JACK:LOSE", "JACK,QUEEN,KING:LOSE"}, delimiterString = ":")
    @DisplayName("GameOver인 참여자가 없는 GameStatus 계산 기능 테스트")
    void determineCardsUnder21Test(String rankNames, String gameStatusName) {
        // given
        List<Rank> ranks = List.of(Rank.JACK, Rank.TWO);
        Participant participant = createParticipantCardsOfRanks(ranks);
        List<Rank> otherRanks = createRanks(rankNames);
        Participant otherParticipant = createParticipantCardsOfRanks(otherRanks);
        // then & when
        assertEquals(GameStatus.valueOf(gameStatusName), otherParticipant.determineGameStatus(participant));
    }

    private static Participant createParticipantCardsOfRanks(List<Rank> ranks) {
        Participant participant = new Participant("행성") {
            @Override
            public boolean ableToAddCard() {
                return false;
            }
        };
        ranks.stream()
                .map(rank -> new Card(rank, Suit.DIAMOND))
                .forEach(participant::addCard);
        return participant;
    }

    private static List<Rank> createRanks(String rankNames) {
        return Arrays.stream(rankNames.split(","))
                .map(Rank::valueOf)
                .toList();
    }
}
