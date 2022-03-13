package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.controller.BlackJackController;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BlackJackControllerTest {
    @Test
    @DisplayName("딜러와 플레이어에게 게임 시작 시 2장씩 배분한다.")
    void initDistribution() {
        BlackJackController blackJackController = new BlackJackController(Arrays.asList("a", "b"));

        Dealer dealerDto = blackJackController.getDealer();
        List<Player> playerDtos = blackJackController.getPlayers();

        assertThat(dealerDto.getCards().size()).isEqualTo(2);
        assertThat(playerDtos)
                .map(dto -> dto.getCards().size())
                .containsExactly(2, 2);
    }

    @Test
    @DisplayName("플레이어에게 2장 배분한다.")
    void distributeCard() {
        BlackJackController blackJackController = new BlackJackController(List.of("name"));
        Player player = blackJackController.getPlayers().get(0);
        assertThat(player.getCards().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("딜러의 점수가 17이상일 때 까지 카드를 1장씩 받는다.")
    void dealerDistribution() {
        BlackJackController blackJackController = new BlackJackController(List.of("name"));
        blackJackController.distributeAdditionalToDealer();
        Dealer dealer = blackJackController.getDealer();
        int cardNumberSum = dealer.getCardsNumberSum();
        assertThat(cardNumberSum).isGreaterThan(16);
    }

    @Test
    @DisplayName("중복된 이름 입력 시, 에러가 발생한다.")
    void validateDuplicationNames() {
        assertThatThrownBy(() -> {
            new BlackJackController(List.of("name", "name"));
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복된 이름이 존재합니다.");
    }

    @Test
    @DisplayName("플레이어 이름을 입력 받아, 플레이어를 반환한다.")
    void findPlayerByName() {
        BlackJackController blackJackController = new BlackJackController(List.of("pobi", "jason"));
        Player pobi = blackJackController.findPlayerByName("pobi");
        assertThat(pobi.isSameName("pobi")).isTrue();
    }

    @Test
    @DisplayName("이름을 입력 받아, 해당 플레이어가 카드 총 합이 21이 넘는 버스트 상태인지 확인한다.")
    void isBurst() {
        BlackJackController blackJackController = new BlackJackController(List.of("pobi", "jason"));
        Player pobi = blackJackController.findPlayerByName("pobi");
        pobi.addCard(Card.getInstance(CardShape.DIAMOND, CardNumber.KING));
        pobi.addCard(Card.getInstance(CardShape.CLOVER, CardNumber.KING));
        pobi.addCard(Card.getInstance(CardShape.HEART, CardNumber.KING));
        assertThat(blackJackController.isBurst("pobi")).isEqualTo(true);
    }
}
