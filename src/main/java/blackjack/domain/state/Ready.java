package blackjack.domain.state;

import blackjack.domain.bet.Betting;
import blackjack.domain.card.Card;
import blackjack.domain.game.PlayingCards;

public class Ready implements State {

    private final PlayingCards playingCards = new PlayingCards();
    private Betting betting;

    private void validateNumber(final String string) {
        if (!string.matches("-?[0-9]+")) {
            throw new IllegalArgumentException("숫자를 입력해주세요.");
        }
    }

    @Override
    public void bet(final String betting) {
        validateNumber(betting);
        this.betting = new Betting(betting);
    }

    @Override
    public State draw(final Card card) {
        playingCards.add(card);

        if (playingCards.isMoreDeal()) {
            return this;
        }
        if (playingCards.isBlackjack()) {
            return new Blackjack(playingCards, betting);
        }
        return new Hit(playingCards, betting);
    }

    @Override
    public State stay() {
        return new Stay(playingCards, betting);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public double profit() {
        throw new IllegalStateException("Ready 상태일 때는 수익을 계산할 수 없습니다.");
    }

    @Override
    public PlayingCards playingCards() {
        return playingCards;
    }

    @Override
    public PlayingCards partOfPlayingCards() {
        PlayingCards playingCards = new PlayingCards();
        playingCards.add(this.playingCards.getPartOfCard());

        return playingCards;
    }

    @Override
    public int cardTotal() {
        if (playingCards.isEmpty()) {
            return 0;
        }
        return playingCards.total();
    }
}
