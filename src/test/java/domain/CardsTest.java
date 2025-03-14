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

public class CardsTest {

    @ParameterizedTest
    @CsvSource(value = {"JACK,ACE:21", "ACE,ACE,JACK:12", "ACE,ACE,ACE,ACE:14"}, delimiterString = ":")
    @DisplayName("카드 묶음의 점수 계산 기능 테스트")
    void calculateCardsScoreTest(String rankNames, int cardsScore) {
        // given
        List<Rank> ranks = createRanks(rankNames);
        Cards cards = createCardsOfRanks(ranks);
        // when & then
        assertEquals(cardsScore, cards.calculateScore());
    }

    private static Cards createCardsOfRanks(List<Rank> ranks) {
        Cards cardsOfRanks = new Cards();
        ranks.stream()
                .map(rank -> new Card(rank, Suit.DIAMOND))
                .forEach(cardsOfRanks::addCard);
        return cardsOfRanks;
    }

    private static List<Rank> createRanks(String rankNames) {
        return Arrays.stream(rankNames.split(","))
                .map(Rank::valueOf)
                .toList();
    }
}
