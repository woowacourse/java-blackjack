package domain.card;

// TODO: Q. infra 계층이 꼭 필요했나? -> 외부 세계와 연결되는 계층 -> shuffle이 외부 세계는 아니긴 해
public interface CardShuffler {
    int shuffleCardDeck(int deckSize);
}
