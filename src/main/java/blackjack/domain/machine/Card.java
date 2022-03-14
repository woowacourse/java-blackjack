package blackjack.domain.machine;

import java.util.Arrays;

public enum Card {

	A_DIAMOND(0,"A다이아몬드", 1),
	TWO_DIAMOND(1,"2다이아몬드", 2),
	THREE_DIAMOND(2,"3다이아몬드", 3),
	FOUR_DIAMOND(3,"4다이아몬드", 4),
	FIVE_DIAMOND(4,"5다이아몬드", 5),
	SIX_DIAMOND(5,"6다이아몬드", 6),
	SEVEN_DIAMOND(6,"7다이아몬드", 7),
	EIGHT_DIAMOND(7,"8다이아몬드", 8),
	NINE_DIAMOND(8,"9다이아몬드", 9),
	J_DIAMOND(9,"J다이아몬드", 10),
	Q_DIAMOND(10,"Q다이아몬드", 10),
	K_DIAMOND(11,"K다이아몬드", 10),

	A_HEART(12,"A하트", 1),
	TWO_HEART(13,"2하트", 2),
	THREE_HEART(14,"3하트", 3),
	FOUR_HEART(15,"4하트", 4),
	FIVE_HEART(16,"5하트", 5),
	SIX_HEART(17,"6하트", 6),
	SEVEN_HEART(18,"7하트", 7),
	EIGHT_HEART(19,"8하트", 8),
	NINE_HEART(20,"9하트", 9),
	J_HEART(21,"J하트", 10),
	Q_HEART(22,"Q하트", 10),
	K_HEART(23,"K하트", 10),

	A_SPADE(24,"A스페이드", 1),
	TWO_SPADE(25,"2스페이드", 2),
	THREE_SPADE(26,"3스페이드", 4),
	FOUR_SPADE(27,"4스페이드", 4),
	FIVE_SPADE(28,"5스페이드", 5),
	SIX_SPADE(29,"6스페이드", 6),
	SEVEN_SPADE(30,"7스페이드", 7),
	EIGHT_SPADE(31,"8스페이드", 8),
	NINE_SPADE(32,"9스페이드", 9),
	J__SPADE(33,"J스페이드", 10),
	Q_SPADE(34,"Q스페이드", 10),
	K_SPADE(35,"K스페이드", 10),

	A_CLOVER(36,"A클로버", 1),
	TWO__CLOVER(37,"2클로버", 2),
	THREE__CLOVER(38,"3클로버", 3),
	FOUR_CLOVER(39,"4클로버", 4),
	FIVE_CLOVER(40,"5클로버", 5),
	SIX_CLOVER(41,"6클로버", 6),
	SEVEN_CLOVER(42,"7클로버", 7),
	EIGHT_CLOVER(43,"8클로버", 8),
	NINE_CLOVER(44,"9클로버", 9),
	J_CLOVER(45,"J클로버", 10),
	Q_CLOVER(46,"Q클로버", 10),
	K_CLOVER(47,"K클로버", 10);

	public static int SIZE = 48;

	private int index;
	private String name;
    private int number;

	Card(int index, String name, int number) {
		this.index = index;
		this.name = name;
		this.number = number;
	}

	public static Card of(final int index) {
		return Arrays.stream(Card.values())
			.filter(card -> card.index == index)
			.findFirst()
			.orElse(null);
	}

	public String getName() {
		return name;
	}

	public int getNumber() {
		return number;
	}
}