package com.blackjack.domain.user;

import java.util.Objects;

public class Name {
	private static final String BLANK_STRING = " ";
	private static final int MAX_NAME_LENGTH = 10;

	private final String name;

	public Name(String name) {
		validateNullOrEmpty(name);
		validateBlank(name);
		validateLength(name);
		this.name = name;
	}

	private void validateLength(String name) {
		if (name.length() >= MAX_NAME_LENGTH) {
			throw new IllegalArgumentException("이름은 길이가 올바르지 않습니다.");
		}
	}

	private void validateBlank(String name) {
		if (name.contains(BLANK_STRING)) {
			throw new IllegalArgumentException("이름에 공백이 포함될 수 없습니다.");
		}
	}

	private void validateNullOrEmpty(String name) {
		if (Objects.isNull(name) || name.isEmpty()) {
			throw new IllegalArgumentException("이름은 null이거나 빈 문자열이 될 수 없습니다.");
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Name name1 = (Name)o;
		return Objects.equals(name, name1.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public String toString() {
		return name;
	}
}
