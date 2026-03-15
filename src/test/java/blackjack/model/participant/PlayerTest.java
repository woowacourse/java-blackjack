package blackjack.model.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.card.Card;
import blackjack.model.card.Rank;
import blackjack.model.card.Suit;
import blackjack.model.cardDeck.CardDeck;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    CardDeck mustPickFive = CardDeck.of(cards -> Card.createOpenedCard(Rank.FIVE, Suit.CLOVER));
    CardDeck mustPickTen = CardDeck.of(cards -> Card.createOpenedCard(Rank.TEN, Suit.CLOVER));
    CardDeck mustPickAce = CardDeck.of(cards -> Card.createOpenedCard(Rank.ACE, Suit.CLOVER));

    @Test
    @DisplayName("플레이어는 2 장의 오픈된 카드를 뽑는다.")
    void pickInitialCards() {
        // given
        Player player = Player.of("player1", 1000);
    
        // when
        player.pickInitialCards(mustPickTen);

        //then
        List<Card> cards = player.getOpenedCards();
        assertThat(cards.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("최종 수익이 -(배팅 금액)인 새로운 플레이어를 반환한다.")
    void lose() {
        //given
        Player player = Player.of("player1", 1000);

        //when & then
        assertThat(player.lose().getPrize())
                .isEqualTo(-1000);
    }

    @Test
    @DisplayName("점수가 21 점을 초과하면 true를 반환한다.")
    void isBustTrue() {
        // given
        Player player = Player.of("player", 1000);

        // when
        player.pickAdditionalCard(mustPickTen);
        player.pickAdditionalCard(mustPickTen);
        player.pickAdditionalCard(mustPickTen); // pick 30

        // then
        assertThat(player.isBust()).isTrue();
    }

    @Test
    @DisplayName("점수가 21 점 이하이면 false를 반환한다.")
    void isBustFalse() {
        // given
        Player player = Player.of("player", 1000);

        // when
        player.pickAdditionalCard(mustPickTen); // pick 10

        // then
        assertThat(player.isBust()).isFalse();
    }

    @Test
    @DisplayName("초기 두 장의 카드가 21점이면 true를 반환한다.")
    void isBlackjackTrue() {
        // given
        Player player = Player.of("player", 1000);

        // when
        player.pickAdditionalCard(mustPickTen);
        player.pickAdditionalCard(mustPickAce);     // 10 + 11 = 21

        // then
        assertThat(player.isBlackjack()).isTrue();
    }

    @Test
    @DisplayName("초기 두 장의 카드가 21점이 아니몀ㄴ false를 반환한다.")
    void isBlackjackFalse() {
        // given
        Player player = Player.of("player", 1000);

        // when
        player.pickAdditionalCard(mustPickTen);
        player.pickAdditionalCard(mustPickFive);    //10 + 5 = 15

        // then
        assertThat(player.isBlackjack()).isFalse();
    }

    @Test
    @DisplayName("카드 덱에서 추가 1장을 더 가지고 온다.")
    void pickAdditionalCard() {
        // given
        Player player = Player.of("player", 1000);

        // when
        player.pickAdditionalCard(mustPickAce);

        // then
        assertThat(player.getAllCard().size()).isEqualTo(1);
        assertThat(player.getAllCard().getFirst().isAce()).isTrue();
    }
}