package domain;

import infra.CardShuffler;

public class CardShufflerImpl implements CardShuffler {
    // TODO: 섞는 책임이 Deck으로 가는게 자연스럽지 않은가 vs 통제가 어려운 로직이 도메인에 섞여 테스트 작성이 어려워도 괜찮은가 
    @Override
    public int shuffleCardDeck(final int deckSize) {
        return (int) (Math.random() * deckSize);
    }
}
