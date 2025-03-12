package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.game.GameManager;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class GameManagerTest {

    @Test
    @DisplayName("참가자에게 초기로 2개의 카드를 나눈다")
    void addParticipants() {
        List<Gambler> names = List.of(new Gambler("비타"));

        GameManager gameManager = new GameManager(names);
        gameManager.dealInitCards();
        assertThat(gameManager.getPlayers().getGamblers().getFirst().getCards()).hasSize(2);
    }

    @DisplayName("참가자에게 카드를 한장 추가 발부한다")
    @Test
    void deal_card_to_gambler_test() {
        Gambler gambler = new Gambler("두리");

        GameManager gameManager = new GameManager(List.of(gambler));

        gameManager.dealAddCard(gambler);
        assertThat(gambler.getCards().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("플레이어의 카드가 버스트면 TRUE를 반환한다")
    void ifThePlayerS_CardIsBurstItReturns_True() {
        Gambler gambler = new Gambler("비타");
        List<Gambler> names = List.of(gambler);

        GameManager gameManager = new GameManager(names);
        gameManager.dealInitCards();
        gambler.pushDealCards(List.of(
                new Card(CardNumber.QUEEN, CardShape.CLOVER),
                new Card(CardNumber.KING, CardShape.CLOVER),
                new Card(CardNumber.JACK, CardShape.CLOVER)
        ));
        boolean result = gambler.isPlayerBust();

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("딜러의 카드가 히트면 카드를 한장 추가한다")
    void ifTheDealerS_CardIsHitAddACard() {
        // given
        GameManager gameManager = new GameManager(List.of());
        Dealer dealer = gameManager.getPlayers().getDealer();
        // when
        dealer.pushDealCards(List.of(
                new Card(CardNumber.FIVE, CardShape.DIAMOND)
        ));
        boolean result = gameManager.isDealerHitThenDealAddCard(dealer);

        // then
        assertAll(
                () -> assertThat(result).isTrue(),
                () -> assertThat(gameManager.getPlayers().getDealer().getCards().size()).isEqualTo(2)
        );
    }

    @Test
    @DisplayName("딜러의 카드가 히트가 아니면 false 를 반환한다")
    void if_the_dealer_card_is_not_hit_get_false() {
        GameManager gameManager = new GameManager(List.of(new Gambler("비타")));
        Dealer dealer = gameManager.getPlayers().getDealer();
        dealer.pushDealCards(List.of(
                new Card(CardNumber.ACE, CardShape.DIAMOND),
                new Card(CardNumber.JACK, CardShape.DIAMOND)
        ));
        boolean result = gameManager.getPlayers().getDealer().isDealerHit();

        assertThat(result).isFalse();
    }
}
