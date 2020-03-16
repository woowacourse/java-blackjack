package domain;

import domain.card.Cards;
import domain.player.Dealer;
import domain.player.UserInformation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserInformationTest {
    @DisplayName("딜러정보를 생성하는지 테스트")
    @Test
    void dealerInformationTest() {
        Cards cards = new Cards();
        Dealer dealer = new Dealer(cards.giveCard(),cards.giveCard());
        UserInformation usersInformations = new UserInformation(dealer);

        Assertions.assertThat(usersInformations.getName()).isEqualTo("딜러");
    }
}
