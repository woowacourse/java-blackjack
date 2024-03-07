package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.deck.Card;
import domain.deck.Deck;
import domain.deck.Rank;
import domain.deck.Shape;
import domain.participants.Name;
import domain.participants.Participants;
import domain.participants.Player;
import domain.participants.Players;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantsTest {

    private Player siso;
    private Player tacan;
    private Player dealer;
    private Players players;
    private Participants participants;

    @BeforeEach
    void beforeEach() {
        siso = new Player(new Name("시소"));
        tacan = new Player(new Name("타칸"));
        dealer = new Player(new Name("딜러"));
        List<Player> playerList = List.of(siso, tacan);
        players = new Players(playerList);
        participants = new Participants(dealer, players);
    }

    @Test
    @DisplayName("게임 참가자가 하나의 카드를 받는다.")
    void receivePlayerCardTest() {
        Card card = new Card(Shape.HEART, Rank.FIVE);

        participants.receivePlayerCard(card, 0);

        assertThat(participants.getOnePlayer(0).getDeck().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("딜러가 하나의 카드를 받는다.")
    void receiveDealerCardTest() {
        Card card = new Card(Shape.HEART, Rank.FIVE);

        participants.receiveDealerCard(card);

        assertThat(participants.getDealer().getDeck().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("게임 참가자들이 초기 카드를 받는다")
    void initialDistributeTest() {
        Deck deck1 = new Deck();
        deck1.addCard(new Card(Shape.HEART, Rank.ACE));
        deck1.addCard(new Card(Shape.HEART, Rank.TWO));

        Deck deck2 = new Deck();
        deck2.addCard(new Card(Shape.SPADE, Rank.ACE));
        deck2.addCard(new Card(Shape.SPADE, Rank.TWO));

        Deck deck3 = new Deck();
        deck3.addCard(new Card(Shape.DIAMOND, Rank.ACE));
        deck3.addCard(new Card(Shape.DIAMOND, Rank.TWO));


        participants.receiveInitialDecks(
                new ArrayList<>(List.of(deck1, deck2, deck3))
        );

        assertThat(siso.getDeck().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("참가자들의 결과를 계산한다.")
    void calculateResultTest() {
        Deck deck1 = new Deck();
        deck1.addCard(new Card(Shape.HEART, Rank.ACE));
        deck1.addCard(new Card(Shape.HEART, Rank.TWO));

        Deck deck2 = new Deck();
        deck2.addCard(new Card(Shape.SPADE, Rank.ACE));
        deck2.addCard(new Card(Shape.SPADE, Rank.TWO));

        Deck deck3 = new Deck();
        deck3.addCard(new Card(Shape.DIAMOND, Rank.ACE));
        deck3.addCard(new Card(Shape.DIAMOND, Rank.TWO));


        participants.receiveInitialDecks(
                new ArrayList<>(List.of(deck1, deck2, deck3))
        );
        Map<Player, Boolean> result = participants.calculateResult();
        assertThat(result.get(siso)).isFalse();
    }

    @Test
    @DisplayName("참가자가 카드를 더 받을 수 있다.")
    void isPlayerNotOverTest() {
        Deck deck1 = new Deck();
        deck1.addCard(new Card(Shape.HEART, Rank.ACE));
        deck1.addCard(new Card(Shape.HEART, Rank.TWO));

        Deck deck2 = new Deck();
        deck2.addCard(new Card(Shape.SPADE, Rank.ACE));
        deck2.addCard(new Card(Shape.SPADE, Rank.TWO));

        Deck deck3 = new Deck();
        deck3.addCard(new Card(Shape.DIAMOND, Rank.ACE));
        deck3.addCard(new Card(Shape.DIAMOND, Rank.TWO));


        participants.receiveInitialDecks(
                new ArrayList<>(List.of(deck1, deck2, deck3))
        );

        assertThat(participants.isPlayerNotOver(0)).isTrue();
    }

    @Test
    @DisplayName("딜러가 카드를 더 받을 수 있다.")
    void isDealerNotOverTest() {
        Deck deck1 = new Deck();
        deck1.addCard(new Card(Shape.HEART, Rank.JACK));
        deck1.addCard(new Card(Shape.HEART, Rank.SIX));

        Deck deck2 = new Deck();
        deck2.addCard(new Card(Shape.SPADE, Rank.ACE));
        deck2.addCard(new Card(Shape.SPADE, Rank.TWO));

        Deck deck3 = new Deck();
        deck3.addCard(new Card(Shape.DIAMOND, Rank.ACE));
        deck3.addCard(new Card(Shape.DIAMOND, Rank.TWO));


        participants.receiveInitialDecks(
                new ArrayList<>(List.of(deck1, deck2, deck3))
        );

        assertThat(participants.isDealerNotOver()).isTrue();
    }

    @Test
    @DisplayName("게임 참가자들의 수를 확인한다.")
    void countTest() {
        assertThat(participants.count()).isEqualTo(3);
    }

    @Test
    @DisplayName("게임 플레이어의 수를 확인한다.")
    void countPlayersTest() {
        assertThat(participants.countPlayers()).isEqualTo(2);
    }
}
