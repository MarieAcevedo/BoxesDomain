/**
 * Copyright 2013 Marie Acevedo (http://www.sojanddesign.com)
 * This file is part of Boxes Domain (http://www.sojanddesign.com/Boxes).
 *
 * Boxes Domain is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Boxes Domain is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Boxes Domain.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.sojanddesign.boxes.domain.model.box;

import java.util.List;
import java.util.Map;

import com.sodcube.domain.core.Entity;
import com.sodcube.domain.core.ValueObject;
import com.sodcube.domain.exception.DomainException;

/**
 * Box is a domain root entity of box's aggregates. A box contains a list of
 * {@link Element}. Deleting a box involves the removal of elements it contains
 * less than moving forward in another box. A box is the unique property of a
 * {@link User} of the system, it can have all of the boxes. When I realized I
 * step collection includes pending cases (all that goes through my head or
 * behind in my workspace or personal life) in a single entry box (INBOX). When
 * I realized the processing step, I empty my inbox Element (ELEMENT) per
 * element, moving to a new box depending on what I decide to do. Possible other
 * boxes that I could identify are primarily for non-activated (for which there
 * is nothing to do):
 * <ul>
 * <li>the trash (TRASH): For items that are cast as useless.
 * <li>the incubator (INCUBATOR) for elements to review later because I can not
 * do anything with it for now and I can deal with one day.
 * <li>reference (REFERENCE) for the elements which serve as reference for other
 * elements.
 * <li>schedule (shedule) or calendar (CALENDAR): refers to elements again later
 * because I can not do anything with it yet but I know that I will treat them
 * to a point
 * </ul>
 * Then for capital items (for which there is something to do) boxes are:
 * <ul>
 * <li>box actions taken in less than 2 minutes (LESS THAN TWO MINUTES SHARES)
 * for the elements to which the spring action that is achievable immediately
 * without exceeding 2 minutes of our time.
 * <li>box of the first actions (NEXT ACTIONS) for the elements to which the
 * spring action that should be performed at the earliest. It is reported ASAP.
 * <li>box projects (PROJECTS) For items for which a single action is not
 * sufficient for its realization. Particular case treated in the future.
 * <li>box shares outstanding (WAITING FOR SHARES) for the elements to which the
 * spring action that must be performed by another person, it is delegated.
 * <li>the calendar (CALENDAR) for the elements to which the spring action that
 * must be performed on a given date. It is postponed.
 * </ul>
 * Pending cases can not be collected if there is no input box to contain
 * <br/>
 * Each box depending on its type can have its own behavior and a box can be a
 * trash can, an inbox of mail or even a calendar. The <b>STRATEGY</b> design
 * pattern is used to allow the multiplication of behavior.
 * <br/>
 * @author Marie Acevedo (http://www.sojanddesign.com)
 * @version 24 jan. 2013 - 0.1
 */
public class Box extends Entity<BoxId> {

	/**
	 * generated serial number ID
	 */
	private static final long serialVersionUID = 4715735734077964168L;
	/**
	 * All elements contained in the box
	 */
	private List<Element> elements;
	/**
	 * descriptive name for the box. for example "mail"
	 */
	private final String name;
	/**
	 * owner of the box
	 */
	private final User owner;
	/**
	 * Management strategy of this box. Depends on the type of box. 
	 */
	private final BoxManagementPolicy policy;
	/**
	 * descriptive properties for the complex box
	 */
	private Map<String, ValueObject> properties;
	/**
	 * type of the box
	 */
	private final TypeBox type;

	/**
	 * DEfault constructor. Default is a box type INBOX, and is involved in the
	 * collection of outstanding business.
	 * 
	 * @param uniqueIdentifier
	 *            {@link BoxId}
	 * @param name
	 *            descriptive name of the box, must be not null.
	 * @param owner
	 *            {@link User}, must be not null.
	 * @param type
	 *            {@link TypeBox}, if null it becomes INBOX
	 * @throws DomainException
	 *             if build entity is unsuccessful
	 */
	public Box(final BoxId uniqueIdentifier, final String name,
			final User owner, final TypeBox type) throws DomainException {
		super(uniqueIdentifier);
		// check parameters if null throws DomainException
		if ((name == null || "".equals(name)) || owner == null) {
			throw new DomainException(
					"name or owner of the box must be not null or blank");
		}
		this.name = name;
		this.owner = owner;
		// if type is null it becomes INBOX
		if (type == null) {
			this.type = TypeBox.INBOX;
		} else {
			this.type = type;
		}
		this.policy = BoxManagementFactory.createBoxManagementPolicy(this);
	}

	/**
	 * @param elts
	 */
	public void fill(List<Element> elts) {
		// begin-user-code
		// TODO Module de remplacement de méthode auto-généré

		// end-user-code
	}
	
	/**
	 * @return the elements
	 */
	public List<Element> getElements() {
		return elements;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the owner
	 */
	public User getOwner() {
		return owner;
	}

	/**
	 * @return the policy
	 */
	public final BoxManagementPolicy getPolicy() {
		return policy;
	}

	/**
	 * @return the properties
	 */
	public Map<String, ValueObject> getProperties() {
		return properties;
	}

	/**
	 * @return the type
	 */
	public TypeBox getType() {
		return type;
	}

	/**
	 * Vacuum box of all the elements it contains
	 */
	public void pullAll() {
		if (elements != null) {
			elements.clear();
		}
	}

	/**
	 * Put the specified element in the box.
	 * Delegate to {@link BoxManagementPolicy#putIn(Element)}
	 * @param elt element to be put in the box
	 * @return true if this box changed as a result of the call
	 * @throws DomainException if the specified element is null.
	 */
	public boolean putIn(Element elt) throws DomainException{
		return policy.putIn(elt);
	}
	
	/**
	 * Pull the specified element of the box.
	 * @param elt element to be pull of the box. 
	 * @return true if this box changed as a result of the call.
	 * @throws DomainException if the specified element is not valid.
	 */
	public boolean pull(Element elt) throws DomainException{
		return policy.pull(elt);
	}

	/**
	 * @return the next element's box 
	 */
	public Element nextElement() {
		return policy.nextElement();
	}
	
	/**
	 * @param elements
	 *            the elements to set
	 */
	public void setElements(List<Element> elements) {
		this.elements = elements;
	}

	/**
	 * @param properties
	 *            the properties to set
	 */
	public void setProperties(Map<String, ValueObject> properties) {
		this.properties = properties;
	}

}
