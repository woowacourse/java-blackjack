package domain;

import static domain.CardFixtures.ACE_SPADES;
import static domain.CardFixtures.KING_HEARTS;
import static domain.CardFixtures.TEN_HEARTS;
import static domain.CardFixtures.THREE_DIAMONDS;
import static domain.CardFixtures.TWO_SPADES;
import static domain.MatchResult.LOSE;
import static domain.MatchResult.WIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.player.Dealer;
import domain.player.Gambler;
import domain.player.Gamblers;
import dto.ResultDto;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackJackResultTest {
    @Test
    @DisplayName("딜러와 겜블러를 전달해서 게임 결과를 반환한다")
    void judgeGameResult() {
        // given
        Dealer dealer = new Dealer(List.of(KING_HEARTS, THREE_DIAMONDS));
        Gamblers gamblers = setupGamblers();
        BlackJackResult blackJackResult = BlackJackResult.of(dealer, gamblers);

        // when
        Map<String, ResultDto> blackjackResult = blackJackResult.getBlackjackResult();
        ResultDto dealerResultDto = blackjackResult.get("딜러");
        ResultDto pobiResultDto = blackjackResult.get("포비");
        ResultDto dolbumResultDto = blackjackResult.get("돌범");
        ResultDto richardResultDto = blackjackResult.get("리차드");

        // then
        assertAll(
                () -> assertThat(dealerResultDto.getMatchResults()).isEqualTo(List.of(WIN, LOSE, LOSE)),
                () -> assertThat(pobiResultDto.getMatchResults()).isEqualTo(List.of(LOSE)),
                () -> assertThat(dolbumResultDto.getMatchResults()).isEqualTo(List.of(WIN)),
                () -> assertThat(richardResultDto.getMatchResults()).isEqualTo(List.of(WIN))
        );
    }

    private Gamblers setupGamblers() {
        Gambler pobi = new Gambler("포비", List.of(KING_HEARTS, TWO_SPADES));
        Gambler dolbum = new Gambler("돌범", List.of(KING_HEARTS, ACE_SPADES));
        Gambler rich = new Gambler("리차드", List.of(ACE_SPADES, TEN_HEARTS));

        return new Gamblers(List.of(pobi, dolbum, rich));
    }
}
