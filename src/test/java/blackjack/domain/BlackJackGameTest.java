package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.player.Player;
import blackjack.domain.player.Score;
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
        blackJackGame = BlackJackGame.from(List.of("ditoo", "bada"));
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
    @DisplayName("canPick()에 대한 테스트")
    void can_pick() {
        Player ditoo = blackJackGame.getChallengers().get(0);
        Score holdingCardsPoint = ditoo.getTotalPoint();

        assertThat(blackJackGame.canPick(ditoo)).isTrue();

        while (!holdingCardsPoint.isBust()) {
            blackJackGame.pick(ditoo);
            holdingCardsPoint = ditoo.getTotalPoint();
        }

        assertThat(blackJackGame.canPick(ditoo)).isFalse();
    }

    @Test
    @DisplayName("getDealer()에 대한 테스트")
    void get_dealer() {
        Player dealer = blackJackGame.getDealer();

        assertThat(dealer.isDealer()).isTrue();
    }

    @Test
    @DisplayName("getChallengers()에 대한 테스트")
    void get_challengers() {
        List<Player> challengers = blackJackGame.getChallengers();

        challengers.forEach(challenger -> assertThat(challenger.isChallenger()).isTrue());
    }
}