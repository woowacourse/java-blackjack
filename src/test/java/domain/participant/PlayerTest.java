package domain.participant;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Number;
import domain.card.Symbol;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("플레이어 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class PlayerTest {

    @Test
    void 플레이어가_카드를_뽑는다() {
        Player player = new Player("drago",
            List.of(
                new Card(Symbol.DIAMOND, domain.card.Number.EIGHT),
                new Card(Symbol.CLOVER, domain.card.Number.JACK)),
            1000);
        Card drawCard = new Card(Symbol.HEART, domain.card.Number.FOUR);
        List<Card> providedCards = List.of(drawCard);

        player.drawCard(providedCards);
        Cards expected = new Cards(List.of(new Card(Symbol.DIAMOND, domain.card.Number.EIGHT), new Card(Symbol.CLOVER, domain.card.Number.JACK), drawCard));
        assertThat(player.getCards()).isEqualTo(expected);
    }

    @Test
    void 플레이어가_가진_카드리스트의_합계가_21초과이면_true_아니면_false를_반환한다() {
        Player drago = new Player("drago",
                List.of(
                    new Card(Symbol.DIAMOND, domain.card.Number.EIGHT),
                    new Card(Symbol.DIAMOND, domain.card.Number.JACK),
                    new Card(Symbol.HEART, domain.card.Number.KING)),
            1000);

        Player duei = new Player("duei",
                List.of(
                    new Card(Symbol.DIAMOND, domain.card.Number.EIGHT),
                    new Card(Symbol.DIAMOND, domain.card.Number.JACK)),
            1000);

        assertAll(
                () -> assertThat(drago.isBurst()).isTrue(),
                () -> assertThat(duei.isBurst()).isFalse()
        );
    }

    @Test
    void 플레이어의_이름과_카드리스트의_총합을_반환한다() {
        Player player = new Player("drago",
                List.of(new Card(Symbol.DIAMOND, domain.card.Number.EIGHT),
                        new Card(Symbol.DIAMOND, domain.card.Number.JACK),
                        new Card(Symbol.HEART, domain.card.Number.FOUR)),
            1000);

        assertThat(player.getTotalNumberSum()).isEqualTo(22);
    }

    @Test
    void 플레이어가_두장으로_21을_완성한_경우_true를_반환한다() {
        Player player = new Player("drago",
            List.of(new Card(Symbol.DIAMOND, domain.card.Number.ACE),
                new Card(Symbol.DIAMOND, domain.card.Number.JACK)),
            1000);

        assertThat(player.isBlackJack()).isTrue();
    }

    @Test
    void 플레이어가_두장으로_21을_완성하지_못한_경우_false를_반환한다() {
        Player noBustPlayer = new Player("drago",
            List.of(new Card(Symbol.DIAMOND, domain.card.Number.ACE),
                new Card(Symbol.DIAMOND, domain.card.Number.NINE)),
            1000);

        Player threeCardTwentyOnePlayer = new Player("drago",
            List.of(new Card(Symbol.DIAMOND, domain.card.Number.NINE),
                new Card(Symbol.DIAMOND, domain.card.Number.NINE),
                new Card(Symbol.HEART, domain.card.Number.THREE)),
            1000);

        assertAll(
            () -> assertThat(noBustPlayer.isBlackJack()).isFalse(),
            () -> assertThat(threeCardTwentyOnePlayer.isBlackJack()).isFalse()
        );
    }

    @Test
    void 플레이어가_자신의_수익을_계산한다() {
        Player player = new Player("drago",
            List.of(new Card(Symbol.DIAMOND, domain.card.Number.ACE),
                new Card(Symbol.DIAMOND, domain.card.Number.JACK)),
            1000);

        Dealer dealer = new Dealer(
            List.of(
                new Card(Symbol.HEART, domain.card.Number.FOUR),
                new Card(Symbol.HEART, Number.FOUR)
            )
        );

        assertThat(player.calculateIncome(dealer)).isEqualTo(1500);
    }
}
