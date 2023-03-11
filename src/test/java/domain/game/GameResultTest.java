package domain.game;

import domain.CardFixtures;
import domain.card.CardNumber;
import domain.user.CardPool;
import domain.user.Dealer;
import domain.user.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class GameResultTest {

    @Test
    @DisplayName("Player가 Dealer보다 값이 작으면 LOSE이다")
    void makePlayerRecordWhenPlayerLose() {
        CardPool playerCardPool = new CardPool(
                List.of(CardFixtures.ofNumber(CardNumber.FIVE))
        );
        CardPool dealerCardPool = new CardPool(
                List.of(CardFixtures.ofNumber(CardNumber.EIGHT))
        );

        Player player = new Player("플레이어", playerCardPool, 0);
        Dealer dealer = new Dealer(dealerCardPool);

        assertThat(GameResult.makePlayerRecord(player, dealer))
                .isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("Player가 Dealer와 값이 같으면 DRAW이다")
    void makePlayerrRecordWhenDraw() {
        CardPool playerCardPool = new CardPool(
                List.of(CardFixtures.ofNumber(CardNumber.FIVE))
        );
        CardPool dealerCardPool = new CardPool(
                List.of(CardFixtures.ofNumber(CardNumber.FIVE))
        );

        Player player = new Player("플레이어", playerCardPool, 0);
        Dealer dealer = new Dealer(dealerCardPool);

        assertThat(GameResult.makePlayerRecord(player, dealer))
                .isEqualTo(GameResult.DRAW);
    }

    @Test
    @DisplayName("Player가 Dealer와 값이 크면 WIN이다")
    void makePlayerRecordWhenPlayerWin() {
        CardPool playerCardPool = new CardPool(
                List.of(CardFixtures.ofNumber(CardNumber.SIX))
        );
        CardPool dealerCardPool = new CardPool(
                List.of(CardFixtures.ofNumber(CardNumber.FIVE))
        );

        Player player = new Player("플레이어", playerCardPool, 0);
        Dealer dealer = new Dealer(dealerCardPool);

        assertThat(GameResult.makePlayerRecord(player, dealer))
                .isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("Player가 블랙잭이고, 딜러는 아니라면 WIN이다")
    void makePlayerRecordWhenBlackjackPlayerWin() {
        CardPool playerCardPool = new CardPool(
                List.of(CardFixtures.ofNumber(CardNumber.SIX),
                        CardFixtures.ofNumber(CardNumber.ACE),
                        CardFixtures.ofNumber(CardNumber.FOUR)
                )
        );
        CardPool dealerCardPool = new CardPool(
                List.of(CardFixtures.ofNumber(CardNumber.FIVE))
        );

        Player player = new Player("플레이어", playerCardPool, 0);
        Dealer dealer = new Dealer(dealerCardPool);

        assertThat(GameResult.makePlayerRecord(player, dealer))
                .isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("Dealer가 블랙잭이고, Player는 아니라면 LOSE이다")
    void makePlayerRecordWhenBlackjackPlayerLose() {
        CardPool playerCardPool = new CardPool(
                List.of(CardFixtures.ofNumber(CardNumber.SIX))
        );
        CardPool dealerCardPool = new CardPool(
                List.of(CardFixtures.ofNumber(CardNumber.SIX),
                        CardFixtures.ofNumber(CardNumber.ACE),
                        CardFixtures.ofNumber(CardNumber.FOUR)
                )
        );

        Player player = new Player("플레이어", playerCardPool, 0);
        Dealer dealer = new Dealer(dealerCardPool);

        assertThat(GameResult.makePlayerRecord(player, dealer))
                .isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("Dealer는 Player가 이기면 1패가 적립된다.")
    void makeDealerRecordWhenLose() {
        Player player = new Player("플레이어", new CardPool(Collections.emptyList()), 0);
        Map<Player, GameResult> playerGameResult = new HashMap<>();

        playerGameResult.put(player, GameResult.WIN);

        assertThat(GameResult.makeDealerRecord(playerGameResult))
                .containsEntry(GameResult.LOSE, 1)
                .containsEntry(GameResult.WIN, 0)
                .containsEntry(GameResult.DRAW, 0);
    }

    @Test
    @DisplayName("Dealer는 Player가 지면 1승이 적립된다.")
    void makeDealerRecordWhenWin() {
        Player player = new Player("플레이어", new CardPool(Collections.emptyList()), 0);
        Map<Player, GameResult> playerGameResult = new HashMap<>();

        playerGameResult.put(player, GameResult.LOSE);

        assertThat(GameResult.makeDealerRecord(playerGameResult))
                .containsEntry(GameResult.LOSE, 0)
                .containsEntry(GameResult.WIN, 1)
                .containsEntry(GameResult.DRAW, 0);
    }

    @Test
    @DisplayName("Dealer는 Player와 점수가 같으면 1무가 적립된다.")
    void makeDealerRecordWhenDraw() {
        Player player = new Player("플레이어", new CardPool(Collections.emptyList()), 0);
        Map<Player, GameResult> playerGameResult = new HashMap<>();

        playerGameResult.put(player, GameResult.DRAW);

        assertThat(GameResult.makeDealerRecord(playerGameResult))
                .containsEntry(GameResult.LOSE, 0)
                .containsEntry(GameResult.WIN, 0)
                .containsEntry(GameResult.DRAW, 1);
    }
}
