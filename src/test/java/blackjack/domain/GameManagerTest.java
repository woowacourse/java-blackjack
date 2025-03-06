package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.dto.DrawnCardResult;
import blackjack.dto.PlayerWinningResult;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameManagerTest {

    GameManager gameManager;
    CardManager cardManager;

    @BeforeEach
    void setUp() {
        cardManager = new CardManager();
        gameManager = new GameManager(cardManager);
    }

    @Test
    @DisplayName("플레이어의 닉네임들로 플레이어와 딜러를 등록할 수 있다.")
    void canRegisterPlayersByNicknames() {
        // given
        List<Nickname> nicknames = new ArrayList<>(List.of(new Nickname("강산"), new Nickname("랜디")));

        // when
        gameManager.registerPlayers(nicknames);

        // then
        nicknames.forEach(nickname ->
                assertThat(cardManager.findCardsByNickname(nickname).getSize()).isZero());
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
                () -> assertThat(cardManager.findCardsByNickname(new Nickname("강산")).getSize())
                        .isEqualTo(GameRule.INITIAL_CARD_COUNT.getValue()),
                () -> assertThat(cardManager.findCardsByNickname(new Nickname("랜디")).getSize())
                        .isEqualTo(GameRule.INITIAL_CARD_COUNT.getValue()),
                () -> assertThat(cardManager.findCardsByNickname(Nickname.createDealerNickname()).getSize())
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
        int originalCardCount = cardManager.findCardsByNickname(player.getNickname()).getSize();

        //when
        gameManager.hit(player);

        //then
        int actualCardCount = cardManager.findCardsByNickname(player.getNickname()).getSize();
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
        int dealerCardPoint = cardManager.calculateSumByNickname(Nickname.createDealerNickname());
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
        int dealerCardPoint = cardManager.calculateSumByNickname(Nickname.createDealerNickname());
        assertThat(dealerCardPoint).isGreaterThan(GameRule.DEALER_LIMIT_POINT.getValue());
    }

    @Test
    @DisplayName("핸재 핸드와 포인트를 계산할 수 있다.")
    void canCalculateDrawnCardResults() {
        //given
        List<Nickname> nicknames = new ArrayList<>(List.of(new Nickname("강산"), new Nickname("랜디")));

        gameManager.registerPlayers(nicknames);
        gameManager.distributeCards();

        //when
        List<DrawnCardResult> drawnCardResults = gameManager.calculateDrawnCardResults();

        //then
        assertThat(drawnCardResults.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("핸재 핸드와 포인트를 계산할 수 있다.")
    void canCalculatePlayerWinningResult() {
        //given
        List<Nickname> nicknames = new ArrayList<>(List.of(new Nickname("강산"), new Nickname("랜디")));

        gameManager.registerPlayers(nicknames);
        gameManager.distributeCards();

        //when
        List<PlayerWinningResult> playerWinningResults = gameManager.calculateGameResult();

        //then
        assertThat(playerWinningResults.size()).isEqualTo(2);
    }

}
