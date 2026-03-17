package domain.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.BettingMoney;
import domain.FixedDeck;
import domain.MatchResult;
import domain.Referee;
import domain.card.Card;
import domain.card.CardRank;
import domain.card.CardSuit;
import domain.deck.Deck;
import exception.BlackjackException;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GamblerTest {
    private Gambler createGambler(String name) {
        return new Gambler(name, new BettingMoney(1000));
    }

    @Test
    @DisplayName("이름이 숫자면 안된다.")
    void 이름이_숫자일_시() {
        //given
        String name = "121345";

        //when & then
        assertThatThrownBy(() -> createGambler(name))
                .isInstanceOf(BlackjackException.class);
    }

    @Test
    @DisplayName("이름은 두글자 이상 열글자 미만으로 한다.")
    void 이름이_열글자를_넘을_시() {
        //given
        String max_range_name = "tobiisverygoob";
        String min_range_name = "h";
        //when & then
        assertThatThrownBy(() -> createGambler(max_range_name))
                .isInstanceOf(BlackjackException.class);
        assertThatThrownBy(() -> createGambler(min_range_name))
                .isInstanceOf(BlackjackException.class);
    }

    @Test
    @DisplayName("승리 정상 판정")
    void 승리_정상_판정() {
        //given
        Dealer dealer = new Dealer();
        Gambler tobi = createGambler("tobi");
        Gambler quda = createGambler("quda");

        Card jack = new Card(CardRank.JACK, CardSuit.CLOVER); // 딜러
        Card eight = new Card(CardRank.EIGHT, CardSuit.DIAMOND); // tobi
        Card ten = new Card(CardRank.TEN, CardSuit.CLOVER); // quda

        FixedDeck fixedDeck = new FixedDeck(List.of(jack, eight, ten));
        dealer.deal(fixedDeck);
        tobi.deal(fixedDeck);
        quda.deal(fixedDeck);

        //when
        MatchResult tobiResult = Referee.judge(dealer, tobi);
        MatchResult qudaResult = Referee.judge(dealer, quda);

        //then
        assertThat(tobiResult).isEqualTo(MatchResult.LOSE);
        assertThat(qudaResult).isEqualTo(MatchResult.DRAW);
    }

    @Test
    @DisplayName("승리 시 배팅금액")
    void 승리_시_배팅금액() {
        //given
        BettingMoney bettingMoney = new BettingMoney(1000);
        Dealer dealer = new Dealer();
        Gambler pobi = new Gambler("pobi", bettingMoney);

        Card ten = new Card(CardRank.TEN, CardSuit.CLOVER);
        Card eight = new Card(CardRank.EIGHT, CardSuit.CLOVER);
        Card nine = new Card(CardRank.NINE, CardSuit.CLOVER);
        Card seven = new Card(CardRank.SEVEN, CardSuit.CLOVER);

        List<Card> pobiCards = List.of(ten, eight);
        List<Card> dealerCards = List.of(nine, seven);

        Deck pobiDeck = new FixedDeck(pobiCards);
        Deck dealerDeck = new FixedDeck(dealerCards);

        pobi.deal(pobiDeck);
        dealer.deal(dealerDeck);
        pobi.deal(pobiDeck);
        dealer.deal(dealerDeck);

        //when
        MatchResult matchResult = Referee.judge(dealer, pobi);
        int finalIncome = matchResult.calculateIncome(pobi.getBettingMoney());

        //then
        assertThat(finalIncome).isEqualTo(1000);
    }

    @Test
    @DisplayName("블랙잭 시 배팅금액")
    void 블랙잭_시_배팅금액() {
        //given
        BettingMoney bettingMoney = new BettingMoney(1000);
        Dealer dealer = new Dealer();
        Gambler pobi = new Gambler("pobi", bettingMoney);

        Card ten = new Card(CardRank.TEN, CardSuit.CLOVER);
        Card ace = new Card(CardRank.ACE, CardSuit.CLOVER);
        Card nine = new Card(CardRank.NINE, CardSuit.CLOVER);
        Card seven = new Card(CardRank.SEVEN, CardSuit.CLOVER);

        List<Card> pobiCards = List.of(ten, ace);
        List<Card> dealerCards = List.of(nine, seven);

        Deck pobiDeck = new FixedDeck(pobiCards);
        Deck dealerDeck = new FixedDeck(dealerCards);

        pobi.deal(pobiDeck);
        dealer.deal(dealerDeck);
        pobi.deal(pobiDeck);
        dealer.deal(dealerDeck);

        //when
        MatchResult matchResult = Referee.judge(dealer, pobi);
        int finalIncome = matchResult.calculateIncome(pobi.getBettingMoney());

        //then
        assertThat(finalIncome).isEqualTo(1500);
    }
}
