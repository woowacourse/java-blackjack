package result;

import static org.assertj.core.api.Assertions.assertThat;

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
import org.junit.jupiter.api.Test;

class MatchResultTest {

    @Test
    void 두_플레이어의_승패_결과를_반환한다() {
        // given
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

        // when & then
        assertThat(MatchResult.calculateParticipantMatchResult(dealer, participant)).isEqualTo(MatchResult.WIN);
    }

    @Test
    void 참여자와_딜러_모두_블랙잭이라면_무승부이다() {
        // given
        Player dealer = new Dealer();
        Player participant = new Participant("시소");

        Players players = new Players(List.of(dealer, participant));

        Deck deck = new Deck(new ArrayList<>(List.of(
                new Card(CardSuit.SPADE, CardRank.JACK),
                new Card(CardSuit.SPADE, CardRank.ACE),
                new Card(CardSuit.SPADE, CardRank.JACK),
                new Card(CardSuit.SPADE, CardRank.ACE)
        )));
        Blackjack blackjack = new Blackjack(players, deck);
        blackjack.distributeInitialCards();

        // when & then
        assertThat(MatchResult.calculateParticipantMatchResult(dealer, participant)).isEqualTo(MatchResult.DRAW);
    }

    @Test
    void 참여자가_버스트라면_패배이다() {
        // given
        Player dealer = new Dealer();
        Player participant = new Participant("시소");

        Deck deck = new Deck(new ArrayList<>(List.of(
                new Card(CardSuit.SPADE, CardRank.JACK),
                new Card(CardSuit.SPADE, CardRank.JACK),
                new Card(CardSuit.SPADE, CardRank.JACK),
                new Card(CardSuit.SPADE, CardRank.JACK),
                new Card(CardSuit.SPADE, CardRank.JACK),
                new Card(CardSuit.SPADE, CardRank.JACK)
        )));

        participant.drawOneCard(deck);
        participant.drawOneCard(deck);
        participant.drawOneCard(deck);
        dealer.drawOneCard(deck);

        // when & then
        assertThat(MatchResult.calculateParticipantMatchResult(dealer, participant)).isEqualTo(MatchResult.LOSE);
    }

    @Test
    void 참여자와_딜러_모두_버스트라면_패배이다() {
        // given
        Player dealer = new Dealer();
        Player participant = new Participant("시소");

        Deck deck = new Deck(new ArrayList<>(List.of(
                new Card(CardSuit.SPADE, CardRank.JACK),
                new Card(CardSuit.SPADE, CardRank.JACK),
                new Card(CardSuit.SPADE, CardRank.JACK),
                new Card(CardSuit.SPADE, CardRank.JACK),
                new Card(CardSuit.SPADE, CardRank.JACK),
                new Card(CardSuit.SPADE, CardRank.JACK)
        )));

        participant.drawOneCard(deck);
        participant.drawOneCard(deck);
        participant.drawOneCard(deck);
        dealer.drawOneCard(deck);
        dealer.drawOneCard(deck);
        dealer.drawOneCard(deck);

        // when & then
        assertThat(MatchResult.calculateParticipantMatchResult(dealer, participant)).isEqualTo(MatchResult.LOSE);
    }

    @Test
    void 딜러만_버스트라면_승리이다() {
        // given
        Player dealer = new Dealer();
        Player participant = new Participant("시소");

        Deck deck = new Deck(new ArrayList<>(List.of(
                new Card(CardSuit.SPADE, CardRank.JACK),
                new Card(CardSuit.SPADE, CardRank.JACK),
                new Card(CardSuit.SPADE, CardRank.JACK),
                new Card(CardSuit.SPADE, CardRank.JACK),
                new Card(CardSuit.SPADE, CardRank.JACK),
                new Card(CardSuit.SPADE, CardRank.JACK)
        )));

        participant.drawOneCard(deck);
        dealer.drawOneCard(deck);
        dealer.drawOneCard(deck);
        dealer.drawOneCard(deck);

        // when & then
        assertThat(MatchResult.calculateParticipantMatchResult(dealer, participant)).isEqualTo(MatchResult.WIN);
    }

    @Test
    void 블랙잭이라면_블랙잭으로의_승리이다() {
        // given
        Player dealer = new Dealer();
        Player participant = new Participant("시소");

        Deck deck = new Deck(new ArrayList<>(List.of(
                new Card(CardSuit.SPADE, CardRank.JACK),
                new Card(CardSuit.SPADE, CardRank.JACK),
                new Card(CardSuit.SPADE, CardRank.JACK),
                new Card(CardSuit.SPADE, CardRank.JACK),
                new Card(CardSuit.SPADE, CardRank.JACK),
                new Card(CardSuit.SPADE, CardRank.ACE)
        )));

        participant.drawOneCard(deck);
        participant.drawOneCard(deck);
        dealer.drawOneCard(deck);
        dealer.drawOneCard(deck);

        // when & then
        assertThat(MatchResult.calculateParticipantMatchResult(dealer, participant)).isEqualTo(MatchResult.WIN_WITH_BLACKJACK);
    }
}
