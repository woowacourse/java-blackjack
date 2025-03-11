package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

public class BlackjackManagerTest {

    @Test
    void 블랙잭_객체를_생성한다() {
        // given
        Players players = new Players(List.of(
                new Dealer(),
                new Participant("시소"),
                new Participant("헤일러"),
                new Participant("부기"),
                new Participant("사나")
        ));

        Deck deck = DeckGenerator.generateDeck();

        // when & then
        Assertions.assertThatCode(() -> new BlackjackManager(players, deck))
                .doesNotThrowAnyException();
    }

    @Test
    void 딜러의_카드_합이_16이하면_카드를_한장_추가한다() {
        // given
        Dealer dealer = new Dealer();
        Players players = new Players(List.of(
                dealer,
                new Participant("시소"),
                new Participant("헤일러"),
                new Participant("부기"),
                new Participant("사나")
        ));

        Deck deck = new Deck(new ArrayList<>(List.of(
                new Card(Suit.SPADE, Rank.EIGHT),
                new Card(Suit.SPADE, Rank.EIGHT),
                new Card(Suit.SPADE, Rank.EIGHT),
                new Card(Suit.SPADE, Rank.EIGHT),
                new Card(Suit.SPADE, Rank.EIGHT),
                new Card(Suit.SPADE, Rank.EIGHT),
                new Card(Suit.SPADE, Rank.EIGHT),
                new Card(Suit.SPADE, Rank.EIGHT),
                new Card(Suit.SPADE, Rank.EIGHT),
                new Card(Suit.SPADE, Rank.EIGHT),

                new Card(Suit.SPADE, Rank.EIGHT)
        )));
        BlackjackManager blackjackManager = new BlackjackManager(players, deck);
        blackjackManager.distributeInitialCards();
        final int beforeSize = dealer.getCards().size();

        // when
        blackjackManager.addCardToDealerIfLowSum();
        final int afterSize = dealer.getCards().size();

        // then
        Assertions.assertThat(afterSize).isEqualTo(beforeSize + 1);
    }

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
        Map<Player, MatchResult> participantsMatchResult
                = blackjackManager.computeParticipantsMatchResult(dealer, participants);

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
                = blackjackManager.computeParticipantsMatchResult(dealer, participants);

        // when
        Map<MatchResult, Integer> dealerMatchResultCount
                = blackjackManager.computeDealerMatchResultCount(participantsMatchResult);

        // then
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(dealerMatchResultCount.get(MatchResult.WIN)).isEqualTo(2);
            softly.assertThat(dealerMatchResultCount.get(MatchResult.LOSE)).isEqualTo(1);
            softly.assertThat(dealerMatchResultCount.get(MatchResult.DRAW)).isEqualTo(1);
        });
    }
}
