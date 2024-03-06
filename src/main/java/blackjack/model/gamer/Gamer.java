package blackjack.model;

public abstract class Gamer {

    protected final Deck deck = new Deck();

    public void receiveCard(Card card) {
        deck.addCard(card);
        GameRule.adjustAceValue(deck);
    }

    public abstract boolean canHit();

    public int getCardScore() {
        return deck.calculateTotalScore();
    }

//    //TODO : 메서드 분리
//    public int calculateFinalScore() {
//        int sum = 0;
//
//        if (cards.stream()
//                .anyMatch(Card::isAce)) {
//            sum = cards.stream()
//                    .mapToInt(Card::extractCardNumber)
//                    .sum();
//
//            if (sum + 10 > 21) {
//                return sum;
//            }
//
//            return sum + 10;
//        }
//
//        sum = cards.stream()
//                .mapToInt(Card::extractCardNumber)
//                .sum();
//
//        return sum;
}
