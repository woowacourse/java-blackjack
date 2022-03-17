package domain.participant;

import domain.GameResult;
import domain.card.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static domain.MockCard.*;
import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
    }

    @ParameterizedTest
    @DisplayName("딜러 게임 결과 반환 테스트")
    @MethodSource("provideCardsWithResult")
    void getResult(final GameResult expected, final List<Card> dealerCards, final List<Card> playerCards) {
        final Player player = new Player("칙촉");
        for (final Card playerCard : playerCards) {
            player.drawCard(playerCard);
        }

        for (final Card dealerCard : dealerCards) {
            dealer.drawCard(dealerCard);
        }

        assertThat(dealer.getGameResult(player)).isEqualTo(expected);
    }

    public static Stream<Arguments> provideCardsWithResult() {
        return Stream.of(
                Arguments.of(GameResult.WIN, List.of(CLUB_ACE_CARD, HEART_TEN_CARD), List.of(HEART_TEN_CARD, SPADE_NINE_CARD)),
                Arguments.of(GameResult.LOSE, List.of(SPADE_NINE_CARD, HEART_TEN_CARD), List.of(HEART_TEN_CARD, CLUB_ACE_CARD)),
                Arguments.of(GameResult.DRAW, List.of(CLUB_ACE_CARD, HEART_TEN_CARD), List.of(CLUB_ACE_CARD, HEART_TEN_CARD))
        );
    }
}
