package domain;

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
        List<Cards> pickedCards = CardSelector.pickCards(names, new RandomCardNumberGenerator(), new CardBox());
        Dealer dealer = new Dealer(new Name("네오"), new Cards(List.of(new Card("A하트", 11))));
        List<Player> players = CardSelector.giveCardsToPlayers(names, pickedCards, dealer);
        //when
        List<Result> results = ResultCalculator.getWinningResult(dealer,players);
        //then
        Assertions.assertThat(results.size()).isEqualTo(players.size()-1);
    }
}
