package blackjack.domain;

import java.util.Random;

public class CardGenerator {
    public Card generate() {
        CardSuit cardSuit = pickRandomSuit();
        CardRank cardRank = pickRandomRank();

        return new Card(cardSuit, cardRank);
    }

    private CardSuit pickRandomSuit() {
        CardSuit[] suits = CardSuit.values();
        int randomIndex = new Random().nextInt(suits.length); // 0~3 랜덤
        return suits[randomIndex];
    }

    private CardRank pickRandomRank() {
        CardRank[] ranks = CardRank.values();
        int randomIndex = new Random().nextInt(ranks.length);
        return ranks[randomIndex];
    }
}
