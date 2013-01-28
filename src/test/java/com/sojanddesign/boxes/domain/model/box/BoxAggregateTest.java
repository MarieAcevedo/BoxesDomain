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
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sodcube.domain.exception.DomainException;
import com.sojanddesign.boxes.domain.model.user.User;
import com.sojanddesign.boxes.domain.model.user.UserId;

/**
 * Class test of Box aggregate (Box-Element-Project)
 * @author Marie Acevedo (http://www.sojanddesign.com)
 * 
 */
public class BoxAggregateTest {

	private static User user;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		user = new User(new UserId("1"));
		user.setName("Jone Doe");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	private void collect(Box inbox) throws DomainException {
		// creating the element collected
		Element elt = new Element(new ElementId("1"));
		elt.setEntitled("lire le livre de Adam Bien");
		inbox.putIn(elt);
		// in gtd thetreatment of element's inbox is LIFO => Stack
		elt = new Element(new ElementId("2"));
		elt.setEntitled("facture de l'hopital");
		inbox.putIn(elt);
		elt = new Element(new ElementId("3"));
		elt.setEntitled("promotion SAV Honda");
		inbox.putIn(elt);
		elt = new Element(new ElementId("4"));
		elt.setEntitled("Appeler pédiatre");
		inbox.putIn(elt);
		elt = new Element(new ElementId("5"));
		elt.setEntitled("me mettre à la poubelle");
		inbox.putIn(elt);
		elt = new Element(new ElementId("6"));
		elt.setEntitled("un jour peut être on changera de maison");
		inbox.putIn(elt);
		elt = new Element(new ElementId("7"));
		elt.setEntitled("à classer dans les références");
		inbox.putIn(elt);
	}

	/**
	 * @return
	 * @throws DomainException
	 */
	private Box createInbox() throws DomainException {
		// the default box INBOX is created for the user
		Box inbox = new Box(new BoxId("1"), "inbox of stuff", user,
				TypeBox.INBOX);
		List<Box> boxes = new ArrayList<Box>();
		boxes.add(inbox);
		user.setBoxes(boxes);
		return inbox;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		List<Box> boxes = user.getBoxes();
		if (boxes != null) {
			for (Box box : boxes) {
				box.pullAll();
			}
			user.setBoxes(null);
		}
	}

	/**
	 * The user uses the application for the first time only the inbox there.
	 * 
	 * @throws DomainException
	 */
	@Test
	public void testCollect() throws DomainException {
		Box inbox = createInbox();
		// creating the element collected
		collect(inbox);
		assertEquals(7, inbox.getElements().size());
	}

	@Test
	public void testCreateDefaultBox() {
		Box inbox = null;
		try {
			inbox = new Box(new BoxId("1"), "inbox of stuff", user, null);
			assertEquals(TypeBox.INBOX, inbox.getType());
			assertEquals(InboxManagementPolicy.class, inbox.getPolicy()
					.getClass());
		} catch (DomainException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

	@Test
	public void testCreateInBox() {
		Box inbox = null;
		try {
			inbox = new Box(new BoxId("1"), "inbox of stuff", user,
					TypeBox.INBOX);
			assertEquals(TypeBox.INBOX, inbox.getType());
			assertEquals(InboxManagementPolicy.class, inbox.getPolicy()
					.getClass());
		} catch (DomainException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

	@Test
	public void testCreateTrashBox() {
		Box trash = null;
		try {
			trash = new Box(new BoxId("2"), "trash", user, TypeBox.TRASH);
			assertEquals(TypeBox.TRASH, trash.getType());
			assertEquals(TrashManagementPolicy.class, trash.getPolicy()
					.getClass());
		} catch (DomainException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * The user uses the application for the first time only the inbox there.
	 * 
	 * @throws DomainException
	 */
	@Test
	public void testFailCollectCausedByElementNull() {
		try {
			Box inbox = createInbox();
			inbox.putIn(null);
			fail("a DomainException is expected.");
		} catch (DomainException e) {
			assertEquals("The element put in the box is null.", e.getMessage());
			e.printStackTrace();
		}

	}

	@Test
	public void testFailCreateBCausedByBoxIdNull() {
		try {
			Box inbox = new Box(null, "inbox of stuff", user, TypeBox.INBOX);
			fail("DomainException not throw");
		} catch (DomainException e) {
			assertEquals("uniqueIdentifier of Entity must be not null",
					e.getMessage());
			e.printStackTrace();
		}
	}

	@Test
	public void testFailCreateCausedByIdBlank() {
		try {
			Box inbox = new Box(new BoxId(""), "inbox of stuff", user,
					TypeBox.INBOX);
			fail("DomainException not throw");
		} catch (DomainException e) {
			assertEquals("id of BoxId must be not null", e.getMessage());
			e.printStackTrace();
		}
	}

	@Test
	public void testFailCreateCausedByIdNull() {
		try {
			Box inbox = new Box(new BoxId(null), "inbox of stuff", user,
					TypeBox.INBOX);
			fail("DomainException not throw");
		} catch (DomainException e) {
			assertEquals("id of BoxId must be not null", e.getMessage());
			e.printStackTrace();
		}
	}

	@Test
	public void testFailPullInboxCausedByNotEltLastIn() {
		try {
			Box inbox = createInbox();
			// creating the element collected
			collect(inbox);
			inbox.pull(inbox.findElementById(new ElementId("2")));
			fail("a NotExpectedElementException is expected.");
		} catch (NotExpectedElementException e) {
			assertEquals(
					"The element pull of the box is not the last in element. Call 'nextElement' to get the last in.",
					e.getMessage());
			e.printStackTrace();
		} catch (DomainException e) {
			fail("a NotExpectedElementException is expected.");
			e.printStackTrace();
		}
	}

	@Test
	public void testPullInBox() throws DomainException {
		Box inbox = createInbox();
		// creating the element collected
		collect(inbox);
		Element elt = inbox.nextElement();
		assertEquals("7", elt.getUniqueIdentifier().getId());
		inbox.pull(elt);
		assertEquals(6, inbox.getElements().size());
	}

}
