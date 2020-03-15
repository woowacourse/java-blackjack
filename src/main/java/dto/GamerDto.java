package dto;

import domain.card.Hands;

/**
 *   class description
 *
 *   @author ParkDooWon
 */
public interface GamerDto {
	String getName();

	Hands getHands();

	int getTotalScore();
}
