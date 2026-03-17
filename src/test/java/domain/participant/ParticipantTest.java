package domain.participant;

import static message.ErrorMessage.INITIAL_CARD_SIZE_MISMATCH;
import static message.ErrorMessage.PARTICIPANT_MUST_NOT_HAVE_INITIAL_CARDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import domain.card.Card;
import domain.enums.Rank;
import domain.enums.Suit;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ParticipantTest {

    private Participant player;
    private Participant dealer;

    @BeforeEach
    void set_up() {
        player = new Player(new Name("stark"));
        dealer = new Dealer();
    }

    @DisplayName("게임 시작 후 플레이어가 2장의 카드를 분배 받는다.")
    @Test
    void 게임_시작_후_플레이어_2장의_카드_분배를_받는다() {
        //given
        //when
        player.receiveInitialCards(List.of(new Card(Rank.FIVE, Suit.CLOVER), new Card(Rank.SIX, Suit.CLOVER)));
        //then
        assertThat(player.getHand().size()).isEqualTo(2);
    }

    @DisplayName("플레이어가 이미 카드를 가지고 있으면 카드를 2장 분배 받으려고 하면 예외가 발생한다.")
    @Test
    void 플레이어_이미_카드_소지_시_2장_카드_분배_시_예외_발생() {
        //given
        //when
        player.addCard(new Card(Rank.JACK, Suit.CLOVER));
        //then
        assertThatThrownBy(() ->
                player.receiveInitialCards(
                        List.of(new Card(Rank.FIVE, Suit.CLOVER),
                                new Card(Rank.SIX, Suit.CLOVER))
                ))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(PARTICIPANT_MUST_NOT_HAVE_INITIAL_CARDS.getMessage());
    }

    private static Stream<Arguments> initialCards() {
        return Stream.of(
                Arguments.of(List.of(new Card(Rank.FIVE, Suit.CLOVER), new Card(Rank.SIX, Suit.CLOVER),
                        new Card(Rank.FIVE, Suit.HEART), new Card(Rank.SIX, Suit.DIAMOND))),
                Arguments.of(List.of(new Card(Rank.JACK, Suit.CLOVER), new Card(Rank.QUEEN, Suit.CLOVER),
                        new Card(Rank.TWO, Suit.CLOVER))),
                Arguments.of(List.of(new Card(Rank.JACK, Suit.CLOVER)))
        );
    }

    @DisplayName("카드 초기 분배 시 2장이 아닌 카드는 분배 시 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("initialCards")
    void 카드_초기_분배_시_2장_아닌_경우_예외_발생(List<Card> initialCards) {
        //given
        //when
        //then
        assertThatThrownBy(() -> player.receiveInitialCards(initialCards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INITIAL_CARD_SIZE_MISMATCH.getMessage());
    }

    private static Stream<Arguments> safeScoreCards() {
        return Stream.of(
                Arguments.of(List.of(new Card(Rank.FIVE, Suit.CLOVER), new Card(Rank.SIX, Suit.CLOVER)), 11),
                Arguments.of(List.of(new Card(Rank.ACE, Suit.CLOVER), new Card(Rank.ACE, Suit.DIAMOND),
                        new Card(Rank.SEVEN, Suit.CLOVER)), 19),
                Arguments.of(List.of(new Card(Rank.ACE, Suit.CLOVER), new Card(Rank.ACE, Suit.DIAMOND),
                        new Card(Rank.ACE, Suit.SPADE), new Card(Rank.ACE, Suit.HEART),
                        new Card(Rank.JACK, Suit.CLOVER)), 14)
        );
    }

    @DisplayName("카드 점수 합계를 정상적으로 계산한다.")
    @ParameterizedTest
    @MethodSource("safeScoreCards")
    public void 카드_점수_합계를_정상적으로_계산한다(List<Card> cards, int expectedValue) {
        cards.forEach(dealer::addCard);
        int score = dealer.getScore();

        assertThat(score).isEqualTo(expectedValue);
    }

    @DisplayName("카드 점수 합계가 21을 초과하면 버스트를 판정한다.")
    @Test
    public void 카드_점수_합계가_21을_넘으면_버스트를_판정한다() {
        List<Card> burstCards = List.of(new Card(Rank.JACK, Suit.CLOVER), new Card(Rank.QUEEN, Suit.CLOVER),
                new Card(Rank.TWO, Suit.CLOVER));
        burstCards.forEach(player::addCard);

        assertThat(player.isBust()).isTrue();
    }

    @DisplayName("플레이어와 딜러가 각자의 기준에 어긋나면 카드를 드로우 할 수 없다")
    @Test
    void 기준_어긋날_때_카드_드로우_예외_발생() {
        //given
        //when
        List<Card> playerCards = List.of(new Card(Rank.JACK, Suit.CLOVER), new Card(Rank.QUEEN, Suit.CLOVER),
                new Card(Rank.TWO, Suit.CLOVER));
        List<Card> dealerCards = List.of(new Card(Rank.SEVEN, Suit.CLOVER), new Card(Rank.QUEEN, Suit.CLOVER));

        //then
        assertSoftly(softAssertions -> {
            softAssertions.assertThat(player.canDraw()).isTrue();
            softAssertions.assertThat(dealer.canDraw()).isTrue();

            playerCards.forEach(player::addCard);
            dealerCards.forEach(dealer::addCard);

            softAssertions.assertThat(player.canDraw()).isFalse();
            softAssertions.assertThat(dealer.canDraw()).isFalse();
        });
    }

    @DisplayName("분배된 카드를 알맞게 들고 있는지 확인한다")
    @Test
    void 분배된_카드_확인() {
        //given
        //when
        player.addCard(new Card(Rank.JACK, Suit.CLOVER));
        player.addCard(new Card(Rank.QUEEN, Suit.CLOVER));
        //then
        assertThat(player.getHand())
                .containsExactly(new Card(Rank.JACK, Suit.CLOVER), new Card(Rank.QUEEN, Suit.CLOVER));
    }
}
