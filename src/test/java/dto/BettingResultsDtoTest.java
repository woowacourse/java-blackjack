package dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import betting.BettingAmount;
import betting.BettingMap;
import participants.Name;
import participants.Player;

class BettingResultsDtoTest {
    BettingMap bettingMap;

    @BeforeEach
    void setUp() {
        bettingMap = new BettingMap();
    }

    @Test
    @DisplayName("플레이어가 이긴 경우 dto 생성 테스트")
    void createWin() {
        Player player = new Player(new Name("폴로"));
        player.win();
        BettingAmount bettingAmount = new BettingAmount(10000);

        bettingMap.saveBet(player, bettingAmount);
        BettingResultsDto dto = BettingResultsDto.from(bettingMap);

        String name = dto.getBettingResults().get(0).getName().getValue();
        int reward = dto.getBettingResults().get(0).getReward().getReward();

        assertThat(name).isEqualTo("폴로");
        assertThat(reward).isEqualTo(10000);
    }

    @Test
    @DisplayName("플레이어가 진 경우의 dto 생성 테스트 ")
    void createLose() {
        Player player = new Player(new Name("폴로"));
        player.lose();
        BettingAmount bettingAmount = new BettingAmount(10000);

        bettingMap.saveBet(player, bettingAmount);
        BettingResultsDto dto = BettingResultsDto.from(bettingMap);

        String name = dto.getBettingResults().get(0).getName().getValue();
        int reward = dto.getBettingResults().get(0).getReward().getReward();

        assertThat(name).isEqualTo("폴로");
        assertThat(reward).isEqualTo(-10000);
    }

    @Test
    @DisplayName("비긴 경우 dto 생성 테스트")
    void createTie() {
        Player player = new Player(new Name("폴로"));
        player.tie();
        BettingAmount bettingAmount = new BettingAmount(10000);

        bettingMap.saveBet(player, bettingAmount);
        BettingResultsDto dto = BettingResultsDto.from(bettingMap);

        String name = dto.getBettingResults().get(0).getName().getValue();
        int reward = dto.getBettingResults().get(0).getReward().getReward();

        assertThat(name).isEqualTo("폴로");
        assertThat(reward).isEqualTo(0);
    }

    @Test
    @DisplayName("블랙잭으로 이긴 경우 dto 생성 테스트")
    void createBlackjackWin() {
        Player player = new Player(new Name("폴로"));
        player.winBlackjack();
        BettingAmount bettingAmount = new BettingAmount(10000);

        bettingMap.saveBet(player, bettingAmount);
        BettingResultsDto dto = BettingResultsDto.from(bettingMap);

        String name = dto.getBettingResults().get(0).getName().getValue();
        int reward = dto.getBettingResults().get(0).getReward().getReward();

        assertThat(name).isEqualTo("폴로");
        assertThat(reward).isEqualTo(15000);
    }

    @Test
    @DisplayName("플레이어가 여러명인 경우")
    void createBulk() {
        Player player1 = new Player(new Name("폴로"));
        player1.tie();
        Player player2 = new Player(new Name("로지"));
        player2.win();
        Player player3 = new Player(new Name("마코"));
        player3.lose();
        BettingAmount bettingAmount1 = new BettingAmount(1000);
        BettingAmount bettingAmount2 = new BettingAmount(2000);
        BettingAmount bettingAmount3 = new BettingAmount(30000);

        bettingMap.saveBet(player1, bettingAmount1);
        bettingMap.saveBet(player2, bettingAmount2);
        bettingMap.saveBet(player3, bettingAmount3);
        BettingResultsDto dto = BettingResultsDto.from(bettingMap);

        String name1 = dto.getBettingResults().get(0).getName().getValue();
        int reward1 = dto.getBettingResults().get(0).getReward().getReward();
        String name2 = dto.getBettingResults().get(1).getName().getValue();
        int reward2 = dto.getBettingResults().get(1).getReward().getReward();
        String name3 = dto.getBettingResults().get(2).getName().getValue();
        int reward3 = dto.getBettingResults().get(2).getReward().getReward();

        assertThat(name1).isEqualTo("폴로");
        assertThat(reward1).isEqualTo(0);
        assertThat(name2).isEqualTo("로지");
        assertThat(reward2).isEqualTo(2000);
        assertThat(name3).isEqualTo("마코");
        assertThat(reward3).isEqualTo(-30000);
    }

    @Test
    @DisplayName("플레이어가 여러명인 경우 블랙잭으로 이긴 플레이어가 있다.")
    void createBulkBlackjack() {
        Player player1 = new Player(new Name("폴로"));
        player1.tie();
        Player player2 = new Player(new Name("로지"));
        player2.winBlackjack();
        Player player3 = new Player(new Name("마코"));
        player3.lose();
        BettingAmount bettingAmount1 = new BettingAmount(1000);
        BettingAmount bettingAmount2 = new BettingAmount(2000);
        BettingAmount bettingAmount3 = new BettingAmount(30000);

        bettingMap.saveBet(player1, bettingAmount1);
        bettingMap.saveBet(player2, bettingAmount2);
        bettingMap.saveBet(player3, bettingAmount3);
        BettingResultsDto dto = BettingResultsDto.from(bettingMap);

        String name1 = dto.getBettingResults().get(0).getName().getValue();
        int reward1 = dto.getBettingResults().get(0).getReward().getReward();
        String name2 = dto.getBettingResults().get(1).getName().getValue();
        int reward2 = dto.getBettingResults().get(1).getReward().getReward();
        String name3 = dto.getBettingResults().get(2).getName().getValue();
        int reward3 = dto.getBettingResults().get(2).getReward().getReward();

        assertThat(name1).isEqualTo("폴로");
        assertThat(reward1).isEqualTo(0);
        assertThat(name2).isEqualTo("로지");
        assertThat(reward2).isEqualTo(3000);
        assertThat(name3).isEqualTo("마코");
        assertThat(reward3).isEqualTo(-30000);
    }
}
