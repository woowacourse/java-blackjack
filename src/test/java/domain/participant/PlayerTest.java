package domain.participant;

import static domain.participant.ParticipantFactory.createPlayerCardsOfRanks;
import static domain.participant.ParticipantFactory.createRanks;
import static org.junit.jupiter.api.Assertions.assertEquals;

import domain.card.Rank;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PlayerTest {

    @ParameterizedTest
    @CsvSource(value = {"TWO:true", "TWO,NINE,JACK:true", "JACK,QUEEN,KING:false"}, delimiterString = ":")
    @DisplayName("플레이어 카드 추가 가능 여부 판단 기능 테스트")
    void playerAbleToAddCardTest(String rankNames, boolean expected) {
        // given
        List<Rank> playerRanks = createRanks(rankNames);
        Player player = createPlayerCardsOfRanks(playerRanks);
        // when & then
        assertEquals(expected, player.ableToAddCard());
    }
}
