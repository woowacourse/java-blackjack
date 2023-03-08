package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CardSelectorTest {

    @Test
    void 인풋으로_주어진_이름에_딜러를_더한_만큼의_Cards를_생성한다() {
        //given
        List<Name> names = List.of(new Name("하마드"), new Name("아코"), new Name("또링"));
        //when
        List<Cards> pickedCards = CardSelector.pickCards(names, new RandomCardNumberGenerator(), new CardBox());
        //then
        assertThat(pickedCards.size()).isEqualTo(4);
    }

    @Test
    void 인풋으로_주어진_이름에_딜러를_더한_만큼_Players를_생성한다() {
        //given
        List<Name> names = List.of(new Name("하마드"), new Name("아코"), new Name("또링"));
        List<Cards> pickedCards = CardSelector.pickCards(names, new RandomCardNumberGenerator(), new CardBox());
        Dealer dealer = new Dealer(new Name("네오"), new Cards(List.of(new Card("A하트", 11))));
        //when
        List<Player> players = CardSelector.giveCardsToPlayers(names, pickedCards, dealer);
        //then
        assertThat(players.size()).isEqualTo(4);
    }

    @Test
    void 플레이어가_카드를_더하기로_선택하면_카드덱에_카드가_늘어난다() {
        //given
        List<Name> names = List.of(new Name("하마드"), new Name("아코"), new Name("또링"));
        List<Cards> pickedCards = CardSelector.pickCards(names, new RandomCardNumberGenerator(), new CardBox());
        Dealer dealer = new Dealer(new Name("네오"), new Cards(List.of(new Card("A하트", 11))));
        List<Player> players = CardSelector.giveCardsToPlayers(names, pickedCards, dealer);
        int index = 1;
        CardBox cardBox = new CardBox();
        int cardBoxIndex = 20;
        //when
        CardSelector.playerDrawIfSelectToAddCard(players, index, cardBox, cardBoxIndex);
        //then
        assertThat(players.get(index).getCards().getCards().size()).isEqualTo(3);

    }

    @Test
    void 딜러의_첫_카드_조합이_16이하면_새로운_카드를_뽑는다() {
        //given
        List<Card> cards = new ArrayList<>();
        cards.add(new Card("6하트", 7));
        cards.add(new Card("9하트", 9));
        Dealer dealer = new Dealer(new Name("네오"), new Cards(cards));
        CardBox cardBox = new CardBox();
        //when
        CardSelector.dealerPickCard(dealer,4,cardBox);
        //then
        assertThat(dealer.isSumUnderStandard()).isFalse();



    }
}
