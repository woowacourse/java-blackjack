package blackjack.domain;

import blackjack.domain.card.CardPack;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameResultTest {
    @Test
    @DisplayName("딜러보다 카드 합이 높으면 WIN을 반환한다")
    void win_if_bigger_than_dealer() {
        CardPack cardPack = new CardPack(new ReversedBlackjackShuffle());
        Dealer dealer = new Dealer();
        Player gambler = new Gambler("두리");

        dealer.pushDealCard(cardPack, 1);
        gambler.pushDealCard(cardPack, 2);

        assertThat(GameResult.evaluateGameResult(dealer, gambler))
                .isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("딜러보다 카드 합이 낮으면 LOSE 을 반환한다")
    void win_if_bigger_than_dealer2() {
        CardPack cardPack = new CardPack(new ReversedBlackjackShuffle());
        Dealer dealer = new Dealer();
        Player gambler = new Gambler("두리");

        dealer.pushDealCard(cardPack, 2);
        gambler.pushDealCard(cardPack, 1);

        assertThat(GameResult.evaluateGameResult(dealer, gambler))
                .isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("딜러와 카드 합이 같으면 DRAW 을 반환한다")
    void win_if_bigger_than_dealer3() {
        CardPack cardPack = new CardPack(new ReversedBlackjackShuffle());
        Dealer dealer = new Dealer();
        Player gambler = new Gambler("두리");

        dealer.pushDealCard(cardPack, 1);
        gambler.pushDealCard(cardPack, 1);

        assertThat(GameResult.evaluateGameResult(dealer, gambler))
                .isEqualTo(GameResult.DRAW);
    }

    @Test
    @DisplayName("딜러와 참가자가 둘다 bust 면 DRAW 를 반환한다")
    void both_bust_test() {
        CardPack cardPack = new CardPack(new SortedBlackjackShuffle());
        Dealer dealer = new Dealer();
        Player gambler = new Gambler("두리");

        dealer.pushDealCard(cardPack, 3); // 10, 10, 10 총합 30
        gambler.pushDealCard(cardPack, 3);

        assertThat(GameResult.evaluateGameResult(dealer, gambler))
                .isEqualTo(GameResult.DRAW);
    }

    @Test
    @DisplayName("참가자만 bust 면 LOSE 를 반환한다")
    void both_bust_test2() {
        CardPack cardPack = new CardPack(new SortedBlackjackShuffle());
        Dealer dealer = new Dealer();
        Player gambler = new Gambler("두리");

        dealer.pushDealCard(cardPack, 1);
        gambler.pushDealCard(cardPack, 3); // 10, 10, 10 총합 30

        assertThat(GameResult.evaluateGameResult(dealer, gambler))
                .isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("딜러만 bust 면 WIN 를 반환한다")
    void both_bust_test3() {
        CardPack cardPack = new CardPack(new SortedBlackjackShuffle());
        Dealer dealer = new Dealer();
        Player gambler = new Gambler("두리");

        dealer.pushDealCard(cardPack, 3); // 10, 10, 10 총합 30
        gambler.pushDealCard(cardPack, 1);

        assertThat(GameResult.evaluateGameResult(dealer, gambler))
                .isEqualTo(GameResult.WIN);
    }
}
