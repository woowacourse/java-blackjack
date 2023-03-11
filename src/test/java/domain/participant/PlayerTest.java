package domain.participant;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardPattern;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class PlayerTest {

    private String playerName;
    private Card card;
    private Player player;

    @BeforeEach
    void init() {
        // given
        playerName = "pobi";
        card = Card.create(CardPattern.HEART, CardNumber.ACE);
        player = Player.create(playerName);
    }

    @Test
    @DisplayName("create()를 호출하면, Player가 생성된다")
    void create_whenCall_thenSuccess() {
        final Player player = assertDoesNotThrow(() -> Player.create(playerName));

        assertThat(player)
                .isExactlyInstanceOf(Player.class);
    }

    @Test
    @DisplayName("isDealerName()은 파라미터로 입력된 name이 '딜러'인지 판단한다")
    void create_givenInvalidName_thenFail() {
        assertThatThrownBy(() -> Player.create("딜러"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어는 '딜러'라는 이름을 가질 수 없습니다.");
    }

    @Test
    @DisplayName("addCard()는 카드를 건네주면 참가자의 카드에 추가한다")
    void addCard_givenCard_thenSuccess() {
        // when
        player.addCard(card);

        // then
        final ParticipantCard participantCard = player.hand;
        final List<Card> cards = participantCard.getCards();

        assertThat(cards)
                .hasSize(1);
    }

    @MethodSource(value = "domain.helper.ParticipantTestHelper#makeCards")
    @ParameterizedTest(name = "calculateScore()는 호출하면 점수를 계산한다")
    void calculateScore_whenCall_thenReturnScore(final List<Card> cards, final int expected) {
        // given
        cards.forEach(player::addCard);

        // when
        final int score = player.calculateScore();

        // then
        assertThat(score)
                .isSameAs(expected);
    }

    @MethodSource(value = "domain.helper.ParticipantTestHelper#makeBustCard")
    @ParameterizedTest(name = "isBust()는 호출하면 버스트인지 확인한다")
    void isBust_whenCall_thenReturnIsBust(final List<Card> cards, final boolean expected) {
        // given
        cards.forEach(player::addCard);

        // when
        final boolean actual = player.isBust();

        // then
        assertThat(actual)
                .isSameAs(expected);
    }

    @MethodSource(value = "domain.helper.ParticipantTestHelper#makeBlackJackCard")
    @ParameterizedTest(name = "isBlackJack()은 호출하면 블랙잭인지 확인한다")
    void isBlackJack_whenCall_thenReturnIsBust(final List<Card> cards, final boolean expected) {
        // given
        cards.forEach(player::addCard);

        // when
        final boolean actual = player.isBlackJack();

        // then
        assertThat(actual)
                .isSameAs(expected);
    }
}
