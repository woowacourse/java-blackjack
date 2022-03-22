package domain.participant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import domain.result.WinOrLose;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    List<Card> handForPlayer = new ArrayList<>(
            List.of(Card.getCard(Rank.RANK_8, Suit.CLOVER), Card.getCard(Rank.RANK_A, Suit.CLOVER))
    );
    Player player = new Player(new Name("pobi"), handForPlayer);

    @Test
    @DisplayName("최종 결과를 위한 승패 판단")
    void compareAtFinal() {
        // when
        List<Card> handForDealer = new ArrayList<>(
                List.of(Card.getCard(Rank.RANK_J, Suit.CLOVER), Card.getCard(Rank.RANK_A, Suit.CLOVER))
        );
        Dealer dealer = new Dealer(handForDealer);

        // then
        assertThat(WinOrLose.compareWinOrLose(dealer, player)).isEqualTo(WinOrLose.LOSE);
    }

    @Test
    @DisplayName("본인 이름인지 확인")
    void isNameMatch() {
        // when
        Name name = new Name("pobi");

        // then
        assertThat(player.isNameMatch(name)).isTrue();
    }
}
