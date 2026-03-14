package domain;

import domain.card.Card;
import domain.card.FixedDeck;

import java.util.List;
import java.util.Map;

import domain.member.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameTableTest {

    private static final String pobiName = "pobi";

    private GameTable gameTable;

    @BeforeEach
    void setUpTest() {
        List<Card> cards = List.of(
                Card.from("10", "클로버"),
                Card.from("9", "클로버"),
                Card.from("8", "클로버"),
                Card.from("7", "클로버")
        );
        Map<String, Money> playerBets = Map.of(pobiName, new Money(10000));
        this.gameTable = new GameTable(playerBets, new FixedDeck(cards));
    }

    @DisplayName("카드의 총합이 21보다 크면 isPlayerBust는 true이다.")
    @Test
    void isPlayerBust_ScoreOver21_ReturnTrue() {
        gameTable.drawForMember(pobiName);
        gameTable.drawForMember(pobiName);
        gameTable.drawForMember(pobiName);

        Assertions.assertTrue(gameTable.isPlayerBust(pobiName));
    }

    @DisplayName("카드의 총합이 21보다 작으면 isPlayerBust는 false이다.")
    @Test
    void isPlayerBust_ScoreUnder21_ReturnFalse() {
        gameTable.drawForMember(pobiName);
        gameTable.drawForMember(pobiName);

        Assertions.assertFalse(gameTable.isPlayerBust(pobiName));
    }
}
