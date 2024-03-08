package blackjack.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class UnmodifiableTest {

	@Test
	void unmodi1() {
		TestClass a = new TestClass(new Name("a"));
		TestClass b = new TestClass(new Name("b"));
		TestClass c = new TestClass(new Name("c"));
		List<TestClass> strings = Collections.unmodifiableList(Arrays.asList(a, b, c));

		a.changeName("A");

		Assertions.assertThat(strings.get(0).name.value).isEqualTo("A");
	}

	@Test
	void unmodi2() {
		TestClass a = new TestClass(new Name("a"));
		TestClass b = new TestClass(new Name("b"));
		TestClass c = new TestClass(new Name("c"));
		List<TestClass> strings = Collections.unmodifiableList(new ArrayList<>(Arrays.asList(a, b, c)));

		a.changeName("A");
		Assertions.assertThat(strings.get(0).name.value).isEqualTo("A");
	}

	private class TestClass {
		private Name name;

		private TestClass(Name name) {
			this.name = name;
		}

		public String getName() {
			return name.value;
		}

		public void changeName(String newName) {
			name.setValue(newName);
		}
	}

	private class Name {
		private String value;

		public Name(String value) {
			this.value = value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}
}
