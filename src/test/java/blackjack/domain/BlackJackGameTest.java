package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Number;
import blackjack.domain.card.Shape;
import blackjack.domain.player.Challenger;
import blackjack.domain.player.ChallengerName;
import blackjack.domain.player.Money;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.dto.ChallengerNameAndMoneyDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class BlackJackGameTest {

    private BlackJackGame blackJackGame;

    @BeforeEach
    void setup() {
        ChallengerNameAndMoneyDto ditoo = new ChallengerNameAndMoneyDto(new ChallengerName("ditoo"), Money.from(1000));
        ChallengerNameAndMoneyDto bada = new ChallengerNameAndMoneyDto(new ChallengerName("bada"), Money.from(1000));
        blackJackGame = BlackJackGame.from(List.of(ditoo, bada));
    }

    @Test
    @DisplayName("초기 배분 카드가 겹치지 않는지 테스트")
    void is_distinct_cards() {
        blackJackGame.handOutStartCards();
        Player dealer = blackJackGame.getDealer();
        Player ditoo = blackJackGame.getChallengers().get(0);
        Player bada = blackJackGame.getChallengers().get(1);

        List<Card> startCards = new ArrayList<>();
        startCards.addAll(dealer.getHoldingCards().getCards());
        startCards.addAll(ditoo.getHoldingCards().getCards());
        startCards.addAll(bada.getHoldingCards().getCards());

        List<Card> distinctCards = startCards.stream()
                .distinct()
                .collect(Collectors.toUnmodifiableList());

        assertThat(distinctCards).containsAll(startCards);
    }

    @Test
    @DisplayName("도전자는 bust가 아닌 경우에 카드를 뽑을 수 있다")
    void can_pick_when_not_bust() {
        Player ditoo = blackJackGame.getChallengers().get(0);

        assertThat(blackJackGame.canPick(ditoo)).isTrue();
    }

    @Test
    @DisplayName("도전자는 bust인 경우에 카드를 뽑을 수 없다")
    void can_not_pick_when_bust() {
        Player ditoo = blackJackGame.getChallengers().get(0);

        Card spadeTen = new Card(Shape.SPADE, Number.TEN);
        Card spadeJack = new Card(Shape.SPADE, Number.JACK);
        Card spadeTwo = new Card(Shape.SPADE, Number.TWO);

        ditoo.pickStartCards(spadeTen, spadeJack);
        ditoo.pick(spadeTwo);

        assertThat(blackJackGame.canPick(ditoo)).isFalse();
    }

    @Test
    @DisplayName("딜러는 카드 합이 16이하인 경우에 카드를 또 뽑을 수 있다")
    void dealer_pick_card_when_under_or_equals_to_16() {
        Player dealer = blackJackGame.getDealer();

        Card heartNine = new Card(Shape.HEART, Number.NINE);
        Card heartSeven = new Card(Shape.HEART, Number.SEVEN);
        dealer.pickStartCards(heartNine, heartSeven);

        assertThat(blackJackGame.isDealerCanPick()).isTrue();
    }

    @Test
    @DisplayName("딜러는 카드 합이 16초과인 경우에 카드를 또 뽑을 수 없다")
    void dealer_pick_card_when_over_16() {
        Player dealer = blackJackGame.getDealer();

        Card heartNine = new Card(Shape.HEART, Number.NINE);
        Card heartEight = new Card(Shape.HEART, Number.EIGHT);
        dealer.pickStartCards(heartNine, heartEight);

        assertThat(blackJackGame.isDealerCanPick()).isFalse();
    }

    @Test
    @DisplayName("딜러를 선택하는 기능에 대한 테스트")
    void get_dealer() {
        Player dealer = blackJackGame.getDealer();

        assertThat(dealer.isDealer()).isTrue();
    }

    @Test
    @DisplayName("도전자를 선택하는 기능 대한 테스트")
    void get_challengers() {
        List<Challenger> challengers = blackJackGame.getChallengers();

        challengers.forEach(challenger -> assertThat(challenger.isChallenger()).isTrue());
    }
}