package blackjackgame.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ResultTest {
    private final Card ten = new Card(Symbol.CLOVER, CardValue.KING);
    private final Card nine = new Card(Symbol.CLOVER, CardValue.NINE);
    private final Card one = new Card(Symbol.CLOVER, CardValue.ACE);
    private final Card two = new Card(Symbol.CLOVER, CardValue.TWO);
    private final Card three = new Card(Symbol.CLOVER, CardValue.THREE);
    private final Card four = new Card(Symbol.CLOVER, CardValue.FOUR);

    @DisplayName("올바른 게임 승패 결과가 나오는지 확인한다")
    @Test
    void Should_EqualExpectedResult_When_GenerateResult() {
        Guest win = new Guest(new Name("win"));
        addCards(win, ten, nine, two);

        Guest lose = new Guest(new Name("lose"));
        addCards(lose, ten, one, two);

        Guest draw = new Guest(new Name("draw"));
        addCards(draw, ten, nine, one);

        Dealer dealer = new Dealer();
        addCards(dealer, ten, nine, one);

        Result result = new Result(dealer, List.of(win, lose, draw));

        Map<String, String> guestsResult = result.getGuestsResult();
        Map<String, Integer> dealerResult = result.getDealerResult();

        assertThat(guestsResult.get("win")).isEqualTo("승");
        assertThat(guestsResult.get("lose")).isEqualTo("패");
        assertThat(guestsResult.get("draw")).isEqualTo("무");
        assertThat(dealerResult.get("승")).isEqualTo(1);
        assertThat(dealerResult.get("패")).isEqualTo(1);
        assertThat(dealerResult.get("무")).isEqualTo(1);
    }

    @Nested
    @DisplayName("딜러와 참가자의 점수가 같을 때, ")
    class SameScore {
        @DisplayName("점수가 21을 넘었다면 게스트 패")
        @Test
        void Should_GuestLose_When_Over21() {
            Guest guest = new Guest(new Name("guest"));
            addCards(guest, ten, nine, three);

            Dealer dealer = new Dealer();
            addCards(dealer, ten, nine, three);

            Result result = new Result(dealer, List.of(guest));

            Map<String, String> guestsResult = result.getGuestsResult();
            Map<String, Integer> dealerResult = result.getDealerResult();

            assertThat(guestsResult.get("guest")).isEqualTo("패");
            assertThat(dealerResult.get("승")).isEqualTo(1);
            assertThat(dealerResult.get("패")).isEqualTo(0);
            assertThat(dealerResult.get("무")).isEqualTo(0);
        }

        @DisplayName("점수가 21이하일때 무승부")
        @Test
        void Should_GuestDraw_When_Below21() {
            Guest guest = new Guest(new Name("guest"));
            addCards(guest, ten, nine, two);

            Dealer dealer = new Dealer();
            addCards(dealer, ten, nine, two);

            Result result = new Result(dealer, List.of(guest));

            Map<String, String> guestsResult = result.getGuestsResult();
            Map<String, Integer> dealerResult = result.getDealerResult();

            assertThat(guestsResult.get("guest")).isEqualTo("무");
            assertThat(dealerResult.get("승")).isEqualTo(0);
            assertThat(dealerResult.get("패")).isEqualTo(0);
            assertThat(dealerResult.get("무")).isEqualTo(1);
        }
    }


    @Nested
    @DisplayName("딜러의 점수가 높을 때, ")
    class DealerHigherScore {
        @DisplayName("딜러와 게스트의 점수가 21을 초과한 경우 게스트 패")
        @Test
        void Should_GuestLose_When_DealerAndGuestOver21() {
            Guest guest = new Guest(new Name("guest"));
            addCards(guest, ten, nine, three);

            Dealer dealer = new Dealer();
            addCards(dealer, ten, nine, four);

            Result result = new Result(dealer, List.of(guest));

            Map<String, String> guestsResult = result.getGuestsResult();
            Map<String, Integer> dealerResult = result.getDealerResult();

            assertThat(guestsResult.get("guest")).isEqualTo("패");
            assertThat(dealerResult.get("승")).isEqualTo(1);
            assertThat(dealerResult.get("패")).isEqualTo(0);
            assertThat(dealerResult.get("무")).isEqualTo(0);
        }

        @DisplayName("딜러의 점수가 21을 초과하고 게스트의 점수가 21이하인 경우 게스트 승")
        @Test
        void Should_GuestWin_When_DealerOver21GuestBelow21() {
            Guest guest = new Guest(new Name("guest"));
            addCards(guest, ten, nine, two);

            Dealer dealer = new Dealer();
            addCards(dealer, ten, nine, three);

            Result result = new Result(dealer, List.of(guest));

            Map<String, String> guestsResult = result.getGuestsResult();
            Map<String, Integer> dealerResult = result.getDealerResult();

            assertThat(guestsResult.get("guest")).isEqualTo("승");
            assertThat(dealerResult.get("승")).isEqualTo(0);
            assertThat(dealerResult.get("패")).isEqualTo(1);
            assertThat(dealerResult.get("무")).isEqualTo(0);
        }

        @DisplayName("딜러의 점수가 21이하고 게스트의 점수가 21 초과인 경우 게스트 패")
        @Test
        void Should_GuestLose_When_DealerBelow21GuestOver21() {
            Guest guest = new Guest(new Name("guest"));
            addCards(guest, ten, nine, three);

            Dealer dealer = new Dealer();
            addCards(dealer, ten, nine, two);

            Result result = new Result(dealer, List.of(guest));

            Map<String, String> guestsResult = result.getGuestsResult();
            Map<String, Integer> dealerResult = result.getDealerResult();

            assertThat(guestsResult.get("guest")).isEqualTo("패");
            assertThat(dealerResult.get("승")).isEqualTo(1);
            assertThat(dealerResult.get("패")).isEqualTo(0);
            assertThat(dealerResult.get("무")).isEqualTo(0);
        }

        @DisplayName("딜러와 게스트의 점수가 21이하인 경우, 게스트 패")
        @Test
        void Should_GuestLose_When_DealerAndGuestBelow21() {
            Guest guest = new Guest(new Name("guest"));
            addCards(guest, ten, nine, one);

            Dealer dealer = new Dealer();
            addCards(dealer, ten, nine, two);

            Result result = new Result(dealer, List.of(guest));

            Map<String, String> guestsResult = result.getGuestsResult();
            Map<String, Integer> dealerResult = result.getDealerResult();

            assertThat(guestsResult.get("guest")).isEqualTo("패");
            assertThat(dealerResult.get("승")).isEqualTo(1);
            assertThat(dealerResult.get("패")).isEqualTo(0);
            assertThat(dealerResult.get("무")).isEqualTo(0);
        }

    }


    @Nested
    @DisplayName("게스트의 점수가 높을 때, ")
    class GuestHigherScore {
        @DisplayName("딜러와 게스트의 점수가 21을 초과한 경우 게스트 패")
        @Test
        void Should_GuestLose_When_DealerAndGuestOver21() {
            Guest guest = new Guest(new Name("guest"));
            addCards(guest, ten, nine, four);

            Dealer dealer = new Dealer();
            addCards(dealer, ten, nine, three);

            Result result = new Result(dealer, List.of(guest));

            Map<String, String> guestsResult = result.getGuestsResult();
            Map<String, Integer> dealerResult = result.getDealerResult();

            assertThat(guestsResult.get("guest")).isEqualTo("패");
            assertThat(dealerResult.get("승")).isEqualTo(1);
            assertThat(dealerResult.get("패")).isEqualTo(0);
            assertThat(dealerResult.get("무")).isEqualTo(0);
        }

        @DisplayName("딜러의 점수가 21이하이고 게스트의 점수가 21초과인 경우 게스트 패")
        @Test
        void Should_GuestWin_When_DealerBelow21GuestOver21() {
            Guest guest = new Guest(new Name("guest"));
            addCards(guest, ten, nine, three);

            Dealer dealer = new Dealer();
            addCards(dealer, ten, nine, two);

            Result result = new Result(dealer, List.of(guest));

            Map<String, String> guestsResult = result.getGuestsResult();
            Map<String, Integer> dealerResult = result.getDealerResult();

            assertThat(guestsResult.get("guest")).isEqualTo("패");
            assertThat(dealerResult.get("승")).isEqualTo(1);
            assertThat(dealerResult.get("패")).isEqualTo(0);
            assertThat(dealerResult.get("무")).isEqualTo(0);
        }

        @DisplayName("딜러의 점수가 21초과이고 게스트의 점수가 21 이하인 경우 게스트 승")
        @Test
        void Should_GuestLose_When_DealerOver21GuestBelow21() {
            Guest guest = new Guest(new Name("guest"));
            addCards(guest, ten, nine, two);

            Dealer dealer = new Dealer();
            addCards(dealer, ten, nine, three);

            Result result = new Result(dealer, List.of(guest));

            Map<String, String> guestsResult = result.getGuestsResult();
            Map<String, Integer> dealerResult = result.getDealerResult();

            assertThat(guestsResult.get("guest")).isEqualTo("승");
            assertThat(dealerResult.get("승")).isEqualTo(0);
            assertThat(dealerResult.get("패")).isEqualTo(1);
            assertThat(dealerResult.get("무")).isEqualTo(0);
        }

        @DisplayName("딜러와 게스트의 점수가 21이하인 경우, 게스트 승")
        @Test
        void Should_GuestLose_When_DealerAndGuestBelow21() {
            Guest guest = new Guest(new Name("guest"));
            addCards(guest, ten, nine, two);

            Dealer dealer = new Dealer();
            addCards(dealer, ten, nine, one);

            Result result = new Result(dealer, List.of(guest));

            Map<String, String> guestsResult = result.getGuestsResult();
            Map<String, Integer> dealerResult = result.getDealerResult();

            assertThat(guestsResult.get("guest")).isEqualTo("승");
            assertThat(dealerResult.get("승")).isEqualTo(0);
            assertThat(dealerResult.get("패")).isEqualTo(1);
            assertThat(dealerResult.get("무")).isEqualTo(0);
        }

    }

    private void addCards(Player player, Card ... cards) {
        for (Card card : cards) {
            player.addCard(card);
        }
    }






}
