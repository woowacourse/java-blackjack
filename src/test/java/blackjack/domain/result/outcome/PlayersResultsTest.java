package blackjack.domain.result.outcome;

import blackjack.domain.card.Card;
import blackjack.domain.card.component.Figure;
import blackjack.domain.card.component.Type;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.participant.PlayersFactory;
import blackjack.domain.participant.attribute.Name;
import blackjack.domain.result.ResultType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static blackjack.domain.card.Card.NULL_ERR_MSG;
import static blackjack.domain.result.ResultType.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayersResultsTest {
    @DisplayName("플레이어들의 결과 생성 확인")
    @Test
    void test4() {
        Dealer dealer = new Dealer();
        dealer.addCard(Card.of(Type.FIVE, Figure.HEART));

        List<Name> names = Arrays.asList("포비", "쪼밀리", "타미").stream()
                .map(Name::new)
                .collect(Collectors.toList());

        Players<Player> players = PlayersFactory.createPlayers(names);
        List<Player> playersList = players.getPlayers();
        playersList.get(0).addCard(Card.of(Type.FOUR, Figure.SPADE));
        playersList.get(1).addCard(Card.of(Type.FIVE, Figure.SPADE));
        playersList.get(2).addCard(Card.of(Type.SIX, Figure.SPADE));

        List<PlayerResult> expectedResult = Arrays.asList(
                new PlayerResult(playersList.get(0).getName(), ResultType.LOSE),
                new PlayerResult(playersList.get(1).getName(), ResultType.DRAW),
                new PlayerResult(playersList.get(2).getName(), ResultType.WIN));

        PlayersResults actualResult = new PlayersResults(players, dealer);

        assertThat(actualResult).isEqualTo(new PlayersResults(expectedResult));
    }

    @DisplayName("예외 테스트: 생성자에 null 값 전달된 경우 Exception 발생")
    @Test
    void test1() {
        assertThatThrownBy(() -> new PlayersResults(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining(NULL_ERR_MSG);
    }

    @DisplayName("딜러 게임 결과 확인")
    @Test
    void test2() {
        List<PlayerResult> results = new ArrayList<>();
        results.add(new PlayerResult(new Name("포비"), LOSE));
        results.add(new PlayerResult(new Name("쪼밀리"), LOSE));
        results.add(new PlayerResult(new Name("타미"), LOSE));
        results.add(new PlayerResult(new Name("CU"), WIN));


        PlayersResults playersResults = new PlayersResults(results);

        Map<ResultType, Long> dealerResult = playersResults.computeDealerResult();

        assertThat(dealerResult.get(WIN)).isEqualTo(3);
        assertThat(dealerResult.get(LOSE)).isEqualTo(1);
        assertThat(dealerResult.get(DRAW)).isEqualTo(null);
    }
}
