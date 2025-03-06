package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardManagerTest {

    CardManager cardManager;

    @BeforeEach
    void setUp() {
        cardManager = new CardManager();
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
        cardManager.distributeCards();
        //then
        assertAll(
                () -> assertThat(cardManager.findCardsByNickname(new Nickname("쿠키")).getSize())
                        .isEqualTo(GameRule.INITIAL_CARD_COUNT.getValue()),
                () -> assertThat(cardManager.findCardsByNickname(new Nickname("빙봉")).getSize())
                        .isEqualTo(GameRule.INITIAL_CARD_COUNT.getValue())
        );
    }

    @Test
    @DisplayName("플레이어의 카드 포인트의 합을 구할 수 있다.")
    void canCalculateSumByNickname() {
        //given
        Nickname nickname = new Nickname("쿠키");
        cardManager.initialize(List.of(nickname));

        cardManager.addCardByNickname(nickname);
        cardManager.addCardByNickname(nickname);

        Cards cards = cardManager.findCardsByNickname(nickname);
        int expectedValue = cards.calculateSum();

        // when
        int actualValue = cardManager.calculateSumByNickname(nickname);

        // then
        assertThat(actualValue).isEqualTo(expectedValue);
    }
}
