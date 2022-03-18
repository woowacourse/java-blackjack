package blackjack.domain.state;

import blackjack.domain.bet.Betting;
import blackjack.domain.card.Card;
import blackjack.domain.game.PlayingCards;

public class Hit implements State {

    private final PlayingCards playingCards;
    private final Betting betting;

    public Hit(final PlayingCards playingCards, final Betting betting) {
        this.playingCards = playingCards;
        this.betting = betting;
    }

    @Override
    public void bet(final String betting) {
        throw new IllegalStateException("Hit 상태일 때는 bet 을 실행할 수 없습니다.");
    }

    @Override
    public State draw(final Card card) {
        playingCards.add(card);

        if (playingCards.isBust()) {
            return new Bust(playingCards, betting);
        }
        return this;
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
        throw new IllegalStateException("Hit 상태일 때는 수익을 계산할 수 없습니다.");
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
        return playingCards.total();
    }
}
