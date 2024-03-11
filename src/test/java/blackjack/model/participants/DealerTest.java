package blackjack.model.participants;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.model.cards.Card;
import blackjack.model.cards.CardNumber;
import blackjack.model.cards.CardShape;
import blackjack.model.results.DealerResult;
import blackjack.model.results.PlayerResult;
import blackjack.model.results.Result;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DealerTest {

    @DisplayName("카드를 추가발급받는다")
    @Test
    void addCardLessThan() {
        List<Card> given = List.of(
                new Card(CardNumber.SIX, CardShape.HEART),
                new Card(CardNumber.TEN, CardShape.CLOVER)
        );
        Dealer dealer = new Dealer();
        dealer.addCards(given);

        Card card = new Card(CardNumber.ACE, CardShape.DIAMOND);
        dealer.addCard(card);

        assertThat(dealer.getCards().getCards()).hasSize(3);
    }

    @DisplayName("플레이어 게임 결과로 딜러 결과를 결정한다.")
    @ParameterizedTest
    @CsvSource(value = {"1,2,3", "2,3,4", "10,1,9"})
    void getDealerResult(int winCount, int loseCount, int pushCount) {
        PlayerResult playerResult = new PlayerResult(createResults(winCount, loseCount, pushCount));
        Dealer dealer = new Dealer();
        DealerResult dealerResult = dealer.getResult(playerResult);

        assertAll(
                () -> assertThat(dealerResult.getDealerResult().get(Result.WIN)).isEqualTo(loseCount),
                () -> assertThat(dealerResult.getDealerResult().get(Result.LOSE)).isEqualTo(winCount),
                () -> assertThat(dealerResult.getDealerResult().get(Result.PUSH)).isEqualTo(pushCount)
        );
    }

    private Map<Player, Result> createResults(int win, int lose, int push) {
        Map<Player, Result> map = new HashMap<>();
        for (int i = 0; i < win; i++) {
            map.put(new Player("daon" + i), Result.WIN);
        }
        for (int i = 0; i < lose; i++) {
            map.put(new Player("pobi" + i), Result.LOSE);
        }
        for (int i = 0; i < push; i++) {
            map.put(new Player("woni" + i), Result.PUSH);
        }
        return map;
    }
}
