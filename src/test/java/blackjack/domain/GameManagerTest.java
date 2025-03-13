package blackjack.domain;

import blackjack.domain.card.CardPack;
import blackjack.domain.player.Gambler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("게임 매니저 테스트")
class GameManagerTest {

    private static final CardPack SORT_CARD_PACK = new CardPack(new SortedBlackjackShuffle());
    private static final CardPack REVERSE_SORT_CARD_PACK = new CardPack(new ReversedBlackjackShuffle());

    @Test
    @DisplayName("플레이어와 카드팩으로 게임 매니저를 생성한다")
    void createGameManagerWithPlayersAndCardPack() {
        GameManager gameManager = new GameManager(SORT_CARD_PACK, List.of(new Gambler("비타", 0)));

        assertAll(
                () -> assertThat(gameManager.getPlayers().getGamblers().size()).isEqualTo(1),
                () -> assertThat(gameManager.getPlayers().getDealer()).isNotNull()
        );
    }

    @Test
    @DisplayName("플레이어와 딜러는 기본 두 장씩 카드를 발급한다")
    void issueTwoCardsToPlayerAndDealerInitially() {
        GameManager gameManager = new GameManager(SORT_CARD_PACK, List.of(new Gambler("비타", 0)));

        assertAll(
                () -> assertThat(gameManager.getPlayers().getGamblers().getFirst().getHand().getCards().getCards().size()).isEqualTo(2),
                () -> assertThat(gameManager.getPlayers().getDealer().getHand().getCards().getCards().size()).isEqualTo(2)
        );
    }

    @DisplayName("참가자에게 카드를 한장 추가 발급한다")
    @Test
    void dealOneCardToParticipant() {
        Gambler gambler = new Gambler("비타", 0);
        GameManager gameManager = new GameManager(SORT_CARD_PACK, List.of(gambler));

        gameManager.addCardForGambler(gambler);

        int result = gameManager.getPlayers().getGamblers().getFirst().getHand().getCards().getCards().size();

        assertThat(result).isEqualTo(3);
    }

    @Test
    @DisplayName("플레이어가 버스트면 TRUE를 반환한다")
    void returnTrueIfPlayerIsBust() {
        Gambler gambler = new Gambler("비타", 0);
        GameManager gameManager = new GameManager(SORT_CARD_PACK, List.of(gambler));

        gameManager.addCardForGambler(gambler);
        boolean result = gameManager.isPlayerBust(gambler);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("플레이어가 버스트가 아니면 FALSE를 반환한다")
    void returnFalseIfPlayerIsNotBust() {
        Gambler gambler = new Gambler("비타", 0);
        GameManager gameManager = new GameManager(REVERSE_SORT_CARD_PACK, List.of(gambler));

        gameManager.addCardForGambler(gambler);
        boolean result = gameManager.isPlayerBust(gambler);

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("딜러가 히트면 카드를 한장 추가하고 TRUE를 반환한다")
    void addCardIfDealerHitsAndReturnTrue() {
        GameManager gameManager = new GameManager(REVERSE_SORT_CARD_PACK, List.of());

        boolean result = gameManager.isDealerHitThenAddCard();

        assertAll(
                () -> assertThat(result).isTrue(),
                () -> assertThat(gameManager.getPlayers().getDealer().getHand().getCards().getCards().size()).isEqualTo(3)
        );
    }
}
