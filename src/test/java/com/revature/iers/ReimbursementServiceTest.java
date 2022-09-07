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
//    public void test_isNumeric_givenNumeric() {
//        double amount = 150.00;
//
//
//    }

//    @Test(expected = InvalidRequestException.class)
//    public void test_isNotValidUsername_givenInCorrectUsername() {
//
//    }
//
//    @Test(expected = InvalidRequestException.class)
//    public void test_isNotValidUsername_givenEmptyUsername() {
//
//    }
//
//    @Test(expected = InvalidRequestException.class)
//    public void test_isNotValidUsername_givenOnlyNumbers() {
//
//    }
//
//    @Test(expected = InvalidRequestException.class)
//    public void test_isNotValidUsername_startingWithUnderscore() {
//
//    }
}
