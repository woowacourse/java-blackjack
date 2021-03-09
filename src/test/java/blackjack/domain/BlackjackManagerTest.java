package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Players;
import blackjack.view.dto.ParticipantDto;
import blackjack.view.dto.ResultDto;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StateFactoryManagerTest {

    private Dealer dealer;
    private Players players;
    private BlackjackManager blackjackManager;

    @BeforeEach
    void setUp() {
        this.dealer = new Dealer();
        this.players = new Players(Arrays.asList("pobi", "jason"));
        this.blackjackManager = new BlackjackManager(this.dealer, this.players);
    }

    @Test
    @DisplayName("각 플레이어가 초기 2장씩 소지한다.")
    void testAllPlayersGetTwoCards() {
        this.blackjackManager.initDrawCards();

        assertThat(this.players
            .toList()
            .stream()
            .filter(player -> player.getCards().size() == 2)
            .count())
            .isEqualTo(2);
    }

    @Test
    @DisplayName("게임 플레이 도중 참가자 DTO 생성")
    void testCreateParticipantDto() {
        this.blackjackManager.initDrawCards();
        ParticipantDto dealerDto = this.blackjackManager.createDealerDto();
        List<ParticipantDto> playerDtos = this.blackjackManager.createPlayerDtos();

        assertThat(dealerDto).isInstanceOf(ParticipantDto.class);
        playerDtos.forEach(dto -> assertThat(dto).isInstanceOf(ParticipantDto.class));
    }

    @Test
    @DisplayName("게임 플레이 종료 후 결과 DTO 생성")
    void testCreateResultDto() {
        this.blackjackManager.initDrawCards();
        ResultDto dealerResultDto = this.blackjackManager.createDealerResultDto();
        List<ResultDto> playerResultDtos = this.blackjackManager.createPlayerResultDtos();

        assertThat(dealerResultDto).isInstanceOf(ResultDto.class);
        playerResultDtos.forEach(dto -> assertThat(dto).isInstanceOf(ResultDto.class));
    }

    @Test
    @DisplayName("한 명의 플레이어 카드뽑기 완료 테스트")
    void testOnePlayerCompleteHitOrStay() {
//        this.blackjackManager.initDrawCards();
//        this.blackjackManager.currentPlayerHitOrStay(true);
//        assertThat(this.blackjackManager.isCurrentPlayerMoreHitable()).isTrue();
//        assertThat(this.blackjackManager.getCurrentPlayerStatus()).isTrue();
    }

    @Test
    @DisplayName("모든 플레이어들이 게임을 진행할때까지 카드뽑기 반복")
    void testDrawCardsUntilAllPlayerPlayed() {
        this.blackjackManager.initDrawCards();
        //this.blackjackManager.currentPlayerHitOrStay(true);
    }
}
