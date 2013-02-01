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
 * Abstract state element.
 * @author Marie Acevedo (http://www.sojanddesign.com)
 * @version 28 Jan. 2013 - 0.1
 */
abstract class AbstractElementState implements ElementState {

	/**
	 * current element
	 */
	protected Element element;

	/**
	 * default constructor
	 */
	AbstractElementState(Element element) {
		this.element = element;
	}
	
	/**
	 * Looking for box using given type.
	 * @param type type of the box wanted.
	 * @return box, if it found else {@code null}.
	 */
	protected Box findTargetBox(TypeBox type) {
		Box box = null;
		for (Box b : element.getBox().getOwner().getBoxes()) {
			if(b.getType().equals(type)){
				box = b;
				break;
			}
		}
		return box;
	}
	
	/**
	 * Switch the current element to type of box.
	 * @param type type of box.
	 * @param nextState new state of the current element.
	 * @throws DomainException if the switch is unsuccessful.
	 */
	protected void switchNotActivableElement(TypeBox type, ElementState nextState) throws DomainException {
		try {
			Box targetBox = findTargetBox(type);
			if (targetBox == null) {
				throw new TargetBoxNotFoundException(type.toString() +
						" not exist or not found for current user");
			}
			element.getBox().pull(element);
			targetBox.putIn(element);
			element.setState(nextState);
			element.setActive(false);
			element.setDone(false);
		} catch (DomainException e) {
			throw e;
		}
	}

}
