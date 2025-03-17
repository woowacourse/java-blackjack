package participant;

import betting.BettingMoney;
import card.Card;
import card.CardNumber;
import card.CardType;
import deck.Deck;
import deck.DeckCreateStrategy;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantTest {

    private Player player;

    @BeforeEach
    void beforeEach() {
        player = new Player(new Nickname("율무"), new BettingMoney(10000));
    }

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
        player.prepareGame(deck.draw(), deck.draw());
        player.hit(deck.draw());

        // when
        boolean result = player.isBust();

        // then
        Assertions.assertThat(result)
                .isTrue();
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

        player.prepareGame(deck.draw(), deck.draw());
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

        player.prepareGame(deck.draw(), deck.draw());
        player.hit(deck.draw());

        // when
        int result = player.score();

        // then
        Assertions.assertThat(result)
                .isEqualTo(21);
    }
}
