package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import domain.participant.HandCards;
import domain.participant.Name;
import domain.participant.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackGameTest {

    @Test
    @DisplayName("플레이어와 사용자 입력을 받아서 추가 드로우 상태를 반환한다.")
    void distributePlayerCardOrPass() {
        BlackJackGame blackJackGame = new BlackJackGame(List.of("aa", "bb"));

        Card card1 = new Card(Suit.SPADE, Denomination.TEN);
        Card card2 = new Card(Suit.DIAMOND, Denomination.SIX);
        Player player = new Player(new Name("aa"), new HandCards(List.of(card1, card2)));

        Assertions.assertThat(blackJackGame.distributePlayerCardOrPass(player, "y")).isEqualTo(AdditionalDrawStatus.DRAW);
        Assertions.assertThat(blackJackGame.distributePlayerCardOrPass(player, "n")).isEqualTo(AdditionalDrawStatus.PASS);
    }
}
