package blackjack.player;

import static blackjack.Fixtures.BET_AMOUNT_10000;
import static blackjack.Fixtures.CARDS_OF_BUST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.Score;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Pattern;
import blackjack.domain.deck.Deck;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.player.AddCardOrNot;
import blackjack.domain.participant.player.Player;
import blackjack.fixedCaradsGenerator.FixedCardsGenerator;
import java.util.List;
import java.util.Stack;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    @DisplayName("플레이어를 생성한다.")
    void createPlayer() {
        Name name = new Name("폴로");
        assertThatCode(() -> new Player(name, BET_AMOUNT_10000))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("플레이어의 이름이 '딜러'일 때 예외가 발생한다.")
    void cannotCreateSameName() {
        Name name = new Name("딜러");
        assertThatThrownBy(() -> new Player(name, BET_AMOUNT_10000))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("플레이어는 카드를 받을 수 있다.")
    void hit() {
        Player player = new Player(new Name("폴로"), BET_AMOUNT_10000);

        Card card = new Card(CardNumber.ACE, Pattern.HEART);

        assertThatCode(() -> player.hit(card))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("플레이어는 받은 카드의 점수 합계를 구할 수 있다")
    void calculateScore() {
        Player player = new Player(new Name("폴로"), BET_AMOUNT_10000);
        Card card1 = new Card(CardNumber.ACE, Pattern.HEART);
        Card card2 = new Card(CardNumber.EIGHT, Pattern.HEART);
        Card card3 = new Card(CardNumber.SIX, Pattern.HEART);
        player.hit(card1);
        player.hit(card2);
        player.hit(card3);

        assertThat(player.calculateScore()).isEqualTo(new Score(15));
    }

    @Test
    @DisplayName("플레이어는 현재 가지고 있는 카드를 반환할수 있다.")
    void showCards() {
        Player player = new Player(new Name("폴로"), BET_AMOUNT_10000);
        Card card1 = new Card(CardNumber.ACE, Pattern.HEART);
        Card card2 = new Card(CardNumber.EIGHT, Pattern.HEART);
        Card card3 = new Card(CardNumber.SIX, Pattern.HEART);
        player.hit(card1);
        player.hit(card2);
        player.hit(card3);

        assertThat(player.showCards()).contains(card1, card2, card3);
    }

    @Test
    @DisplayName("플레이어는 자신의 버스트 여부를 반환할 수 있다.")
    void isBust() {
        Player player = new Player(new Name("폴로"), BET_AMOUNT_10000);
        player.hit(CARDS_OF_BUST);

        assertThat(player.isBust()).isTrue();
    }

    @DisplayName("추가 카드를 받는 기능")
    @Nested
    class HitAdditionalCardTest {
        @DisplayName("버스트가 아니고, 카드를 받겠다고 결정하면 카드를 받는다.")
        @Test
        void hitCardWhenNotBust() {
            //given
            Player soni = new Player(new Name("소니"), BET_AMOUNT_10000);
            soni.hit(List.of(new Card(CardNumber.KING, Pattern.DIAMOND), new Card(CardNumber.KING, Pattern.CLOVER)));
            Card twoHeart = new Card(CardNumber.TWO, Pattern.HEART);
            Deck deck = new Deck(() -> {
                Stack<Card> cards = new Stack<>();
                cards.push(twoHeart);
                return cards;
            });
            //when
            soni.hitAdditionalCardFrom(deck, (name) -> AddCardOrNot.YES, (player) -> {
            });
            //then
            assertThat(soni.showCards()).contains(twoHeart);
        }

        @DisplayName("버스트가 아니고, 카드를 받지 않겠다 결정하면 카드를 받지 않는다.")
        @Test
        void dontHitWhenDecisionIsNo() {
            //given
            Player polo = new Player(new Name("폴로"), BET_AMOUNT_10000);
            //when
            polo.hitAdditionalCardFrom(new Deck(new FixedCardsGenerator()), (name) -> AddCardOrNot.NO, (player) -> {
            });
            //then
            assertThat(polo.showCards()).isEmpty();
        }

        @DisplayName("버스트이면 카드를 받지 않는다.")
        @Test
        void dontHitWhenPlayerIsBust() {
            //given
            Player rosie = new Player(new Name("로지"), BET_AMOUNT_10000);
            List<Card> already = List.of(new Card(CardNumber.KING, Pattern.DIAMOND),
                    new Card(CardNumber.KING, Pattern.CLOVER),
                    new Card(CardNumber.KING, Pattern.HEART));
            rosie.hit(already);
            //when
            rosie.hitAdditionalCardFrom(new Deck(new FixedCardsGenerator()), (name) -> AddCardOrNot.YES, (player) -> {
            });
            //then
            assertThat(rosie.showCards()).containsExactlyElementsOf(already);
        }

        @DisplayName("21이면 카드를 받을 수 있다.")
        @Test
        void canHitWhenPlayerIsBlackjack() {
            //given
            Player kiara = new Player(new Name("키아라"), BET_AMOUNT_10000);
            List<Card> already = List.of(new Card(CardNumber.TEN, Pattern.CLOVER),
                    new Card(CardNumber.ACE, Pattern.HEART));
            kiara.hit(already);
            //when
            kiara.hitAdditionalCardFrom(new Deck(new FixedCardsGenerator()), (name) -> AddCardOrNot.YES, (player) -> {
            });
            //then
            assertThat(kiara.showCards()).hasSize(3);
        }
    }
}
