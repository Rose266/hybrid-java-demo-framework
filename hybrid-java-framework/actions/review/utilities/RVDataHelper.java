package review.utilities;

import java.util.Locale;

import com.github.javafaker.Faker;

public class RVDataHelper {
	private Locale local = new Locale("en");
	private Faker faker = new Faker(local);

	public static RVDataHelper getRVDataHelper() {
		return new RVDataHelper();
	}

	public String getFirstName() {
		return faker.address().firstName();
	}

	public String getLastName() {
		return faker.address().lastName();
	}

	public String getEmail() {
		return faker.internet().emailAddress();
	}

	public String getPassword() {
		return faker.internet().password();
	}

	public String getPhoneNumber() {
		return faker.phoneNumber().cellPhone();
	}

	public String getAddress() {
		return faker.address().fullAddress();
	}

	public String getCompany() {
		return faker.company().name();
	}

}
