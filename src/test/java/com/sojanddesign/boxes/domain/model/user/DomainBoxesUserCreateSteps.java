/**
 * Copyright 2013 Marie Acevedo (http://www.sojanddesign.com)
 * This file is part of Framework So'D Cube (http://www.sojanddesign.com/Sodcube).
 *
 * Framework So'D Cube is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Framework So'D Cube is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Framework So'D Cube.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.sojanddesign.boxes.domain.model.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Pending;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import com.sodcube.domain.exception.DomainException;


/**
 * TODO UserCreateSteps.java
 * @author Marie Acevedo (http://www.sojanddesign.com)
 * @version 30 janv. 2013 - 0.1
 */
public class DomainBoxesUserCreateSteps {
	
	private String email;
	private String userName;
	private String password;
	private User user;
	private UserRepository mockUserRepository = mock(UserRepository.class);
	private DomainException exception;

	/**
	 * 
	 */
	public DomainBoxesUserCreateSteps() {
	}
	
	@Given("personal information of the user account to be created: $email, $userName and $password")
	public void givenPersonalInfoOfUserAccountToBeCreated(String email, String userName, String password){
		this.email = email;
		this.password = password;
		this.userName = userName;
	}
	
	@Given("$email is not already registered on Boxes")
	public void isNotAlreadyRegisteredEmail(String email){
		try {
			when(mockUserRepository.findByEmail(email)).thenReturn(null);
			when(mockUserRepository.nextUserId()).thenReturn(new UserId("1"));
		} catch (DomainException e) {
			e.printStackTrace();
		}
	}
	
	@When("It calls domain boxes to create an account")
	public void callDomainBoxesToCreateAccount() {
		try {
			user = UserFactory.getInstance(mockUserRepository).createUser(email, userName, password);
		} catch (DomainException e) {
			exception = e;
			e.printStackTrace();
		}
	}

	@Then("Boxes must create a user with the account settings whose value $email, $userName and $password")
	public void createAccount(String email, String userName, String password){
		assertNull(exception);
		assertNotNull(user);
		assertNotNull(user.getAccountSettings());
		AccountSettings settings = new AccountSettings(email, userName, password);
		assertTrue(settings.isSameAs(user.getAccountSettings()));
	}
	
	@Then("the user should not be active")
	public void isNotActiveUser(){
		assertFalse(user.isActive());
	}
	
	@Then("Boxes must be transmitted a notification to request the sending mail account activation")
	@Pending
	public void notifiateRequestTheSendingMailAccountActivation(){
		
	}
	
	@Given("$email is already registered on Boxes")
//FIXME	@Composite(steps = {"Given personal information of the user account to be created: $email, $userName and $password",
//	"When It calls domain boxes to create an account"})
	public void isAlreadyRegisteredEmailCompositeStep(@Named("email") String email/*, @Named("userName") String userName, @Named("password") String password*/){
		try {
			when(mockUserRepository.findByEmail(email)).thenReturn(new User(new UserId("2")));
		} catch (DomainException e) {
			e.printStackTrace();
		}
	}

	@Then("Boxes must identifiy that $email already exists")
	public void findEmailThatAlreadyExist(String email){
	}

	@Then("Boxes must throw an $exception with $message")
	public void throwException(String exception, String message){
		assertNotNull(this.exception);
		assertEquals(exception, this.exception.getClass().getName());
		assertEquals(message, this.exception.getMessage());
	}
}
