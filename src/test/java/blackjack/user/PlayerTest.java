package blackjack.user;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.card.Card;
import blackjack.card.CardDeck;
import blackjack.card.CardHand;
import blackjack.card.Denomination;
import blackjack.card.Suit;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PlayerTest {

    @Nested
    @DisplayName("플레이어 생성 테스트")
    class CreatePlayerTest {

        @ParameterizedTest
        @ValueSource(strings = {"iiif", "pppk"})
        @DisplayName("한 명의 플레이어를 이름으로 생성할 수 있다.")
        void createPlayerByName(String name) {
            Player player = new Player(new PlayerName(name), new CardHand(21));

            assertThat(player).isInstanceOf(Player.class);
        }
    }

    @Nested
    @DisplayName("플레이어의 추가 배부 테스트")
    class DistributionTest {

        @Test
        @DisplayName("보유 카드가 21 미만이면 추가 배부가 가능하다")
        void canDistributeCard_Under21() {
            List<Card> initialCards = new ArrayList<>(List.of(
                new Card(Suit.HEART, Denomination.TWO),
                new Card(Suit.SPADE, Denomination.TEN),
                new Card(Suit.CLUB, Denomination.ACE)
            ));
            CardDeck cardDeck = new CardDeck(initialCards);

            Player player = new Player(new PlayerName("sana"), new CardHand(21));
            player.addCards(cardDeck, 3);

            assertThat(player.isPossibleToAdd()).isTrue();
        }

        @Test
        @DisplayName("보유 카드가 21 이상이면 추가 배부가 불가능하다")
        void canDistributeCard_Over21() {
            List<Card> initialCards = new ArrayList<>(List.of(
                new Card(Suit.HEART, Denomination.TWO),
                new Card(Suit.SPADE, Denomination.TEN),
                new Card(Suit.CLUB, Denomination.JACK)
            ));
            CardDeck cardDeck = new CardDeck(initialCards);

            Player player = new Player(new PlayerName("sana"), new CardHand(21));
            player.addCards(cardDeck, 3);

            assertThat(player.isPossibleToAdd()).isFalse();
        }
    }
}
