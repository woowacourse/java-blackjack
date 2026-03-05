package service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import java.util.Map;
import model.Cards;
import model.Dealer;
import model.DealerWinning;
import model.MatchStatus;
import model.Player;
import model.PlayerName;
import model.Players;
import model.PlayersWinning;
import model.dto.ParticipantWinning;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TestBlackJackService {
    /*
        카드 뽑기 void
         - 카드를 뽑는다.
         - 뽑은 카드가 일반이면, 그냥 / ace면 이전 점수를 가져와 점수값을 가져온다.
         - 플레이어 카드풀 업데이트한다.
         - 해당 점수를 업데이트한다.

        버스트 판정 (플레이어)
         - 플레이어 스코어가 21인지 여부 확인해서, boolean 리턴

         승패 판정 -> 결과 dto 만들어서 리턴하는 메서드
     */
    @Test
    public void 카드_뽑기_정상_작동() {
        //given
        Cards cards = new Cards();
        BlackJackService blackJackService = new BlackJackService(cards);

        Player player = new Player(new PlayerName("player1"));

        //when
        blackJackService.draw(player);

        //then
        assertThat(player.getResult().score()).isGreaterThan(0);
        assertThat(player.getResult().deck().size()).isEqualTo(1);

    }

    @ParameterizedTest
    @CsvSource({"22,24,무","10,10,무","22,1,승","10,11,승","1,22,패","11,10,패"})
    public void 블랙잭_참가자_승_패_판정_정상_작동(Integer dealerScore, Integer playerScore, String result) {
        //given
        Cards cards = new Cards();
        BlackJackService blackJackService = new BlackJackService(cards);

        Player player = new Player(new PlayerName("player1"));
        Players players = new Players(List.of(player));

        Dealer dealer = new Dealer();


        //when
        dealer.addScore(dealerScore);
        player.addScore(playerScore);

        ParticipantWinning gameResult = blackJackService.getGameResult(players, dealer);
        Map<MatchStatus, Integer> dealerWinning = gameResult.dealerWinning().getDealerWinning();

        //then
        assertThat(gameResult.playersWinning().getPlayersWinnings().getFirst().matchStatus().getStatus()).isEqualTo(result);

        if(result.equals("승")) {
            assertThat(dealerWinning.get(MatchStatus.WIN)).isEqualTo(1);
            assertThat(dealerWinning.get(MatchStatus.LOSE)).isEqualTo(0);
            assertThat(dealerWinning.get(MatchStatus.DRAW)).isEqualTo(0);
            return;
        }

        if(result.equals("패")) {
            assertThat(dealerWinning.get(MatchStatus.WIN)).isEqualTo(0);
            assertThat(dealerWinning.get(MatchStatus.LOSE)).isEqualTo(1);
            assertThat(dealerWinning.get(MatchStatus.DRAW)).isEqualTo(0);
            return;
        }

        assertThat(dealerWinning.get(MatchStatus.WIN)).isEqualTo(0);
        assertThat(dealerWinning.get(MatchStatus.LOSE)).isEqualTo(0);
        assertThat(dealerWinning.get(MatchStatus.DRAW)).isEqualTo(1);
    }
}
