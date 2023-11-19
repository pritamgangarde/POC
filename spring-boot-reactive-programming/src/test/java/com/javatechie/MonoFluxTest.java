package com.javatechie;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

class MonoFluxTest {

	@Test
	void testMono() {
		Mono<String> monoString = Mono.just("This is my first reactive web program").log();
		monoString.subscribe(System.out::println);
	}

	@Test
	void testOnException() {
		Mono<Object> monoString = Mono.just("This is my first reactive web program")
				.then(Mono.error(new RuntimeException("Are yedya ase coding kartat ka"))).log();
		monoString.subscribe(System.out::println);
	}

	@Test
	void testFlux() {
		Flux<String> fluxString = Flux.just("Spring", "Spring Boot", "Microservices", "Hibernate")
				.concatWithValues("AWS").concatWith(Flux.error(new RuntimeException("Are yedya ase coding kartat ka")))
				.log();
		fluxString.subscribe(System.out::println);
	}
}
