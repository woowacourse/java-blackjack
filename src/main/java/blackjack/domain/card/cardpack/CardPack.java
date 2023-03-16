package blackjack.domain.card.cardpack;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardPack {

    private final List<Card> cards;

    public CardPack() {
        cards = new ArrayList<>();
        initCardsShape();
    }

    public CardPack(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    private void initCardsShape() {
        Arrays.stream(CardShape.values())
                .flatMap(cardShape -> Arrays.stream(CardNumber.values())
                        .map(cardNumber -> new Card(cardNumber, cardShape)))
                .forEach(cards::add);
    }

    public void shuffle(final ShuffleStrategy strategy) {
        strategy.shuffle(cards);
    }

    public int size() {
        return cards.size();
    }

    public Card takeOne() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("카드를 모두 소진했습니다.");
        }
        return cards.remove(cards.size() - 1);
    }
}
