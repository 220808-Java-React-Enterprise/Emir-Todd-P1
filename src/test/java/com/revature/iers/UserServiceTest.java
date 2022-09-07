package com.revature.iers;

import com.revature.iers.daos.UserDAO;
import com.revature.iers.dtos.requests.LoginRequest;
import com.revature.iers.dtos.responses.Principal;
import com.revature.iers.models.User;
import com.revature.iers.services.UserService;
import com.revature.iers.utils.custom_exceptions.InvalidRequestException;
import com.revature.iers.utils.custom_exceptions.InvalidUserException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class UserServiceTest {

    /* dependency injection */
    private UserService sut; // sut = system under test
    private final UserDAO mockUserDao = mock(UserDAO.class);

    /* jank constructor */
    @Before
    public void setup() {
        sut = new UserService(mockUserDao);
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
    public void test_isValidUsername_givenCorrectUsername() {
        // Arrange
        String validUsername = "toddles6";

        // Act
        boolean flag = sut.isValidUsername(validUsername);

        // Assert
        Assert.assertTrue(flag);
    }

    @Test(expected = InvalidRequestException.class)
    public void test_isNotValidUsername_givenInCorrectUsername() {
        // Arrange
        String invalidUsername = "toddle";

        // Act
        sut.isValidUsername(invalidUsername);
    }

    @Test(expected = InvalidRequestException.class)
    public void test_isNotValidUsername_givenEmptyUsername() {
        // Arrange
        String invalidUsername = "";

        // Act
        sut.isValidUsername(invalidUsername);
    }

    @Test(expected = InvalidRequestException.class)
    public void test_isNotValidUsername_givenOnlyNumbers() {
        // Arrange
        String invalidUsername = "123456";

        // Act
        sut.isValidUsername(invalidUsername);
    }

    @Test(expected = InvalidRequestException.class)
    public void test_isNotValidUsername_startingWithUnderscore() {
        // Arrange
        String invalidUsername = "_toddles6";

        // Act
        sut.isValidUsername(invalidUsername);
    }

    //@Test
//    public void test_login_validLoginGivenCorrectCredentials() {
//        // Arrange
//        UserService spiedSut = Mockito.spy(sut);
//        LoginRequest request = new LoginRequest();
//
//        String validUsername = "toddles6";
//        String validPassword = "Passw0rd";
//        Boolean isActive = true;
//
//        when(spiedSut.isActiveUser(isActive)).thenReturn(true);
//        when(spiedSut.isValidUsername(validUsername)).thenReturn(true);
//        when(spiedSut.isValidPassword(validPassword)).thenReturn(true);
//        when(mockUserDao.getUserByUsernameAndPassword(validUsername, validPassword)).thenReturn(new User());
//
//
//        // Act
//        Principal user = spiedSut.login(request);
//
//        // Assert
//        Assert.assertNotNull(user);
//        verify(mockUserDao, times(1)).getUserByUsernameAndPassword(validUsername, validPassword);
//    }

//    @Test(expected = InvalidRequestException.class)
//    public void test_login_invalidLoginGivenIncorrectCredentials() {
//        // Arrange
//        UserService spiedSut = Mockito.spy(sut);
//        LoginRequest request = new LoginRequest();
//
//        request.setUsername("invalid");
//        request.setPassword("invalid");
//        when(mockUserDao.getUserByUsernameAndPassword(request.getUsername(), request.getPassword())).thenReturn(null);
//
//        // Act
//        sut.login(request);
//    }
}
