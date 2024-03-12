package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static blackjack.domain.card.CardScore.ACE;
import static blackjack.domain.card.CardScore.FIVE;
import static blackjack.domain.card.CardScore.FOUR;
import static blackjack.domain.card.CardScore.JACK;
import static blackjack.domain.card.CardScore.NINE;
import static blackjack.domain.card.CardScore.QUEEN;
import static blackjack.domain.card.CardSymbol.CLUB;
import static blackjack.domain.card.CardSymbol.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("핸드")
public class HandTest {
    @Test
    @DisplayName("에서 카드 한 장을 뽑는다.")
    void draw() {
        // given & when
        Hand hand = new Hand(new Deck());
        hand.draw();

        // then
        assertThat(hand.getCards().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("에서 카드 두 장을 뽑는다.")
    void drawCount() {
        // given & when
        Hand hand = new Hand(new Deck());
        hand.draw(2);

        // then
        assertThat(hand.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("에서 뽑은 카드들의 합을 구한다.")
    void totalScore() {
        // given
        Hand hand = new Hand(new Deck() {
            @Override
            public List<Card> draw(int count) {
                return List.of(new Card(CLUB, FIVE), new Card(SPADE, NINE));
            }
        });

        // when
        hand.draw(2);

        // then
        assertThat(hand.totalScore()).isEqualTo(14);
    }

    @Test
    @DisplayName("의 카드가 J, Q 이면 점수는 20 이다")
    void scoreNormal() {
        // given
        Hand hand = new Hand(new Deck() {
            @Override
            public List<Card> draw(int count) {
                return List.of(new Card(CLUB, JACK), new Card(SPADE, QUEEN));
            }
        });

        // when
        hand.draw(2);

        // then
        assertThat(hand.totalScore()).isEqualTo(20);
    }

    @Test
    @DisplayName("의 카드가 J, A 이면 점수는 21 이다.")
    void scoreWithAceNotExceed() {
        // given
        Hand hand = new Hand(new Deck() {
            @Override
            public List<Card> draw(int count) {
                return List.of(new Card(CLUB, JACK), new Card(SPADE, ACE));
            }
        });

        // when
        hand.draw(2);

        // then
        assertThat(hand.totalScore()).isEqualTo(21);
    }

    @Test
    @DisplayName("의 카드가 4, J, A 이면 점수는 15 이다.")
    void scoreWithAceExceed() {
        // given
        Hand hand = new Hand(new Deck() {
            @Override
            public List<Card> draw(int count) {
                return List.of(new Card(CLUB, FOUR), new Card(CLUB, JACK), new Card(SPADE, ACE));
            }
        });

        // when
        hand.draw(3);

        // then
        assertThat(hand.totalScore()).isEqualTo(15);
    }

    @Test
    @DisplayName("의 카드가 5, A, A 이면 점수는 16 이다.")
    void scoreWithManyAce() {
        // given
        Hand hand = new Hand(new Deck() {
            @Override
            public List<Card> draw(int count) {
                return List.of(new Card(CLUB, FIVE), new Card(CLUB, ACE), new Card(SPADE, ACE));
            }
        });

        // when
        hand.draw(3);

        // then
        assertThat(hand.totalScore()).isEqualTo(16);
    }
}
