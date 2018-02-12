package springbook.learningtest.jdk;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ProxyTest {
	
	@Test
	public void simpleProxy() {
		Hello hello = new HelloTarget();
		assertThat(hello.sayHello("Toby"), is("Hello Toby"));
		assertThat(hello.sayHi("Toby"), is("Hi Toby"));
		assertThat(hello.sayThankYou("Toby"), is("Thank You Toby"));
	}
	
	@Test
	public void uppercaseProxy() {
		Hello hello = new HelloUppercase(new HelloTarget());
		assertThat(hello.sayHello("Toby"), is("HELLO TOBY"));
		assertThat(hello.sayHi("Toby"), is("HI TOBY"));
		assertThat(hello.sayThankYou("Toby"), is("THANK YOU TOBY"));
	}
}
