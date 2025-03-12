package blackjack.domain;

import blackjack.domain.card.CardPack;
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

    private static final CardPack SORT_CARD_PACK = new CardPack(new SortedBlackjackShuffle());
    private static final CardPack REVERSE_SORT_CARD_PACK = new CardPack(new ReversedBlackjackShuffle());

    @Test
    @DisplayName("딜러가 이기면 참가자의 배팅 금액을 얻는다")
    void 딜러가_이기면_참가자의_배팅_금액을_얻는다() {
        Dealer dealer = new Dealer();
        dealer.pushDealCard(SORT_CARD_PACK, 2);

        Gambler gambler = new Gambler("비타", 1_000);
        gambler.pushDealCard(SORT_CARD_PACK, 1);

        List<Gambler> gamblers = List.of(gambler);

        GameResults gameResults = new GameResults(dealer, gamblers);

        Map<Player, Integer> result = gameResults.getGameResults();

        assertAll(
                () -> assertThat(result.get(dealer)).isEqualTo(1_000),
                () -> assertThat(result.get(gambler)).isEqualTo(-1_000)
        );
    }

    @Test
    @DisplayName("딜러가 지면 참가자는 배팅 금액의 배로 얻는다")
    void 딜러가_지면_참가자는_배팅_금액의_배로_얻는다() {
        Dealer dealer = new Dealer();
        dealer.pushDealCard(SORT_CARD_PACK, 1);

        Gambler gambler = new Gambler("비타", 1_000);
        gambler.pushDealCard(SORT_CARD_PACK, 2);

        List<Gambler> gamblers = List.of(gambler);

        GameResults gameResults = new GameResults(dealer, gamblers);

        Map<Player, Integer> result = gameResults.getGameResults();

        assertAll(
                () -> assertThat(result.get(dealer)).isEqualTo(-1_000),
                () -> assertThat(result.get(gambler)).isEqualTo(1_000)
        );
    }

    @Test
    @DisplayName("무승부인 경우 각자 최종 수익은 0원이다")
    void 무승부인_경우_각자_최종_수익은_0원이다() {
        Dealer dealer = new Dealer();
        dealer.pushDealCard(SORT_CARD_PACK, 2);

        Gambler gambler = new Gambler("비타", 1_000);
        gambler.pushDealCard(SORT_CARD_PACK, 2);

        List<Gambler> gamblers = List.of(gambler);

        GameResults gameResults = new GameResults(dealer, gamblers);

        Map<Player, Integer> result = gameResults.getGameResults();

        assertAll(
                () -> assertThat(result.get(dealer)).isEqualTo(0),
                () -> assertThat(result.get(gambler)).isEqualTo(0)
        );
    }
}
