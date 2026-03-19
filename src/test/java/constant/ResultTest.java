package constant;

import static org.assertj.core.api.Assertions.assertThat;

import domain.bet.Money;
import domain.card.Card;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Participant;
import domain.participant.Player;
import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ResultTest {

    @Nested
    class FromTest {

        @Nested
        class Success {

            @Test
            void 플레이어와_딜러가_둘다_버스트여도_플레이어_버스트를_우선해_LOSE를_반환한다() {

                // given
                Dealer dealer = new Dealer();
                Player player = new Player(new Name("pobi"), Money.from("1000"));
                addCards(dealer, Rank.K, Rank.Q, Rank.TWO);
                addCards(player, Rank.K, Rank.Q, Rank.THREE);

                // when
                Result actual = Result.from(dealer, player);

                // then
                assertThat(actual).isEqualTo(Result.LOSE);
            }

            @Test
            void 딜러가_버스트면_WIN을_반환한다() {

                // given
                Dealer dealer = new Dealer();
                Player player = new Player(new Name("pobi"), Money.from("1000"));
                addCards(dealer, Rank.K, Rank.Q, Rank.TWO);
                addCards(player, Rank.TEN, Rank.NINE);

                // when
                Result actual = Result.from(dealer, player);

                // then
                assertThat(actual).isEqualTo(Result.WIN);
            }

            @Test
            void 딜러만_블랙잭이면_LOSE를_반환한다() {

                // given
                Dealer dealer = new Dealer();
                Player player = new Player(new Name("pobi"), Money.from("1000"));
                addCards(dealer, Rank.ACE, Rank.K);
                addCards(player, Rank.TEN, Rank.NINE);

                // when
                Result actual = Result.from(dealer, player);

                // then
                assertThat(actual).isEqualTo(Result.LOSE);
            }

            @Test
            void 둘다_블랙잭이면_PUSH를_반환한다() {

                // given
                Dealer dealer = new Dealer();
                Player player = new Player(new Name("pobi"), Money.from("1000"));
                addCards(dealer, Rank.ACE, Rank.K);
                addCards(player, Rank.ACE, Rank.Q);

                // when
                Result actual = Result.from(dealer, player);

                // then
                assertThat(actual).isEqualTo(Result.PUSH);
            }

            @Test
            void 플레이어만_블랙잭이면_BLACKJACK을_반환한다() {

                // given
                Dealer dealer = new Dealer();
                Player player = new Player(new Name("pobi"), Money.from("1000"));
                addCards(dealer, Rank.TEN, Rank.NINE);
                addCards(player, Rank.ACE, Rank.K);

                // when
                Result actual = Result.from(dealer, player);

                // then
                assertThat(actual).isEqualTo(Result.BLACKJACK);
            }

            @Test
            void 둘다_블랙잭이_아니고_점수가_같으면_PUSH를_반환한다() {

                // given
                Dealer dealer = new Dealer();
                Player player = new Player(new Name("pobi"), Money.from("1000"));
                addCards(dealer, Rank.TEN, Rank.SEVEN);
                addCards(player, Rank.NINE, Rank.EIGHT);

                // when
                Result actual = Result.from(dealer, player);

                // then
                assertThat(actual).isEqualTo(Result.PUSH);
            }

            @Test
            void 둘다_블랙잭이_아니고_딜러_점수가_높으면_LOSE를_반환한다() {

                // given
                Dealer dealer = new Dealer();
                Player player = new Player(new Name("pobi"), Money.from("1000"));
                addCards(dealer, Rank.TEN, Rank.EIGHT);
                addCards(player, Rank.TEN, Rank.SEVEN);

                // when
                Result actual = Result.from(dealer, player);

                // then
                assertThat(actual).isEqualTo(Result.LOSE);
            }

            @Test
            void 둘다_블랙잭이_아니고_플레이어_점수가_높으면_WIN을_반환한다() {

                // given
                Dealer dealer = new Dealer();
                Player player = new Player(new Name("pobi"), Money.from("1000"));
                addCards(dealer, Rank.TEN, Rank.SEVEN);
                addCards(player, Rank.TEN, Rank.EIGHT);

                // when
                Result actual = Result.from(dealer, player);

                // then
                assertThat(actual).isEqualTo(Result.WIN);
            }
        }
    }

    private void addCards(Participant participant, Rank... ranks) {
        Suit[] suits = Suit.values();
        for (int i = 0; i < ranks.length; i++) {
            participant.addCard(List.of(new Card(ranks[i], suits[i % suits.length])));
        }
    }
}
