package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static blackjack.domain.FixtureCardDeck.NOT_SHUFFLED_CARD_DECK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class BlackJackGameTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        List<String> names = List.of("위브", "산초");
        List<Integer> bets = List.of(10, 20);
        Players players = new Players(names, bets);
        Dealer dealer = new Dealer(NOT_SHUFFLED_CARD_DECK);

        assertThatCode(() -> new BlackJackGame(players, dealer))
                .doesNotThrowAnyException();
    }

    @DisplayName("결과 계산 테스트")
    @Test
    void judgeResult() {
        String name = "산초";
        int bet = 10;
        Players players = new Players(List.of(name), List.of(bet));
        Dealer dealer = new Dealer(NOT_SHUFFLED_CARD_DECK);
        BlackJackGame blackJackGame = new BlackJackGame(players, dealer);
        blackJackGame.initHand(); // 딜러는 2,3 플레이어는 4,5

        Map<Participant, Integer> resultMap = blackJackGame.settleBets();

        int playerSettlement = resultMap.get(new Player(name, bet));
        int dealerSettlement = resultMap.get(dealer);
        Assertions.assertAll(
                () -> assertThat(playerSettlement).isEqualTo(bet),
                () -> assertThat(dealerSettlement).isEqualTo(-1 * bet)
        );
    }
}
