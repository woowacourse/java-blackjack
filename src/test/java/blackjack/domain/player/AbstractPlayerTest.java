package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.bet.BetMoney;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Deck;
import blackjack.domain.card.JustBlackjackDeck;
import blackjack.domain.card.JustTenSpadeDeck;
import blackjack.domain.card.JustTwoSpadeDeck;
import blackjack.domain.card.Suit;
import blackjack.domain.state.State;
import blackjack.domain.state.Stay;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AbstractPlayerTest {

    private AbstractPlayer abstractPlayer;

    @BeforeEach
    void setUp() {
        Deck deck = new JustTenSpadeDeck();
        abstractPlayer = new Participant(new Name("alien"), deck, new BetMoney(10));
    }

    @Test
    @DisplayName("player는 생성되면서 카드 두 장을 받는다.")
    void create() {
        List<Card> playerCards = abstractPlayer.getPlayerCards().get();

        assertThat(playerCards.get(0)).isEqualTo(Card.of(CardNumber.TEN, Suit.SPADE));
        assertThat(playerCards.get(1)).isEqualTo(Card.of(CardNumber.TEN, Suit.SPADE));
    }

    @Test
    @DisplayName("hit 메서드는 입력받은 카드를 카드뭉치에 저장한다.")
    void hit_test() {
        abstractPlayer.hit(Card.of(CardNumber.TEN, Suit.CLUB));

        List<Card> playerCards = abstractPlayer.getPlayerCards().get();

        assertThat(playerCards.get(0)).isEqualTo(Card.of(CardNumber.TEN, Suit.SPADE));
        assertThat(playerCards.get(1)).isEqualTo(Card.of(CardNumber.TEN, Suit.SPADE));
        assertThat(playerCards.get(2)).isEqualTo(Card.of(CardNumber.TEN, Suit.CLUB));
    }

    @Test
    @DisplayName("stay()를 하면 상태가 stay가 된다.")
    void stay_test() {
        abstractPlayer.stay();

        assertThat(abstractPlayer.canHit()).isFalse();
        assertThat(abstractPlayer.getState()).isInstanceOf(Stay.class);
    }

    @Test
    @DisplayName("플레이어의 상태를 반환한다.")
    void get_state_test() {
        State state = abstractPlayer.getState();

        assertThat(state).isInstanceOf(State.class);
    }

    @Test
    @DisplayName("isBust 메서드는 점수가 21을 초과했으면 참을 반환한다.")
    void check_bust_true() {
        abstractPlayer.hit(Card.of(CardNumber.TEN, Suit.CLUB));

        assertThat(abstractPlayer.isBust()).isTrue();
    }

    @Test
    @DisplayName("isBust 메서드는 점수가 21이하면 거짓을 반환한다.")
    void check_bust_false() {
        assertThat(abstractPlayer.isBust()).isFalse();
    }

    @Test
    @DisplayName("isBlackjack 메서드는 카드가 두장이면서 21점이면 참을 반환한다.")
    void check_blackjack() {
        Deck deck = new JustBlackjackDeck();
        BetMoney money = new BetMoney(10);
        AbstractPlayer abstractPlayer = new Participant(new Name("alien"), deck, money);

        assertThat(abstractPlayer.isBlackjack()).isTrue();
    }

    @Test
    @DisplayName("isDealer 메서드는 자신이 딜러면 참을 반환한다.")
    void check_dealer() {
        assertThat(abstractPlayer.isDealer()).isFalse();
    }

    @Test
    @DisplayName("Player 클래스가 가진 카드의 점수를 계산하여 반환한다.")
    void get_score() {
        assertThat(abstractPlayer.getScore()).isEqualTo(20);
    }

    @Test
    @DisplayName("Ace 가진 카드의 총합이 21을 넘지 않으면 Ace는 11로 계산한다.")
    void ace_calculate_11() {
        Deck deck = new JustTwoSpadeDeck();
        BetMoney money = new BetMoney(10);
        AbstractPlayer abstractPlayer = new Participant(new Name("alien"), deck, money);
        abstractPlayer.hit(Card.of(CardNumber.ACE, Suit.HEART));

        assertThat(abstractPlayer.getScore()).isEqualTo(15);
    }

    @Test
    @DisplayName("Ace 가진 카드의 총합이 21을 넘으면 Ace는 1로 계산한다.")
    void ace_calculate_1() {
        abstractPlayer.hit(Card.of(CardNumber.ACE, Suit.SPADE));

        assertThat(abstractPlayer.getScore()).isEqualTo(21);
    }

    @Test
    @DisplayName("equals, hashCode, toString 테스트")
    void equals() {
        Deck deck = new JustTwoSpadeDeck();
        int input = 10000;
        BetMoney money = new BetMoney(input);
        AbstractPlayer o1 = new Participant(new Name("alien"), deck, money);
        AbstractPlayer o2 = new Participant(new Name("alien"), deck, money);
        Object o = new Object();

        assertThat(o1.equals(o2)).isTrue();
        assertThat(o1.equals(o1)).isTrue();
        assertThat(o1.equals(o)).isFalse();
        assertThat(o1.hashCode() == o2.hashCode()).isTrue();
        assertThat(o1.toString()).isEqualTo(o2.toString());
    }
}
