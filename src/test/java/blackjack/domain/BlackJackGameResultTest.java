package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Shape;
import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.GameParticipant;
import blackjack.domain.participants.Hands;
import blackjack.domain.participants.Name;
import blackjack.domain.participants.Player;
import blackjack.domain.participants.BlackJackGameResult;
import blackjack.domain.participants.State;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackJackGameResultTest {

    private List<Player> players;
    private Player siso;
    private Player takan;
    private Dealer dealer;

    @BeforeEach
    void beforeEach() {
        siso = new Player(new Name("시소"));
        takan = new Player(new Name("타칸"));
        dealer = new Dealer();

        Hands sisoHands = new Hands(List.of(
                new Card(Shape.HEART, Rank.JACK),
                new Card(Shape.HEART, Rank.EIGHT))
        ); // 18

        Hands takanHands = new Hands(List.of(
                new Card(Shape.SPADE, Rank.QUEEN),
                new Card(Shape.SPADE, Rank.JACK))
        ); // 20

        siso.receiveHands(sisoHands);
        takan.receiveHands(takanHands);

        players = new ArrayList<>(List.of(siso, takan));
    }

    @Test
    @DisplayName("딜러가 터지고 플레이어가 안 터진 경우 플레이어가 이긴다.")
    void playerWinWithDealerBurstTest() {
        Hands dealerHands = new Hands(List.of(
                new Card(Shape.DIAMOND, Rank.JACK),
                new Card(Shape.DIAMOND, Rank.SIX),
                new Card(Shape.DIAMOND, Rank.SEVEN)
        ));

        dealer.receiveHands(dealerHands);
        BlackJackGameResult blackJackGameResult = new BlackJackGameResult(players, dealer);
        Map<Player, State> gameResult = blackJackGameResult.getGameResult();

        Assertions.assertThat(gameResult.get(siso)).isEqualTo(State.WIN);
        Assertions.assertThat(gameResult.get(takan)).isEqualTo(State.WIN);
    }

    @Test
    @DisplayName("둘 다 터지지 않고 플레이어 점수가 더 높은 경우 플레이어가 이긴다.")
    void playerWinWithScoreTest() {
        Hands dealerHands = new Hands(List.of(
                new Card(Shape.DIAMOND, Rank.JACK),
                new Card(Shape.DIAMOND, Rank.NINE)
        ));

        dealer.receiveHands(dealerHands);
        BlackJackGameResult blackJackGameResult = new BlackJackGameResult(players, dealer);
        Map<Player, State> gameResult = blackJackGameResult.getGameResult();

        Assertions.assertThat(gameResult.get(takan)).isEqualTo(State.WIN);
    }

    @Test
    @DisplayName("딜러와 플레이어가 터진 경우 딜러가 이긴다.")
    void dealerWinWithBothBurstTest() {
        Hands dealerHands = new Hands(List.of(
                new Card(Shape.DIAMOND, Rank.JACK),
                new Card(Shape.DIAMOND, Rank.SIX),
                new Card(Shape.DIAMOND, Rank.SEVEN)
        ));

        Hands sisoHands = new Hands(List.of(
                new Card(Shape.HEART, Rank.JACK),
                new Card(Shape.HEART, Rank.EIGHT),
                new Card(Shape.HEART, Rank.NINE))
        );


        Hands takanHands = new Hands(List.of(
                new Card(Shape.SPADE, Rank.QUEEN),
                new Card(Shape.SPADE, Rank.JACK),
                new Card(Shape.SPADE, Rank.NINE))
        );

        dealer.receiveHands(dealerHands);
        siso.receiveHands(sisoHands);
        takan.receiveHands(takanHands);
        BlackJackGameResult blackJackGameResult = new BlackJackGameResult(players, dealer);
        Map<Player, State> gameResult = blackJackGameResult.getGameResult();

        Assertions.assertThat(gameResult.get(siso)).isEqualTo(State.LOSE);
        Assertions.assertThat(gameResult.get(takan)).isEqualTo(State.LOSE);
    }

    @Test
    @DisplayName("모두 터지지 않고 딜러 점수가 더 높은 경우 딜러가 이긴다.")
    void dealerWinWithScoreTest() {
        Hands dealerHands = new Hands(List.of(
                new Card(Shape.DIAMOND, Rank.JACK),
                new Card(Shape.DIAMOND, Rank.ACE)
        ));

        dealer.receiveHands(dealerHands);
        BlackJackGameResult blackJackGameResult = new BlackJackGameResult(players, dealer);
        Map<Player, State> gameResult = blackJackGameResult.getGameResult();

        Assertions.assertThat(gameResult.get(siso)).isEqualTo(State.LOSE);
        Assertions.assertThat(gameResult.get(takan)).isEqualTo(State.LOSE);
    }

    @Test
    @DisplayName("딜러와 플레이어 점수가 같고 블랙잭이 아닌 경우 딜러가 이긴다.")
    void dealerWinWithNotBlackjackTest() {
        Hands dealerHands = new Hands(List.of(
                new Card(Shape.DIAMOND, Rank.JACK),
                new Card(Shape.DIAMOND, Rank.QUEEN)
        ));

        dealer.receiveHands(dealerHands);
        BlackJackGameResult blackJackGameResult = new BlackJackGameResult(players, dealer);
        Map<Player, State> gameResult = blackJackGameResult.getGameResult();

        Assertions.assertThat(gameResult.get(takan)).isEqualTo(State.LOSE);
    }

    @Test
    @DisplayName("딜러와 플레이어 점수가 같고 블랙잭인 경우 무승부이다.")
    void tieWithBothBlackjackTest() {
        Hands dealerHands = new Hands(List.of(
                new Card(Shape.DIAMOND, Rank.JACK),
                new Card(Shape.DIAMOND, Rank.ACE)
        ));

        Hands takanHands = new Hands(List.of(
                new Card(Shape.SPADE, Rank.QUEEN),
                new Card(Shape.SPADE, Rank.ACE))
        );

        dealer.receiveHands(dealerHands);
        takan.receiveHands(takanHands);
        BlackJackGameResult blackJackGameResult = new BlackJackGameResult(players, dealer);
        Map<Player, State> gameResult = blackJackGameResult.getGameResult();

        Assertions.assertThat(gameResult.get(takan)).isEqualTo(State.TIE);
    }
}
