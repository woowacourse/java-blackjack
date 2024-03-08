package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class BlackjackResultTest {

    @Test
    @DisplayName("플레이어가 버스트 되면 딜러가 게임을 이긴다")
    void playerBust() {
        final Player dealer = new Dealer();
        final Player teba = new Participant(new Name("테바"));
        teba.addCard(new Card(Denomination.JACK,Symbol.CLOVER));
        teba.addCard(new Card(Denomination.KING,Symbol.CLOVER));
        teba.addCard(new Card(Denomination.SIX,Symbol.CLOVER));
        final Blackjack blackjack = new Blackjack(new Players(List.of(teba, dealer)), dealer);
        //


        final BlackjackResultDTO blackjackResultDTO = blackjack.finishGame();
        final Integer tebaLose = blackjackResultDTO.getLose(teba);
        final Integer dealerWin = blackjackResultDTO.getWin(dealer);

        Assertions.assertThat(tebaLose).isEqualTo(1);
        Assertions.assertThat(dealerWin).isEqualTo(1);
    }

    @Test
    @DisplayName("딜러가 버스트 되면 플레이어가 게임을 이긴다")
    void dealerBust() {
        final Player dealer = new Dealer();
        final Player teba = new Participant(new Name("테바"));
        dealer.addCard(new Card(Denomination.JACK,Symbol.CLOVER));
        dealer.addCard(new Card(Denomination.KING,Symbol.CLOVER));
        dealer.addCard(new Card(Denomination.SIX,Symbol.CLOVER));
        final Blackjack blackjack = new Blackjack(new Players(List.of(teba,dealer)), dealer);

        final BlackjackResultDTO blackjackResultDTO = blackjack.finishGame();
        final Integer dealerLose = blackjackResultDTO.getLose(dealer);
        final Integer tebaWin = blackjackResultDTO.getWin(teba);

        Assertions.assertThat(dealerLose).isEqualTo(1);
        Assertions.assertThat(tebaWin).isEqualTo(1);
    }
}
