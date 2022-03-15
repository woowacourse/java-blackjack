package blackjack.domain;

import static blackjack.domain.Result.LOSS;
import static blackjack.domain.Result.TIE;
import static blackjack.domain.Result.WIN;
import static blackjack.domain.card.Denomination.ACE;
import static blackjack.domain.card.Denomination.FIVE;
import static blackjack.domain.card.Denomination.KING;
import static blackjack.domain.card.Denomination.SIX;
import static blackjack.domain.card.Suit.CLOVER;
import static blackjack.domain.card.Suit.DIAMOND;
import static blackjack.domain.card.Suit.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.User;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultTest {

    @DisplayName("게임 결과를 제대로 반환하는지 테스트")
    @Test
    public void testGetResult() {
        //given
        Deck deck = initDeck();

        List<User> players = List.of(
                Player.from("pobi"),
                Player.from("jason")
        );

        for (User player : players) {
            player.drawCard(deck);
            player.drawCard(deck);
            player.calculate();
        }

        Dealer dealer = new Dealer();
        dealer.drawCard(deck);
        dealer.drawCard(deck);
        dealer.calculate();

        //when
        Map<String, Result> result = Result.getMap(players, dealer);

        //then
        assertThat(result.get("pobi")).isEqualTo(LOSS);
        assertThat(result.get("jason")).isEqualTo(WIN);
    }

    @DisplayName("게임 결과 뒤집어서 반환하는지 테스트")
    @Test
    public void testReverseResult() {
        //given & when
        Result reverseResultByLoss = LOSS.reverseResult();
        Result reverseResultByWin = WIN.reverseResult();
        Result reverseResultByTie = TIE.reverseResult();

        //then
        assertThat(reverseResultByLoss).isEqualTo(WIN);
        assertThat(reverseResultByWin).isEqualTo(LOSS);
        assertThat(reverseResultByTie).isEqualTo(TIE);
    }

    private Deck initDeck() {
        return new Deck(() -> new ArrayDeque<>(List.of(
                new Card(CLOVER, ACE),
                new Card(DIAMOND, FIVE),    //16
                new Card(SPADE, KING),
                new Card(SPADE, ACE),   //21
                new Card(DIAMOND, SIX),
                new Card(SPADE, ACE)    //17
        )));
    }
}