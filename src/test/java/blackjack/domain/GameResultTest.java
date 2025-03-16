package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPack;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.Cards;
import blackjack.domain.game.GameManager;
import blackjack.domain.game.GameResult;
import blackjack.domain.player.BetAmount;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.PlayerName;
import blackjack.domain.player.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GameResultTest {

    @Test
    @DisplayName("딜러보다 카드 합이 높으면 WIN을 반환한다")
    void win_if_bigger_than_dealer() {
        Gambler gambler = new Gambler(new PlayerName("비타"), new BetAmount(0), new Cards());
        Players players = new Players(new Dealer(new Cards()), List.of(gambler));
        GameManager gameManager = new GameManager(
                CardPack.createShuffled(),
                players);
        Dealer dealer = gameManager.getPlayers().getDealer();

        dealer.pushDealCards(List.of(
                new Card(CardNumber.QUEEN, CardShape.CLOVER)
        ));
        gambler.pushDealCards(List.of(
                new Card(CardNumber.QUEEN, CardShape.CLOVER),
                new Card(CardNumber.JACK, CardShape.CLOVER)

        ));

        assertThat(GameResult.getGameResult(dealer, gambler)).isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("딜러보다 카드 합이 낮으면 LOSE 을 반환한다")
    void win_if_bigger_than_dealer2() {
        Gambler gambler = new Gambler(new PlayerName("두리"), new BetAmount(0), new Cards());
        Players players = new Players(new Dealer(new Cards()), List.of(gambler));
        GameManager gameManager = new GameManager(CardPack.createShuffled(), players);
        Dealer dealer = gameManager.getPlayers().getDealer();

        dealer.pushDealCards(List.of(
                new Card(CardNumber.QUEEN, CardShape.CLOVER),
                new Card(CardNumber.JACK, CardShape.CLOVER)

        ));
        gambler.pushDealCards(List.of(
                new Card(CardNumber.QUEEN, CardShape.CLOVER)
        ));

        assertThat(GameResult.getGameResult(dealer, gambler)).isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("딜러와 카드 합이 같으면 DRAW 을 반환한다")
    void win_if_bigger_than_dealer3() {
        Gambler gambler = new Gambler(new PlayerName("두리"), new BetAmount(0), new Cards());
        Players players = new Players(new Dealer(new Cards()), List.of(gambler));
        GameManager gameManager = new GameManager(CardPack.createShuffled(), players);
        Dealer dealer = gameManager.getPlayers().getDealer();

        dealer.pushDealCards(List.of(
                new Card(CardNumber.QUEEN, CardShape.CLOVER),
                new Card(CardNumber.JACK, CardShape.CLOVER)

        ));
        gambler.pushDealCards(List.of(
                new Card(CardNumber.QUEEN, CardShape.CLOVER),
                new Card(CardNumber.JACK, CardShape.CLOVER)
        ));

        assertThat(GameResult.getGameResult(dealer, gambler)).isEqualTo(GameResult.DRAW);
    }

    @Test
    @DisplayName("딜러와 참가자가 둘다 bust 면 LOSE 를 반환한다")
    void both_bust_test() {
        Gambler gambler = new Gambler(new PlayerName("두리"), new BetAmount(0), new Cards());
        Players players = new Players(new Dealer(new Cards()), List.of(gambler));
        GameManager gameManager = new GameManager(CardPack.createShuffled(), players);
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

        assertThat(GameResult.getGameResult(dealer, gambler)).isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("참가자만 bust 면 LOSE 를 반환한다")
    void both_bust_test2() {
        Gambler gambler = new Gambler(new PlayerName("두리"), new BetAmount(0), new Cards());
        Players players = new Players(new Dealer(new Cards()), List.of(gambler));
        GameManager gameManager = new GameManager(CardPack.createShuffled(), players);
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

        assertThat(GameResult.getGameResult(dealer, gambler)).isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("딜러만 bust 면 WIN 를 반환한다")
    void both_bust_test3() {
        Gambler gambler = new Gambler(new PlayerName("두리"), new BetAmount(0), new Cards());
        Players players = new Players(new Dealer(new Cards()), List.of(gambler));
        GameManager gameManager = new GameManager(CardPack.createShuffled(), players);
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

        assertThat(GameResult.getGameResult(dealer, gambler)).isEqualTo(GameResult.WIN);
    }
}
