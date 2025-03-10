package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

class MatchResultCalculatorTest {

    @Test
    void 참여자들의_승패_결과를_반환한다() {
        // given
        Dealer dealer = new Dealer();

        Player siso = new Participant("시소");
        Player heiler = new Participant("헤일러");
        Player boogie = new Participant("부기");
        Player sana = new Participant("사나");

        List<Player> participants = List.of(siso, heiler, boogie, sana);
        List<Player> playersList = new ArrayList<>(List.of(dealer));
        playersList.addAll(participants);
        Players players = new Players(playersList);

        Deck deck = new Deck(new ArrayList<>(List.of(
                new Card(Suit.SPADE, Rank.FIVE),
                new Card(Suit.SPADE, Rank.FIVE),
                new Card(Suit.SPADE, Rank.THREE),
                new Card(Suit.SPADE, Rank.THREE),
                new Card(Suit.SPADE, Rank.TWO),
                new Card(Suit.SPADE, Rank.TWO),
                new Card(Suit.SPADE, Rank.SEVEN),
                new Card(Suit.SPADE, Rank.SEVEN),
                new Card(Suit.SPADE, Rank.FIVE),
                new Card(Suit.SPADE, Rank.FIVE)
        )));
        BlackjackManager blackjackManager = new BlackjackManager(players, deck);
        blackjackManager.distributeInitialCards();

        // when
        Map<Player, MatchResult> participantsMatchResult = MatchResultCalculator.computeParticipantsMatchResult(dealer,
                participants);

        // then
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(participantsMatchResult.get(siso)).isEqualTo(MatchResult.WIN);
            softly.assertThat(participantsMatchResult.get(heiler)).isEqualTo(MatchResult.LOSE);
            softly.assertThat(participantsMatchResult.get(boogie)).isEqualTo(MatchResult.LOSE);
            softly.assertThat(participantsMatchResult.get(sana)).isEqualTo(MatchResult.DRAW);
        });
    }

    @Test
    void 딜러의_승패_결과를_반환한다() {
        // given
        Dealer dealer = new Dealer();

        Player siso = new Participant("시소");
        Player heiler = new Participant("헤일러");
        Player boogie = new Participant("부기");
        Player sana = new Participant("사나");

        List<Player> participants = List.of(siso, heiler, boogie, sana);
        List<Player> playersList = new ArrayList<>(List.of(dealer));
        playersList.addAll(participants);
        Players players = new Players(playersList);

        Deck deck = new Deck(new ArrayList<>(List.of(
                new Card(Suit.SPADE, Rank.FIVE),
                new Card(Suit.SPADE, Rank.FIVE),
                new Card(Suit.SPADE, Rank.THREE),
                new Card(Suit.SPADE, Rank.THREE),
                new Card(Suit.SPADE, Rank.TWO),
                new Card(Suit.SPADE, Rank.TWO),
                new Card(Suit.SPADE, Rank.SEVEN),
                new Card(Suit.SPADE, Rank.SEVEN),
                new Card(Suit.SPADE, Rank.FIVE),
                new Card(Suit.SPADE, Rank.FIVE)
        )));
        BlackjackManager blackjackManager = new BlackjackManager(players, deck);
        blackjackManager.distributeInitialCards();
        Map<Player, MatchResult> participantsMatchResult
                = MatchResultCalculator.computeParticipantsMatchResult(dealer, participants);

        // when
        Map<MatchResult, Integer> dealerMatchResultCount
                = MatchResultCalculator.computeDealerMatchResultCount(participantsMatchResult);

        // then
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(dealerMatchResultCount.get(MatchResult.WIN)).isEqualTo(2);
            softly.assertThat(dealerMatchResultCount.get(MatchResult.LOSE)).isEqualTo(1);
            softly.assertThat(dealerMatchResultCount.get(MatchResult.DRAW)).isEqualTo(1);
        });
    }
}