package domain.participant;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

import domain.card.Card;
import domain.card.CardNumberType;
import domain.card.CardType;
import domain.card.Hand;
import domain.result.GameResult;
import domain.result.GameResultStatus;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @DisplayName("플레이어의 이름들로 플레이어를 모두 생성한다")
    @Test
    void test1() {
        //given
        List<String> playerNames = List.of("mimi", "wade", "pobi");

        //when
        Players players = Players.createByNames(playerNames);

        //then
        Assertions.assertThat(players.getPlayers()).containsExactlyInAnyOrderElementsOf(
                List.of(
                        new Player("mimi", Hand.createEmpty()),
                        new Player("wade", Hand.createEmpty()),
                        new Player("pobi", Hand.createEmpty())
                )
        );
    }

    @DisplayName("딜러와 참여자의 카드 합을 비교해 참여자의 승패 여부를 결정한다")
    @Test
    void test2() {
        //given
        Dealer dealer = Dealer.createEmpty();
        dealer.getCards().add(new Card(CardNumberType.JACK, CardType.DIAMOND));
        dealer.getCards().add(new Card(CardNumberType.QUEEN, CardType.DIAMOND));

        Hand winHand = new Hand(List.of(
                new Card(CardNumberType.ACE, CardType.DIAMOND),
                new Card(CardNumberType.QUEEN, CardType.DIAMOND)
        ));
        Hand drawHand = new Hand(List.of(
                new Card(CardNumberType.JACK, CardType.DIAMOND),
                new Card(CardNumberType.QUEEN, CardType.SPACE)
        ));
        Hand loseHand = new Hand(List.of(
                new Card(CardNumberType.FIVE, CardType.DIAMOND),
                new Card(CardNumberType.EIGHT, CardType.DIAMOND)
        ));
        Player winner = new Player("mimi", winHand);
        Player drawer = new Player("wade", drawHand);
        Player loser = new Player("pobi", loseHand);
        Players players = Players.create(List.of(winner, drawer, loser));

        //when
        GameResult gameResult = players.judgeAgainstDealer(dealer);

        //then
        assertSoftly(softly -> {
            softly.assertThat(gameResult.getGameResultstatus(winner)).isEqualTo(GameResultStatus.WIN);
            softly.assertThat(gameResult.getGameResultstatus(loser)).isEqualTo(GameResultStatus.LOSE);
        });
    }
}
