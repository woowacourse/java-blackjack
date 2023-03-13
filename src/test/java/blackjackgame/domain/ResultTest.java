package blackjackgame.domain;

class ResultTest {/*
    @DisplayName("올바른 게임 승패 결과가 나오는지 확인한다")
    @Test
    void Should_EqualExpectedResult_When_GenerateResult() {
        // given
        Dealer dealer = new Dealer(new ArrayList<>(List.of(CLOVER_KING, CLOVER_NINE)));
        Guest win = new Guest(new Name("win"), new ArrayList<>(List.of(CLOVER_KING, CLOVER_NINE)));
        Guest lose = new Guest(new Name("lose"), new ArrayList<>(List.of(CLOVER_KING, CLOVER_ACE)));
        Guest draw = new Guest(new Name("draw"), new ArrayList<>(List.of(CLOVER_KING, CLOVER_NINE)));
        dealer.addCard(CLOVER_ACE);
        win.addCard(CLOVER_TWO);
        lose.addCard(CLOVER_TWO);
        draw.addCard(CLOVER_ACE);

        // when
        Judge judge = new Judge(dealer, new Guests(List.of(win, lose, draw)));
        Result result = new Result(judge.guestsResult());
        result.generateDealer();
        Map<Guest, GameOutcome> guestsResult = result.getGuests();
        Map<GameOutcome, Integer> dealerResult = result.getDealer();

        // then
        assertThat(guestsResult.get(win)).isEqualTo(GameOutcome.WIN);
        assertThat(guestsResult.get(lose)).isEqualTo(GameOutcome.LOSE);
        assertThat(guestsResult.get(draw)).isEqualTo(GameOutcome.DRAW);
        assertThat(dealerResult.get(GameOutcome.WIN)).isEqualTo(1);
        assertThat(dealerResult.get(GameOutcome.LOSE)).isEqualTo(1);
        assertThat(dealerResult.get(GameOutcome.DRAW)).isEqualTo(1);
    }

    @Nested
    @DisplayName("딜러와 참가자의 점수가 같을 때, ")
    class SameScore {
        @DisplayName("점수가 21을 넘었다면 게스트 패")
        @Test
        void Should_GuestLose_When_Over21() {
            // given
            Guest guest = new Guest(new Name("guest"), new ArrayList<>(List.of(CLOVER_KING, CLOVER_NINE)));
            guest.addCard(CLOVER_THREE);

            Dealer dealer = new Dealer(new ArrayList<>(List.of(CLOVER_KING, CLOVER_NINE)));
            dealer.addCard(CLOVER_THREE);

            // when
            Judge judge = new Judge(dealer, new Guests(List.of(guest)));
            Result result = new Result(judge.guestsResult());
            result.generateDealer();

            Map<Guest, GameOutcome> guestsResult = result.getGuests();
            Map<GameOutcome, Integer> dealerResult = result.getDealer();

            // then
            assertThat(guestsResult.get(guest)).isEqualTo(GameOutcome.LOSE);
            assertThat(dealerResult.get(GameOutcome.WIN)).isEqualTo(1);
            assertThat(dealerResult.get(GameOutcome.LOSE)).isEqualTo(0);
            assertThat(dealerResult.get(GameOutcome.DRAW)).isEqualTo(0);
        }

        @DisplayName("점수가 21이하일때 무승부")
        @Test
        void Should_GuestDraw_When_Below21() {
            // given
            Guest guest = new Guest(new Name("guest"), new ArrayList<>(List.of(CLOVER_KING, CLOVER_NINE)));
            guest.addCard(CLOVER_TWO);

            Dealer dealer = new Dealer(new ArrayList<>(List.of(CLOVER_KING, CLOVER_NINE)));
            dealer.addCard(CLOVER_TWO);

            // when
            Judge judge = new Judge(dealer, new Guests(List.of(guest)));
            Result result = new Result(judge.guestsResult());
            result.generateDealer();

            Map<Guest, GameOutcome> guestsResult = result.getGuests();
            Map<GameOutcome, Integer> dealerResult = result.getDealer();

            //then
            assertThat(guestsResult.get(guest)).isEqualTo(GameOutcome.DRAW);
            assertThat(dealerResult.get(GameOutcome.WIN)).isEqualTo(0);
            assertThat(dealerResult.get(GameOutcome.LOSE)).isEqualTo(0);
            assertThat(dealerResult.get(GameOutcome.DRAW)).isEqualTo(1);
        }
    }

    @Nested
    @DisplayName("딜러의 점수가 높을 때, ")
    class DealerHigherScore {
        @DisplayName("딜러와 게스트의 점수가 21을 초과한 경우 게스트 패")
        @Test
        void Should_GuestLose_When_DealerAndGuestOver21() {
            // given
            Guest guest = new Guest(new Name("guest"), new ArrayList<>(List.of(CLOVER_KING, CLOVER_NINE)));
            guest.addCard(CLOVER_THREE);

            Dealer dealer = new Dealer(new ArrayList<>(List.of(CLOVER_KING, CLOVER_NINE)));
            dealer.addCard(CLOVER_FOUR);

            // when
            Judge judge = new Judge(dealer, new Guests(List.of(guest)));
            Result result = new Result(judge.guestsResult());
            result.generateDealer();

            Map<Guest, GameOutcome> guestsResult = result.getGuests();
            Map<GameOutcome, Integer> dealerResult = result.getDealer();

            // then
            assertThat(guestsResult.get(guest)).isEqualTo(GameOutcome.LOSE);
            assertThat(dealerResult.get(GameOutcome.WIN)).isEqualTo(1);
            assertThat(dealerResult.get(GameOutcome.LOSE)).isEqualTo(0);
            assertThat(dealerResult.get(GameOutcome.DRAW)).isEqualTo(0);
        }

        @DisplayName("딜러의 점수가 21을 초과하고 게스트의 점수가 21이하인 경우 게스트 승")
        @Test
        void Should_GuestWin_When_DealerOver21GuestBelow21() {
            // given
            Guest guest = new Guest(new Name("guest"), new ArrayList<>(List.of(CLOVER_KING, CLOVER_NINE)));
            guest.addCard(CLOVER_TWO);

            Dealer dealer = new Dealer(new ArrayList<>(List.of(CLOVER_KING, CLOVER_NINE)));
            dealer.addCard(CLOVER_THREE);

            // when
            Judge judge = new Judge(dealer, new Guests(List.of(guest)));
            Result result = new Result(judge.guestsResult());
            result.generateDealer();

            Map<Guest, GameOutcome> guestsResult = result.getGuests();
            Map<GameOutcome, Integer> dealerResult = result.getDealer();

            // then
            assertThat(guestsResult.get(guest)).isEqualTo(GameOutcome.WIN);
            assertThat(dealerResult.get(GameOutcome.WIN)).isEqualTo(0);
            assertThat(dealerResult.get(GameOutcome.LOSE)).isEqualTo(1);
            assertThat(dealerResult.get(GameOutcome.DRAW)).isEqualTo(0);
        }

        @DisplayName("딜러의 점수가 21이하고 게스트의 점수가 21 초과인 경우 게스트 패")
        @Test
        void Should_GuestLose_When_DealerBelow21GuestOver21() {
            // given
            Guest guest = new Guest(new Name("guest"), new ArrayList<>(List.of(CLOVER_KING, CLOVER_NINE)));
            guest.addCard(CLOVER_THREE);

            Dealer dealer = new Dealer(new ArrayList<>(List.of(CLOVER_KING, CLOVER_NINE)));
            dealer.addCard(CLOVER_TWO);

            // when
            Judge judge = new Judge(dealer, new Guests(List.of(guest)));
            Result result = new Result(judge.guestsResult());
            result.generateDealer();

            Map<Guest, GameOutcome> guestsResult = result.getGuests();
            Map<GameOutcome, Integer> dealerResult = result.getDealer();

            // then
            assertThat(guestsResult.get(guest)).isEqualTo(GameOutcome.LOSE);
            assertThat(dealerResult.get(GameOutcome.WIN)).isEqualTo(1);
            assertThat(dealerResult.get(GameOutcome.LOSE)).isEqualTo(0);
            assertThat(dealerResult.get(GameOutcome.DRAW)).isEqualTo(0);
        }

        @DisplayName("딜러와 게스트의 점수가 21이하인 경우, 게스트 패")
        @Test
        void Should_GuestLose_When_DealerAndGuestBelow21() {
            // given
            Guest guest = new Guest(new Name("guest"), new ArrayList<>(List.of(CLOVER_KING, CLOVER_NINE)));
            guest.addCard(CLOVER_ACE);

            Dealer dealer = new Dealer(new ArrayList<>(List.of(CLOVER_KING, CLOVER_NINE)));
            dealer.addCard(CLOVER_TWO);

            // when
            Judge judge = new Judge(dealer, new Guests(List.of(guest)));
            Result result = new Result(judge.guestsResult());
            result.generateDealer();

            Map<Guest, GameOutcome> guestsResult = result.getGuests();
            Map<GameOutcome, Integer> dealerResult = result.getDealer();

            // then
            assertThat(guestsResult.get(guest)).isEqualTo(GameOutcome.LOSE);
            assertThat(dealerResult.get(GameOutcome.WIN)).isEqualTo(1);
            assertThat(dealerResult.get(GameOutcome.LOSE)).isEqualTo(0);
            assertThat(dealerResult.get(GameOutcome.DRAW)).isEqualTo(0);
        }

    }

    @Nested
    @DisplayName("게스트의 점수가 높을 때, ")
    class GuestHigherScore {
        @DisplayName("딜러와 게스트의 점수가 21을 초과한 경우 게스트 패")
        @Test
        void Should_GuestLose_When_DealerAndGuestOver21() {
            // given
            Guest guest = new Guest(new Name("guest"), new ArrayList<>(List.of(CLOVER_KING, CLOVER_NINE)));
            guest.addCard(CLOVER_FOUR);

            Dealer dealer = new Dealer(new ArrayList<>(List.of(CLOVER_KING, CLOVER_NINE)));
            dealer.addCard(CLOVER_THREE);

            // when
            Judge judge = new Judge(dealer, new Guests(List.of(guest)));
            Result result = new Result(judge.guestsResult());
            result.generateDealer();

            Map<Guest, GameOutcome> guestsResult = result.getGuests();
            Map<GameOutcome, Integer> dealerResult = result.getDealer();

            // then
            assertThat(guestsResult.get(guest)).isEqualTo(GameOutcome.LOSE);
            assertThat(dealerResult.get(GameOutcome.WIN)).isEqualTo(1);
            assertThat(dealerResult.get(GameOutcome.LOSE)).isEqualTo(0);
            assertThat(dealerResult.get(GameOutcome.DRAW)).isEqualTo(0);
        }

        @DisplayName("딜러의 점수가 21이하이고 게스트의 점수가 21초과인 경우 게스트 패")
        @Test
        void Should_GuestWin_When_DealerBelow21GuestOver21() {
            // given
            Guest guest = new Guest(new Name("guest"), new ArrayList<>(List.of(CLOVER_KING, CLOVER_NINE)));
            guest.addCard(CLOVER_THREE);

            Dealer dealer = new Dealer(new ArrayList<>(List.of(CLOVER_KING, CLOVER_NINE)));
            dealer.addCard(CLOVER_TWO);

            // when
            Judge judge = new Judge(dealer, new Guests(List.of(guest)));
            Result result = new Result(judge.guestsResult());
            result.generateDealer();

            Map<Guest, GameOutcome> guestsResult = result.getGuests();
            Map<GameOutcome, Integer> dealerResult = result.getDealer();

            // then
            assertThat(guestsResult.get(guest)).isEqualTo(GameOutcome.LOSE);
            assertThat(dealerResult.get(GameOutcome.WIN)).isEqualTo(1);
            assertThat(dealerResult.get(GameOutcome.LOSE)).isEqualTo(0);
            assertThat(dealerResult.get(GameOutcome.DRAW)).isEqualTo(0);
        }

        @DisplayName("딜러의 점수가 21초과이고 게스트의 점수가 21 이하인 경우 게스트 승")
        @Test
        void Should_GuestLose_When_DealerOver21GuestBelow21() {
            // given
            Guest guest = new Guest(new Name("guest"), new ArrayList<>(List.of(CLOVER_KING, CLOVER_NINE)));
            guest.addCard(CLOVER_TWO);

            Dealer dealer = new Dealer(new ArrayList<>(List.of(CLOVER_KING, CLOVER_NINE)));
            dealer.addCard(CLOVER_THREE);

            // when
            Judge judge = new Judge(dealer, new Guests(List.of(guest)));
            Result result = new Result(judge.guestsResult());
            result.generateDealer();

            Map<Guest, GameOutcome> guestsResult = result.getGuests();
            Map<GameOutcome, Integer> dealerResult = result.getDealer();

            // then
            assertThat(guestsResult.get(guest)).isEqualTo(GameOutcome.WIN);
            assertThat(dealerResult.get(GameOutcome.WIN)).isEqualTo(0);
            assertThat(dealerResult.get(GameOutcome.LOSE)).isEqualTo(1);
            assertThat(dealerResult.get(GameOutcome.DRAW)).isEqualTo(0);
        }

        @DisplayName("딜러와 게스트의 점수가 21이하인 경우, 게스트 승")
        @Test
        void Should_GuestLose_When_DealerAndGuestBelow21() {
            // given
            Guest guest = new Guest(new Name("guest"), new ArrayList<>(List.of(CLOVER_KING, CLOVER_NINE)));
            guest.addCard(CLOVER_TWO);

            Dealer dealer = new Dealer(new ArrayList<>(List.of(CLOVER_KING, CLOVER_NINE)));
            dealer.addCard(CLOVER_ACE);

            // when
            Judge judge = new Judge(dealer, new Guests(List.of(guest)));
            Result result = new Result(judge.guestsResult());
            result.generateDealer();

            Map<Guest, GameOutcome> guestsResult = result.getGuests();
            Map<GameOutcome, Integer> dealerResult = result.getDealer();

            // then
            assertThat(guestsResult.get(guest)).isEqualTo(GameOutcome.WIN);
            assertThat(dealerResult.get(GameOutcome.WIN)).isEqualTo(0);
            assertThat(dealerResult.get(GameOutcome.LOSE)).isEqualTo(1);
            assertThat(dealerResult.get(GameOutcome.DRAW)).isEqualTo(0);
        }

    }*/

}
