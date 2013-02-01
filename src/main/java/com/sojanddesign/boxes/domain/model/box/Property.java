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

import com.sodcube.domain.core.DomainObject;
import com.sodcube.domain.core.ValueObject;

/**
 * TODO Property
 * @author Marie Acevedo (http://www.sojanddesign.com)
 * @version 29 janv. 2013 - 0.1
 */
public class Property extends ValueObject {
	
	/**
	 * generated serial version UID
	 */
	private static final long serialVersionUID = 6449428333803025010L;
	private final String value;

	/**
	 * 
	 */
	public Property(String value) {
		this.value = value;
	}

	/**
	 * @see com.sodcube.domain.core.DomainObject#isSameAs(com.sodcube.domain.core.DomainObject)
	 */
	@Override
	public boolean isSameAs(DomainObject dob) {
		boolean isSameAs = false;
		if(dob != null  && dob instanceof Property){
			isSameAs = ((Property)dob).getValue().equals(value);
		}
		return isSameAs;
	}

	/**
	 * @return the value
	 */
	public final String getValue() {
		return value;
	}

}
