class DealerTest {
//    public static Stream<Arguments> needsToHit() {
//        return Stream.of(
//                Arguments.of(new Hand(TestDefaults.getCardsByRanks(List.of(Rank.TEN, Rank.SIX))), true),
//                Arguments.of(new Hand(TestDefaults.getCardsByRanks(List.of(Rank.TEN, Rank.SEVEN))), false)
//        );
//    }
//
//    public static Stream<Arguments> allPlayersBurst() {
//        return Stream.of(
//                Arguments.of(
//                        List.of(new Score(22, false)),
//                        new Hand(TestDefaults.getCardsByRanks(List.of(Rank.TEN, Rank.SIX))),
//                        false)
//        );
//    }
//
//    @ParameterizedTest
//    @MethodSource
//    @DisplayName("16이하의 값이라면 딜러는 카드를 더 받는다.")
//    void needsToHit(Hand hand, boolean expected) {
//        Dealer dealer = new Dealer(hand, new CasinoDealerHitStrategy());
//
//        assertThat(dealer.needsToHit(new Hit() List.of())).isEqualTo(expected);
//    }
//
//    @ParameterizedTest
//    @MethodSource
//    @DisplayName("만약 플레이어가 전부 버스트났다면 16 이하여도 카드를 더 뽑지 않는다.")
//    void allPlayersBurst(List<Score> playerScores, Hand hand, boolean expected) {
//        Dealer dealer = new Dealer(hand, new CasinoDealerHitStrategy());
//
//        assertThat(dealer.needsToHit(new Hit(),playerScores)).isEqualTo(expected);
//    }
}
