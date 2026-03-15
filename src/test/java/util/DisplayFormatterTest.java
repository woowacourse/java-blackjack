package util;

import static org.assertj.core.api.Assertions.assertThat;

import domain.Card;
import domain.Dealer;
import domain.User;
import dto.DealerResultDTO;
import dto.ProfitResultDTO;
import dto.UserCardsDTO;
import dto.UserResultDTO;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vo.Money;
import vo.Rank;
import vo.Suit;

public class DisplayFormatterTest {
    @Test
    @DisplayName("카드가 한 장인 경우, UserCardsDTO를 출력 형식에 맞춰 포맷팅 한다.")
    void 카드_한_장_출력_포맷팅_테스트() {
        // given
        User user = new User("라이", new Money(1000));
        user.receiveCard(new Card(Suit.CLUB, Rank.ACE));
        UserCardsDTO userCardsDTO = UserCardsDTO.fromUser(user);

        // when
        String actualResult = DisplayFormatter.formatUserCardsDisplay(userCardsDTO);

        // then
        assertThat(actualResult).isEqualTo("라이카드: A클로버");
    }

    @Test
    @DisplayName("카드가 여러 장인 경우, UserCardsDTO를 출력 형식에 맞춰 포맷팅 한다.")
    void 카드_여러_장_출력_포맷팅_테스트() {
        // given
        User user = new User("라이", new Money(1000));
        user.receiveCard(new Card(Suit.CLUB, Rank.ACE));
        user.receiveCard(new Card(Suit.DIAMOND, Rank.JACK));
        UserCardsDTO userCardsDTO = UserCardsDTO.fromUser(user);

        // when
        String actualResult = DisplayFormatter.formatUserCardsDisplay(userCardsDTO);

        // then
        assertThat(actualResult).isEqualTo("라이카드: A클로버, J다이아몬드");
    }

    @Test
    @DisplayName("카드가 한 장인 경우, UserResultDTO를 출력 형식에 맞춰 포맷팅 한다.")
    void 카드_한_장_결과_출력_포맷팅_테스트() {
        // given
        User user = new User("라이", new Money(1000));
        user.receiveCard(new Card(Suit.CLUB, Rank.ACE));
        user.calculateScore();
        UserResultDTO userResultDTO = UserResultDTO.fromUser(user);

        // when
        String actualResult = DisplayFormatter.formatUserResultDisplay(userResultDTO);

        // then
        assertThat(actualResult).isEqualTo("라이카드: A클로버 - 결과: 11");
    }

    @Test
    @DisplayName("카드가 여러 장인 경우, UserResultDTO를 출력 형식에 맞춰 포맷팅 한다.")
    void 카드_여러_장_결과_출력_포맷팅_테스트() {
        // given
        User user = new User("라이", new Money(1000));
        user.receiveCard(new Card(Suit.CLUB, Rank.ACE));
        user.receiveCard(new Card(Suit.DIAMOND, Rank.JACK));
        user.calculateScore();
        UserResultDTO userResultDTO = UserResultDTO.fromUser(user);

        // when
        String actualResult = DisplayFormatter.formatUserResultDisplay(userResultDTO);

        // then
        assertThat(actualResult).isEqualTo("라이카드: A클로버, J다이아몬드 - 결과: 21");
    }

    @Test
    @DisplayName("카드가 한 장인 경우, DealerResultDTO를 출력 형식에 맞춰 포맷팅 한다.")
    void 딜러_카드_한_장_결과_출력_포맷팅_테스트() {
        // given
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Suit.SPADE, Rank.KING));
        dealer.calculateScore();
        DealerResultDTO dealerResultDTO = DealerResultDTO.fromDealer(dealer);

        // when
        String actualResult = DisplayFormatter.formatDealerResultDisplay(dealerResultDTO);

        // then
        assertThat(actualResult).isEqualTo("딜러 카드: K스페이드 - 결과: 10");
    }

    @Test
    @DisplayName("카드가 여러 장인 경우, DealerResultDTO를 출력 형식에 맞춰 포맷팅 한다.")
    void 딜러_카드_여러_장_결과_출력_포맷팅_테스트() {
        // given
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Suit.SPADE, Rank.KING));
        dealer.receiveCard(new Card(Suit.HEART, Rank.NINE));
        dealer.calculateScore();
        DealerResultDTO dealerResultDTO = DealerResultDTO.fromDealer(dealer);

        // when
        String actualResult = DisplayFormatter.formatDealerResultDisplay(dealerResultDTO);

        // then
        assertThat(actualResult).isEqualTo("딜러 카드: K스페이드, 9하트 - 결과: 19");
    }

    @Test
    @DisplayName("ProfitResultDTO를 출력 형식에 맞춰 포맷팅 한다.")
    void 수익_결과_출력_포맷팅_테스트() {
        // given
        Money dealerProfit = new Money(10000);
        Map<String, Money> participantsProfit = new LinkedHashMap<>();
        participantsProfit.put("라이", new Money(10000));
        participantsProfit.put("영기", new Money(-20000));

        ProfitResultDTO profitResultDTO = new ProfitResultDTO(dealerProfit, participantsProfit);

        // when
        List<String> actualResult = DisplayFormatter.formatProfitResult(profitResultDTO);

        // then
        assertThat(actualResult).containsExactly(
                "딜러: 10000",
                "라이: 10000",
                "영기: -20000"
        );
    }
}
