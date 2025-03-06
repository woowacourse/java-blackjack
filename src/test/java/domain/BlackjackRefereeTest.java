package domain;

import static org.assertj.core.api.SoftAssertions.*;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackjackRefereeTest {

    @DisplayName("딜러와 참여자의 카드 합을 비교해 참여자의 승패 여부를 결정한다")
    @Test
    void test1() {
        //given
        Participant dealer = Participant.createDealer();
        dealer.cards().add(new Card(CardNumberType.JACK, CardType.DIAMOND));
        dealer.cards().add(new Card(CardNumberType.QUEEN, CardType.DIAMOND));

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
        Participant winner = new Participant("mimi", winCards, Role.PLAYER);
        Participant drawer = new Participant("wade", drawCards, Role.PLAYER);
        Participant loser = new Participant("pobi", loseCards, Role.PLAYER);
        List<Participant> participants = List.of(winner, drawer, loser);

        BlackjackReferee blackjackReferee = new BlackjackReferee();

        //when
        GameResult gameResult = blackjackReferee.judge(dealer, participants);

        //then
        assertSoftly(softly -> {
            softly.assertThat(gameResult.getGameResultstatus(winner)).isEqualTo(GameResultStatus.WIN);
            softly.assertThat(gameResult.getGameResultstatus(drawer)).isEqualTo(GameResultStatus.DRAW);
            softly.assertThat(gameResult.getGameResultstatus(loser)).isEqualTo(GameResultStatus.LOSE);
        });
    }
}
