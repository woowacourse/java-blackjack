package domain;

import domain.generator.CardNumberGenerator;
import domain.generator.RandomCardNumberGenerator;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ResultCalculatorTest {

    @Test
    void 딜러를_기준으로_딜러제외_플레이어_수만큼의_결과반환() {
        //given
        List<Name> names = List.of(new Name("하마드"), new Name("아코"), new Name("또링"));
        CardNumberGenerator cardNumberGenerator = new RandomCardNumberGenerator();
        List<Player> players = CardSelector.giveCardsToPlayers(names, cardNumberGenerator,List.of(100,200,300));
        List<Card> dealerCardsByCardBox = new ArrayList<>();
        dealerCardsByCardBox.add(new Card("5하트", 5));
        dealerCardsByCardBox.add(new Card("10하트", 10));
        Cards dealerCards = new Cards(dealerCardsByCardBox);
        Dealer dealer = new Dealer(dealerCards);
        //when
        List<Result> results = ResultCalculator.getWinningResult(dealer, players);
        //then
        Assertions.assertThat(results.size()).isEqualTo(players.size());
    }
}
