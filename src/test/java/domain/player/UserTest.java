package domain.player;

import static domain.fixture.BlackjackCardFixture.ACE_HEART;
import static domain.fixture.BlackjackCardFixture.JACK_HEART;
import static domain.fixture.BlackjackCardFixture.KING_HEART;
import static domain.fixture.BlackjackCardFixture.TEN_HEART;
import static domain.fixture.BlackjackCardFixture.TWO_HEART;

import domain.card.Cards;
import domain.card.Deck;
import domain.card.DeckGenerator;
import domain.state.Stay;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    void 초기_카드_분배_시_유저는_2장을_받는다() {
        // given
        Deck deck = DeckGenerator.generateDeck();
        User user = User.of("새로이", 1000);

        // when
        user.drawInitialCards(deck);

        // when & then
        Assertions.assertThat(user.cards().size())
                .isEqualTo(2);
    }

    @Test
    void 초기_카드_공개_시_딜러는_카드_한장만_공개한다() {
        // given
        Deck deck = DeckGenerator.generateDeck();
        User user = User.of("라젤", 2000);
        user.drawInitialCards(deck);

        // when
        user.openInitialCards();

        // when & then
        Assertions.assertThat(user.cards().openedCards().size())
                .isEqualTo(2);
    }

    @Test
    void 유저가_버스트가_아닌_상태에서_스테이를_선언하면_스테이_상태가_된다() {
        // given
        Deck deck = new Deck(List.of(
                JACK_HEART, KING_HEART, TEN_HEART,
                TWO_HEART, ACE_HEART    // initial cards
        ));
        User user = User.of("라젤", 2000);
        user.drawInitialCards(deck);
        user.openInitialCards();

        Function<User, Boolean> alwaysFalse = u -> false;
        BiConsumer<User, Cards> onHit = (u, c) -> {
        };

        // when
        user.hitUntilStay(deck, alwaysFalse, onHit);

        // then
        Assertions.assertThat(user.state).isInstanceOf(Stay.class);
    }
}
