package result;

import blackjack.Blackjack;
import card.Card;
import card.CardRank;
import card.CardSuit;
import card.Deck;
import player.Dealer;
import player.Participant;
import player.Player;
import player.Players;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

class MatchResultTest {

    @Test
    void 승패_결과를_받아_반전한_승패_결과를_반환한다() {
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(MatchResult.inverse(MatchResult.WIN))
                    .isEqualTo(MatchResult.LOSE);
            softAssertions.assertThat(MatchResult.inverse(MatchResult.LOSE))
                    .isEqualTo(MatchResult.WIN);
            softAssertions.assertThat(MatchResult.inverse(MatchResult.DRAW))
                    .isEqualTo(MatchResult.DRAW);
        });
    }

    @Test
    void 두_플레이어의_승패_결과를_반환한다() {
        // when
        Player dealer = new Dealer();
        Player participant = new Participant("시소");

        Players players = new Players(List.of(dealer, participant));

        Deck deck = new Deck(new ArrayList<>(List.of(
                new Card(CardSuit.SPADE, CardRank.SEVEN),
                new Card(CardSuit.SPADE, CardRank.SEVEN),
                new Card(CardSuit.SPADE, CardRank.FIVE),
                new Card(CardSuit.SPADE, CardRank.FIVE)
        )));
        Blackjack blackjack = new Blackjack(players, deck);
        blackjack.distributeInitialCards();

        assertThat(MatchResult.calculateParticipantMatchResult(dealer, participant)).isEqualTo(MatchResult.WIN);
    }

}
