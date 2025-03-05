package blackjack.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class GameManagerTest {

    GameManager gameManager;
    CardManager cardManager;
    CardDeck cardDeck;

    @BeforeEach
    void setUp() {
        cardDeck = CardDeck.initialize();
        cardManager = new CardManager(cardDeck);
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
                assertThat( cardManager.findCardsByNickname(nickname)).isEmpty());
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
                () -> assertThat(cardManager.findCardsByNickname(new Nickname("강산")))
                        .hasSize(CardManager.INITIAL_CARD_COUNT),
                () -> assertThat(cardManager.findCardsByNickname(new Nickname("랜디")))
                        .hasSize(CardManager.INITIAL_CARD_COUNT),
                () -> assertThat(cardManager.findCardsByNickname(Nickname.createDealer()))
                        .hasSize(CardManager.INITIAL_CARD_COUNT)

        );
    }
}