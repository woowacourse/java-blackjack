package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CardsTest {

    @Test
    @DisplayName("카드 묶음의 점수 계산 기능 테스트")
    void calculateCardsScoreTest() {
        // given
        List<Rank> ranks = List.of(Rank.JACK, Rank.ACE);
        Cards cards = createCardsOfRanks(ranks);
        // when & then
        assertEquals(11, cards.calculateScore());
    }

    @ParameterizedTest
    @CsvSource(value = {"TWO:WIN", "JACK,QUEEN,KING:TIE"}, delimiterString = ":")
    @DisplayName("GameOver인 카드 묶음이 있는 GameStatus 계산 기능 테스트")
    void determineCardsOver21Test(String rankNames, String gameStatusName) {
        // given
        List<Rank> ranks = List.of(Rank.JACK, Rank.QUEEN, Rank.KING);
        Cards cards = createCardsOfRanks(ranks);
        List<Rank> otherRanks = createRanks(rankNames);
        Cards otherCards = createCardsOfRanks(otherRanks);
        // then & when
        assertEquals(GameStatus.valueOf(gameStatusName), otherCards.determineGameStatus(cards));
    }

    @ParameterizedTest
    @CsvSource(value = {"JACK,QUEEN:WIN", "QUEEN,TWO:TIE", "JACK:LOSE", "JACK,QUEEN,KING:LOSE"}, delimiterString = ":")
    @DisplayName("GameOver인 카드 묶음이 없는 GameStatus 계산 기능 테스트")
    void determineCardsUnder21Test(String rankNames, String gameStatusName) {
        // given
        List<Rank> ranks = List.of(Rank.JACK, Rank.TWO);
        Cards cards = createCardsOfRanks(ranks);
        List<Rank> otherRanks = createRanks(rankNames);
        Cards otherCards = createCardsOfRanks(otherRanks);
        // then & when
        assertEquals(GameStatus.valueOf(gameStatusName), otherCards.determineGameStatus(cards));
    }

    @Test
    @DisplayName("Card 1장 뽑기 기능 테스트")
    void pickCardTest() {
        // given
        Cards cards = createCardsOfRanks(List.of(Rank.ACE));
        Card card = cards.pickCard();
        // when & then
        assertThat(card).isNotNull();
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
