package participant;

import card.Card;
import card.CardNumber;
import card.CardType;
import card.Cards;
import deck.Deck;
import deck.DeckCreateStrategy;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import state.finished.Bust;
import state.running.Hit;

class ParticipantTest {

    @Test
    @DisplayName("21이 넘는 패를 가지면 Bust이다.")
    void test1() {
        // given
        Deck deck = new Deck(new DeckCreateStrategy() {
            @Override
            public List<Card> createAllCards() {
                return List.of(
                        new Card(CardType.DIAMOND, CardNumber.TEN),
                        new Card(CardType.DIAMOND, CardNumber.NINE),
                        new Card(CardType.DIAMOND, CardNumber.THREE)
                );
            }
        });

        Player player = new Player("율무", new Hit(new Cards()));
        player.hit(deck.draw());
        player.hit(deck.draw());
        player.hit(deck.draw());

        // when
        // then
        Assertions.assertThat(player.canReceiveCard())
                .isFalse();
    }

    @Test
    @DisplayName("21이 넘지 않으면 Bust가 아니다.")
    void test2() {
        // given
        Deck deck = new Deck(new DeckCreateStrategy() {
            @Override
            public List<Card> createAllCards() {
                return List.of(
                        new Card(CardType.DIAMOND, CardNumber.TEN),
                        new Card(CardType.DIAMOND, CardNumber.NINE),
                        new Card(CardType.DIAMOND, CardNumber.TWO)
                );
            }
        });

        Player player = new Player("율무", new Hit(new Cards()));
        player.hit(deck.draw());
        player.hit(deck.draw());
        player.hit(deck.draw());

        // when
        boolean result = player.isBust();

        // then
        Assertions.assertThat(result)
                .isFalse();
    }

    @Test
    @DisplayName("참가자가 자신 패의 합을 구할 수 있다.")
    void test3() {
        // given
        Deck deck = new Deck(new DeckCreateStrategy() {
            @Override
            public List<Card> createAllCards() {
                return List.of(
                        new Card(CardType.DIAMOND, CardNumber.TEN),
                        new Card(CardType.DIAMOND, CardNumber.NINE),
                        new Card(CardType.DIAMOND, CardNumber.TWO)
                );
            }
        });

        Player player = new Player("율무", new Hit(new Cards()));
        player.hit(deck.draw());
        player.hit(deck.draw());
        player.hit(deck.draw());

        // when
        int result = player.score();

        // then
        Assertions.assertThat(result)
                .isEqualTo(21);
    }
}
