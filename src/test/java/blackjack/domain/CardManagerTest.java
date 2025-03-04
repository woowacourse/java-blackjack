package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardManagerTest {

    CardDeck cardDeck;
    CardManager cardManager;

    @BeforeEach
    void setUp() {
        cardDeck = CardDeck.initialize();
        cardManager = new CardManager(cardDeck);
    }

    @Test
    @DisplayName("사용자를 등록할 수 있다.")
    void canRegisterUser() {
        //given
        List<Nickname> nicknames = List.of(new Nickname("쿠키"), new Nickname("빙봉"));

        //when
        assertThatCode(() -> cardManager.initialize(nicknames))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("카드를 지정된 초기 개수만큼 모두에게 분배할 수 있다.")
    void canDistributeCards() {
        //given
        List<Nickname> nicknames = List.of(new Nickname("쿠키"), new Nickname("빙봉"));
        cardManager.initialize(nicknames);
        //when
        cardManager.distributeCard();
        //then
        assertAll(
                () -> assertThat(cardManager.findCardsByNickname(new Nickname("쿠키")))
                        .hasSize(CardManager.INITIAL_CARD_COUNT),
                () -> assertThat(cardManager.findCardsByNickname(new Nickname("빙봉")))
                        .hasSize(CardManager.INITIAL_CARD_COUNT),
                () -> assertThat(cardManager.findDealerCards())
                        .hasSize(CardManager.INITIAL_CARD_COUNT)
        );
    }
}