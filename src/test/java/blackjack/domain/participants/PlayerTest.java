package blackjack.domain.participants;

import static blackjack.domain.ExceptionMessage.INVALID_PARTICIPANT_NAME_EMPTY;
import static blackjack.domain.ParticipantFixtures.BETTING_MONEY_1000;
import static blackjack.domain.card.Denomination.JACK;
import static blackjack.domain.card.Suit.DIAMOND;
import static blackjack.domain.card.Suit.SPADE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.dto.HandStatus;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PlayerTest {

    final Card CARD_FIRST = new Card(SPADE, JACK);
    final Card CARD_SECOND = new Card(DIAMOND, JACK);

    @DisplayName("플레이어는 이름과 베팅 금액을 가진다.")
    @Test
    void should_NotThrowException_When_CreateWithNameAndBettingMoney() {
        assertThatCode(() -> new Player("이름", BETTING_MONEY_1000))
                .doesNotThrowAnyException();
    }

    @DisplayName("빈 문자열이거나 공백인 이름은 허용하지 않는다.")
    @ParameterizedTest
    @NullAndEmptySource
    void should_ThrowException_When_NameIsEmpty(final String name) {
        assertThatThrownBy(() -> new Player(name, BETTING_MONEY_1000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_PARTICIPANT_NAME_EMPTY);
    }

    @DisplayName("플레이어는 카드 오픈 시 모든 카드를 확인한다.")
    @Test
    void should_OpenAllCard_When_PlayerOpenHandStatus() {
        final Player player = new Player("이름", BETTING_MONEY_1000);
        player.take(CARD_FIRST);
        player.take(CARD_SECOND);

        final HandStatus status = player.toHandStatus();
        final List<Card> openedCards = status.getCards();

        assertThat(openedCards).containsExactly(CARD_FIRST, CARD_SECOND);
    }


    @DisplayName("플레이어는 카드 합이 21 미만이면 히트 가능하다.")
    @Test
    void should_ReturnTrue_OfIsAbleToHit_When_HandSumUnder17() {
        final Player player = new Player("이름", BETTING_MONEY_1000);

        player.take(CARD_FIRST);
        player.take(CARD_SECOND);

        assertThat(player.isHitAble()).isTrue();
    }
}
