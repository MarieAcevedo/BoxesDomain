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

import com.sodcube.domain.core.DomainObject;
import com.sodcube.domain.core.ValueObject;
import com.sodcube.domain.exception.DomainException;

/**
 * Unique identifier for {@link Box} entity
 * 
 * @author Marie Acevedo (http://www.sojanddesign.com)
 * @version 24 jan. 2013 - 0.1
 */
public class BoxId extends ValueObject {

	/**
	 * generated serial version UID
	 */
	private static final long serialVersionUID = 3568378483357502560L;

	/**
	 * String identifier for Box
	 */
	private final String id;

	/**
	 * Default constructor with id
	 * 
	 * @param id
	 *            must be not null or blank
	 * @throws DomainException
	 */
	public BoxId(final String id) throws DomainException {
		if (id == null || "".equals(id)) {
			throw new DomainException("id of BoxId must be not null");
		}
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return id field hashCode
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return this.id.hashCode();
	}

	/**
	 * Check the id field
	 * 
	 * @see com.sodcube.domain.core.DomainObject#isSameAs(com.sodcube.domain.core.DomainObject)
	 */
	@Override
	public boolean isSameAs(DomainObject dob) {
		boolean isSameAs = false;
		if (dob instanceof BoxId) {
			isSameAs = this.id.equals(((BoxId) dob).getId());
		}
		return isSameAs;
	}
}