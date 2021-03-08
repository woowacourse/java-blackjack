package blackjack.domain.participant;

import blackjack.domain.card.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

class PlayersTest {
    private static final List<Card> CARDS_BLACKJACK = Arrays.asList(
            new Card(Symbol.JACK, Shape.HEART),
            new Card(Symbol.ACE, Shape.DIAMOND)
    );
    private static final List<Card> CARDS_SCORE_20 = Arrays.asList(
            new Card(Symbol.ACE, Shape.HEART),
            new Card(Symbol.KING, Shape.HEART),
            new Card(Symbol.NINE, Shape.HEART)
    );
    private static final List<Card> CARDS_SCORE_19 = Arrays.asList(
            new Card(Symbol.ACE, Shape.HEART),
            new Card(Symbol.KING, Shape.HEART),
            new Card(Symbol.EIGHT, Shape.HEART)
    );

    @DisplayName("플레이어는 최소 1명 최대 7명은 있어야 한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 8})
    void cannotMakePlayers(int playerCounts) {
        List<String> playerNames = new ArrayList<>();
        List<Integer> dummyBettingMoneys = new ArrayList<>();
        for (int i = 0; i < playerCounts; i++) {
            playerNames.add("test" + i);
            dummyBettingMoneys.add(10);
        }

        assertThatCode(() -> Players.of(playerNames, dummyBettingMoneys))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("게임 참가자의 수는 딜러 제외 최소 1명 최대 7명입니다.");
    }

    @DisplayName("플레이어 그룹 생성시 입력된 이름의 개수와 배팅 금액의 개수가 같아야 한다.")
    @Test
    void cannotMakePlayersWhenCountsAreInvalid() {
        List<String> playerNames = Arrays.asList("a1", "a2");
        List<Integer> bettingMoneys = Arrays.asList(1, 2, 3);

        assertThatCode(() -> Players.of(playerNames, bettingMoneys))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력된 참가자의 이름 개수와 배팅 금액 개수가 일치하지 않습니다.");
    }

    @DisplayName("참가자들의 이름은 중복이 없어야 한다.")
    @Test
    void validateOverlappedNames() {
        List<String> duplicatedPlayers = Arrays.asList("jason", "jason");
        List<Integer> dummyBettingMoneys = Arrays.asList(1, 2);

        assertThatCode(() -> Players.of(duplicatedPlayers, dummyBettingMoneys))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("참가자들의 이름은 중복이 없어야 합니다.");
    }

    @DisplayName("플레이어들이 카드를 2장씩 받는다.")
    @Test
    void receiveDefaultCards() {
        CardDeck cardDeck = new CardDeck(CardsGenerator.generateShuffledCards());
        Players players = Players.of(Arrays.asList("jason"), Arrays.asList(1000));
        Participant jason = players.getPlayers()
                .get(0);

        players.receiveDefaultCards(cardDeck);

        assertThat(jason.getCards()).hasSize(2);
    }

    @DisplayName("참가자들과 딜러의 점수를 비교하고 각 참가자별로 수익(손실)금액을 Map에 집계한다")
    @Test
    void aggregateProfitMoneyByPlayerName() {
        Players players = Players.of(Arrays.asList("pobi", "jason"), Arrays.asList(1000, 2000));
        Dealer dealer = new Dealer();
        List<Player> playerList = players.getPlayers();
        Player pobi = playerList.get(0);
        Player jason = playerList.get(1);

        pobi.receiveCards(new Cards(CARDS_BLACKJACK));
        jason.receiveCards(new Cards(CARDS_SCORE_19));
        dealer.receiveCards(new Cards(CARDS_SCORE_20));
        Map<Player, Integer> profitStatistics = players.aggregateProfitMoneyByPlayer2(dealer);

        assertThat(profitStatistics.keySet()).containsExactly(pobi, jason);
        assertThat(profitStatistics.values()).containsExactly(1500, -2000);
    }
}
