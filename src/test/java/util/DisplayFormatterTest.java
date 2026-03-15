package util;

import static org.assertj.core.api.Assertions.assertThat;

import domain.Card;
import domain.User;
import dto.UserCardsDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vo.Money;
import vo.Rank;
import vo.Suit;

public class DisplayFormatterTest {
    @Test
    @DisplayName("카드가 한 장인 경우, UserCardsDTO를 출력 형식에 맞춰 포맷팅 한다.")
    void 카드_한_장_출력_형식_포맷팅_테스트() {
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
    void 카드_여러_장_출력_형식_포맷팅_테스트() {
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
}
