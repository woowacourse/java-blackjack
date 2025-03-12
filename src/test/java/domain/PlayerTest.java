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

    private static Player createPlayerCardsOfRanks(List<Rank> ranks) {
        Player player = new Player("행성");
        ranks.stream()
                .map(rank -> new Card(rank, Suit.DIAMOND))
                .forEach(player::addCard);
        return player;
    }

    private static List<Rank> createRanks(String rankNames) {
        return Arrays.stream(rankNames.split(","))
                .map(Rank::valueOf)
                .toList();
    }
}
