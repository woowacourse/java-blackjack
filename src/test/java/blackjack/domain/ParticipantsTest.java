package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.deck.Card;
import blackjack.domain.deck.Hands;
import blackjack.domain.deck.Rank;
import blackjack.domain.deck.Shape;
import blackjack.domain.participants.Name;
import blackjack.domain.participants.Participants;
import blackjack.domain.participants.Player;
import blackjack.domain.participants.Players;
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

        assertThat(participants.getOnePlayer(0).getHands().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("딜러가 하나의 카드를 받는다.")
    void receiveDealerCardTest() {
        Card card = new Card(Shape.HEART, Rank.FIVE);

        participants.receiveDealerCard(card);

        assertThat(participants.getDealer().getHands().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("게임 참가자들이 초기 카드를 받는다")
    void initialDistributeTest() {
        Hands hands1 = new Hands(List.of(
                new Card(Shape.HEART, Rank.ACE),
                new Card(Shape.HEART, Rank.TWO))
        );

        Hands hands2 = new Hands(List.of(
                new Card(Shape.SPADE, Rank.ACE),
                new Card(Shape.SPADE, Rank.TWO))
        );

        Hands hands3 = new Hands(List.of(
                new Card(Shape.DIAMOND, Rank.ACE),
                new Card(Shape.DIAMOND, Rank.TWO))
        );


        participants.receiveInitialHands(
                new ArrayList<>(List.of(hands1, hands2, hands3))
        );

        assertThat(siso.getHands().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("참가자들의 결과를 계산한다.")
    void calculateResultTest() {
        Hands hands1 = new Hands(List.of(
                new Card(Shape.HEART, Rank.ACE),
                new Card(Shape.HEART, Rank.TWO))
        );

        Hands hands2 = new Hands(List.of(
                new Card(Shape.SPADE, Rank.ACE),
                new Card(Shape.SPADE, Rank.TWO))
        );

        Hands hands3 = new Hands(List.of(
                new Card(Shape.DIAMOND, Rank.ACE),
                new Card(Shape.DIAMOND, Rank.TWO))
        );


        participants.receiveInitialHands(
                new ArrayList<>(List.of(hands1, hands2, hands3))
        );

        Map<Player, Boolean> result = participants.calculateWinOrLose();
        assertThat(result.get(siso)).isFalse();
    }

    @Test
    @DisplayName("참가자가 카드를 더 받을 수 있다.")
    void isPlayerNotOverTest() {
        Hands hands1 = new Hands(List.of(
                new Card(Shape.HEART, Rank.ACE),
                new Card(Shape.HEART, Rank.TWO))
        );

        Hands hands2 = new Hands(List.of(
                new Card(Shape.SPADE, Rank.ACE),
                new Card(Shape.SPADE, Rank.TWO))
        );

        Hands hands3 = new Hands(List.of(
                new Card(Shape.DIAMOND, Rank.ACE),
                new Card(Shape.DIAMOND, Rank.TWO))
        );


        participants.receiveInitialHands(
                new ArrayList<>(List.of(hands1, hands2, hands3))
        );

        assertThat(participants.isPlayerNotOver(0)).isTrue();
    }

    @Test
    @DisplayName("딜러가 카드를 더 받을 수 있다.")
    void isDealerNotOverTest() {
        Hands hands1 = new Hands(List.of(
                new Card(Shape.HEART, Rank.JACK),
                new Card(Shape.HEART, Rank.SIX))
        );

        Hands hands2 = new Hands(List.of(
                new Card(Shape.SPADE, Rank.ACE),
                new Card(Shape.SPADE, Rank.TWO))
        );

        Hands hands3 = new Hands(List.of(
                new Card(Shape.DIAMOND, Rank.ACE),
                new Card(Shape.DIAMOND, Rank.TWO))
        );


        participants.receiveInitialHands(
                new ArrayList<>(List.of(hands1, hands2, hands3))
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
