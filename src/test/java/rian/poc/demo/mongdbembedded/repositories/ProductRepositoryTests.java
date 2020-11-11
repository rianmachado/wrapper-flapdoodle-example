package rian.poc.demo.mongdbembedded.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.security.SecureRandom;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import rian.poc.demo.mongdbembedded.domain.Product;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
public class ProductRepositoryTests {
	
	@Autowired
	private ProductRepository productRepository;

	private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
	private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
	private static final String NUMBER = "0123456789";
	private static final String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;
	private static SecureRandom random = new SecureRandom();
	private static Product product;

	@BeforeClass
	public static void init() {
		String valueRandon = generateRandomString(8);
		product = Product.builder().description("Des-" + valueRandon)
				.imageUrl("http://rianmachado.com.br/" + valueRandon).price(new BigDecimal(12.3)).build();
	}

	@Test
	public void aSaveProductTest() {
		productRepository.save(product);
		assertNotNull(product.get_id());
	}

	@Test
	public void bSearchProductTest() {
		Product p = productRepository.findBydescription(product.getDescription());
		assertEquals(p, product);
	}

	public static String generateRandomString(int length) {
		if (length < 1)
			throw new IllegalArgumentException();
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
			char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);
			sb.append(rndChar);
		}
		return sb.toString();
	}
}
