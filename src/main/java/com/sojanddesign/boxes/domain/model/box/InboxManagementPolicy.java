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

import java.util.List;
import java.util.Stack;

import com.sodcube.domain.exception.DomainException;

/**
 * InBoxManagementPolicy is policy for Inbox and have to manage Element's Stack (LIFO) 
 * @author Marie Acevedo (http://www.sojanddesign.com)
 * @version 28 Jan. 2013 - 0.1
 *
 */
class InboxManagementPolicy implements BoxManagementPolicy {

	/**
	 * Box that is managing by the current policy
	 */
	private Box managedBox;

	/**
	 * Default constructor.
	 * @param box box to manage
	 */
	public InboxManagementPolicy(Box box) {
		this.managedBox = box;
	}

	/**
	 * Pushes an Element onto the top of the stack. If stack is null, it creates one.
	 * @param elt Element as specified in {@link BoxManagementPolicy}
	 * @return true if the specified element is put in the box.
	 * @throws DomainException if element specified is null
	 * @see com.sojanddesign.boxes.domain.model.box.box.BoxManagementPolicy#putIn(com.sojanddesign.boxes.domain.model.box.box.Element)
	 */
	@Override
	public boolean putIn(Element elt) throws DomainException {
		if(elt == null){
			throw new DomainException("The element put in the box is null.");
		}
		List<Element> elements = managedBox.getElements();
		if(elements == null) {
			elements = new Stack<Element>();
		}
		return ((Stack<Element>)elements).push(elt) != null;
	}

	/**
	 * Removes the specified element at the top of the stack if it's the last in element.
	 * @param elt the specified element to pull of the box
	 * @return true if the specified element is pull of the box.
	 * @throws DomainException if the specified element is not the last in element
	 * @see com.sojanddesign.boxes.domain.model.box.box.BoxManagementPolicy#pull(com.sojanddesign.boxes.domain.model.box.box.Element)
	 */
	@Override
	public boolean pull(Element elt) throws DomainException {
		if(elt == null){
			throw new DomainException("The element pull of the box is null.");
		}
		boolean isPull = false;
		List<Element> elements = managedBox.getElements();
		if(elements != null && !elements.isEmpty()){
			 if (elt.equals(((Stack<Element>)elements).peek())){
				 isPull = ((Stack<Element>)elements).pop() != null;
			 }else{
				 throw new DomainException("The element pull of the box is not the last in element. Call 'nextElement' to get the last in.");
			 }
		}
		return isPull;
	}

	/**
	 * Get the last in element
	 * @return the last in element on the stack
	 * @see com.sojanddesign.boxes.domain.model.box.box.BoxManagementPolicy#nextElement()
	 */
	@Override
	public Element nextElement() {
		List<Element> elements = managedBox.getElements();
		Element eltFounded = null;
		if(elements != null && !elements.isEmpty()){
			eltFounded = ((Stack<Element>)elements).peek();
		}
		return eltFounded;
	}

}
