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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.sodcube.domain.core.Entity;
import com.sodcube.domain.core.ValueObject;
import com.sodcube.domain.exception.DomainException;

/**
 * Element is a domain local entity of box's aggregates.
 * 
 * @author Marie Acevedo (http://www.sojanddesign.com)
 * @version 24 jan. 2013 - 0.1
 */
public class Element extends Entity<ElementId> {

	/**
	 * generated serial version UID
	 */
	private static final long serialVersionUID = -7685345381197024163L;

	public static final String ENTITLED_ELT_PROPERTY = "ENTITLED_ELT";
	/**
	 * flag if element became an action
	 */
	private boolean active;
	
	/**
	 * day of element's collect
	 */
	private final Date creationDate;
	/**
	 * flag if action of element is done (finished)
	 */
	private boolean done;
	/**
	 * description of element
	 */
	private String entitled;
	/**
	 * Set of property participating to description
	 */
	private Map<String, ValueObject> properties;
	
	/**
	 * element state when it is placed in the "inbox".
	 */
	private ElementState inboxState;
	/**
	 * element state when it is placed in the "trash".
	 */
	private ElementState trashState;
	/**
	 * element state when it is placed in the "incubator".
	 */
	private ElementState incubatorState;
	/**
	 * current state, default it is {@link InboxElementState}
	 */
	private ElementState state;
	
	/**
	 * current {@link Box} containing this element
	 */
	private Box box;

	/**
	 * Default constructor with uniqueIdentifier. the creation date takes the
	 * definitive value of today.
	 * 
	 * @param uniqueIdentifier
	 *            {@link ElementId}
	 * @throws DomainException 
	 */
	public Element(ElementId uniqueIdentifier, Box box) throws DomainException {
		super(uniqueIdentifier);
		this.creationDate = new Date();// today
		this.active = false;// by default is not active
		this.inboxState = new InboxElementState(this);
		this.trashState = new TrashElementState(this);
		this.incubatorState = new IncubatorElementState(this);
		this.state = inboxState;
		this.box = box;
	}

	public void activate(String entitledOfAction) throws DomainException {
		//save old entitled in properties
		if(properties == null){
			properties = new HashMap<String, ValueObject>();
		}
		properties.put(ENTITLED_ELT_PROPERTY, new Property(entitled));
		entitled = entitledOfAction;
		active = true;
		state.activate();
	}

	public void collect() throws DomainException {
		state.collect();
	}

	public void defer() throws DomainException {
		state.defer();
	}

	public void delegate() throws DomainException {
		state.delegate();
	}

	public void doIt() throws Exception {
		state.doIt();
	}

	public void eleminate() throws DomainException {
		state.eliminate();
	}

	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @return the entitled
	 */
	public String getEntitled() {
		return entitled;
	}

	/**
	 * @return the properties
	 */
	public Map<String, ValueObject> getProperties() {
		return properties;
	}

	public void incubate() throws DomainException {
		state.incubate();
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @return the done
	 */
	public boolean isDone() {
		return done;
	}

	public void reference() throws DomainException {
		state.reference();
	}

	/**
	 * @param active
	 *            the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * @param done
	 *            the done to set
	 */
	public void setDone(boolean done) {
		this.done = done;
	}

	/**
	 * @param entitled
	 *            the entitled to set
	 */
	public void setEntitled(String entitled) {
		this.entitled = entitled;
	}

	/**
	 * @param properties
	 *            the properties to set
	 */
	public void setProperties(Map<String, ValueObject> properties) {
		this.properties = properties;
	}

	/**
	 * @param state the state to set
	 */
	final void setState(ElementState state) {
		this.state = state;
	}

	/**
	 * @return the box
	 */
	public final Box getBox() {
		return box;
	}

	/**
	 * @param box the box to set
	 */
	public final void setBox(Box box) {
		this.box = box;
	}

	/**
	 * @return the inboxState
	 */
	final ElementState getInboxState() {
		return inboxState;
	}

	/**
	 * @return the trashState
	 */
	final ElementState getTrashState() {
		return trashState;
	}

	/**
	 * @return the incubatorState
	 */
	final ElementState getIncubatorState() {
		return incubatorState;
	}
}