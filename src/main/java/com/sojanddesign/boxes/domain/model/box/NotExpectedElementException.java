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
 * DomainException throw if the {@link Element} contains in the box is not the element expected for the action.
 * @author Marie Acevedo (http://www.sojanddesign.com)
 * @version 28 Jan. 2013 - 0.1
 *
 */
public class NotExpectedElementException extends DomainException {

	/**
	 * generated serial version UID
	 */
	private static final long serialVersionUID = -5455395718372047499L;

	/**
	 * 
	 */
	public NotExpectedElementException() {
	}

	/**
	 * @param message
	 */
	public NotExpectedElementException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public NotExpectedElementException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public NotExpectedElementException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public NotExpectedElementException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
