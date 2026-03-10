package service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.Cards;
import domain.Player;
import domain.Players;

class BlackjackServiceTest {
    private BlackjackService blackjackService;

    @BeforeEach
    void setUp() {
        BlackjackService blackjackService = new BlackjackService();
        this.blackjackService = blackjackService;
    }

    @DisplayName("처음 전쳬 카드는 52장 생성되야한다.")
    @Test
    void 처음_전쳬_카드_개수_테스트() {
        Cards cards = blackjackService.generateCards();

        assertThat(cards.getLength()).isEqualTo(52);
    }

    @DisplayName("처음에 카드 2장씩 베부 정상 테스트")
    @Test
    void 초기_카드_2장_배부_테스트() {
        Cards cards = blackjackService.generateCards();
        Players playerList = blackjackService.createPlayers(List.of("요크", "아티"), cards);

        assertThat(playerList.getPlayer(0).getCardCount()).isEqualTo(2);
    }

}