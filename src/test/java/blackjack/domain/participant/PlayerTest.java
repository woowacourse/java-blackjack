package blackjack.domain.participant;

import static blackjack.domain.card.CardNumber.ACE;
import static blackjack.domain.card.CardNumber.JACK;
import static blackjack.domain.card.CardNumber.KING;
import static blackjack.domain.card.CardNumber.NINE;
import static blackjack.domain.card.CardNumber.QUEEN;
import static blackjack.domain.card.CardNumber.TWO;
import static blackjack.domain.card.CardSymbol.DIAMOND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.playerstatus.Blackjack;
import blackjack.domain.participant.playerstatus.Bust;
import blackjack.domain.participant.playerstatus.Hit;
import blackjack.domain.participant.playerstatus.PlayerStatus;
import blackjack.domain.participant.playerstatus.Stay;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PlayerTest {

    @ParameterizedTest
    @CsvSource(value = {"TWO:false", "ACE:true"}, delimiter = ':')
    @DisplayName("카드의 합이 21을 초과하면 더 이상 카드를 뽑을 수 없다.")
    void isDrawable(CardNumber cardNumber, boolean expected) {
        // give
        final List<Card> cards = List.of(
                Card.of(DIAMOND, cardNumber),
                Card.of(DIAMOND, QUEEN),
                Card.of(DIAMOND, JACK));
        final Deck deck = Deck.from(() -> cards);

        final Player player = new Player("pobi", 1000);
        for (int i = 0; i < cards.size(); i++) {
            player.hit(deck);
        }

        // when
        final boolean actual = player.isDrawable();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("카드를 뽑은 후 상태를 업데이트한다.")
    void hit_bust() {
        // give
        final List<Card> cards = List.of(
                Card.of(DIAMOND, TWO),
                Card.of(DIAMOND, QUEEN),
                Card.of(DIAMOND, JACK));
        final Deck deck = Deck.from(() -> cards);

        final Player player = new Player("rick", 1000);
        for (int i = 0; i < cards.size(); i++) {
            player.hit(deck);
        }

        // when
        final PlayerStatus actual = player.getStatus();

        // then
        assertThat(actual).isEqualTo(Bust.getInstance());
    }

    @Test
    @DisplayName("카드를 뽑은 후 상태를 업데이트한다.")
    void hit_hit() {
        // give
        final List<Card> cards = List.of(
                Card.of(DIAMOND, ACE),
                Card.of(DIAMOND, QUEEN),
                Card.of(DIAMOND, JACK));
        final Deck deck = Deck.from(() -> cards);

        final Player player = new Player("rick", 1000);
        for (int i = 0; i < cards.size(); i++) {
            player.hit(deck);
        }

        // when
        final PlayerStatus actual = player.getStatus();

        // then
        assertThat(actual).isEqualTo(Hit.getInstance());
    }

    @Test
    @DisplayName("카드를 더 이상 받지 않는다면 상태를 업데이트한다.")
    void stay() {
        // give
        final List<Card> cards = List.of(
                Card.of(DIAMOND, TWO),
                Card.of(DIAMOND, NINE),
                Card.of(DIAMOND, JACK));
        final Deck deck = Deck.from(() -> cards);

        final Player player = new Player("rick", 1000);
        for (int i = 0; i < cards.size(); i++) {
            player.hit(deck);
        }

        // when
        player.stay();
        final PlayerStatus actual = player.getStatus();

        // then
        assertThat(actual).isEqualTo(Stay.getInstance());
    }

    @Test
    @DisplayName("블랙잭을 뽑고 더 이상 카드를 뽑지 않으면 상태를 업데이트한다.")
    void stay_blackjack() {
        // give
        final List<Card> cards = List.of(
                Card.of(DIAMOND, ACE),
                Card.of(DIAMOND, JACK));
        final Deck deck = Deck.from(() -> cards);

        final Player player = new Player("rick", 100);
        for (int i = 0; i < cards.size(); i++) {
            player.hit(deck);
        }

        // when
        player.stay();
        final PlayerStatus actual = player.getStatus();

        // then
        assertThat(actual).isEqualTo(Blackjack.getInstance());
    }

    @Test
    @DisplayName("게임준비를 위해 가진 카드를 초기화한다.")
    void init() {
        // give
        final List<Card> cards = List.of(
                Card.of(DIAMOND, QUEEN),
                Card.of(DIAMOND, KING),
                Card.of(DIAMOND, JACK));
        final Deck deck = Deck.from(() -> cards);

        final Player player = new Player("pobi", 1000);

        // when
        player.initCards(deck);
        final int actual = player.getCards().getValue().size();

        // then
        assertThat(actual).isEqualTo(2);
    }
    
    @Test
    @DisplayName("턴이 진행중일 때 상금을 계산하면 예외를 던진다.")
    void calculateProfit_exception() {
        // give
        final Player player = new Player("rick", 1000);

        // when
        // then
        assertThatThrownBy(() -> player.calculateProfit(19, false))
                .isInstanceOf(IllegalStateException.class);
    }
}
