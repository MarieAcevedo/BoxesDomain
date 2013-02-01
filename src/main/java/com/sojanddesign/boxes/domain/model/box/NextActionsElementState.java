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
 * TODO NextActionsElementState
 * @author Marie Acevedo (http://www.sojanddesign.com)
 * @version 29 janv. 2013 - 0.1
 */
public class NextActionsElementState extends AbstractElementState {

	/**
	 * @param element
	 */
	public NextActionsElementState(Element element) {
		super(element);
	}

	/**
	 * @see com.sojanddesign.boxes.domain.model.box.ElementState#activate()
	 */
	@Override
	public void activate() throws DomainException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see com.sojanddesign.boxes.domain.model.box.ElementState#collect()
	 */
	@Override
	public void collect() throws DomainException {
		throw new IllegalElementStateException("An element cannot be collected when it is in the next actions box.");
	}

	/**
	 * @see com.sojanddesign.boxes.domain.model.box.ElementState#defer()
	 */
	@Override
	public void defer() throws DomainException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see com.sojanddesign.boxes.domain.model.box.ElementState#delegate()
	 */
	@Override
	public void delegate() throws DomainException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see com.sojanddesign.boxes.domain.model.box.ElementState#doIt()
	 */
	@Override
	public void doIt() throws DomainException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see com.sojanddesign.boxes.domain.model.box.ElementState#eliminate()
	 */
	@Override
	public void eliminate() throws DomainException {
		switchNotActivableElement(TypeBox.TRASH, element.getTrashState());
	}

	/**
	 * @see com.sojanddesign.boxes.domain.model.box.ElementState#incubate()
	 */
	@Override
	public void incubate() throws DomainException {
		switchNotActivableElement(TypeBox.INCUBATOR, element.getIncubatorState());
	}

	/**
	 * @see com.sojanddesign.boxes.domain.model.box.ElementState#reference()
	 */
	@Override
	public void reference() throws DomainException {
		throw new IllegalElementStateException("An active element cannot be referenced when it is in the next actions box.");
	};

}
