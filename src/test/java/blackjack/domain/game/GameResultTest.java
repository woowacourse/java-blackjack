package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.TestUtil.BETTING_1000;
import static utils.TestUtil.CLOVER_ACE;
import static utils.TestUtil.CLOVER_JACK;
import static utils.TestUtil.CLOVER_NINE;
import static utils.TestUtil.CLOVER_QUEEN;
import static utils.TestUtil.CLOVER_SEVEN;
import static utils.TestUtil.CLOVER_TWO;
import static utils.TestUtil.getCards;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.participant.BettingAmount;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class GameResultTest {

    private static final Cards CARDS_BLACKJACK = getCards(CLOVER_ACE, CLOVER_JACK);
    private static final Cards CARDS_BUST = getCards(CLOVER_TWO, CLOVER_JACK, CLOVER_QUEEN);
    private static final Cards CARDS_17 = getCards(CLOVER_SEVEN, CLOVER_JACK);
    private static final Cards CARDS_19 = getCards(CLOVER_NINE, CLOVER_JACK);

    private Participants participants;

    @BeforeEach
    void init() {
        Map<Name, BettingAmount> map = new HashMap<>();
        map.put(new Name("yeonlog"), BETTING_1000);

        participants = new Participants(map);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("provideParameters")
    @DisplayName("수익 구하기")
    void bettingAmount(String comment, Cards playerCards, Cards dealerCards, long expect) {
        // given
        Player player = participants.getPlayers().get(0);
        drawCards(player, playerCards);

        Dealer dealer = participants.getDealer();
        drawCards(dealer, dealerCards);

        GameResult gameResult = new GameResult(participants);

        // then
        assertThat(gameResult.getBettingResult(player)).isEqualTo(expect);
    }

    private static Stream<Arguments> provideParameters() {
        return Stream.of(
                Arguments.arguments(
                        "사용자 bust, 딜러 blackjack",
                        CARDS_BUST, CARDS_BLACKJACK,
                        -1000L
                ),
                Arguments.arguments(
                        "사용자 bust, 딜러 bust",
                        CARDS_BUST, CARDS_BUST,
                        0L
                ),
                Arguments.arguments(
                        "사용자 bust, 딜러 17",
                        CARDS_BUST, CARDS_17,
                        -1000L
                ),
                Arguments.arguments(
                        "사용자 blackjack, 딜러 bust",
                        CARDS_BLACKJACK, CARDS_BUST,
                        1500L
                ),
                Arguments.arguments(
                        "사용자 blackjack, 딜러 blackjack",
                        CARDS_BLACKJACK, CARDS_BLACKJACK,
                        0L
                ),
                Arguments.arguments(
                        "사용자 blackjack, 딜러 17",
                        CARDS_BLACKJACK, CARDS_17,
                        1500L
                ),
                Arguments.arguments(
                        "사용자 19, 딜러 blackjack",
                        CARDS_19, CARDS_BLACKJACK,
                        -1000L
                ),
                Arguments.arguments(
                        "사용자 19, 딜러 bust",
                        CARDS_19, CARDS_BUST,
                        1000L
                ),
                Arguments.arguments(
                        "사용자 19, 딜러 19",
                        CARDS_19, CARDS_19,
                        0L
                ),
                Arguments.arguments(
                        "사용자 17, 딜러 19",
                        CARDS_17, CARDS_19,
                        -1000L
                ),
                Arguments.arguments(
                        "사용자 19, 딜러 17",
                        CARDS_19, CARDS_17,
                        1000L
                )
        );
    }

    @Test
    @DisplayName("딜러 수익")
    void dealer() {
        // given
        Map<Name, BettingAmount> map = new LinkedHashMap<>();
        map.put(new Name("winPlayer"), BETTING_1000);
        map.put(new Name("pushPlayer"), BETTING_1000);
        map.put(new Name("losePlayer"), BETTING_1000);

        Participants participants = new Participants(map);

        List<Player> players = participants.getPlayers();
        drawCards(players.get(0), CARDS_BLACKJACK);
        drawCards(players.get(1), CARDS_19);
        drawCards(players.get(2), CARDS_BUST);

        Dealer dealer = participants.getDealer();
        drawCards(dealer, CARDS_19);

        GameResult gameResult = new GameResult(participants);

        // then
        assertThat(gameResult.getDealerProfit()).isEqualTo(-500L);
    }

    private void drawCards(Participant participant, Cards cards) {
        for (Card card : cards.getValue()) {
            participant.drawCard(card);
        }
    }
}
