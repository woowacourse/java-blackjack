package domain;

import static domain.participant.ParticipantFactory.createCardsOfRanks;
import static domain.participant.ParticipantFactory.createRanks;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import domain.card.Card;
import domain.card.Rank;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
}
