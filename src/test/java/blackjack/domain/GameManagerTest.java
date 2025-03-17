package blackjack.domain;

import blackjack.domain.card.CardPack;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("게임 매니저 테스트")
class GameManagerTest {

    private static final CardPack SORT_CARD_PACK = new CardPack(new SortedBlackjackShuffle());
    private static final CardPack REVERSE_SORT_CARD_PACK = new CardPack(new ReversedBlackjackShuffle());

    private final Gambler gambler = new Gambler("비타", 0);
    private final Players players = new Players(new Dealer(), List.of(gambler));

    @Test
    @DisplayName("플레이어와 카드팩으로 게임 매니저를 생성한다")
    void createGameManagerWithPlayersAndCardPack() {
        GameManager sortGameManager = new GameManager(SORT_CARD_PACK, players);

        assertAll(
                () -> assertThat(sortGameManager.getPlayers().getGamblers().size()).isEqualTo(1),
                () -> assertThat(sortGameManager.getPlayers().getDealer()).isNotNull()
        );
    }

    @Test
    @DisplayName("플레이어와 딜러는 기본 두 장씩 카드를 발급한다")
    void issueTwoCardsToPlayerAndDealerInitially() {
        GameManager sortGameManager = new GameManager(SORT_CARD_PACK, players);

        assertAll(
                () -> assertThat(sortGameManager.getPlayers().getGamblers().getFirst().getHand().getCards().getCards().size()).isEqualTo(2),
                () -> assertThat(sortGameManager.getPlayers().getDealer().getHand().getCards().getCards().size()).isEqualTo(2)
        );
    }

    @DisplayName("참가자에게 카드를 한장 추가 발급한다")
    @Test
    void dealOneCardToParticipant() {
        GameManager sortGameManager = new GameManager(SORT_CARD_PACK, players);
        sortGameManager.addCardForGambler(gambler);

        int result = sortGameManager.getPlayers().getGamblers().getFirst().getHand().getCards().getCards().size();

        assertThat(result).isEqualTo(3);
    }

    @Test
    @DisplayName("플레이어가 버스트면 TRUE를 반환한다")
    void returnTrueIfPlayerIsBust() {
        GameManager sortGameManager = new GameManager(SORT_CARD_PACK, players);
        sortGameManager.addCardForGambler(gambler);

        boolean result = sortGameManager.isPlayerBust(gambler);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("플레이어가 버스트가 아니면 FALSE를 반환한다")
    void returnFalseIfPlayerIsNotBust() {
        GameManager reverseSortGameManager = new GameManager(REVERSE_SORT_CARD_PACK, players);
        reverseSortGameManager.addCardForGambler(gambler);

        boolean result = reverseSortGameManager.isPlayerBust(gambler);

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("딜러가 히트면 카드를 한장 추가하고 TRUE를 반환한다")
    void addCardIfDealerHitsAndReturnTrue() {
        GameManager reverseSortGameManager = new GameManager(REVERSE_SORT_CARD_PACK, players);

        boolean result = reverseSortGameManager.isDealerHitThenAddCard();

        assertAll(
                () -> assertThat(result).isTrue(),
                () -> assertThat(reverseSortGameManager.getPlayers().getDealer().getHand().getCards().getCards().size()).isEqualTo(3)
        );
    }
}
