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
 * Interface defines the strategy contract for the management of a box.
 * 
 * @author Marie Acevedo (http://www.sojanddesign.com)
 * @version 25 Jan. 2013- 0.1
 */
interface BoxManagementPolicy {

	/**
	 * Put the specified element in the box
	 * 
	 * @param elt
	 *            element to be put in the box
	 * @return true if this box changed as a result of the call.
	 * @throws DomainException if the specified element is null.
	 */
	boolean putIn(Element elt) throws DomainException;

	/**
	 * Pulls the specified element from this box, if it is present. If this box
	 * does not contain the element, it is unchanged.Returns true if this box
	 * contained the specified element (or equivalently, if this box changed as
	 * a result of the call).
	 * 
	 * @param elt
	 *            element to be removed from this box, if present
	 * @return if this box contained the specified element
	 * @throws DomainException if the specified element is null.
	 */
	boolean pull(Element elt) throws DomainException;
	
	/**
	 * @return the next element's box to manage 
	 * @throws NoBehaviorException if behavior is not implemented for a type of box
	 */
	Element nextElement() throws NoBehaviorException;

}
