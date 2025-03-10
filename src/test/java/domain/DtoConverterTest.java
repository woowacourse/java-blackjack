package domain;

import controller.DtoConverter;
import controller.dto.DealerMatchResultCount;
import controller.dto.ParticipantsMatchResult;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

class DtoConverterTest {

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
        ParticipantsMatchResult participantsMatchResult
                = DtoConverter.computeParticipantsMatchResult(dealer, participants);

        // then
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(participantsMatchResult.participantMatchResult().get(siso)).isEqualTo(MatchResult.WIN);
            softly.assertThat(participantsMatchResult.participantMatchResult().get(heiler)).isEqualTo(MatchResult.LOSE);
            softly.assertThat(participantsMatchResult.participantMatchResult().get(boogie)).isEqualTo(MatchResult.LOSE);
            softly.assertThat(participantsMatchResult.participantMatchResult().get(sana)).isEqualTo(MatchResult.DRAW);
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
        ParticipantsMatchResult participantsMatchResult
                = DtoConverter.computeParticipantsMatchResult(dealer, participants);

        // when
        DealerMatchResultCount dealerMatchResultCount
                = DtoConverter.computeDealerMatchResultCount(participantsMatchResult.participantMatchResult());

        // then
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(dealerMatchResultCount.matchResultCount().get(MatchResult.WIN)).isEqualTo(2);
            softly.assertThat(dealerMatchResultCount.matchResultCount().get(MatchResult.LOSE)).isEqualTo(1);
            softly.assertThat(dealerMatchResultCount.matchResultCount().get(MatchResult.DRAW)).isEqualTo(1);
        });
    }
}