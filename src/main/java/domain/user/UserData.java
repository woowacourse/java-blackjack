//package domain.user;
//
//import domain.card.Card;
//import domain.card.Score;
//import domain.state.Ready;
//import domain.state.State;
//
//import java.util.List;
//
//public final class UserData {
//    private final Name name;
//    private State state;
//
//    public UserData(Name name, Ready ready) {
//        this.name = name;
//        this.state = ready;
//    }
//
//    public void receiveCard(Card card) {
//        state = state.draw(card);
//    }
//
//    public void doStay() {
//        state = state.stay();
//    }
//
//    public boolean hasResult() {
//        return state.isFinished();
//    }
//
//    public boolean isBust() {
//        return state.isBust();
//    }
//
//    public boolean isBlackjack() {
//        return state.isBlackjack();
//    }
//
//    public List<Card> getCards() {
//        return state.getCards();
//    }
//
//    public Score getScore() {
//        return state.getScore();
//    }
//
//    public Name getName() {
//        return name;
//    }
//
////    public int getPrize(int batting) {
////        return state.calculatePrize(batting);
////    }
//}
