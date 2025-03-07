package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameManagerTest {

    GameManager gameManager;
    CardManager cardManager;
    GameUserStorage gameUserStorage;

    @BeforeEach
    void setUp() {
        gameUserStorage = new GameUserStorage();
        cardManager = new CardManager();
        gameManager = new GameManager(cardManager, gameUserStorage);
    }

    @Test
    @DisplayName("플레이어의 닉네임들로 플레이어와 딜러를 등록할 수 있다.")
    void canRegisterPlayersByNicknames() {
        // given
        List<Nickname> nicknames = new ArrayList<>(List.of(new Nickname("강산"), new Nickname("랜디")));

        // when
        gameManager.registerPlayers(nicknames);

        // then
        assertThat(gameUserStorage.getPlayers())
                .extracting(Player::getNickname)
                .containsExactlyInAnyOrderElementsOf(nicknames);
        assertThat(gameUserStorage.getDealer().getNickname())
                .isEqualTo(new Nickname("딜러"));
    }

    @Test
    @DisplayName("딜러와 플레이어에게 카드를 2장씩 분배할 수 있다.")
    void canDistributeCards() {
        //given
        List<Nickname> nicknames = new ArrayList<>(List.of(new Nickname("강산"), new Nickname("랜디")));
        gameManager.registerPlayers(nicknames);

        //when
        gameManager.distributeCards();

        //then
        assertAll(
                () -> assertThat(cardManager.findCardsByNickname(makePlayer("강산")).size())
                        .isEqualTo(GameRule.INITIAL_CARD_COUNT.getValue()),
                () -> assertThat(cardManager.findCardsByNickname(makePlayer("랜디")).size())
                        .isEqualTo(GameRule.INITIAL_CARD_COUNT.getValue()),
                () -> assertThat(cardManager.findCardsByNickname(makePlayer("딜러")).size())
                        .isEqualTo(GameRule.INITIAL_CARD_COUNT.getValue())
        );
    }

    @Test
    @DisplayName("플레이어에게 카드를 추가할 수 있다.")
    void canHitToPlayer() {
        //given
        List<Nickname> nicknames = new ArrayList<>(List.of(new Nickname("강산")));
        gameManager.registerPlayers(nicknames);
        gameManager.distributeCards();
        Player player = gameManager.getPlayers().get(0);
        int originalCardCount = cardManager.findCardsByNickname(player).size();

        //when
        gameManager.hit(player);

        //then
        int actualCardCount = cardManager.findCardsByNickname(player).size();
        assertThat(actualCardCount).isEqualTo(originalCardCount + 1);
    }

    @Test
    @DisplayName("딜러의 카드합이 정해진 수를 넘기전까지 카드를 뽑아야한다.")
    void canHitToDealer() {
        //given
        gameManager.registerPlayers(new ArrayList<>());

        //when
        gameManager.drawDealerCards();

        //then
        int dealerCardPoint = cardManager.calculateSumByNickname(makePlayer("딜러"));
        assertThat(dealerCardPoint).isGreaterThan(GameRule.DEALER_LIMIT_POINT.getValue());
    }

    @Test
    @DisplayName("플레이어가 버스트인지 확인할 수 있다.")
    void canCheckIsBust() {
        //given
        gameManager.registerPlayers(new ArrayList<>());

        //when
        gameManager.drawDealerCards();

        //then
        int dealerCardPoint = cardManager.calculateSumByNickname(makePlayer("딜러"));
        assertThat(dealerCardPoint).isGreaterThan(GameRule.DEALER_LIMIT_POINT.getValue());
    }

    public static Player makePlayer(String nickname) {
        return new Player(new Nickname(nickname));
    }
}
