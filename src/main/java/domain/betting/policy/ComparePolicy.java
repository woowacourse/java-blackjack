//package domain.betting.policy;
//
//import domain.betting.BettingResult;
//import domain.betting.BettingRate;
//import domain.gamer.Dealer;
//import domain.gamer.Player;
//
//public class ComparePolicy extends BettingPolicy{
//
//    public ComparePolicy() {
//        super(BettingResult.PLAYER_WIN);
//    }
//
//    @Override
//    public boolean isPolicyApplied(Dealer dealer, Player player) {
//        return player.isStand() && dealer.isStand();
//    }
//
//    @Override
//    public BettingRate getBettingRate(Dealer dealer, Player player) {
//        return compareScore(dealer.getResultScore(), player.getResultScore());
//    }
//
//    private BettingRate compareScore(int dealerScore, int playerScore) {
//        if (playerScore > dealerScore) {
//            return bettingResult.bettingRate();
//        }
//        BettingResult lose = bettingResult.reverseResult();
//        return lose.bettingRate();
//    }
//
//}
