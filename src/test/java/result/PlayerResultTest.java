package result;

import blackjack.Blackjack;
import card.Card;
import card.CardRank;
import card.CardSuit;
import card.Deck;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import player.Dealer;
import player.Participant;
import player.Player;
import player.Players;

class PlayerResultTest {

    @Test
    void 참여자들의_승패_결과를_반환한다() {
        // given
        Player siso = new Participant("시소");
        Player hailer = new Participant("헤일러");
        Player bugi = new Participant("부기");
        Player sana = new Participant("사나");

        Players players = new Players(List.of(new Dealer(), siso, hailer, bugi, sana));

        Deck deck = new Deck(new ArrayList<>(List.of(
                new Card(CardSuit.SPADE, CardRank.FIVE),
                new Card(CardSuit.SPADE, CardRank.FIVE),
                new Card(CardSuit.SPADE, CardRank.THREE),
                new Card(CardSuit.SPADE, CardRank.THREE),
                new Card(CardSuit.SPADE, CardRank.TWO),
                new Card(CardSuit.SPADE, CardRank.TWO),
                new Card(CardSuit.SPADE, CardRank.SEVEN),
                new Card(CardSuit.SPADE, CardRank.SEVEN),
                new Card(CardSuit.SPADE, CardRank.FIVE),
                new Card(CardSuit.SPADE, CardRank.FIVE)
        )));
        Blackjack blackjack = new Blackjack(players, deck);
        blackjack.distributeInitialCards();

        // when
        PlayerResult playerResult = new PlayerResult(blackjack.getDealer(), blackjack.getParticipants());

        // then
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(playerResult.getMatchResults().get(siso)).isEqualTo(MatchResult.WIN);
            softly.assertThat(playerResult.getMatchResults().get(hailer)).isEqualTo(MatchResult.LOSE);
            softly.assertThat(playerResult.getMatchResults().get(bugi)).isEqualTo(MatchResult.LOSE);
            softly.assertThat(playerResult.getMatchResults().get(sana)).isEqualTo(MatchResult.DRAW);
        });
    }
}