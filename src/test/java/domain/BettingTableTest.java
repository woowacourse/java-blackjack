package domain;

import domain.participant.WinStatus;
import domain.vo.Money;
import domain.vo.Name;
import dto.ResultForBettingDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BettingTableTest {
    private final Name playerName = new Name("테스트");
    private BettingTable bettingTable;

    @BeforeEach
    void setUp() {
        bettingTable = new BettingTable();
    }

    @Test
    @DisplayName("블랙잭으로 승리하면 판돈의 1.5배가 수익이 된다")
    void 블랙잭_승리_판돈_테스트() {
        // given
        WinStatus winStatus = WinStatus.WIN;
        boolean isBlackjack = true;
        Money initialBet = new Money("10000");

        bettingTable.placeBet(playerName, initialBet);

        // when
        Money profit = bettingTable.calculateProfit(new ResultForBettingDto(playerName, winStatus, isBlackjack));

        // then
        assertThat(profit.toLong()).isEqualTo(15000L);
    }

    @Test
    void 패배_판돈_마이너스_테스트() {
        // given
        bettingTable.placeBet(playerName, new Money("10000"));

        // when
        Money profit = bettingTable.calculateProfit(new ResultForBettingDto(playerName, WinStatus.LOSS, false));

        // then
        assertThat(profit.toLong()).isEqualTo(-10000L);
    }

    @Test
    void 무승부_판돈_테스트() {
        // given
        bettingTable.placeBet(playerName, new Money("10000"));

        // when
        Money profit = bettingTable.calculateProfit(new ResultForBettingDto(playerName, WinStatus.DRAW, false));

        // then
        assertThat(profit.toLong()).isEqualTo(0L);
    }

    @Test
    @DisplayName("딜러의 총 수익은 플레이어들의 수익 합계의 반전이다")
    void 딜러_총수익_계산_테스트() {
        // given
        Name name2 = new Name("네임");
        bettingTable.placeBet(playerName, new Money("10000"));
        bettingTable.placeBet(name2, new Money("20000"));

        List<ResultForBettingDto> dtos = new ArrayList<>();
        dtos.add(new ResultForBettingDto(playerName, WinStatus.LOSS, false));
        dtos.add(new ResultForBettingDto(name2, WinStatus.WIN, true));

        // when
        Money dealerProfit = bettingTable.getDealerProfit(dtos);

        // then
        assertThat(dealerProfit.toLong()).isEqualTo(-20000L);
    }
}