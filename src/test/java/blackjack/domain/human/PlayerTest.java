package blackjack.domain.human;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.cards.card.Card;
import blackjack.domain.cards.card.denomination.Denomination;
import blackjack.domain.cards.card.denomination.Suit;
import blackjack.domain.participant.human.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    @DisplayName("참여자 객체 생성 기능 테스트")
    public void createTest() {
        Player player = Player.from("test");
        assertThat(player.getName())
                .isEqualTo("test");
    }

    @Test
    @DisplayName("참여자 객체 카드 추가 기능 테스트")
    public void addCardTest() {
        // given
        Player player = Player.from("test");
        Card card5 = Card.of(Denomination.fromInitial("5"), Suit.SPADE);
        Card card6 = Card.of(Denomination.fromInitial("6"), Suit.SPADE);

        // when
        player.addCard(card5);
        player.addCard(card6);

        // then
        assertThat(player.getPoint())
                .isEqualTo(11);
    }

    @Test
    @DisplayName("참여자 객체 카드 2개인지 확인 기능 테스트")
    public void isThatSize() {
        // given
        Player player = Player.from("test");
        Card card5 = Card.of(Denomination.fromInitial("5"), Suit.SPADE);
        Card card6 = Card.of(Denomination.fromInitial("6"), Suit.SPADE);

        // when
        player.addCard(card5);
        player.addCard(card6);

        // then
        assertThat(player.isCardsThatSize(2))
                .isTrue();
    }

    @Test
    @DisplayName("카드모음 포인트 올바른지 테스트")
    public void equalPointTest() {
        // given
        Player player = Player.from("test");
        Card card5 = Card.of(Denomination.fromInitial("2"), Suit.SPADE);
        Card card6 = Card.of(Denomination.fromInitial("9"), Suit.SPADE);

        // when
        player.addCard(card5);
        player.addCard(card6);

        // then
        assertThat(player.getPoint())
                .isEqualTo(11);
    }
}
