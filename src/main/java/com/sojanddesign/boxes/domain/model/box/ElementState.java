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
 * Interface defining a state of {@link Element}.
 * Refers to the State design pattern.
 * @author Marie Acevedo (http://www.sojanddesign.com)
 * @version 28 Jan. 2013 - 0.1
 */
interface ElementState {
	/**
	 * Element becomes active.
	 * @throws DomainException 
	 */
	void activate() throws DomainException;

	/**
	 * Element is collected in the inbox. He is not active.
	 * @throws DomainException 
	 */
	void collect() throws DomainException;
	/**
	 * Element is active and its 'doIt' is deferred in calendar.
	 */
	void defer() throws DomainException;
	/**
	 * Element is active and the "doIt" is delegate to an other user (of the system or not).
	 */
	void delegate() throws DomainException;
	/**
	 * Active element is done and action is executed.
	 */
	void doIt() throws DomainException;
	/**
	 * Element is put in the trash box.
	 * @throws DomainException if eleminate method is unsuccessfuled
	 */
	void eliminate() throws DomainException;
	/**
	 * Element is not active and put in incubator (one day may be ...)
	 * @throws DomainException 
	 */
	void incubate() throws DomainException;
	/**
	 * Element is not active and is put in reference.
	 */
	void reference() throws DomainException;
}
