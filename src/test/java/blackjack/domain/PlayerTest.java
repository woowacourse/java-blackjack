package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @DisplayName("카드가 21이 초과하지 않는다면 카드를 더 뽑을 수 있다.")
    @Test
    void testPlayerCanDrawCard() {
        // given
        CardDeck cardDeck = new CardDeck();
        CardDump cardDump = new CardDump();
        cardDeck.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardDeck.add(new Card(CardSuit.CLUB, CardRank.SEVEN));

        Player player = new Player("user1", cardDeck, cardDump);

        // when
        boolean canTakeExtraCard = player.canHit();

        // then
        assertThat(canTakeExtraCard).isTrue();
    }

    @DisplayName("카드가 21이 초과하지 않는다면 카드를 더 뽑을 수 있다.")
    @Test
    void testPlayerCanDrawCard_false() {
        // given
        CardDeck cardDeck = new CardDeck();
        CardDump cardDump = new CardDump();
        cardDeck.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardDeck.add(new Card(CardSuit.CLUB, CardRank.SEVEN));
        cardDeck.add(new Card(CardSuit.CLUB, CardRank.EIGHT));

        Player player = new Player("user1", cardDeck, cardDump);

        // when
        boolean canTakeExtraCard = player.canHit();

        // then
        assertThat(canTakeExtraCard).isFalse();
    }

    @DisplayName("플레이어의 카드 덱의 최종 점수를 계산한다")
    @Test
    void testPlayerTotalCardScore() {
        // given
        CardDeck cardDeck = new CardDeck();
        CardDump cardDump = new CardDump();
        cardDeck.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardDeck.add(new Card(CardSuit.CLUB, CardRank.EIGHT)); //17

        Player player = new Player("user1", cardDeck, cardDump);

        // when
        int totalScore = player.calculateTotalCardScore();
        assertThat(totalScore).isEqualTo(17);
    }

    @DisplayName("플레이어의 카드 덱에 에이스가 있고, 버스트가 아닐 때, 최대 점수를 계산한다")
    @Test
    void testPlayerTotalCardScore_hasAce_noBust() {
        // given
        CardDeck cardDeck = new CardDeck();
        CardDump cardDump = new CardDump();
        cardDeck.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardDeck.add(new Card(CardSuit.CLUB, CardRank.ACE)); //11 -> 20

        Player player = new Player("user1", cardDeck, cardDump);

        // when
        int totalScore = player.calculateTotalCardScore();
        assertThat(totalScore).isEqualTo(20);
    }

    @DisplayName("플레이어의 카드 덱에 에이스가 있고, 버스트일 때, 에이스를 1로 계산한다")
    @Test
    void testPlayerTotalCardScore_hasAce_Bust() {
        // given
        CardDeck cardDeck = new CardDeck();
        CardDump cardDump = new CardDump();
        cardDeck.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardDeck.add(new Card(CardSuit.CLUB, CardRank.SEVEN)); //16
        cardDeck.add(new Card(CardSuit.CLUB, CardRank.ACE)); //1선택 -> 17

        Player player = new Player("user1", cardDeck, cardDump);

        // when
        int totalScore = player.calculateTotalCardScore();
        assertThat(totalScore).isEqualTo(17);
    }

    @DisplayName("플레이어의 카드 덱에 에이스가 여러 개인 있는 경우 알맞은 합을 반환")
    @Test
    void testPlayerTotalCardScore_hasMultipleAce() {
        // given
        CardDeck cardDeck = new CardDeck();
        CardDump cardDump = new CardDump();
        cardDeck.add(new Card(CardSuit.CLUB, CardRank.ACE));
        cardDeck.add(new Card(CardSuit.CLUB, CardRank.ACE));
        cardDeck.add(new Card(CardSuit.CLUB, CardRank.NINE));

        Player player = new Player("user1", cardDeck, cardDump);

        // when
        int totalScore = player.calculateTotalCardScore();
        assertThat(totalScore).isEqualTo(21);
    }

    @DisplayName("플레이어는 자신의 카드 덱에 카드를 추가할 수 있다")
    @Test
    void testPlayerAddCard() {
        // given
        CardDeck cardDeck = new CardDeck();
        CardDump cardDump = new CardDump();
        Player player = new Player("user1", cardDeck, cardDump);

        // when
        player.addCard();

        // then
        assertThat(cardDeck.getDeckSize()).isEqualTo(1);
    }
}
