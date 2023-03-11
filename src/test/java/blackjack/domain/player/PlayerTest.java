package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Number;
import blackjack.domain.card.Shape;
import blackjack.dto.ChallengerNameAndMoneyDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    private Player player;

    @BeforeEach
    void setup() {
        player = new Player() {
            @Override
            public Boolean canPick() {
                return null;
            }

            @Override
            public Boolean isDealer() {
                return false;
            }

            @Override
            public Boolean isChallenger() {
                return true;
            }

            @Override
            public String getName() {
                return null;
            }
        };
    }


    @Test
    @DisplayName("플레이어는 초기 카드 2장을 받는다")
    void get_two_cards() {
        Card card1 = new Card(Shape.HEART, Number.FOUR);
        Card card2 = new Card(Shape.CLOVER, Number.KING);
        player.pickStartCards(card1, card2);

        assertThat(player.getHoldingCards().getCards())
                .containsExactly(card1, card2);
    }

    @Test
    @DisplayName("추가 카드를 뽑는다")
    void pick_card() {
        Card card = new Card(Shape.DIAMOND, Number.JACK);
        player.pick(card);

        assertThat(player.getHoldingCards().getCards())
                .contains(card);
    }

    @Test
    @DisplayName("bust이면 bust임을 알려준다")
    void bust() {
        Card card1 = new Card(Shape.HEART, Number.FOUR);
        Card card2 = new Card(Shape.CLOVER, Number.KING);
        Card card3 = new Card(Shape.DIAMOND, Number.QUEEN);

        player.pickStartCards(card1, card2);
        player.pick(card3);

        assertThat(player.isBust()).isTrue();
    }

    @Test
    @DisplayName("bust가 아니면 bust가 아님을 알려준다")
    void not_bust() {
        Card card1 = new Card(Shape.HEART, Number.FOUR);
        Card card2 = new Card(Shape.CLOVER, Number.KING);

        player.pickStartCards(card1, card2);

        assertThat(player.isBust()).isFalse();
    }

    @Test
    @DisplayName("비교 대상보다 점수가 높은 경우 테스트")
    void is_more_score() {
        Card card1 = new Card(Shape.HEART, Number.FOUR);
        Card card2 = new Card(Shape.CLOVER, Number.KING);

        player.pickStartCards(card1, card2);
        ChallengerNameAndMoneyDto dto = new ChallengerNameAndMoneyDto(new ChallengerName("oing"), Money.from(1000));
        Player targetPlayer = new Challenger(dto);
        targetPlayer.pick(card1);

        assertThat(player.moreScoreThan(targetPlayer)).isTrue();
    }

    @Test
    @DisplayName("비교 대상보다 점수가 낮은 경우 테스트")
    void is_less_score() {
        Card card1 = new Card(Shape.HEART, Number.FOUR);
        Card card2 = new Card(Shape.CLOVER, Number.KING);

        player.pick(card1);
        ChallengerNameAndMoneyDto dto = new ChallengerNameAndMoneyDto(new ChallengerName("oing"), Money.from(1000));
        Player targetPlayer = new Challenger(dto);
        targetPlayer.pickStartCards(card1, card2);

        assertThat(player.moreScoreThan(targetPlayer)).isFalse();
    }

    @Test
    @DisplayName("비교 대상과 점수가 같은 경우 테스트")
    void is_same_score() {
        Card card1 = new Card(Shape.HEART, Number.FOUR);
        Card card2 = new Card(Shape.CLOVER, Number.KING);

        player.pickStartCards(card1, card2);
        ChallengerNameAndMoneyDto dto = new ChallengerNameAndMoneyDto(new ChallengerName("oing"), Money.from(1000));
        Player targetPlayer = new Challenger(dto);
        targetPlayer.pickStartCards(card1, card2);

        assertThat(player.isSameScore(targetPlayer)).isTrue();
    }

    @Test
    @DisplayName("카드가 2장이고, 21점이면 블랙잭이다")
    void is_blackjack_true() {
        Card spadeTen = new Card(Shape.SPADE, Number.TEN);
        Card spadeAce = new Card(Shape.SPADE, Number.ACE);

        player.pickStartCards(spadeTen, spadeAce);

        assertThat(player.isBlackjack()).isTrue();
    }

    @Test
    @DisplayName("카드가 2장이고, 20점이면 블랙잭이 아니다")
    void not_blackjack_when_score_is_not_21() {
        Card heartQueen = new Card(Shape.HEART, Number.QUEEN);
        Card heartKing = new Card(Shape.HEART, Number.KING);

        player.pickStartCards(heartQueen, heartKing);

        assertThat(player.isBlackjack()).isFalse();
    }

    @Test
    @DisplayName("카드가 3장이고, 21점이면 블랙잭이 아니다")
    void not_blackjack_when_card_count_is_not_2() {
        Card heartQueen = new Card(Shape.HEART, Number.QUEEN);
        Card heartFour = new Card(Shape.HEART, Number.FOUR);
        Card heartSeven = new Card(Shape.HEART, Number.SEVEN);

        player.pickStartCards(heartQueen, heartFour);
        player.pick(heartSeven);

        assertThat(player.isBlackjack()).isFalse();
    }
}
