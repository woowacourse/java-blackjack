package result;

import blackjack.Blackjack;
import card.Card;
import card.CardRank;
import card.CardSuit;
import card.Deck;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import player.Participant;
import player.Participants;

class MatchResultsTest {

    @Test
    void 참여자들의_승패_결과를_반환한다() {
        // given
        Participant siso = new Participant("시소");
        Participant hailer = new Participant("헤일러");
        Participant bugi = new Participant("부기");
        Participant sana = new Participant("사나");

        Participants participants = new Participants(List.of(siso, hailer, bugi, sana));

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
        Blackjack blackjack = new Blackjack(participants, deck);
        blackjack.distributeInitialCards();

        // when
        MatchResults matchResults = new MatchResults(blackjack.getDealer(), blackjack.getParticipants());

        // then
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(matchResults.getMatchResults().get(siso)).isEqualTo(MatchResult.WIN);
            softly.assertThat(matchResults.getMatchResults().get(hailer)).isEqualTo(MatchResult.LOSE);
            softly.assertThat(matchResults.getMatchResults().get(bugi)).isEqualTo(MatchResult.LOSE);
            softly.assertThat(matchResults.getMatchResults().get(sana)).isEqualTo(MatchResult.DRAW);
        });
    }
}