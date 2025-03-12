package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class GameResultTest {

    @Test
    @DisplayName("딜러가 이기면 참가자의 배팅 금액을 얻는다")
    void 딜러가_이기면_참가자의_배팅_금액을_얻는다() {
        Card ace = new Card(CardNumber.ACE, CardShape.CLOVER);

        Dealer dealer = new Dealer();
        dealer.addCards(List.of(ace, ace));

        Gambler gambler = new Gambler("비타", 1_000);
        gambler.addCards(List.of(ace));

        GameResults gameResults = new GameResults(dealer, List.of(gambler));

        Map<Player, Integer> result = gameResults.getGameResults();

        assertAll(
                () -> assertThat(result.get(dealer)).isEqualTo(1_000),
                () -> assertThat(result.get(gambler)).isEqualTo(-1_000)
        );
    }

    @Test
    @DisplayName("딜러가 지면 참가자는 배팅 금액의 배로 얻는다")
    void 딜러가_지면_참가자는_배팅_금액의_배로_얻는다() {
        Card ace = new Card(CardNumber.ACE, CardShape.CLOVER);

        Dealer dealer = new Dealer();
        dealer.addCards(List.of(ace));

        Gambler gambler = new Gambler("비타", 1_000);
        gambler.addCards(List.of(ace, ace));

        GameResults gameResults = new GameResults(dealer, List.of(gambler));

        Map<Player, Integer> result = gameResults.getGameResults();

        assertAll(
                () -> assertThat(result.get(dealer)).isEqualTo(-1_000),
                () -> assertThat(result.get(gambler)).isEqualTo(1_000)
        );
    }

    @Test
    @DisplayName("무승부인 경우 각자 최종 수익은 0원이다")
    void 무승부인_경우_각자_최종_수익은_0원이다() {
        Card ace = new Card(CardNumber.ACE, CardShape.CLOVER);

        Dealer dealer = new Dealer();
        dealer.addCards(List.of(ace));

        Gambler gambler = new Gambler("비타", 1_000);
        gambler.addCards(List.of(ace));

        GameResults gameResults = new GameResults(dealer, List.of(gambler));

        Map<Player, Integer> result = gameResults.getGameResults();

        assertAll(
                () -> assertThat(result.get(dealer)).isEqualTo(0),
                () -> assertThat(result.get(gambler)).isEqualTo(0)
        );
    }

    @Test
    @DisplayName("참가자가 블랙잭인 경우 수익은 1.5배이다")
    void 참가자가_블랙잭인_경우_수익은_1_5배이다() {
        Card ace = new Card(CardNumber.ACE, CardShape.CLOVER);
        Card ten = new Card(CardNumber.TEN, CardShape.CLOVER);

        Dealer dealer = new Dealer();
        dealer.addCards(List.of(ace));

        Gambler gambler = new Gambler("비타", 1_000);
        gambler.addCards(List.of(ace, ten));

        GameResults gameResults = new GameResults(dealer, List.of(gambler));

        Map<Player, Integer> result = gameResults.getGameResults();

        assertAll(
                () -> assertThat(result.get(dealer)).isEqualTo(-2_500),
                () -> assertThat(result.get(gambler)).isEqualTo(2_500)
        );
    }
}
