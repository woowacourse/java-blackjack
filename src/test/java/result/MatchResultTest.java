package result;

import blackjack.Blackjack;
import card.Card;
import card.CardRank;
import card.CardSuit;
import card.Deck;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import player.Dealer;
import player.Participant;
import player.Participants;

class MatchResultTest {

    @Test
    void 두_플레이어의_승패_결과를_반환한다() {
        // given
        Deck deck = new Deck(new ArrayList<>(List.of(
                new Card(CardSuit.SPADE, CardRank.SEVEN),
                new Card(CardSuit.SPADE, CardRank.SEVEN),
                new Card(CardSuit.SPADE, CardRank.FIVE),
                new Card(CardSuit.SPADE, CardRank.FIVE)
        )));

        Participant participant = new Participant("시소");

        Participants participants = new Participants(List.of(participant));

        Blackjack blackjack = new Blackjack(participants, deck);
        blackjack.distributeInitialCards();

        // when & then
        assertThat(MatchResult.calculateParticipantMatchResult(blackjack.getDealer(), participant)).isEqualTo(MatchResult.WIN);
    }

    @Test
    void 참여자와_딜러_모두_블랙잭이라면_무승부이다() {
        // given
        Deck deck = new Deck(new ArrayList<>(List.of(
                new Card(CardSuit.SPADE, CardRank.JACK),
                new Card(CardSuit.SPADE, CardRank.ACE),
                new Card(CardSuit.SPADE, CardRank.JACK),
                new Card(CardSuit.SPADE, CardRank.ACE)
        )));

        Participant participant = new Participant("시소");

        Participants participants = new Participants(List.of(participant));

        Blackjack blackjack = new Blackjack(participants, deck);
        blackjack.distributeInitialCards();

        // when & then
        assertThat(MatchResult.calculateParticipantMatchResult(blackjack.getDealer(), participant)).isEqualTo(MatchResult.DRAW);
    }

    @Test
    void 참여자가_버스트라면_패배이다() {
        // given
        Deck deck = new Deck(new ArrayList<>(List.of(
                new Card(CardSuit.SPADE, CardRank.JACK),
                new Card(CardSuit.SPADE, CardRank.JACK),
                new Card(CardSuit.SPADE, CardRank.JACK),
                new Card(CardSuit.SPADE, CardRank.JACK),
                new Card(CardSuit.SPADE, CardRank.JACK),
                new Card(CardSuit.SPADE, CardRank.JACK)
        )));

        Participant participant = new Participant("시소");

        Participants participants = new Participants(List.of(participant));

        Blackjack blackjack = new Blackjack(participants, deck);
        blackjack.distributeInitialCards();
        blackjack.addCardToParticipant(participant);

        // when & then
        assertThat(MatchResult.calculateParticipantMatchResult(blackjack.getDealer(), participant)).isEqualTo(MatchResult.LOSE);
    }



    @Test
    void 참여자와_딜러_모두_버스트라면_패배이다() {
        // given
        Deck deck = new Deck(new ArrayList<>(List.of(
                new Card(CardSuit.SPADE, CardRank.JACK),
                new Card(CardSuit.SPADE, CardRank.JACK),
                new Card(CardSuit.SPADE, CardRank.JACK),
                new Card(CardSuit.SPADE, CardRank.JACK),
                new Card(CardSuit.SPADE, CardRank.JACK),
                new Card(CardSuit.SPADE, CardRank.JACK),
                new Card(CardSuit.SPADE, CardRank.JACK),
                new Card(CardSuit.SPADE, CardRank.JACK)
        )));

        Participant participant = new Participant("시소");
        Dealer dealer = new Dealer(deck);

        participant.receiveInitialCards(deck);
        participant.receiveInitialCards(deck);

        dealer.receiveInitialCards();
        dealer.receiveInitialCards();

        // when & then
        assertThat(MatchResult.calculateParticipantMatchResult(dealer, participant)).isEqualTo(MatchResult.LOSE);
    }

    @Test
    void 딜러만_버스트라면_승리이다() {
        // given
        Deck deck = new Deck(new ArrayList<>(List.of(
                new Card(CardSuit.SPADE, CardRank.JACK),
                new Card(CardSuit.SPADE, CardRank.JACK),
                new Card(CardSuit.SPADE, CardRank.JACK),
                new Card(CardSuit.SPADE, CardRank.JACK),
                new Card(CardSuit.SPADE, CardRank.JACK),
                new Card(CardSuit.SPADE, CardRank.JACK)
        )));

        Participant participant = new Participant("시소");
        Dealer dealer = new Dealer(deck);

        participant.receiveInitialCards(deck);

        dealer.receiveInitialCards();
        dealer.receiveInitialCards();

        // when & then
        assertThat(MatchResult.calculateParticipantMatchResult(dealer, participant)).isEqualTo(MatchResult.WIN);
    }

    @Test
    void 블랙잭이라면_블랙잭으로의_승리이다() {
        // given
        Deck deck = new Deck(new ArrayList<>(List.of(
                new Card(CardSuit.SPADE, CardRank.JACK),
                new Card(CardSuit.SPADE, CardRank.JACK),
                new Card(CardSuit.SPADE, CardRank.JACK),
                new Card(CardSuit.SPADE, CardRank.JACK),
                new Card(CardSuit.SPADE, CardRank.JACK),
                new Card(CardSuit.SPADE, CardRank.ACE)
        )));

        Participant participant = new Participant("시소");
        Dealer dealer = new Dealer(deck);

        participant.receiveInitialCards(deck);

        dealer.receiveInitialCards();

        // when & then
        assertThat(MatchResult.calculateParticipantMatchResult(dealer, participant)).isEqualTo(MatchResult.WIN_WITH_BLACKJACK);
    }
}
