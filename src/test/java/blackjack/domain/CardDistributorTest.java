package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.cards.Card;
import blackjack.domain.cards.CardValue;
import blackjack.domain.cards.Deck;
import blackjack.domain.cards.Shape;
import blackjack.domain.names.Name;
import blackjack.domain.participants.Betting;
import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Participant;
import blackjack.domain.participants.Player;
import blackjack.domain.participants.Players;
import blackjack.dto.Participants;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardDistributorTest {
    CardDistributor cardDistributor;

    @BeforeEach
    void setUp() {
        Deck deck = new Deck(Arrays.asList(
            Card.valueOf(Shape.DIAMOND, CardValue.TEN),
            Card.valueOf(Shape.SPADE, CardValue.EIGHT),
            Card.valueOf(Shape.DIAMOND, CardValue.ACE),
            Card.valueOf(Shape.HEART, CardValue.EIGHT),
            Card.valueOf(Shape.SPADE, CardValue.TEN),
            Card.valueOf(Shape.CLOVER, CardValue.EIGHT),
            Card.valueOf(Shape.CLOVER, CardValue.TEN),
            Card.valueOf(Shape.SPADE, CardValue.SEVEN)));
        cardDistributor = new CardDistributor(deck);
    }

    @Test
    @DisplayName("게임 시작시 성상적으로 카드를 2장씩 분배하는지 확인")
    void distributeStartingCardsTo() {
        Dealer dealer = new Dealer();
        Players players = new Players(Arrays.asList(
            new Player(new Name("pobi"), Betting.valueOf("1")),
            new Player(new Name("jason"), Betting.valueOf("1")),
            new Player(new Name("root"), Betting.valueOf("1"))
        ));
        Participants participants = Participants.valueOf(dealer, players);

        cardDistributor.distributeStartingCardsTo(participants);

        for (Participant participant : participants.unwrap()) {
            assertThat(participant.getHand().size()).isEqualTo(2);
        }
    }


    @Test
    @DisplayName("카드를 성공적으로 분배하는지 확인")
    void distributeCardTo() {
        Player root = new Player(new Name("root"), Betting.valueOf("1"));
        cardDistributor.distributeCardTo(root);
        assertThat(root.getHand()).containsExactly(Card.valueOf(Shape.DIAMOND, CardValue.TEN));
    }
}
