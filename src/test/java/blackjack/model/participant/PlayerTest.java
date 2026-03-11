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

    CardDeck mustPickFive = CardDeck.of(cards -> Card.openedCard(Rank.FIVE, Suit.CLOVER));
    CardDeck mustPickTen = CardDeck.of(cards -> Card.openedCard(Rank.TEN, Suit.CLOVER));

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
    @DisplayName("플레이어는 점수가 21점 이하이면, 카드를 추가로 뽑을 수 있다.")
    void canPick() {
        //given
        Player player = Player.of("player1", 1000);

        player.pickAdditionalCard(mustPickFive);
        player.pickAdditionalCard(mustPickFive); // 총 10점

        //when & then
        assertThat(!player.isBust()).isTrue();
    }

    @Test
    @DisplayName("플레이어는 점수가 21점 초과이면, 카드를 추가로 뽑을 수 없다.")
    void canNotPick() {
        //given
        Player player = Player.of("player1", 1000);

        player.pickAdditionalCard(mustPickTen);
        player.pickAdditionalCard(mustPickTen);
        player.pickAdditionalCard(mustPickTen); // 총 30점

        //when & then
        assertThat(!player.isBust()).isFalse();
    }

    @Test
    @DisplayName("최종 수익이 0인 새로운 플레이어를 반환한다.")
    void bust() {
        //given
        Player player = Player.of("player1", 1000);

        //when & then
        assertThat(player.bust().getPrize())
                .isEqualTo(0);
    }
}