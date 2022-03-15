package domain.participant;

import domain.GameResult;
import domain.card.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

    @Test
    @DisplayName("딜러의 상태가 RUNNING 인 경우 카드를 한 장만 반환한다.")
    void getCard() {
        dealer.drawCard(CLUB_ACE_CARD);
        dealer.drawCard(SPADE_NINE_CARD);

        assertThat(dealer.getCards().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("딜러의 상태가 END 인 경우 모든 카드를 반환 한다.")
    void getCard2() {
        //given
        dealer.drawCard(SPADE_NINE_CARD);
        dealer.drawCard(HEART_TEN_CARD);

        //when
        dealer.stopRunning();

        //then
        assertThat(dealer.getCards().size()).isEqualTo(2);
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
