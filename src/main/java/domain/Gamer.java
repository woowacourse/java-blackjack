package domain;

/**
 *    게임 진행자 인터페이스입니다.
 *
 *    @author AnHyungJu, ParkDooWon
 */
public interface Gamer {
	boolean isBust();

	void add(Card card);

	int scoreHands();
}
