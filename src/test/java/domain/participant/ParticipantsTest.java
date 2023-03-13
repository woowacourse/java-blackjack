package domain.participant;

import java.util.List;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantsTest {

    private Players players;
    private Dealer dealer;
    private Participants participants;

    @BeforeEach
    void init() {
        List<Card> cards1 = List.of(new Card(Suit.CLOVER, Denomination.SIX), new Card(Suit.SPADE, Denomination.TEN));
        List<Card> cards2 = List.of(new Card(Suit.CLOVER, Denomination.NINE), new Card(Suit.SPADE, Denomination.SEVEN));
        List<Card> cards3 = List.of(new Card(Suit.CLOVER, Denomination.FIVE), new Card(Suit.SPADE, Denomination.SIX));
        Player player1 = new Player(new Name("seongha"), new Hand(cards1));
        Player player2 = new Player(new Name("dino"), new Hand(cards2));
        List<Player> gamePlayers = List.of(player1, player2);

        this.players = new Players(gamePlayers);
        this.dealer = new Dealer(new Hand(cards3));
        this.participants = new Participants(players, dealer);
    }

    @Test
    @DisplayName("참가자 이름으로 참가자의 카드 이름 리스트를 반환한다.")
    void findCardNamesByParticipantName() {
        List<String> seonghaCardNames = participants.findCardNamesByParticipantName("seongha");
        List<String> dinoCardNames = participants.findCardNamesByParticipantName("dino");
        List<String> dealerCardNames = participants.findCardNamesByParticipantName("딜러");

        Assertions.assertThat(seonghaCardNames).contains("6클로버", "10스페이드");
        Assertions.assertThat(dinoCardNames).contains("9클로버", "7스페이드");
        Assertions.assertThat(dealerCardNames).contains("5클로버", "6스페이드");
    }
}
