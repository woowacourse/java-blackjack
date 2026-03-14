package blackjack.model.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.common.error.ErrorCode;
import blackjack.dto.CardDto;
import blackjack.model.card.Card;
import blackjack.model.card.Rank;
import blackjack.model.card.Suit;
import blackjack.model.carddeck.CardDeck;
import blackjack.model.money.Money;
import blackjack.model.result.PlayerResult;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PlayerTest {


    @ParameterizedTest
    @ValueSource(strings = {
            " name",
            "name ",
            " name "
    })
    @DisplayName("이름에 공백으로 시작하거나 끝나면 예외 발생")
    void invalidNameTest(String name) {
        // when & then
        assertThatThrownBy(() -> Player.of(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorCode.NAME_STARTS_OR_ENDS_WITH_SPACE.getMessage());
    }

    @Test
    @DisplayName("이름에 공백으로 시작하거나 끝나면 예외 발생")
    void emptyNameTest() {
        // given
        String name = "";

        // when & then
        assertThatThrownBy(() -> Player.of(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorCode.NO_NAME_PARTICIPANT_NAME.getMessage());
    }

    @Test
    @DisplayName("첫 카드 뽑기 테스트")
    void pickInitCardsTest() {
        // given
        Card tenClover = Card.of(Rank.TEN, Suit.CLOVER);
        Player player = Player.of("player");

        // when
        player.pickInitCards(CardDeck.of(cards -> tenClover));

        // then
        List<CardDto> cards = player.getAllCards();
        assertThat(cards).hasSize(2);
        assertThat(cards).containsExactlyInAnyOrder(
                CardDto.from(tenClover),
                CardDto.from(tenClover)
        );
    }

    @Test
    @DisplayName("카드 덱에서 추가 1장 더 가져오기")
    void pickAdditionalTest() {
        // given
        Card tenClover = Card.of(Rank.TEN, Suit.CLOVER);
        Player player = Player.of("player");
        CardDeck mustPickTenClover = CardDeck.of(cards -> tenClover);

        // when
        player.pickAdditionalCard(mustPickTenClover);

        // then
        assertThat(player.getAllCards()).hasSize(1);
        assertThat(player.getAllCards().getFirst()).isEqualTo(CardDto.from(tenClover));
    }

    @Test
    @DisplayName("버스트 테스트")
    void isBustTest() {
        // given
        Card tenClover = Card.of(Rank.TEN, Suit.CLOVER);
        CardDeck mustPickTenClover = CardDeck.of(cards -> tenClover);

        Player player1 = Player.of("player1");
        Player player2 = Player.of("player2");

        // when
        player1.pickAdditionalCard(mustPickTenClover);
        player1.pickAdditionalCard(mustPickTenClover);
        player1.pickAdditionalCard(mustPickTenClover); // pick 30

        player2.pickAdditionalCard(mustPickTenClover); // pick 10

        // then
        assertThat(player1.isBust()).isTrue();
        assertThat(player2.isBust()).isFalse();
    }

    @Test
    @DisplayName("손익 계산 테스트")
    void calculateProfitTest() {
        // given
        CardDeck aceCardDeck = CardDeck.of(cards -> Card.of(Rank.ACE, Suit.CLOVER));
        CardDeck tenCardDeck = CardDeck.of(cards -> Card.of(Rank.TEN, Suit.CLOVER));

        Player player1 = Player.of("player1");
        player1.bet(10_000);

        player1.pickAdditionalCard(aceCardDeck);
        player1.pickAdditionalCard(tenCardDeck);

        // when
        Money money = player1.calculateProfit(PlayerResult.WIN);

        // then
        assertThat(money).isEqualTo(Money.of(15_000));
    }
}
