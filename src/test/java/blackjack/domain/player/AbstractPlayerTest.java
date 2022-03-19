package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.bet.BetMoney;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Deck;
import blackjack.domain.card.JustBlackjackDeck;
import blackjack.domain.card.JustTenSpadeDeck;
import blackjack.domain.card.JustTwoSpadeDeck;
import blackjack.domain.card.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

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

        assertThat(playerCards.get(0)).isEqualTo(Card.of(CardNumber.TEN, Type.SPADE));
        assertThat(playerCards.get(1)).isEqualTo(Card.of(CardNumber.TEN, Type.SPADE));
    }

    @Test
    @DisplayName("hit 메서드는 입력받은 카드를 카드뭉치에 저장한다.")
    void hit_test() {
        abstractPlayer.hit(Card.of(CardNumber.TEN, Type.CLOVER));

        List<Card> playerCards = abstractPlayer.getPlayerCards().get();

        assertThat(playerCards.get(0)).isEqualTo(Card.of(CardNumber.TEN, Type.SPADE));
        assertThat(playerCards.get(1)).isEqualTo(Card.of(CardNumber.TEN, Type.SPADE));
        assertThat(playerCards.get(2)).isEqualTo(Card.of(CardNumber.TEN, Type.CLOVER));
    }

    @Test
    @DisplayName("isBust 메서드는 점수가 21을 초과했으면 참을 반환한다.")
    void check_bust_true() {
        abstractPlayer.hit(Card.of(CardNumber.TEN, Type.CLOVER));

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
        abstractPlayer.hit(Card.of(CardNumber.ACE, Type.HEART));

        assertThat(abstractPlayer.getScore()).isEqualTo(15);
    }

    @Test
    @DisplayName("Ace 가진 카드의 총합이 21을 넘으면 Ace는 1로 계산한다.")
    void ace_calculate_1() {
        abstractPlayer.hit(Card.of(CardNumber.ACE, Type.SPADE));

        assertThat(abstractPlayer.getScore()).isEqualTo(21);
    }
}
