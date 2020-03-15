package com.blackjack.exception;

public class EmptyCardDeckException extends RuntimeException {
	public EmptyCardDeckException() {
		super("카드가 남아있지 않아 더 이상 뽑을 수 없습니다.");
	}
}
