package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    void 유저_생성_테스트() {
        // given
        String name = "흑곰";

        // when & then
        assertThatCode(() -> new Player(name))
                .doesNotThrowAnyException();
    }

    @Test
    void 유저가_카드_한_장을_가져오는_테스트() {
        // given
        Player player = new Player("밀란");
        Card card = new Card(CardValue.ACE, CardShape.DIAMOND);

        // when
        int before = player.getCards().size();
        player.draw(card);

        // then
        assertThat(player.getCards().size()).isEqualTo(before + 1);
    }

    @Test
    void 가지고있는_카드의_합을_계산하는_기능_테스트() {
        // given
        Player player = new Player("밀란");
        player.draw(new Card(CardValue.FOUR, CardShape.DIAMOND));
        player.draw(new Card(CardValue.TWO, CardShape.DIAMOND));

        // when
        int sum = player.calculateCardsValue();

        // then
        assertThat(sum).isEqualTo(6);
    }

    @Test
    void 가지고_있는_카드의_핪이_버스트인지_확인하는_테스트() {
        // given
        Player player = new Player("밀란");
        player.draw(new Card(CardValue.TEN, CardShape.DIAMOND));
        player.draw(new Card(CardValue.TEN, CardShape.DIAMOND));
        player.draw(new Card(CardValue.TEN, CardShape.DIAMOND));

        // when
        boolean isBurst = player.isBurst();

        // then
        assertThat(isBurst).isTrue();
    }

    @Test
    void ACE의_값이_1이_유리할_때_테스트() {
        // given
        Player player = new Player("밀란");
        player.draw(new Card(CardValue.ACE, CardShape.DIAMOND));
        player.draw(new Card(CardValue.TEN, CardShape.DIAMOND));
        player.draw(new Card(CardValue.THREE, CardShape.DIAMOND));

        // when
        int sum = player.calculateCardsValue();

        // then
        assertThat(sum).isEqualTo(14);
    }

    @Test
    void ACE의_값이_11이_유리할_때_테스트() {
        // given
        Player player = new Player("밀란");
        player.draw(new Card(CardValue.ACE, CardShape.DIAMOND));
        player.draw(new Card(CardValue.TEN, CardShape.DIAMOND));

        // when
        int sum = player.calculateCardsValue();

        // then
        assertThat(sum).isEqualTo(21);
    }

    @Test
    void 카드_합_Burst_판단_테스트() {
        // given
        Player player = new Player("밀란");
        player.draw(new Card(CardValue.TEN, CardShape.CLOVER));
        player.draw(new Card(CardValue.TEN, CardShape.DIAMOND));
        player.draw(new Card(CardValue.TEN, CardShape.HEART));

        // when
        boolean isBurst = player.isBurst();

        // then
        assertThat(isBurst).isTrue();
    }

    @Test
    void 카드_합_Blackjack_판단_테스트() {
        // given
        Player player = new Player("밀란");
        player.draw(new Card(CardValue.ACE, CardShape.CLOVER));
        player.draw(new Card(CardValue.TEN, CardShape.DIAMOND));

        // when
        boolean isBlackjack = player.isBlackjack();

        // then
        assertThat(isBlackjack).isTrue();
    }

}
