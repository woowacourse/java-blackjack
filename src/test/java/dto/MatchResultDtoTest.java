package dto;

import static domain.CardFixtures.ACE_SPADES;
import static domain.CardFixtures.EIGHT_DIAMONDS;
import static domain.CardFixtures.FIVE_SPADES;
import static domain.CardFixtures.SEVEN_CLUBS;
import static domain.CardFixtures.SIX_HEARTS;
import static domain.CardFixtures.TEN_HEARTS;
import static org.assertj.core.api.Assertions.assertThat;

import domain.MatchResult;
import domain.player.Dealer;
import domain.player.Gambler;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vo.Wallet;

public class MatchResultDtoTest {
    @Test
    @DisplayName("패배시 MatchResultDto 생성 테스트")
    void createMatchResultDtoTest() {
        // given
        Dealer dealer = new Dealer(List.of(TEN_HEARTS, SIX_HEARTS, FIVE_SPADES));
        Gambler gambler = new Gambler(Wallet.of("rich", 1000), List.of(ACE_SPADES, FIVE_SPADES));

        // when
        MatchResult matchResult = gambler.match(dealer);
        double revenue = gambler.getRevenue(matchResult);
        MatchResultDto matchResultDto = MatchResultDto.of(matchResult, revenue);

        // then
        assertThat(matchResultDto.getMatchResult()).isEqualTo(MatchResult.LOSE);
        assertThat(matchResultDto.getRevenue()).isEqualTo(-1000);
    }

    @Test
    @DisplayName("승리시 MatchResultDto 생성 테스트")
    void createMatchResultDtoWhenGamblerWin() {
        // given
        Dealer dealer = new Dealer(List.of(TEN_HEARTS, SEVEN_CLUBS));
        Gambler gambler = new Gambler(Wallet.of("rich", 1000), List.of(TEN_HEARTS, EIGHT_DIAMONDS));

        // when
        MatchResult matchResult = gambler.match(dealer);
        double revenue = gambler.getRevenue(matchResult);
        MatchResultDto matchResultDto = MatchResultDto.of(matchResult, revenue);

        // then
        assertThat(matchResultDto.getMatchResult()).isEqualTo(MatchResult.WIN);
        assertThat(matchResultDto.getRevenue()).isEqualTo(1000);
    }

    @Test
    @DisplayName("블랙잭 승리시 MatchResultDto 생성 테스트")
    void createMatchResultDtoWhenGamblerWinByBlackjack() {
        // given
        Dealer dealer = new Dealer(List.of(TEN_HEARTS, SEVEN_CLUBS));
        Gambler gambler = new Gambler(Wallet.of("rich", 1000), List.of(TEN_HEARTS, ACE_SPADES));

        // when
        MatchResult matchResult = gambler.match(dealer);
        double revenue = gambler.getRevenue(matchResult);
        MatchResultDto matchResultDto = MatchResultDto.of(matchResult, revenue);

        // then
        assertThat(matchResultDto.getMatchResult()).isEqualTo(MatchResult.WIN);
        assertThat(matchResultDto.getRevenue()).isEqualTo(1500);
    }

    @Test
    @DisplayName("무승부시 MatchResultDto 생성 테스트")
    void createMatchResultDtoWhenDraw() {
        // given
        Dealer dealer = new Dealer(List.of(TEN_HEARTS, SEVEN_CLUBS));
        Gambler gambler = new Gambler(Wallet.of("rich", 1000), List.of(TEN_HEARTS, SEVEN_CLUBS));

        // when
        MatchResult matchResult = gambler.match(dealer);
        double revenue = gambler.getRevenue(matchResult);
        MatchResultDto matchResultDto = MatchResultDto.of(matchResult, revenue);

        // then
        assertThat(matchResultDto.getMatchResult()).isEqualTo(MatchResult.DRAW);
        assertThat(matchResultDto.getRevenue()).isEqualTo(0);
    }
}
