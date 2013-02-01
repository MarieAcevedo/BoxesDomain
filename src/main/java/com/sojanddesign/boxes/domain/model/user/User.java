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
package com.sojanddesign.boxes.domain.model.user;

import java.util.List;

import com.sodcube.domain.core.Entity;
import com.sodcube.domain.exception.DomainException;
import com.sojanddesign.boxes.domain.model.box.Box;

/**
 * The system user is a root entity of user's aggregates. It's the owner of the
 * boxes.
 * 
 * @author Marie Acevedo (http://www.sojanddesign.com)
 * @version 24 jan. 2013 - 0.1
 * 
 */
public class User extends Entity<UserId> {

	/**
	 * generated serial version UID
	 */
	private static final long serialVersionUID = 6759287467150584116L;
	private List<Box> boxes;
	private AccountSettings accountSettings;
	/**
	 * true if registration user has been validated by mail
	 */
	private boolean active;
	
	/**
	 * Default constructor with uniqueIdentifier
	 * 
	 * @param uniqueIdentifier
	 *            {@link UserId}
	 * @throws DomainException 
	 */
	public User(UserId uniqueIdentifier) throws DomainException {
		super(uniqueIdentifier);
		active = false;
	}

	/**
	 * @return the boxes
	 */
	public List<Box> getBoxes() {
		return boxes;
	}


	/**
	 * @param boxes
	 *            the boxes to set
	 */
	public void setBoxes(List<Box> boxes) {
		this.boxes = boxes;
	}

	/**
	 * @return the accountSettings
	 */
	public final AccountSettings getAccountSettings() {
		return accountSettings;
	}

	/**
	 * @param accountSettings the accountSettings to set
	 */
	public final void setAccountSettings(AccountSettings accountSettings) {
		this.accountSettings = accountSettings;
	}

	/**
	 * @return the active
	 */
	public final boolean isActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public final void setActive(boolean active) {
		this.active = active;
	}


}