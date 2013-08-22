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
package com.sojanddesign.boxes.domain.model.box;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Pending;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import com.sodcube.domain.exception.DomainException;
import com.sojanddesign.boxes.domain.model.user.AccountSettings;
import com.sojanddesign.boxes.domain.model.user.User;
import com.sojanddesign.boxes.domain.model.user.UserId;

/**
 * TODO DomainBoxesCollectSteps
 * @author Marie Acevedo (http://www.sojanddesign.com)
 * @version 27 juin 2013 - 0.1
 */
public class DomainBoxesCollectSteps {

	private User user;
	private String entitledReadyElt;
	private Element collectedElt;
	/**
	 * 
	 */
	public DomainBoxesCollectSteps() {
	}
	
	@Given("the active user login to boxes with $email")
	public void givenActiveUserAccount(String email){
		try {
			user = new User(new UserId("3"));
			user.setAccountSettings(new AccountSettings(email, null, null));
			user.activate();
		} catch (DomainException e) {
			e.printStackTrace();
		}
	}
	
	@Given("an element ready to be collected entitled : $entitled")
	public void anElementReadyToBeCollected(String entitled){
		entitledReadyElt = entitled;
	}
	
	@When("It calls domain boxes to put in the INBOX the given element")
	public void callDomainBoxesToPutINBOX() {
		try {
			collectedElt = new Element(new ElementId("1"), user.getBoxes().get(0));
			collectedElt.setEntitled(entitledReadyElt);
			collectedElt.collect();
		} catch (DomainException e) {
			e.printStackTrace();
		}
	}
	
	@Then("Boxes must create an element entitled $entitled")
	public void createElement(String entitled){
		assertNotNull(user);
		final Box inbox = user.findBoxByType(TypeBox.INBOX);
		assertNotNull(inbox);
		assertEquals(1, inbox.getElements().size());
		Element eltInboxFound;
		try {
			eltInboxFound = inbox.findElementById(new ElementId("1"));
			assertTrue(eltInboxFound.isSameAs(collectedElt));
		} catch (DomainException e) {
			e.printStackTrace();
		}
	}
	
	@Then("the element must be contained in box with type INBOX")
	@Pending
	public void isContainedInboxType(){
	}
	
	@Then("the element must not be activate and done")
	public void isNotActivateAndDone(){
		assertFalse(collectedElt.isActive());
		assertFalse(collectedElt.isDone());
	}
}
