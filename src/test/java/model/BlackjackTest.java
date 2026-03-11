package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import model.card.Card;
import model.card.Deck;
import model.card.Rank;
import model.card.Suit;
import model.participant.Dealer;
import model.participant.Participant;
import model.participant.Participants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BlackjackTest {
    Blackjack blackjack;
    Participants participants;
    Deck simpleDeck = () -> Card.of(Suit.SPADE, Rank.ACE);

    @BeforeEach
    void setUp() {
        participants = Participants.from(List.of("pobi", "jason"));
        Dealer dealer = participants.getDealer();
        dealer.receive(Card.of(Suit.SPADE, Rank.TWO));
        dealer.receive(Card.of(Suit.SPADE, Rank.FOUR));

        Participant player1 = participants.getPlayers().getFirst();
        player1.receive(Card.of(Suit.HEART, Rank.TWO));
        player1.receive(Card.of(Suit.HEART, Rank.THREE));
        Participant player2 = participants.getPlayers().get(1);
        player2.receive(Card.of(Suit.HEART, Rank.FOUR));
        player2.receive(Card.of(Suit.HEART, Rank.SIX));

        blackjack = Blackjack.of(participants, simpleDeck);
    }

    @Test
    void 전체_참여자들의_승패를_계산한다() {
        // when
        Map<String, Integer> dealerResult = blackjack.calculateDealerResult();
        Map<String, Boolean> playerResult = blackjack.calculatePlayerResult();

        assertThat(dealerResult.get("승")).isEqualTo(1);
        assertThat(dealerResult.get("패")).isEqualTo(1);

        assertThat(playerResult.get("pobi")).isFalse();
        assertThat(playerResult.get("jason")).isTrue();
    }
}
