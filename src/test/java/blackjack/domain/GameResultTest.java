package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.game.GameManager;
import blackjack.domain.game.GameResult;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class GameResultTest {

    @Test
    @DisplayName("딜러보다 카드 합이 높으면 WIN을 반환한다")
    void win_if_bigger_than_dealer() {
        Gambler gambler = new Gambler("비타");
        GameManager gameManager = new GameManager(List.of(gambler));
        Dealer dealer = gameManager.getPlayers().getDealer();

        dealer.pushDealCards(List.of(
                new Card(CardNumber.QUEEN, CardShape.CLOVER)
        ));
        gambler.pushDealCards(List.of(
                new Card(CardNumber.QUEEN, CardShape.CLOVER),
                new Card(CardNumber.JACK, CardShape.CLOVER)

        ));

        Assertions.assertThat(GameResult.getGameResult(dealer, gambler)).isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("딜러보다 카드 합이 낮으면 LOSE 을 반환한다")
    void win_if_bigger_than_dealer2() {
        Gambler gambler = new Gambler("두리");
        GameManager gameManager = new GameManager(List.of(gambler));
        Dealer dealer = gameManager.getPlayers().getDealer();

        dealer.pushDealCards(List.of(
                new Card(CardNumber.QUEEN, CardShape.CLOVER),
                new Card(CardNumber.JACK, CardShape.CLOVER)

        ));
        gambler.pushDealCards(List.of(
                new Card(CardNumber.QUEEN, CardShape.CLOVER)
        ));

        Assertions.assertThat(GameResult.getGameResult(dealer, gambler)).isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("딜러와 카드 합이 같으면 DRAW 을 반환한다")
    void win_if_bigger_than_dealer3() {
        Gambler gambler = new Gambler("두리");
        GameManager gameManager = new GameManager(List.of(gambler));
        Dealer dealer = gameManager.getPlayers().getDealer();

        dealer.pushDealCards(List.of(
                new Card(CardNumber.QUEEN, CardShape.CLOVER),
                new Card(CardNumber.JACK, CardShape.CLOVER)

        ));
        gambler.pushDealCards(List.of(
                new Card(CardNumber.QUEEN, CardShape.CLOVER),
                new Card(CardNumber.JACK, CardShape.CLOVER)
        ));

        Assertions.assertThat(GameResult.getGameResult(dealer, gambler)).isEqualTo(GameResult.DRAW);
    }

    @Test
    @DisplayName("딜러와 참가자가 둘다 bust 면 LOSE 를 반환한다")
    void both_bust_test() {
        Gambler gambler = new Gambler("두리");
        GameManager gameManager = new GameManager(List.of(gambler));
        Dealer dealer = gameManager.getPlayers().getDealer();

        dealer.pushDealCards(List.of(
                new Card(CardNumber.QUEEN, CardShape.CLOVER),
                new Card(CardNumber.JACK, CardShape.CLOVER),
                new Card(CardNumber.JACK, CardShape.DIAMOND)

        ));
        gambler.pushDealCards(List.of(
                new Card(CardNumber.QUEEN, CardShape.CLOVER),
                new Card(CardNumber.JACK, CardShape.CLOVER),
                new Card(CardNumber.JACK, CardShape.DIAMOND)
        ));

        Assertions.assertThat(GameResult.getGameResult(dealer, gambler)).isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("참가자만 bust 면 LOSE 를 반환한다")
    void both_bust_test2() {
        Gambler gambler = new Gambler("두리");
        GameManager gameManager = new GameManager(List.of(gambler));
        Dealer dealer = gameManager.getPlayers().getDealer();

        dealer.pushDealCards(List.of(
                new Card(CardNumber.QUEEN, CardShape.CLOVER),
                new Card(CardNumber.JACK, CardShape.CLOVER)

        ));
        gambler.pushDealCards(List.of(
                new Card(CardNumber.QUEEN, CardShape.CLOVER),
                new Card(CardNumber.JACK, CardShape.CLOVER),
                new Card(CardNumber.JACK, CardShape.DIAMOND)
        ));

        Assertions.assertThat(GameResult.getGameResult(dealer, gambler)).isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("딜러만 bust 면 WIN 를 반환한다")
    void both_bust_test3() {
        Gambler gambler = new Gambler("두리");
        GameManager gameManager = new GameManager(List.of(gambler));
        Dealer dealer = gameManager.getPlayers().getDealer();

        dealer.pushDealCards(List.of(
                new Card(CardNumber.QUEEN, CardShape.CLOVER),
                new Card(CardNumber.JACK, CardShape.CLOVER),
                new Card(CardNumber.JACK, CardShape.DIAMOND)

        ));
        gambler.pushDealCards(List.of(
                new Card(CardNumber.QUEEN, CardShape.CLOVER),
                new Card(CardNumber.JACK, CardShape.CLOVER)
        ));

        Assertions.assertThat(GameResult.getGameResult(dealer, gambler)).isEqualTo(GameResult.WIN);
    }
}
