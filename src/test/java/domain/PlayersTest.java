package domain;

import static domain.HandsTestFixture.bustHands;
import static domain.HandsTestFixture.noBustHands;
import static domain.HandsTestFixture.sum18Size2;
import static domain.HandsTestFixture.sum20Size2;
import static domain.HandsTestFixture.sum20Size3;
import static domain.HandsTestFixture.sum21Size2;
import static domain.Result.LOSE;
import static domain.Result.TIE;
import static domain.Result.WIN;
import static domain.Result.WIN_BLACKJACK;

import domain.card.CardDeck;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Player;
import domain.participant.Players;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

class PlayersTest {

    @Test
    @DisplayName("참가자 중 버스트 되지 않은 참가자가 있다면 isAllBust가 False를 반환한다.")
    void isAllBustFalse() {
        //given
        final Player bustPlayer = new Player(new Name("레디"), bustHands);
        final Player noBustPlayer = new Player(new Name("제제"), noBustHands);
        final Players players = new Players(List.of(bustPlayer, noBustPlayer));

        //when && then
        Assertions.assertThat(players.isAllBust()).isFalse();
    }

    @Test
    @DisplayName("모든 참가자가 버스트되면 isAllBust가 True를 반환한다.")
    void isAllBustTrue() {
        //given
        final Player bustPlayer1 = new Player(new Name("레디"), bustHands);
        final Player bustPlayer2 = new Player(new Name("제제"), bustHands);
        final Player bustPlayer3 = new Player(new Name("수달"), bustHands);
        final Player bustPlayer4 = new Player(new Name("피케이"), bustHands);

        Players players = new Players(List.of(bustPlayer1, bustPlayer2, bustPlayer3, bustPlayer4));

        //when && then
        Assertions.assertThat(players.isAllBust()).isTrue();
    }

    @Test
    @DisplayName("참여자의 승패무를 판단한다.")
    void playerResult() {
        //given
        final Player loser = new Player(new Name("레디"), sum18Size2);
        final Player winner = new Player(new Name("제제"), sum21Size2);
        final Player tier = new Player(new Name("수달"), sum20Size3);

        final Players players = new Players(List.of(loser, winner, tier));
        final Dealer dealer = new Dealer(CardDeck.generate(), sum20Size3);

        //when & then
        final Map<Player, Result> expected = Map.of(loser, LOSE, winner, WIN_BLACKJACK, tier, TIE);
        Assertions.assertThat(players.calculateResultBy(dealer)).isEqualTo(expected);
    }

    @Test
    @DisplayName("딜러가 버스트일때 참여자가 버스트가 아니면 WIN")
    void all() {
        //given
        final Dealer bustDealer = new Dealer(CardDeck.generate(), bustHands);
        final Player winner1 = new Player(new Name("레디"), sum18Size2);
        final Player winner2 = new Player(new Name("브라운"), sum20Size2);
        final Player loser = new Player(new Name("제제"), bustHands);

        final Players players = new Players(List.of(winner1, winner2, loser));

        //when
        final Map<Player, Result> expectedPlayerResult = Map.of(winner1, WIN, winner2, WIN, loser, LOSE);
        final Map<Result, Integer> expectedDealerResult = Map.of(WIN, 1, LOSE, 2);

        //then
        Assertions.assertThat(players.calculateResultBy(bustDealer)).isEqualTo(expectedPlayerResult);
        Assertions.assertThat(bustDealer.calculateResultBy(players)).isEqualTo(expectedDealerResult);
    }

    static Stream<Arguments> invalidPlayersSizeParameterProvider() {
        return Stream.of(
                Arguments.of(
                        List.of("pobi")
                ),
                Arguments.of(
                        List.of("1", "2", "3", "4", "5", "6", "7", "8", "9")
                )
        );
    }
}
