package domain;

import static org.assertj.core.api.SoftAssertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RefereeTest {

    @DisplayName("딜러와 참여자의 카드 합을 비교해 참여자의 승패 여부를 결정한다")
    @Test
    void test1() {
        //given
        Dealer dealer = Dealer.createEmpty();
        dealer.getCards().add(new Card(CardNumberType.JACK, CardType.DIAMOND));
        dealer.getCards().add(new Card(CardNumberType.QUEEN, CardType.DIAMOND));

        Cards winCards = new Cards(List.of(
                new Card(CardNumberType.ACE, CardType.DIAMOND),
                new Card(CardNumberType.QUEEN, CardType.DIAMOND)
        ));
        Cards drawCards = new Cards(List.of(
                new Card(CardNumberType.JACK, CardType.DIAMOND),
                new Card(CardNumberType.QUEEN, CardType.SPACE)
        ));
        Cards loseCards = new Cards(List.of(
                new Card(CardNumberType.FIVE, CardType.DIAMOND),
                new Card(CardNumberType.EIGHT, CardType.DIAMOND)
        ));
        Player winner = new Player("mimi", winCards);
        Player drawer = new Player("wade", drawCards);
        Player loser = new Player("pobi", loseCards);
        Players players = new Players(List.of(winner, drawer, loser));

        Referee referee = new Referee();

        //when
        GameResult gameResult = referee.judge(dealer, players);

        //then
        assertSoftly(softly -> {
            softly.assertThat(gameResult.getGameResultstatus(winner)).isEqualTo(GameResultStatus.WIN);
            softly.assertThat(gameResult.getGameResultstatus(drawer)).isEqualTo(GameResultStatus.DRAW);
            softly.assertThat(gameResult.getGameResultstatus(loser)).isEqualTo(GameResultStatus.LOSE);
        });
    }
}
