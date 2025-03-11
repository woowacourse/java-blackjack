package blackjack.domain;

import blackjack.domain.card.CardPack;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class GameManagerTest {

    private static final CardPack SORT_CARD_PACK = new CardPack(new SortedBlackjackShuffle());
    private static final CardPack REVERSE_SORT_CARD_PACK = new CardPack(new ReversedBlackjackShuffle());

    @Test
    @DisplayName("참가자를 추가한다")
    void addParticipants() {
        Players players = new Players(List.of(new Gambler("비타")), SORT_CARD_PACK);
        GameManager gameManager = new GameManager(SORT_CARD_PACK, players);

        assertThat(gameManager.getPlayers().getGamblers().size())
                .isEqualTo(1);
    }

    @DisplayName("참가자에게 카드를 한장 추가 발급한다")
    @Test
    void deal_card_to_gambler_test() {
        Gambler gambler = new Gambler("비타");
        Players players = new Players(List.of(gambler), SORT_CARD_PACK);
        GameManager gameManager = new GameManager(SORT_CARD_PACK, players);

        gameManager.dealAddCard(gambler);

        assertThat(gambler.getCards().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("플레이어의 카드가 버스트면 TRUE를 반환한다")
    void ifThePlayerS_CardIsBurstItReturns_True() {
        Gambler gambler = new Gambler("비타");

        Players players = new Players(List.of(gambler), SORT_CARD_PACK);
        GameManager gameManager = new GameManager(SORT_CARD_PACK, players);

        gameManager.dealAddCard(gambler);
        boolean result = gameManager.isPlayerBust(gambler);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("딜러의 카드가 히트면 카드를 한장 추가한다")
    void ifTheDealerS_CardIsHitAddACard() {
        Players players = new Players(List.of(new Gambler("비타")), REVERSE_SORT_CARD_PACK);
        GameManager gameManager = new GameManager(REVERSE_SORT_CARD_PACK, players);

        boolean result = gameManager.isDealerHitThenDealAddCard();

        assertAll(
                () -> assertThat(result).isTrue(),
                () -> assertThat(gameManager.getPlayers().getDealer().getCards().size()).isEqualTo(3)
        );
    }

    @Test
    @DisplayName("딜러의 카드가 히트가 아니면 false 를 반환한다")
    void if_the_dealer_card_is_not_hit_get_false() {
        Players players = new Players(List.of(new Gambler("비타")), SORT_CARD_PACK);
        GameManager gameManager = new GameManager(SORT_CARD_PACK, players);

        boolean result = gameManager.isDealerHitThenDealAddCard();

        assertThat(result).isFalse();
    }
}
