package com.revature.iers;

import com.revature.iers.daos.ReimbursementDAO;
import com.revature.iers.services.ReimbursementService;

import com.revature.iers.utils.custom_exceptions.InvalidRequestException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class ReimbursementServiceTest {
    private ReimbursementService sut; // sut = system under test
    private final ReimbursementDAO mockReimbursementDao = mock(ReimbursementDAO.class);

    /* jank constructor */
    @Before
    public void setup() {
        sut = new ReimbursementService(mockReimbursementDao);
    }

    /*
        Common JUnit annotations:
            - @Test (marks a method as a test case)
            - @Ignore (tells JUnit to skip this test case)
            - @Before (logic that runs once before every test case)
            - @After (logic that runs once after every test case)
            - @BeforeClass (logic that runs only once before all test cases)
            - @AfterClass (logic that runs only once after all test cases)
     */
    @Test
    public void test_isNumeric_givenNumeric() {
        //arrange
        double amount = 150.00;

        //act
        sut.isNumeric(amount);
    }

    @Test(expected = InvalidRequestException.class)
    public void test_isNotNumeric() {
        //arrange
        double amount = 123.0912;

        //act
        sut.isNumeric(amount);

    }

    @Test
    public void test_isValidStatus() {
        //arrange
        String status = "456";

        //act
        sut.isValidStatus(status);
    }
    @Test(expected = InvalidRequestException.class)
    public void test_isNotValidStatus() {
        //arrange
        String status = "234";

        //act
        sut.isValidStatus(status);
    }
    @Test
    public void test_isValidType() {
        //arrange
        String type = "000";

        //act
        sut.isValidType(type);
    }
    @Test(expected = InvalidRequestException.class)
    public void test_isNotValidType() {
        //arrange
        String type = "1";

        //act
        sut.isValidType(type);
    }
    @Test
    public void test_isNotTooManyCharacters() {
        //arrange
        String amount = "Hello World";

        //act
        sut.isTooManyCharacters(amount);
    }

    @Test(expected = InvalidRequestException.class)
    public void test_isTooManyCharacters() {
        //arrange
        String amount = "djfnbviwebviwebrvibewrvlkwejbvkljwenvlkjwnelkvjnervwebvoiwebviwuebvioebvoibeoiurvbweivbiuewbviuebviue";

        //act
        sut.isTooManyCharacters(amount);
    }
}
