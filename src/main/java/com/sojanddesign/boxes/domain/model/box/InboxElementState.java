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

import com.sodcube.domain.exception.DomainException;

/**
 * TODO InboxElementState
 * @author Marie Acevedo (http://www.sojanddesign.com)
 * @version 29 janv. 2013 - 0.1
 */
class InboxElementState extends AbstractElementState {

	/**
	 * default constructor
	 */
	InboxElementState(Element element) {
		super(element);
	}

	/**
	 * @see com.sojanddesign.boxes.domain.model.box.ElementState#collect()
	 */
	public void collect() throws DomainException {
		if (element.getBox() == null) {
			throw new TargetBoxNotFoundException(
					"INBOX not exist or not found for current user");
		}
		element.getBox().putIn(element);
		element.setActive(false);
		element.setDone(false);
	}

	/**
	 * @see com.sojanddesign.boxes.domain.model.box.ElementState#defer()
	 */
	public void defer() {
		// TODO Auto-generated method stub

	}

	/**
	 * @see com.sojanddesign.boxes.domain.model.box.ElementState#delegate()
	 */
	public void delegate() {
		// TODO Auto-generated method stub

	}

	/**
	 * @see com.sojanddesign.boxes.domain.model.box.ElementState#doIt()
	 */
	public void doIt() {
		// TODO Auto-generated method stub

	}

	/**
	 * @see com.sojanddesign.boxes.domain.model.box.ElementState#eliminate()
	 */
	public void eliminate() throws DomainException {
		switchNotActivableElement(TypeBox.TRASH, element.getTrashState());
	}

	/**
	 * @throws DomainException 
	 * @see com.sojanddesign.boxes.domain.model.box.ElementState#incubate()
	 */
	public void incubate() throws DomainException {
		switchNotActivableElement(TypeBox.INBOX, element.getIncubatorState());
	}

	/**
	 * @see com.sojanddesign.boxes.domain.model.box.ElementState#reference()
	 */
	public void reference() {
		// TODO Auto-generated method stub

	}

	/**
	 * @see com.sojanddesign.boxes.domain.model.box.ElementState#activate(java.lang.String)
	 */
	public void activate() throws DomainException {
		// TODO Auto-generated method stub
		
	}

}
