package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackResultTest {

    @Test
    @DisplayName("플레이어가 버스트 되면 딜러가 게임을 이긴다")
    void playerBust() {
        Player dealer = new Player(new Name("딜러"));
        Player teba = new Player(new Name("테바"));
        teba.addCard(Card.makeRandomCard(new RandomNumberGeneartor(11, 12)));
        teba.addCard(Card.makeRandomCard(new RandomNumberGeneartor(11, 12)));
        teba.addCard(Card.makeRandomCard(new RandomNumberGeneartor(5, 6)));
        Blackjack blackjack = new Blackjack(new Players(List.of(teba)), dealer);

        BlackjackResultDTO blackjackResultDTO = blackjack.finishGame();
        Integer tebaLose = blackjackResultDTO.getLose(teba);
        Integer dealerWin = blackjackResultDTO.getWin(dealer);

        Assertions.assertThat(tebaLose).isEqualTo(1);
        Assertions.assertThat(dealerWin).isEqualTo(1);
    }

    @Test
    @DisplayName("딜러가 버스트 되면 플레이어가 게임을 이긴다")
    void dealerBust() {
        Player dealer = new Player(new Name("딜러"));
        Player teba = new Player(new Name("테바"));
        dealer.addCard(Card.makeRandomCard(new RandomNumberGeneartor(11, 12)));
        dealer.addCard(Card.makeRandomCard(new RandomNumberGeneartor(11, 12)));
        dealer.addCard(Card.makeRandomCard(new RandomNumberGeneartor(5, 6)));
        Blackjack blackjack = new Blackjack(new Players(List.of(teba)), dealer);

        BlackjackResultDTO blackjackResultDTO = blackjack.finishGame();
        Integer dealerLose = blackjackResultDTO.getLose(dealer);
        Integer tebaWin = blackjackResultDTO.getWin(teba);

        Assertions.assertThat(dealerLose).isEqualTo(1);
        Assertions.assertThat(tebaWin).isEqualTo(1);
    }
}
