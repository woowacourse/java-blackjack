package service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.card.Deck;
import domain.participant.Bet;
import domain.participant.Player;
import domain.participant.Players;

class BlackjackServiceTest {
    private BlackjackService blackjackService;

    @BeforeEach
    void setUp() {
        this.blackjackService = new BlackjackService();
    }

    @DisplayName("처음 전쳬 카드는 52장 생성되야한다.")
    @Test
    void 처음_전쳬_카드_개수_테스트() {
        Deck cards = blackjackService.generateCards();

        assertThat(cards.getSize()).isEqualTo(52);
    }

    @DisplayName("처음에 카드 2장씩 베부 정상 테스트")
    @Test
    void 초기_카드_2장_배부_테스트() {
        Deck cards = blackjackService.generateCards();
        Players players = new Players(List.of(
                new Player("요크", new Bet(1000)),
                new Player("아티", new Bet(1000))
        ));
        blackjackService.distributeInitialCards(players, cards);

        for (Player player : players) {
            assertThat(player.getCardCount()).isEqualTo(2);
        }
    }
}