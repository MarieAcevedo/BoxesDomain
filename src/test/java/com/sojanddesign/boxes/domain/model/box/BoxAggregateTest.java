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
import java.util.Stack;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sodcube.domain.exception.DomainException;

/**
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
		//the default box INBOX is created for the user
		Box inbox = new Box(new BoxId("1"),"inbox of stuff",user,TypeBox.INBOX);
		List<Box> boxes = new ArrayList<Box>();
		boxes.add(inbox);
		user.setBoxes(boxes);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
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
	}
	
	@Test
	public void testFailCreateBoxBoxIdNull(){
		Box inbox = null;
		try {
			inbox = new Box(null,"inbox of stuff",user,TypeBox.INBOX);
			fail("DomainException not throw");
		} catch (DomainException e) {
			assertEquals("uniqueIdentifier of Entity must be not null", e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFailCreateIdNull(){
		Box inbox = null;
		try {
			inbox = new Box(new BoxId(null),"inbox of stuff",user,TypeBox.INBOX);
			fail("DomainException not throw");
		} catch (DomainException e) {
			assertEquals("id of BoxId must be not null", e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFailCreateIdBlank(){
		Box inbox = null;
		try {
			inbox = new Box(new BoxId(""),"inbox of stuff",user,TypeBox.INBOX);
			fail("DomainException not throw");
		} catch (DomainException e) {
			assertEquals("id of BoxId must be not null", e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCreateDefaultBox(){
		Box inbox = null;
		try {
			inbox = new Box(new BoxId("1"),"inbox of stuff",user,null);
			assertEquals(TypeBox.INBOX, inbox.getType());
			assertEquals(InboxManagementPolicy.class, inbox.getPolicy().getClass());
		} catch (DomainException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCreateInBox(){
		Box inbox = null;
		try {
			inbox = new Box(new BoxId("1"),"inbox of stuff",user,TypeBox.INBOX);
			assertEquals(TypeBox.INBOX, inbox.getType());
			assertEquals(InboxManagementPolicy.class, inbox.getPolicy().getClass());
		} catch (DomainException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * The user uses the application for the first time only the inbox there.
	 * @throws DomainException 
	 */
	@Test
	public void testCollect() throws DomainException {
		//creating the element collected
		Element elt = new Element(new ElementId("1"));
		elt.setEntitled("lire le livre de Adam Bien");
		Box inbox = user.getBoxes().get(0);
		//in gtd thetreatment of element's inbox is LIFO => Stack
		Stack<Element> eltSInbox = new Stack<Element>();
		eltSInbox.push(elt);
		elt = new Element(new ElementId("2"));
		elt.setEntitled("facture de l'hopital");
		eltSInbox.push(elt);
		elt = new Element(new ElementId("3"));
		elt.setEntitled("promotion SAV Honda");
		eltSInbox.push(elt);
		elt = new Element(new ElementId("4"));
		elt.setEntitled("Appeler pédiatre");
		eltSInbox.push(elt);
		elt = new Element(new ElementId("5"));
		elt.setEntitled("me mettre à la poubelle");
		eltSInbox.push(elt);
		elt = new Element(new ElementId("6"));
		elt.setEntitled("un jour peut être on changera de maison");
		eltSInbox.push(elt);
		elt = new Element(new ElementId("7"));
		elt.setEntitled("à classer dans les références");
		eltSInbox.push(elt);
		inbox.setElements(eltSInbox);
	}

}
