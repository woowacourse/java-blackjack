package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import blackjack.domain.Bet;
import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import blackjack.domain.game.GameStatus;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
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
        Player player = createParticipant(playerRanks, name -> new Player(name, Bet.startingBet()));
        // when & then
        assertEquals(expected, player.ableToAddCard());
    }

    @ParameterizedTest
    @CsvSource(value = {"TWO,FIVE:BLACKJACK", "QUEEN,ACE:TIE", "QUEEN,NINE,TWO:BLACKJACK"}, delimiterString = ":")
    @DisplayName("Blackjack인 플레이어가 있는 GameStatus 계산 기능 테스트")
    void determineGameStatusWithBlackjackTest(String dealerRankNames, String playerStatus) {
        // given
        List<Rank> playerRanks = List.of(Rank.JACK, Rank.ACE);
        Player player = createParticipant(playerRanks, name -> new Player(name, Bet.startingBet()));
        List<Rank> dealerRanks = createRanks(dealerRankNames);
        Dealer dealer = createParticipant(dealerRanks, Dealer::new);
        // when
        GameStatus actual = player.determineGameStatus(dealer);
        // then
        assertThat(actual).isEqualTo(GameStatus.valueOf(playerStatus));
    }

    @ParameterizedTest
    @CsvSource(value = {"TWO:LOSE", "JACK,QUEEN,KING:LOSE"}, delimiterString = ":")
    @DisplayName("Bust인 플레이어가 있는 GameStatus 계산 기능 테스트")
    void determineGameStatusWithBustTest(String dealerRankNames, String playerStatus) {
        // given
        List<Rank> playerRanks = List.of(Rank.JACK, Rank.QUEEN, Rank.KING);
        Player player = createParticipant(playerRanks, name -> new Player(name, Bet.startingBet()));
        List<Rank> dealerRanks = createRanks(dealerRankNames);
        Dealer dealer = createParticipant(dealerRanks, Dealer::new);
        // when
        GameStatus actual = player.determineGameStatus(dealer);
        // then
        assertThat(actual).isEqualTo(GameStatus.valueOf(playerStatus));
    }

    @ParameterizedTest
    @CsvSource(value = {"JACK,QUEEN:LOSE", "QUEEN,TWO:TIE", "JACK:WIN", "JACK,QUEEN,KING:WIN"}, delimiterString = ":")
    @DisplayName("Bust인 플레이어가 없는 GameStatus 계산 기능 테스트")
    void determineGameStatusTest(String dealerRankNames, String playerStatus) {
        // given
        List<Rank> playerRanks = List.of(Rank.JACK, Rank.TWO);
        Player player = createParticipant(playerRanks, name -> new Player(name, Bet.startingBet()));
        List<Rank> dealerRanks = createRanks(dealerRankNames);
        Dealer dealer = createParticipant(dealerRanks, Dealer::new);
        // when
        GameStatus actual = player.determineGameStatus(dealer);
        // then
        assertThat(actual).isEqualTo(GameStatus.valueOf(playerStatus));
    }

    @ParameterizedTest
    @CsvSource(value = {"JACK,QUEEN:10000", "QUEEN,TWO:0", "JACK,ACE:15000", "JACK,QUEEN,KING:-10000"},
            delimiterString = ":")
    @DisplayName("플레이어의 수익 계산 기능 테스트")
    void calculateBetTest(String playerRankNames, int expectedBet) {
        // given
        List<Rank> dealerRanks = List.of(Rank.JACK, Rank.TWO);
        Dealer dealer = createParticipant(dealerRanks, Dealer::new);
        List<Rank> playerRanks = createRanks(playerRankNames);
        Player player = createParticipant(playerRanks, name -> new Player(name, Bet.valueOf(10000)));
        // when
        int actualBet = player.calculateProfit(dealer);
        // then
        assertThat(actualBet).isEqualTo(expectedBet);
    }

    private static <T extends Participant> T createParticipant(List<Rank> ranks, Function<String, T> creator) {
        T participant = creator.apply("행성");
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
