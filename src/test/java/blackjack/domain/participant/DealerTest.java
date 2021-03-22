package blackjack.domain.participant;

import blackjack.controller.dto.GameResultDto;
import blackjack.domain.Money;
import blackjack.domain.carddeck.Card;
import blackjack.domain.carddeck.Number;
import blackjack.domain.carddeck.Pattern;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DealerTest {

    Dealer dealer;
    Players players;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
        List<Card> cards = Collections.singletonList(new Card(Pattern.CLOVER, Number.TEN));
        dealer.receiveFirstHand(new ArrayList<>(cards));

        Player player1 = new Player(new Name("pobi"), Money.of(100));
        player1.receiveFirstHand(Collections.singletonList(
                new Card(Pattern.SPADE, Number.NINE)
        ));
        player1.stay();

        players = Players.createPlayers(Collections.singletonList(player1));
    }

    @Test
    @DisplayName("딜러는 52장의 카드가 있는 카드 덱을 가진다.")
    void testDealerTakeCardDeck() {
        for (int i = 0; i < 52; i++) {
            dealer.drawCard();
        }
        assertThatThrownBy(() -> {
            dealer.drawCard();
        }).isInstanceOf(NoSuchElementException.class).hasMessage("더이상 뽑을 카드가 없습니다.");
    }

    @Test
    @DisplayName("딜러는 총점수 17이상일시 카드 뽑기를 멈춘다.")
    void testStopDrawDealerWhenTotalScoreOverSeventeen() {
        dealer.receiveCard(new Card(Pattern.DIAMOND, Number.JACK));
        for (int i = 0; i < 999999; i++) {
            while (!dealer.isOverLimitScore()) {
                dealer.receiveCard(dealer.drawCard());
            }
            assertThat(dealer.getTotalScore().isDealerLimitScore()).isTrue();
        }
    }


    @Test
    @DisplayName("딜러의 점수와 플레이어들의 점수를 비교해서 결과를 반환한다.")
    void getPlayersResultTest() {
        //given
        dealer.stay();
        List<GameResultDto> gameResultDtos = dealer.getPlayersResult(players);

        //then
        for (GameResultDto gameResultDto : gameResultDtos) {
            assertThat(gameResultDto.getName()).isEqualTo("pobi");
            assertThat(gameResultDto.getEarning()).isEqualTo(-100);
        }
    }

    @Test
    void getDealerResult() {
        //given
        dealer.stay();
        List<GameResultDto> gameResultDtos = dealer.getPlayersResult(players);
        GameResultDto dealerDto = dealer.getDealerResult(gameResultDtos);

        //when
        double dealerEarning = dealerDto.getEarning();

        //then
        assertThat(dealerEarning).isEqualTo(100);
    }
}
