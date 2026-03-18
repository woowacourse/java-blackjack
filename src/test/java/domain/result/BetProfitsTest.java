package domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import domain.BlackjackGame;
import domain.card.Card;
import domain.card.CardRank;
import domain.card.CardSuit;
import domain.participant.Name;
import domain.participant.Participant;
import domain.participant.Participants;
import domain.participant.Player;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BetProfitsTest {

    private static final int ONE_HUNDRED_THOUSAND = 100_000;


    @Test
    @DisplayName("딜러는 플레이어들이 얻은 수익만큼 잃는다.")
    public void 딜러는_플레이어들이_얻은_수익만큼_잃는다() {
        // given
        final Player player1 = new Player(new Name("포비"), ONE_HUNDRED_THOUSAND);
        player1.draw(new Card(CardSuit.DIAMOND, CardRank.QUEEN));
        player1.draw(new Card(CardSuit.DIAMOND, CardRank.KING)); // 20

        final Player player2 = new Player(new Name("타스"), ONE_HUNDRED_THOUSAND);
        player2.draw(new Card(CardSuit.DIAMOND, CardRank.TEN));
        player2.draw(new Card(CardSuit.DIAMOND, CardRank.JACK)); // 20

        final Participants participants = new Participants(List.of(player1, player2));
        final Participant dealer = participants.getDealer();
        dealer.draw(new Card(CardSuit.DIAMOND, CardRank.SEVEN));
        dealer.draw(new Card(CardSuit.DIAMOND, CardRank.QUEEN)); // 17 - WIN

        final BlackjackGame game = new BlackjackGame(participants);

        // when
        final BetProfits betProfits = game.getBetProfits();
        final int dealerProfit = betProfits.dealerProfit().profit();
        int playerProfit = 0;
        for (final BetProfit betProfit : betProfits.betProfits()) {
            playerProfit += betProfit.profit();
        }

        // then
        assertThat(dealerProfit).isEqualTo(-playerProfit);
    }

    @Test
    @DisplayName("딜러는 플레이어들이 잃은 만큼 수익을 얻는다.")
    public void 딜러는_플레이어들이_잃은_만큼_수익을_얻는다() {
        // given
        final Player player1 = new Player(new Name("포비"), ONE_HUNDRED_THOUSAND);
        player1.draw(new Card(CardSuit.DIAMOND, CardRank.TWO));
        player1.draw(new Card(CardSuit.DIAMOND, CardRank.KING)); // 12

        final Player player2 = new Player(new Name("타스"), ONE_HUNDRED_THOUSAND);
        player2.draw(new Card(CardSuit.DIAMOND, CardRank.THREE));
        player2.draw(new Card(CardSuit.DIAMOND, CardRank.JACK)); // 13

        final Participants participants = new Participants(List.of(player1, player2));
        final Participant dealer = participants.getDealer();
        dealer.draw(new Card(CardSuit.DIAMOND, CardRank.SEVEN));
        dealer.draw(new Card(CardSuit.DIAMOND, CardRank.QUEEN)); // 17 - LOSE

        final BlackjackGame game = new BlackjackGame(participants);

        // when
        final BetProfits betProfits = game.getBetProfits();
        final int dealerProfit = betProfits.dealerProfit().profit();
        int playerProfit = 0;
        for (final BetProfit betProfit : betProfits.betProfits()) {
            playerProfit += betProfit.profit();
        }

        // then
        assertThat(dealerProfit).isEqualTo(-playerProfit);
    }
}
