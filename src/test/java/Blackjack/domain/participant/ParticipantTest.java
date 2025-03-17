package Blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import Blackjack.domain.Bet;
import Blackjack.domain.card.Card;
import Blackjack.domain.card.Rank;
import Blackjack.domain.card.Suit;
import Blackjack.domain.game.GameStatus;
import Blackjack.exception.ErrorException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class ParticipantTest {

    @ParameterizedTest
    @CsvSource(value = {"TWO:LOSE:WIN", "JACK,QUEEN,KING:LOSE:WIN"}, delimiterString = ":")
    @DisplayName("Bust인 플레이어가 있는 GameStatus 계산 기능 테스트")
    void determineCardsOver21Test(String rankNames, String playerStatus, String dealerStatus) {
        // given
        List<Rank> playerRanks = List.of(Rank.JACK, Rank.QUEEN, Rank.KING);
        Player player = createParticipant(playerRanks, name -> new Player(name, Bet.startingBet()));
        List<Rank> dealerRanks = createRanks(rankNames);
        Dealer dealer = createParticipant(dealerRanks, Dealer::new);
        // then & when
        Assertions.assertEquals(GameStatus.valueOf(playerStatus), player.determineGameStatus(dealer));
        assertEquals(GameStatus.valueOf(dealerStatus), dealer.determineGameStatus(player));
    }

    @ParameterizedTest
    @CsvSource(value = {"JACK,QUEEN:LOSE:WIN", "QUEEN,TWO:TIE:TIE", "JACK:WIN:LOSE", "JACK,QUEEN,KING:WIN:LOSE"},
            delimiterString = ":")
    @DisplayName("Bust인 플레이어가 없는 GameStatus 계산 기능 테스트")
    void determineCardsUnder21Test(String rankNames, String playerStatus, String dealerStatus) {
        // given
        List<Rank> playerRanks = List.of(Rank.JACK, Rank.TWO);
        Player player = createParticipant(playerRanks, name -> new Player(name, Bet.startingBet()));
        List<Rank> DealerRanks = createRanks(rankNames);
        Dealer dealer = createParticipant(DealerRanks, Dealer::new);
        // then & when
        assertEquals(GameStatus.valueOf(playerStatus), player.determineGameStatus(dealer));
        assertEquals(GameStatus.valueOf(dealerStatus), dealer.determineGameStatus(player));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @DisplayName("이름이 공백인 참여자 예외 테스트")
    void blankParticipantNameTest(String name) {
        // given & when & then
        assertThatThrownBy(() -> new Player(name, Bet.startingBet()))
                .isInstanceOf(ErrorException.class)
                .hasMessageContaining("[ERROR]");
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
