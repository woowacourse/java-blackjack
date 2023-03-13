package domain;

import static domain.card.CardInfo.A;
import static domain.card.CardInfo.FOUR;
import static domain.card.CardInfo.THREE;
import static domain.card.Shape.HEART;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import domain.bank.BettingMoney;
import domain.card.Card;
import domain.card.Cards;
import domain.participant.Name;
import domain.participant.Player;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PlayerTest {

    @Test
    void 본인의_카드뭉치의_총합을_반환한다() {
        Name name = getName();
        Cards cards = getCards();
        BettingMoney bettingMoney = getBettingMoney();

        Player player = new Player(name, cards, bettingMoney);

        assertThat(player.sumOfCards()).isEqualTo(cards.sumOfCards());
    }

    @Test
    void 카드를_받아_자신의_카드뭉치에_더할수_있다() {
        Name name = getName();
        Cards cards = getCards();
        BettingMoney bettingMoney = getBettingMoney();

        Player player = new Player(name, cards, bettingMoney);

        //중복카드면 false
        assertThat(player.addCard(new Card(HEART, A))).isFalse();
        //중복카드가 아니면 true
        assertThat(player.addCard(new Card(HEART, FOUR))).isTrue();
    }

    @Test
    void 자신의_카드뭉치가_블랙잭임을_확인한다() {
        Name name = getName();
        Cards cards = getCards();
        BettingMoney bettingMoney = getBettingMoney();

        Player player = new Player(name, cards, bettingMoney);

        assertThat(player.isBlackJack()).isFalse();
    }

    @Test
    void 자신이_가진돈과_수익률을_곱해_돈을_교체한다() {
        Name name = getName();
        Cards cards = getCards();
        BettingMoney bettingMoney = getBettingMoney();

        Player player = new Player(name, cards, bettingMoney);

        double profit = 1.0;

        assertDoesNotThrow(
                () -> player.multiplyInterestOfPlayer(profit)
        );
    }

    private BettingMoney getBettingMoney() {
        return new BettingMoney(1000);
    }

    private Cards getCards() {
        List<Card> cardsByCardBox = new ArrayList<>();
        cardsByCardBox.add(new Card(HEART, A));
        cardsByCardBox.add(new Card(HEART, THREE));
        return new Cards(cardsByCardBox);
    }

    private Name getName() {
        return new Name("hamad");
    }
}
