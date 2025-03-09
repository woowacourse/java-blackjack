package blackjack.gamer;

import blackjack.domain.GameRule;
import blackjack.domain.card.CardFixture;
import blackjack.domain.card.Cards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    @Test
    @DisplayName("플레이어는 생성 시점에 0장의 카드를 가진다")
    void initializedPlayerShouldHave0Card() {
        // given
        Player player = GameParticipantFixture.createPlayer("강산");

        // when
        // then
        assertThat(player.getHand().size()).isEqualTo(0);
    }

    @Test
    @DisplayName("플레이어는 히트를 결정할 수 있다")
    void canDecideToHit() {
        // given
        Function<Player, Boolean> alwaysReturnTrueHitDecision = shouldHitIsAlwaysReturnTruePlayer -> true;
        Player player = GameParticipantFixture.createPlayer("강산", alwaysReturnTrueHitDecision);

        // when
        // then
        assertThat(player.shouldHit()).isTrue();
    }

    @Test
    @DisplayName("플레이어는 히트를 하지 않을 수 있다")
    void canDecideNotToHit() {
        // given
        Function<Player, Boolean> alwaysReturnFalseHitDecision = shouldHitIsAlwaysReturnTruePlayer -> false;
        Player player = GameParticipantFixture.createPlayer("강산", alwaysReturnFalseHitDecision);

        // when
        // then
        assertThat(player.shouldHit()).isFalse();
    }

    @Test
    @DisplayName("플레이어는 초기에 카드를 보여줄 때, 모두 보여준다.")
    void whenAfterInitialDealingShouldShowFirstCard() {
        // given
        Player player = GameParticipantFixture.createPlayer("강산");
        for (int i = 0; i < GameRule.INITIAL_DEALING_CARD_COUNT.getValue(); i++) {
            player.drawCard(CardFixture.createCard());
        }

        // when
        Cards cards = player.showCards();

        // then
        assertThat(cards.size()).isEqualTo(GameRule.INITIAL_DEALING_CARD_COUNT.getValue());
    }
}
