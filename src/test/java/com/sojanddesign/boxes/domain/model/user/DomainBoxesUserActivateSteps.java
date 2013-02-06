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

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.joda.time.Duration;
import org.joda.time.Instant;

import com.sodcube.domain.exception.DomainException;
import com.sojanddesign.boxes.domain.model.box.Box;


/**
 * TODO DomainBoxesUserActivateSteps.java
 * @author Marie Acevedo (http://www.sojanddesign.com)
 * @version 01 Fev. 2013 - 0.1
 */
public class DomainBoxesUserActivateSteps {
	
	private String email;
	private User user;
	private UserRepository mockUserRepository = mock(UserRepository.class);
	private DomainException exception;

	/**
	 * 
	 */
	public DomainBoxesUserActivateSteps() {
	}
	
	/*Scenario:  account activation on Boxes*/
	
	@Given("Inactive user account created previously with $email")
	public void givenEmailOfInactiveUserAccountCreatedPreviously(String email){
		this.email = email;
		try {
			User mockUser = new User(new UserId("3"));
			mockUser.setActive(false);
			mockUser.setAccountSettings(new AccountSettings(email, null, null));
			when(mockUserRepository.findByEmail(email)).thenReturn(mockUser);
		} catch (DomainException e) {
			e.printStackTrace();
		}
	}
	
	@When("It calls domain boxes to activate his account")
	public void callDomainBoxesToActivateAccount() {
		user = mockUserRepository.findByEmail(email);
		try {
			user.activate();
			mockUserRepository.store(user);
		} catch (ExpirationActivationUserException e) {
			exception = e;
			e.printStackTrace();
		} catch (DomainException e) {
			exception = e;
			e.printStackTrace();
		}
	}

	@Then("Boxes must activate the user whose email address is $email")
	public void activateAccount(String email){
		assertNull(exception);
		assertNotNull(user);
		assertNotNull(user.getAccountSettings());
		assertEquals(email, user.getAccountSettings().getEmail());
	}
	
	@Then("the user should be active")
	public void isActiveUser(){
		assertTrue(user.isActive());
	}
	
	@Then("the user must be initialized with defaults boxes labeled $inbox, $trash, $next_actions, $less_than_two_minutes_actions and $incubator")
	public void intializeUserWithDefaultBoxes(String inbox,String trash,String next_actions,String less_than_two_minutes_actions,String incubator){
		assertNotNull(user.getBoxes());
		for (Box box : user.getBoxes()) {
			assertNotNull(box);
			switch (box.getType()) {
			case INBOX:
				assertEquals(inbox, box.getName());
				break;
			case TRASH:
				assertEquals(trash, box.getName());
				break;
			case NEXT_ACTIONS:
				assertEquals(next_actions, box.getName());
				break;
			case LESS_THAN_TWO_MINUTES_ACTIONS:
				assertEquals(less_than_two_minutes_actions, box.getName());
				break;
			case INCUBATOR:
				assertEquals(incubator, box.getName());
				break;

			default:
				fail("All box of the user must have a type.");
				break;
			}
			Logger.getLogger("com.sojanddesign.boxes.domain.model.user.DomainBoxesUserActivateSteps").log(Level.INFO, "BoxId for " + box.getType() + " is " + (box.getUniqueIdentifier()).getId());

		}
	}
	
	/* expiration of activation account */
	@Given("$email of inactive user account created previously 4 days ago")
	public void givenEmailOfInactiveUserAccountCreatedPreviouslyFoorDaysAgo(String email){
		this.email = email;
		try {
			User mockUser = new User(new UserId("4"));
			mockUser.setActive(false);
			Duration foorDays = Duration.standardDays(4);
			Instant fourDaysAgo = Instant.now().minus(foorDays);
			mockUser.setAccountSettings(new AccountSettings(email, null, null,fourDaysAgo.toDate()));
			when(mockUserRepository.findByEmail(email)).thenReturn(mockUser);
		} catch (DomainException e) {
			exception = e;
			e.printStackTrace();
		}
	}
	
	@Then("Boxes must identify the activation confirmation has expired")
	public void identifyActivationConfirmationHasExpired(){
	}

	@Then("Boxes must cancel the activation launching an $exception with $message")
	public void throwException(String exception, String message){
		assertNotNull(this.exception);
		assertEquals(exception, this.exception.getClass().getName());
		assertEquals(message, this.exception.getMessage());
	}
}
