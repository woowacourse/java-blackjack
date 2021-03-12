package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardLetter;
import blackjack.domain.card.CardSuit;
import blackjack.domain.result.Result;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class PlayersTest {
    @Test
    @DisplayName("Players가 생성자로 정상 생성되는지 테스트")
    void initConstructor() {
        assertThatCode(() -> new Players(Arrays.asList(
                new Player("joel"),
                new Player("bada"),
                new Player("jon"))))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Players가 정적 팩터리 메서드로 정상 생성되는지 테스트")
    void initFactoryMethod() {
        List<String> playerName = Arrays.asList("joel", "bada", "jon");
        assertThatCode(() -> Players.of(playerName))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Players에 중복된 이름이 입력되면 에러가 발생한다")
    void duplicateNameInit() {
        assertThatThrownBy(() -> new Players(Arrays.asList(
                new Player("joel"),
                new Player("joel")
        ))).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력된 플레이어의 이름이 중복됩니다.");

        List<String> playerName = Arrays.asList("joel", "joel");
        assertThatThrownBy(() -> Players.of(playerName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력된 플레이어의 이름이 중복됩니다.");
    }

    @Test
    @DisplayName("플레이어는 최대 8명까지 허용한다")
    void maxPlayerInit() {
        List<String> playerName = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");
        assertThatThrownBy(() -> Players.of(playerName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어는 최대 8명까지 허용합니다.");
    }

    @Test
    @DisplayName("플레이어의 이름, 플레이어의 베팅 금액을 동시에 입력받는 정적 팩토리 메서드 테스트")
    void initFactoryMethodWithTwoParameter() {
        List<String> playerName = Arrays.asList("joel", "bada", "jon");
        List<Integer> playerMoney = Arrays.asList(1000, 2000, 3000);
        assertThatCode(() -> Players.of(playerName, playerMoney))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("플레이어의 베팅 금액에 0 미만인 값이 포함되어 있으면 에러가 발생한다")
    void belowZeroMoneyInit() {
        List<String> playerName = Arrays.asList("joel", "bada", "jon");
        List<Integer> playerMoney = Arrays.asList(1000, 2000, -3000);
        assertThatThrownBy(() -> Players.of(playerName, playerMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("베팅 금액은 0 이상이여야 합니다.");
    }

    @Test
    @DisplayName("플레이어의 이름, 플레이어의 베팅 금액을 담은 리스트의 크기가 다르면 에러가 발생한다")
    void listSizeDifferenceInit() {
        List<String> playerName = Arrays.asList("joel", "bada", "jon");
        List<Integer> playerMoney = Arrays.asList(1000, 2000);
        assertThatThrownBy(() -> Players.of(playerName, playerMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력 받은 플레이어의 수와 베팅 금액의 수가 같지 않습니다");
    }

    @Test
    @DisplayName("Players receiveInitialCard 테스트")
    void receiveInitialCard() {
        final CardDeck cardDeck = new CardDeck();
        List<String> playerName = Arrays.asList("joel", "bada", "jon");
        final Players players = Players.of(playerName);

        players.receiveInitialCard(cardDeck);

        for (Player player : players.getPlayers()) {
            assertThat(player.getHand().getCards()).hasSize(2);
        }
    }

    @Test
    @DisplayName("모든 플레이어에 대해 Dealer와의 결과를 조회한다.")
    void generateEveryPlayerResult() {
        Player player1 = new Player("joel");
        Player player2 = new Player("bada");
        Player player3 = new Player("j.on");
        Player player4 = new Player("blackjack");

        player1.receiveAdditionalCard(new Card(CardLetter.TEN, CardSuit.CLOVER));
        player1.receiveAdditionalCard(new Card(CardLetter.JACK, CardSuit.CLOVER));

        player2.receiveAdditionalCard(new Card(CardLetter.EIGHT, CardSuit.HEART));
        player2.receiveAdditionalCard(new Card(CardLetter.NINE, CardSuit.HEART));

        player3.receiveAdditionalCard(new Card(CardLetter.TWO, CardSuit.DIAMOND));
        player3.receiveAdditionalCard(new Card(CardLetter.THREE, CardSuit.DIAMOND));

        player4.receiveAdditionalCard(new Card(CardLetter.ACE, CardSuit.CLOVER));
        player4.receiveAdditionalCard(new Card(CardLetter.JACK, CardSuit.CLOVER));

        final Players players = new Players(Arrays.asList(player1, player2, player3, player4));

        Dealer dealer = new Dealer();
        dealer.receiveAdditionalCard(new Card(CardLetter.EIGHT, CardSuit.SPADE));
        dealer.receiveAdditionalCard(new Card(CardLetter.NINE, CardSuit.SPADE));

        final Map<Player, Result> allPlayerResult = players.generateEveryPlayerResult(dealer);
        assertThat(allPlayerResult.get(player1)).isEqualTo(Result.WIN);
        assertThat(allPlayerResult.get(player2)).isEqualTo(Result.DRAW);
        assertThat(allPlayerResult.get(player3)).isEqualTo(Result.LOSE);
        assertThat(allPlayerResult.get(player4)).isEqualTo(Result.BLACKJACK_WIN);
    }

    @Test
    @DisplayName("모든 플레이어에 수익률을 조회한다.")
    void generateEveryPlayerProfit() {
        Player player1 = new Player("joel", new PlayerMoney(1000));
        Player player2 = new Player("bada", new PlayerMoney(1000));
        Player player3 = new Player("j.on", new PlayerMoney(1000));
        Player player4 = new Player("blackjack", new PlayerMoney(1000));

        player1.receiveAdditionalCard(new Card(CardLetter.TEN, CardSuit.CLOVER));
        player1.receiveAdditionalCard(new Card(CardLetter.JACK, CardSuit.CLOVER));

        player2.receiveAdditionalCard(new Card(CardLetter.EIGHT, CardSuit.HEART));
        player2.receiveAdditionalCard(new Card(CardLetter.NINE, CardSuit.HEART));

        player3.receiveAdditionalCard(new Card(CardLetter.TWO, CardSuit.DIAMOND));
        player3.receiveAdditionalCard(new Card(CardLetter.THREE, CardSuit.DIAMOND));

        player4.receiveAdditionalCard(new Card(CardLetter.ACE, CardSuit.CLOVER));
        player4.receiveAdditionalCard(new Card(CardLetter.JACK, CardSuit.CLOVER));

        final Players players = new Players(Arrays.asList(player1, player2, player3, player4));

        Dealer dealer = new Dealer();
        dealer.receiveAdditionalCard(new Card(CardLetter.EIGHT, CardSuit.SPADE));
        dealer.receiveAdditionalCard(new Card(CardLetter.NINE, CardSuit.SPADE));

        final Map<Player, Double> allPlayerProfit = players.generateEveryPlayerProfit(dealer);
        assertThat(allPlayerProfit.get(player1)).isEqualTo(1000);
        assertThat(allPlayerProfit.get(player2)).isEqualTo(0);
        assertThat(allPlayerProfit.get(player3)).isEqualTo(-1000);
        assertThat(allPlayerProfit.get(player4)).isEqualTo(1500);
    }
}
