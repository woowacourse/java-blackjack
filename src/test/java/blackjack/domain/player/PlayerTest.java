package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;
import blackjack.domain.card.Cards;
import blackjack.domain.state.Finished;
import blackjack.domain.state.State;
import blackjack.domain.state.Stay;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PlayerTest {

    private Cards firstDrawTwoCards;
    private Player player;

    @BeforeEach
    void setUp() {
        firstDrawTwoCards = createFirstDrawTwoCards();
        player = new Player("slow", firstDrawTwoCards);
    }

    private static Cards createFirstDrawTwoCards() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(CardPattern.HEART, CardNumber.JACK));
        cards.add(new Card(CardPattern.CLOVER, CardNumber.KING));
        return new Cards(cards);
    }

    @Test
    @DisplayName("플레이어를 정상 생성한다.")
    void createPlayer() {
        assertThatCode(() -> new Player("slow", firstDrawTwoCards))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("플레이어는 처음 받은 카드 두 장을 모두 보여준다.")
    void openFirstCards() {
        final List<Card> actual = player.openFirstCards();

        assertThat(actual).isEqualTo(firstDrawTwoCards.getCards());
    }

    @Test
    @DisplayName("플레이어는 카드를 받아서 본인의 카드 리스트에 추가할 수 있다.")
    void receiveCard() {
        final Card card = new Card(CardPattern.DIAMOND, CardNumber.SIX);

        player.receiveCard(card);

        assertThat(player.getCards().contains(card));
    }

    @Test
    @DisplayName("가지고 있는 모든 카드를 보여준다.")
    void openAllOfCards() {
        final List<Card> expected = firstDrawTwoCards.getCards();

        final List<Card> actual = player.openAllOfCards();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("플레이어는 베팅을 할 수 있다.")
    void betMoney() {
        final int bettingMoney = 1000;

        player.betMoney(bettingMoney);
        final int actual = player.getBettingMoney();

        assertThat(actual).isEqualTo(bettingMoney);
    }

    @Test
    @DisplayName("이미 베팅 금액을 입력한 경우, 다시 베팅을 하려고 하면 예외가 발생한다.")
    void betMoneyAgain() {
        player.betMoney(1000);

        assertThatThrownBy(() -> player.betMoney(2000))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("베팅은 한 번만 할 수 있습니다.");
    }

    @Test
    @DisplayName("DrawStatus 응답이 YES 인 경우, Hit 할 수 있으로 true를 반환한다.")
    void checkDrawStatusYes() {
        boolean actual = player.isHit(DrawStatus.YES);

        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("DrawStatus 응답이 NO 인 경우, Hit 할 수 없으므로 false를 반환한다.")
    void checkDrawStatusNo() {
        boolean actual = player.isHit(DrawStatus.NO);

        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("플레이어의 이름이 공백인 경우, 예외를 발생한다.")
    void checkEmptyName() {
        assertThatThrownBy(() -> new Player("", firstDrawTwoCards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 공백이 될 수 없습니다.");
    }

    @Test
    @DisplayName("플레이어는 hit 상태일 때 stay 를 할 수 있다.")
    void stay() {
        player.stay();

        final State actual = player.getState();

        assertThat(actual).isInstanceOf(Stay.class);
    }

    @ParameterizedTest
    @MethodSource("provideFinishedPlayer")
    @DisplayName("플레이어가 blackjack, bust, stay 인 경우 finished 상태가 된다.")
    void checkFinishedState(Player player) {
        final State actual = player.getState();

        assertThat(actual).isInstanceOf(Finished.class);
    }

    static Stream<Arguments> provideFinishedPlayer() {
        final Cards blackjackCards = new Cards(List.of(
                new Card(CardPattern.HEART, CardNumber.JACK),
                new Card(CardPattern.HEART, CardNumber.ACE)
        ));
        final Cards bustCards = new Cards(List.of(
                new Card(CardPattern.HEART, CardNumber.JACK),
                new Card(CardPattern.HEART, CardNumber.ACE)
        ));
        final Player stayPlayer = new Player("java", createFirstDrawTwoCards());
        stayPlayer.stay();

        return Stream.of(Arguments.of(
                new Player("slow", blackjackCards),
                new Player("pobi", bustCards),
                stayPlayer
        ));
    }
}
